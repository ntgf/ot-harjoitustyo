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

public class FileMessageDaoTest {
    
    private MessageDao messageDao;
    
    @Before
    public void setUpClass() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String messageDatabaseAddress = properties.getProperty("testMessagesDatabaseAddress");
        messageDao = new FileMessageDao(messageDatabaseAddress);
    }
    
    @Test
    public void createsNewMessageCorrectly() throws IOException {
        User user = new User("username", "user");
        this.messageDao.createNewMessage(user, "message");
        setUpClass();
        List<String> messages = this.messageDao.getLast10Messages(0);
        assertEquals("message", messages.get(0).substring(messages.get(0).length() - 7, messages.get(0).length()));
    }
    
    @Test
    public void getsLast10MessagesCorrectly() {
        List<String> messages = this.messageDao.getLast10Messages(0);
        assertEquals(10, messages.size());
    }
}
