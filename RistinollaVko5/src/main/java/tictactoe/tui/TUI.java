package tictactoe.tui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import tictactoe.dao.FileUserDao;
import tictactoe.dao.UserDao;
import tictactoe.domain.BoardChecker;
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.User;

public class TUI {
    
    private TictactoeAI tictactoeai;
    private Scanner scanner;
    private TictactoeService tictactoeservice;
    
    public TUI(TictactoeAI tictactoeai, Scanner scanner) throws IOException {
        this.tictactoeai = tictactoeai;
        this.scanner = scanner;
        this.init();
    }
    
    public void init() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String file = properties.getProperty("userFile");
        
        UserDao userDao = new FileUserDao(file);
        
        this.tictactoeservice = new TictactoeService(userDao);
    }
    
    public void start() {
        while (true) {
            if (login() == false) {
                break;
            }
            
            while(this.tictactoeservice.getLoggedIn() == true) {
                loggedIn();
            }
        }
    }
    
    public boolean login() {
        while (!this.tictactoeservice.getLoggedIn()) {
            System.out.println("Enter username.\nNew user insert 'x'.");
            System.out.println("To exit the game, insert 'Q'.");
            System.out.print("\nUsername: ");

            String inputUsername = scanner.nextLine();
            
            if (inputUsername.equals("Q") || inputUsername.equals("q")) {
                System.out.println("\nThank you! See you again!");
                return false;
            }

            if (inputUsername.equals("x")) {
                newUser();
            } else {
                User user = this.tictactoeservice.usernameExists(inputUsername);
                
                if (user != null) {
                    this.tictactoeservice.setLoggedInUser(user);
                    System.out.println("\nLogin successful.\n");
                    continue;
                } else {
                    System.out.println("\nUsername not found. Please type again.\n");
                }
            }
        }
        
        return true;
    }
    
    public void newUser() {
        while (true) {
            String inputUsername = "";
            
            while (inputUsername.equals("")) {
                System.out.print("\nEnter new username: ");
                inputUsername = scanner.nextLine();
                
                if (inputUsername.equals("")) {
                    System.out.println("\nNo username entered. Please enter new username.");
                    continue;
                }
                
                break;
            }
            
            if (this.tictactoeservice.usernameExists(inputUsername) != null) {
                System.out.println("\nUsername already exists. Please pick another username.");
                continue;
            }
            
            String inputName = "";
            
            while (inputName.equals("")) {
                System.out.print("Enter name: ");
                inputName = scanner.nextLine();
                
                if (inputName.equals("")) {
                    System.out.println("\nNo name entered. Please enter your name.\n");
                    continue;
                }
                
                break;
            }
            
            System.out.println("\nWill create new username '" + inputUsername + "' for user " + inputName + "?");
            
            String inputConfirmation = "";
            
            while (true) {
                System.out.print("Type [Y/N]: ");

                inputConfirmation = scanner.nextLine();

                if (inputConfirmation.equals("Y")) {
                    User newUser = new User(inputUsername, inputName);
                    
                    this.tictactoeservice.createUser(inputUsername, inputName);
                    //this.tictactoeservice.setLoggedInUser(newUser);
                    System.out.println("\nNew user successfully created!\n");
                    break;
                }
                
                if (inputConfirmation.equals("N")) {
                    break;
                }
                
                System.out.println("\nInvalid input. Please type again.\n");
            }
            
            if (inputConfirmation.equals("Y")) {
                break;
            }
        }
    }
    
    public void loggedIn() {
        while (true) {
            System.out.println("Press any key and/or enter to play.");
            //System.out.println("Insert 'Q' to logout and exit game.");
            System.out.println("Insert '!' to see game score.");
            System.out.println("Insert 'Q' to logout.");
            
            String input = scanner.nextLine();
            
            if (input.equals("Q") || input.equals("q")) {
                //System.out.println("\nThank you! See you next time!");
                this.tictactoeservice.logOut();
                System.out.println("\nSuccessfully logged out.\n");
                break;
            }
            
            if (input.equals("!")) {
                this.gameScore();
                continue;
            }
            
            if (!input.equals("")) {
                System.out.println("");
            }
            
            boolean playsx = playsx();
        
            tictactoeai.setplaysx(!playsx);
        
            play(playsx);
        }
    }
    
    public boolean playsx() {
        while (true) {
            System.out.println("Will you play X or O?\nX starts.\n");
            System.out.print("Please type either 'x' or 'o': ");
            
            String input = scanner.nextLine();
            
            if (input.equals("x")) {
                return true;
            }
            
            if (input.equals("o")) {
                return false;
            }
            
            else {
                System.out.println("\nInvalid input. Please type again.\n");
                continue;
            }
        }
    }
    
    public void play(boolean playsx) {
        
        String mark = "X";
        
        if (!playsx) {
            mark = "O";
        }
        
        if (!playsx) {
            tictactoeai.nextMove();
        }

        System.out.println("\nOpponent plays:\n" + tictactoeai);
        
        while (!tictactoeai.boardCompleted() && tictactoeai.checkBoardForWinner() == 0) {
            
            System.out.println("\nPlease choose your next move.\n");
            
            //Row:
            
            System.out.print("Row: ");
            
            String rowstring = scanner.nextLine();
            
            if (!(rowstring.equals("1") || rowstring.equals("2") || rowstring.equals("3"))) {
                System.out.println("\nInvalid input. Please type again.");
                continue;
            }
            
            int row = Integer.valueOf(rowstring);
            
            //Column:
            
            System.out.print("Column: ");
            
            String columnstring = scanner.nextLine();
            
            if (!(columnstring.equals("1") || columnstring.equals("2") || columnstring.equals("3"))) {
                System.out.println("\nInvalid input. Please type again.");
                continue;
            }
            
            int column = Integer.valueOf(columnstring);
            
            //Next board:
            
            if (!(tictactoeai.getboard().charAt(3 * (row - 1) + column - 1) == '-')) {
                System.out.println("\nInvalid input. Board position already played. Please type again.");
                continue;
            }
            
            tictactoeai.setboard(tictactoeai.getboard().substring(0, 3 * (row - 1) + column - 1)
                    + mark + tictactoeai.getboard().substring(3 * (row - 1) + column, 9));
            
            tictactoeai.increasemoves();
            
            System.out.println("\nYour move:\n" + tictactoeai + "\n");
            
            tictactoeai.nextMove();
            
            System.out.println("\nOpponent plays:\n" + tictactoeai);
        }
        
        if (tictactoeai.checkBoardForWinner() == 1 && tictactoeai.getplaysx() == true) {
            this.tictactoeservice.getLoggedInUser().increaseLosses();
            this.tictactoeservice.writeUsersToFile();
            System.out.println("\nYou lose!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == 1 && tictactoeai.getplaysx() == false) {
            this.tictactoeservice.getLoggedInUser().increaseWins();
            this.tictactoeservice.writeUsersToFile();
            System.out.println("\nYOU WIN!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == -1 && tictactoeai.getplaysx() == true) {
            this.tictactoeservice.getLoggedInUser().increaseWins();
            this.tictactoeservice.writeUsersToFile();
            System.out.println("\nYOU WIN!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == -1 && tictactoeai.getplaysx() == false) {
            this.tictactoeservice.getLoggedInUser().increaseLosses();
            this.tictactoeservice.writeUsersToFile();
            System.out.println("\nYou lose!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == 0) {
            System.out.println("\nDraw!\n");
        }
        
        tictactoeai.setboard("---------");
    }
    
    public void gameScore() {
        System.out.println("\nWins: " + this.tictactoeservice.getLoggedInUser().getWins() +
                "\nLosses: " + this.tictactoeservice.getLoggedInUser().getLosses() + "\n");
    }
}



