<!-- ABOUT THE PROJECT -->
## Tutorial

Firstly, make sure:
  1. Start redis by running 'redis-server' on command line
  2. Run RedisTest.java and make sure all tests pass

Now you can begin writing your own queries, here's some info:

  - We will be working with a redis connector library called Redisson.
  - Redisson allows you to use live objects so basically we will have special objects connected to the DB.
  - This makes writing code to ping the database super easy for example:
      ex. User user = getOrCreateUser("user1234");
          user.setFirstName("Joe");
          user.setLastName("Joe");
          user.setPassword("password1234");
  
    This will set the user in the database with unique name "user1234"'s variables as such.
    This will directly set them in the database immediately and it's fully thread-safe. This makes DB
    queries super easy and this can be run within the android studio code as well!
      
  - Also the two main functions you should use to get a Building or User object in the database will be getOrCreateUser(String username) or getOrCreateBuilding(String buildingId)
