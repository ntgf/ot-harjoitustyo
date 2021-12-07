package tictactoe.domain;

import java.util.List;
import tictactoe.dao.FileUserDao;
import tictactoe.dao.UserDao;

public class TictactoeService {
    
    //private List<User> users;
    private UserDao userdao;
    private User loggedInUser;
    private boolean loggedIn;
    
    public TictactoeService(UserDao userdao) {
        this.userdao = userdao;
        this.loggedIn = false;
    }
    
    public User usernameExists(String username) {
        return this.userdao.usernameExists(username);
    }
    
    public List<User> getUsers() {
        return this.userdao.getUsers();
    }
    
    public boolean createUser(String username, String name) {
        return this.userdao.createUser(username, name);
    }
    
    public boolean getLoggedIn() {
        return this.loggedIn;
    }
    
    public void logOut() {
        this.loggedInUser = null;
        this.loggedIn = false;
    }
    
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
    
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        this.loggedIn = true;
    }
    
    public void writeUsersToFile() {
        this.userdao.writeUsersToFile();
    }
}
