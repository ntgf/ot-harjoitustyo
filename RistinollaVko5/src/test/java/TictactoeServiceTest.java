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
import tictactoe.dao.FileUserDao;
import tictactoe.dao.UserDao;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.User;

public class TictactoeServiceTest {
    
    //private UserDao userDao;
    private TictactoeService tictactoeservice;
    
    @Before
    public void setUp() /*throws FileNotFoundException, IOException*/ {
        /*Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String file = properties.getProperty("userFile");*/
        
        UserDao userDao = new FileUserDao("usersTest.txt");
        
        this.tictactoeservice = new TictactoeService(userDao);
        
        //this.tictactoeservice = new TictactoeService(userDao);
    }
    
    @Test
    public void checksUsernameExistsCorrectly() {
        User user = this.tictactoeservice.usernameExists("username");
        
        assertEquals("username", user.getUsername());
    }
    
    @Test
    public void getUsersFunctionsCorrectly() {
        List<User> users = this.tictactoeservice.getUsers();
        
        for (User user : users) {
            assertEquals("username", user.getUsername());
        }
    }
    
    /* CREATE METHOD REMOVE
    @Test
    public void createsUserCorrectly() {
        this.tictactoeservice.createUser("username2", "name2");
        
        List<User> users = this.tictactoeservice.getUsers();
        
        assertEquals("username", users.get(0).getUsername());
        assertEquals("username2", users.get(1).getUsername());
        
        users.remove(1);
        
        
        
        this.tictactoeservice.writeUsersToFile();
        
    }
    */
    
    @Test
    public void getLoggedInCorrect() {
        assertEquals(false, this.tictactoeservice.getLoggedIn());
    }
    
    @Test
    public void logOutSetsLoggedInUserNull() {
        //Refactor
        User user = this.tictactoeservice.usernameExists("username");
        this.tictactoeservice.setLoggedInUser(user);
        this.tictactoeservice.logOut();
        
        assertEquals(null, this.tictactoeservice.getLoggedInUser());
    }
    
    @Test
    public void logOutSetsLoggedInFalse() {
        //Refactor
        User user = this.tictactoeservice.usernameExists("username");
        this.tictactoeservice.setLoggedInUser(user);
        this.tictactoeservice.logOut();
        
        assertEquals(false, this.tictactoeservice.getLoggedIn());
    }
    
    @Test
    public void getLoggedInUserCorrect() {
        //Refactor
        User user = this.tictactoeservice.usernameExists("username");
        this.tictactoeservice.setLoggedInUser(user);
        
        assertEquals("username", this.tictactoeservice.getLoggedInUser().getUsername());
    }
    /*
    @Test
    public void setLoggedInUserCorrect() {
        //Refactor
        User user = this.tictactoeservice.usernameExists("username");
        this.tictactoeservice.setLoggedInUser(user);
        
        assertEquals("username", this.tictactoeservice.getLoggedInUser().getUsername());
    }
    */
}
