package tictactoe.dao;

import java.util.List;
import tictactoe.domain.User;

public interface UserDao {
    List<User> getUsers();
    
    boolean createUser(String username, String name);
    
    User usernameExists(String username);
    
    void updateUserToDatabase(User user);
}