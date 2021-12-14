package tictactoe.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tictactoe.domain.User;

public class FileMessageDao implements MessageDao {
    
    //private List<User> users;
    private List<String> messages;
    private String file;
    
    public FileMessageDao(String file) {
        this.file = file;
        this.messages = new ArrayList<>();
        
        try (Scanner fileReader = new Scanner(new File(file))) {
            while (fileReader.hasNextLine()) {
                //String[] line = fileReader.nextLine().split(";");
                //users.add(new User(line[0], line[1], Integer.valueOf(line[2]), Integer.valueOf(line[3])));
                messages.add(fileReader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void writeMessagesToFile() {
        try (
                PrintWriter fileWriter = new PrintWriter(file)) {
            for (String message : messages) {
                //fileWriter.println(user.getUsername() + ";" + user.getName() + ";" + user.getWins() + ";" + user.getLosses());
                fileWriter.println(message);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
//    public void writeUsersToFile() /*throws Exception*/ {
//        try (//new File(file)?
//                PrintWriter fileWriter = new PrintWriter(file)) {
//            for (User user : users) {
//                fileWriter.println(user.getUsername() + ";" + user.getName() + ";" + user.getWins() + ";" + user.getLosses());
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
    
//    public boolean createUser(String username, String user) {
//        if (this.usernameExists(username) != null) {
//            return false;
//        }
//        
//        this.users.add(new User(username, user));
//        
//        //writeUsersToFile()
//        
//        try (//new File(file)?
//                PrintWriter fileWriter = new PrintWriter(file)) {
//            for (User knownUser : users) {
//                fileWriter.println(knownUser.getUsername() + ";" + knownUser.getName() + ";" + knownUser.getWins() + ";" + knownUser.getLosses());
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        
//        return true;
//    }
//    
//    public User usernameExists(String username) {
//        for (User user : this.users) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
//        
//        return null;
//    }
    
    public void createNewMessage(String message) {
        this.messages.add(message);
        this.writeMessagesToFile();
    }
    
    public List<String> getMessages() {
        return this.messages;
    }
    
}