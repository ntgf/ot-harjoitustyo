package tictactoe.domain;

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
import tictactoe.dao.FileMessageDao;
import tictactoe.dao.FileUserDao;
import tictactoe.dao.MessageDao;
import tictactoe.dao.UserDao;

public class TictactoeServiceTest {
    
    private TictactoeService tictactoeService;
    
    @Before
    public void setUpClass() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String usersDatabaseAddress = properties.getProperty("testUsersDatabaseAddress");
        UserDao userDao = new FileUserDao(usersDatabaseAddress);
        
        String messageDatabaseAddress = properties.getProperty("testMessagesDatabaseAddress");
        MessageDao messageDao = new FileMessageDao(messageDatabaseAddress);
        
        this.tictactoeService = new TictactoeService(userDao, messageDao);
    }
    
    @Test
    public void checksUsernameExistsCorrectly() {
        assertEquals("jk", this.tictactoeService.usernameExists("jk").getUsername());
    }
    
    @Test
    public void checksUsernameDoesntExistsCorrectly() {
        assertEquals(null, this.tictactoeService.usernameExists("unknownuser"));
    }
    
    @Test
    public void getsUsersCorrectly() {
        List<User> users = tictactoeService.getUsers();
        
        assertEquals("jk", users.get(0).getUsername());
        assertEquals("h", users.get(1).getUsername());
    }
    
    @Test
    public void createsUserCorrectly() throws IOException {
        this.tictactoeService.createUser("username3", "name");
        setUpClass();
        List <User> users = this.tictactoeService.getUsers();
        assertEquals("username3", users.get(users.size() - 1).getUsername());
    }
    
    @Test
    public void getsLoggedInCorrectly() {
        List<User> users = tictactoeService.getUsers();
        tictactoeService.setLoggedInUser(users.get(0));
        assertEquals("jk", tictactoeService.getLoggedInUser().getUsername());
        tictactoeService.logOut();
    }
    
    @Test
    public void logoutCorrect() {
        List<User> users = tictactoeService.getUsers();
        tictactoeService.setLoggedInUser(users.get(0));
        tictactoeService.logOut();
        assertEquals(null, tictactoeService.getLoggedInUser());
    }
    
    @Test
    public void updatesUserToDatabaseCorrectly() throws IOException {
        List<User> users = tictactoeService.getUsers();
        tictactoeService.setLoggedInUser(users.get(0));
        
        int winsBefore = users.get(0).getWins();
        users.get(0).increaseWins();
        
        tictactoeService.updateUserToDatabase();
        tictactoeService.logOut();
        
        setUpClass();
        assertEquals(winsBefore + 1, tictactoeService.getUsers().get(0).getWins());
    }
    
    @Test
    public void createsNewMessageCorrectly() {
        tictactoeService.setLoggedInUser(tictactoeService.getUsers().get(0));
        this.tictactoeService.createNewMessage("message");
        tictactoeService.logOut();
        List<String> messages = tictactoeService.getLast10Messages();
        assertEquals("message", messages.get(0).substring(messages.get(0).length() - 7, messages.get(0).length()));
    }
    
    @Test
    public void getsLast10MessagesCorrectly() {
        tictactoeService.setLoggedInUser(tictactoeService.getUsers().get(0));
        this.tictactoeService.createNewMessage("message");
        tictactoeService.logOut();
        List<String> messages = tictactoeService.getLast10Messages();
        assertEquals(10, messages.size());
    }
}
