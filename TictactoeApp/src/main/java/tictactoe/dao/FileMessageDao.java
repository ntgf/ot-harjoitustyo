package tictactoe.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.domain.User;
/**
 * The class responsible for handling user messages and database related operations.
 */
public class FileMessageDao implements MessageDao {
    
    private String messageDatabaseAddress;
    
    public FileMessageDao(String messageDatabaseAddress) {
        this.messageDatabaseAddress = messageDatabaseAddress;
        
        //Code for creating new Messages database if needed
//        try (Connection connection = DriverManager.getConnection(this.messageDatabaseAddress)) {
//            
//            Statement statement = connection.createStatement();
//            
//            statement.execute("CREATE TABLE Messages (id INTEGER PRIMARY KEY, username TEXT, message TEXT, time TEXT)");
//        
//            statement.close();
//            connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(FileMessageDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    /**
     * The method creates a new message to the database.
     *
     * @param   user  User object of the user.
     * 
     * @param   message  String message of the user.
     */
    @Override
    public void createNewMessage(User user, String message) {
        Date date = java.util.Calendar.getInstance().getTime();

        String[] dateShort = ("" + date).split(" ");

        String time = dateShort[3] + ", " + dateShort[1] + " " + dateShort[2] + ", " + dateShort[5];

        try (Connection connection = DriverManager.getConnection(this.messageDatabaseAddress)) {
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Messages(username,message,time) VALUES (?,?,?)");
            
            statement.setString(1, user.getUsername());
            statement.setString(2, message);
            statement.setString(3, time);
            
            statement.execute();
            
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FileMessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * The method returns the last 10 new messages from the database 
     * starting from startindex.
     *
     * @param   startindex  Start index for the newest 10 messages.
     * 
     * @return Returns String list of messages in the form: time + username + message.
     */
    @Override
    public List<String> getLast10Messages(int startindex) {
        List<String> last10Messages = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(this.messageDatabaseAddress)) {
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Messages ORDER BY id DESC LIMIT 10 OFFSET ?");
            
            statement.setInt(1, startindex);
        
            ResultSet resultSet = statement.executeQuery();
        
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String message = resultSet.getString("message");
                String time = resultSet.getString("time");
                
                last10Messages.add("(" + time + ") <" + username + ">: " + message);
            }
            
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FileMessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return last10Messages;
    }
}