package tictactoe.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.User;

public class FileUserDaoTest {
    
    private UserDao userDao;
    
    @Before
    public void setUpClass() throws FileNotFoundException, IOException {
         Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String usersDatabaseAddress = properties.getProperty("testUsersDatabaseAddress");
        userDao = new FileUserDao(usersDatabaseAddress);
    }
    
    @Test
    public void updatesUserToDatabaseCorrectly() throws IOException {
        User user = new User("username3", "user");
        this.userDao.updateUserToDatabase(user);
        setUpClass();
        List<User> users = userDao.getUsers();
        assertEquals("username3", users.get(users.size() - 1).getUsername());
    }
    
    @Test
    public void createsUserCorrectly() throws IOException {
        //List<User> users = userDao.getUsers();
        //String username = "username" + users.size();
        userDao.createUser("username", "user");
        setUpClass();
        List<User> users = userDao.getUsers();
        assertEquals("user", users.get(users.size() - 1).getName());
    }
    
    @Test
    public void checksUsernameExistsCorrectly() {
        assertEquals("jk", this.userDao.usernameExists("jk").getUsername());
    }
    
    @Test
    public void checksUsernameDoesntExistsCorrectly() {
        assertEquals(null, this.userDao.usernameExists("unknownusername"));
    }
    
    @Test
    public void getsUsersCorrectly() {
        List<User> users = userDao.getUsers();
        assertEquals("jk", users.get(0).getUsername());
    }
}
