package tictactoe.domain;

import java.util.List;
import tictactoe.dao.UserDao;
import tictactoe.dao.MessageDao;
/**
 * The class responsible for handling all user related operations.
 */
public class TictactoeService {
    
    private UserDao userdao;
    private User loggedInUser;
    private MessageDao messagedao;
    private boolean loggedIn;
    
    public TictactoeService(UserDao userdao, MessageDao messagedao) {
        this.userdao = userdao;
        this.messagedao = messagedao;
        this.loggedIn = false;
    }
    /**
     * The method checks whether the given username exists
     * and returns the user object of the given username.
     *
     * @param   username  Username of the searched user.
     *
     * @return User object of the given username.
     */
    public User usernameExists(String username) {
        return this.userdao.usernameExists(username);
    }
    /**
     * The method returns a list of all the existing users.
     *
     * @return Complete list of all the user objects.
     */
    public List<User> getUsers() {
        return this.userdao.getUsers();
    }
    /**
     * The method creates a new user and writes it down to the users database.
     *
     * @param   username  Username of the created user.
     *
     * @param   name  Name of the created user.
     *
     * @return Returns false if user already exists, true otherwise.
     */
    public boolean createUser(String username, String name) {
        return this.userdao.createUser(username, name);
    }
    /**
     * The method returns a boolean value for whether there is a user logged in.
     *
     * @return Returns true if a user is logged in, false otherwise.
     */
    public boolean getLoggedIn() {
        return this.loggedIn;
    }
    /**
     * The method logs out the user and sets the value for logged in false.
     *
     */
    public void logOut() {
        this.loggedInUser = null;
        this.loggedIn = false;
    }
    /**
     * The method returns the logged in user as an user object.
     *
     * @return Returns the user object for the logged in user.
     */
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
    /**
     * The method sets the logged in user and logged in value true.
     *
     * @param   user  User object of the user.
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        this.loggedIn = true;
    }
    /**
     * The method updates the logged in user into the database.
     *
     */
    public void updateUserToDatabase() {
        this.userdao.updateUserToDatabase(loggedInUser);
    }
    /**
     * The method creates a new message in the database.
     *
     * @param   message  The new essage to be written in the database.
     */
    public void createNewMessage(String message) {
        this.messagedao.createNewMessage(loggedInUser, message);
    }
    /**
     * The method returns the last 10 messages from the messages database.
     *
     * @return List of String valued 10 last messages in the form: time + user + message.
     */
    public List<String> getLast10Messages() {
        return this.messagedao.getLast10Messages(0);
    }
}