package lv.rvt;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {

    /**
     * Get user username test
     */
    @Test
    public void getUsernameTest()
    {
        User user = new User("test_user");
        assertEquals( "test_user", user.getUsername());


        User user2 = new User("test_user2");
        assertEquals( "test_user2", user2.getUsername());


        User user3 = new User("test_user3");
        assertEquals( "test_user3", user3.getUsername());
    }

    /**
     * Get user email
     * 
     */
    @Test
    public void getEmailTest()
    {
        User user = new User("test_user", "test_user1@gmail.com");
        assertEquals( "test_user1@gmail.com", user.getEmail());

        User user2 = new User("test_user", "test_user2@gmail.com");
        assertEquals( "test_user2@gmail.com", user2.getEmail());

        User user3 = new User("test_user", "test_user3@gmail.com");
        assertEquals( "test_user3@gmail.com", user3.getEmail());
    }

    /**
     * To CSV row
     * 
     */
    @Test
    public void toCsvRowTest()
    {
        User user = new User("test_user", "test_user1@gmail.com");
        assertEquals( "test_user, test_user1@gmail.com", user.toCsvRow());

        User user2 = new User("test_user", "test_user2@gmail.com");
        assertEquals( "test_user, test_user2@gmail.com", user2.toCsvRow());

        User user3 = new User("test_user", "test_user3@gmail.com");
        assertEquals( "test_user, test_user3@gmail.com", user3.toCsvRow());
    }
}