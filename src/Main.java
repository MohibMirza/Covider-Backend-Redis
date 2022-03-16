import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.liveobject.resolver.LongGenerator;
import org.redisson.liveobject.resolver.UUIDGenerator;

public class Main {

    @REntity
    public static class Product implements Serializable {

        @RId
        private Long id;

        private String name;

        private Map<String, Integer> itemName2Amount;

        private BigDecimal price;

        private Integer unitsInStock;

        protected Product() {
        }

        public Product(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;

        }

        public void setUnitsInStock(Integer unitsInStock) {
            this.unitsInStock = unitsInStock;

        }

        public Integer getUnitsInStock() {
            return unitsInStock;

        }

        public String getName() {
            return name;

        }

        public Map<String, Integer> getItemName2Amount() {
            return itemName2Amount;
        }

    }

    @REntity
    public static class OrderDetail implements Serializable {

        @RId(generator = LongGenerator.class)
        private Long id;

        private Order order;

        private Product product;

        private BigDecimal price;

        private Integer quantity;

        private BigDecimal discount;

        protected OrderDetail() {
        }

        public OrderDetail(Order order, Product product) {
            super();
            this.order = order;
            this.product = product;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public Long getId() {
            return id;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Product getProduct() {
            return product;
        }

    }

    @REntity

    public static class Customer implements Serializable {

        @RId(generator = UUIDGenerator.class)
        private String id;

        private List<Order> orders;

        private String name;

        private String address;

        private String phone;

        protected Customer() {
        }

        public Customer(String id) {
            super();
            this.id = id;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void addOrder(Order order) {
            orders.add(order);
        }

        public List<Order> getOrders() {
            return orders;
        }

        public String getId() {
            return id;
        }
    }

    @REntity
    public static class Order implements Serializable {

        @RId(generator = LongGenerator.class)
        private Long id;

        private List<OrderDetail> orderDetails;

        private Customer customer;

        private Date date;

        private Date shippedDate;

        private String shipName;

        private String shipAddress;

        private String shipPostalCode;

        protected Order() {
        }

        public Order(Customer customer) {
            super();
            this.customer = customer;
        }

        public List<OrderDetail> getOrderDetails() {
            return orderDetails;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Long getId() {
            return id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Date getShippedDate() {
            return shippedDate;
        }

        public void setShippedDate(Date shippedDate) {
            this.shippedDate = shippedDate;
        }

        public String getShipName() {
            return shipName;
        }

        public void setShipName(String shipName) {
            this.shipName = shipName;
        }

        public String getShipAddress() {
            return shipAddress;
        }

        public void setShipAddress(String shipAddress) {
            this.shipAddress = shipAddress;
        }

        public String getShipPostalCode() {
            return shipPostalCode;
        }

        public void setShipPostalCode(String shipPostalCode) {
            this.shipPostalCode = shipPostalCode;
        }

    }

    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RLiveObjectService liveObjectService = redisson.getLiveObjectService();


        Product product = new Product(1L, "FoodBox");
        // product object is becoming "live" object
        product = liveObjectService.merge(product);

        product.getItemName2Amount().put("apple", 1);
        product.getItemName2Amount().put("banana", 12);
        product.setPrice(BigDecimal.valueOf(10));
        product.setUnitsInStock(12);


        product.getItemName2Amount().clear();
        liveObjectService.delete(Product.class, 1L);



        redisson.shutdown();
    }

}