package tictactoe.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.domain.User;
/**
 * The class responsible for handling user and database related operations.
 */
public class FileUserDao implements UserDao {
    
    private List<User> users;
    private String userDatabaseAddress;
    
    public FileUserDao(String userDatabaseAddress) {
        this.userDatabaseAddress = userDatabaseAddress;
        this.users = new ArrayList<>();
            
        try (Connection connection = DriverManager.getConnection(this.userDatabaseAddress)) {
            
            Statement statement = connection.createStatement();
            
            //statement.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, username TEXT, name TEXT, wins INTEGER, losses INTEGER)");
        
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                
                this.users.add(new User(username, name, wins, losses));
            }
            
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * The method updates given user to the database.
     *
     * @param   user  User object of the user.
     */
    @Override
    public void updateUserToDatabase(User user) {
        try (Connection connection = DriverManager.getConnection(userDatabaseAddress)) {
            
            PreparedStatement statement = connection.prepareStatement("UPDATE Users SET wins=?, losses=? WHERE username=?");
            
            statement.setInt(1, user.getWins());
            statement.setInt(2, user.getLosses());
            statement.setString(3, user.getUsername());
            
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * The method creates a new user to the database.
     *
     * @param   username  Chosen username of the user.
     * 
     * @param   user  Name of the user.
     *
     * @return Returns false if username already exists, true otherwise.
     */
    @Override
    public boolean createUser(String username, String user) {
        if (this.usernameExists(username) != null) {
            return false;
        }

        this.users.add(new User(username, user));
            
        try (Connection connection = DriverManager.getConnection(userDatabaseAddress)) {
            
            int wins = 0;
            int losses = 0;
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users(username,name,wins,losses) VALUES (?,?,?,?)");
            
            statement.setString(1, username);
            statement.setString(2, user);
            statement.setInt(3, wins);
            statement.setInt(4, losses);
            
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    /**
     * The method checks whether the username already exists.
     *
     * @param   username  Username of the user.
     * 
     * @return Returns the user object if user exists, null otherwise.
     */
    @Override
    public User usernameExists(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        
        return null;
    }
    /**
     * The method returns a list of all existing user objects.
     *
     * @return Returns a list of all existing user objects.
     */
    @Override
    public List<User> getUsers() {
        return this.users;
    }
}