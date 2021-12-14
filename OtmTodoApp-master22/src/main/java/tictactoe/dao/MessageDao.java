package tictactoe.dao;

import java.util.List;
import tictactoe.domain.User;

public interface MessageDao {
    
    List<String> getMessages();
    
    public void createNewMessage(String message);
    
    public void writeMessagesToFile();
    
    //boolean createUser(String username, String name);
    
//    List<User> getUsers();
//    
//    boolean createUser(String username, String name);
//    
//    User usernameExists(String username);
//    
//    void writeUsersToFile();
}
