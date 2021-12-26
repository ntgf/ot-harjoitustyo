package tictactoe.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private User user;
    
    @Before
    public void setUp() {
        this.user = new User("username", "name");
    }
    
    @Test
    public void getNameFunctionsCorrectly() {
        assertEquals("name", user.getName());
    }
    
    @Test
    public void getUsernameFunctionsCorrectly() {
        assertEquals("username", user.getUsername());
    }
    
    @Test
    public void getWinsCorrectly() {
        assertEquals(0, user.getWins());
    }
    
    @Test
    public void increasesWinsCorrectly() {
        user.increaseWins();
        assertEquals(1, user.getWins());
    }
    
    @Test
    public void getLossesCorrectly() {
        assertEquals(0, user.getLosses());
    }
    
    @Test
    public void increasesLossesCorrectly() {
        user.increaseLosses();
        assertEquals(1, user.getLosses());
    }
    
    @Test
    public void createsUserCorrectlyAndWinsAreCorrect() {
        this.user = new User("username", "name", 5, 2);
        
        assertEquals(5, user.getWins());
    }
    
    @Test
    public void createsUserCorrectlyAndLossesAreCorrect() {
        this.user = new User("username", "name", 5, 2);
        
        assertEquals(2, user.getLosses());
    }
}
