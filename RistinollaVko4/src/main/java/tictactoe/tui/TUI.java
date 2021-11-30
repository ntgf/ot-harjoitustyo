package tictactoe.tui;

import java.util.List;
import java.util.Scanner;
import tictactoe.dao.FileUserDao;
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.User;

public class TUI {
    
    private TictactoeAI tictactoeai;
    private Scanner scanner;
    private FileUserDao fileuserdao; //refactor
    private User user; //refactor?
    
    public TUI(TictactoeAI tictactoeai, Scanner scanner, FileUserDao fileuserdao/*refactor*/, User user) {
        this.tictactoeai = tictactoeai;
        this.scanner = scanner;
        this.fileuserdao = fileuserdao; //refactor
        this.user = null; //refactor?
    }
    
    public void start() {
        //Login
        login();
        //Choose xs or os
        //Print win or lose
        //Play again?
        //Logout, Quit
        
        /*
        boolean playsx = playsx();
        
        tictactoeai.setplaysx(!playsx);
        
        play(playsx);
        */
        
        loggedIn();
        
        //System.out.println(tictactoeai.nextMove("X--XO----", playsx));
    }
    
    public void login() {
        boolean loginSuccessful = false;
        
        while (!loginSuccessful) {
            System.out.println("Enter username.\nNew user insert 'x'.");
            System.out.print("Username: ");

            String input = scanner.nextLine();

            if (input.equals("x")) {
                newUser();
                //login on? user?
                break;
            } else {
                List<User> users = fileuserdao.getUsers();
                /*
                if (!users.contains(input)) {
                    System.out.println("User not found. Please type again.\n");
                    continue;
                }
                */
                for (User user : users) {
                    if (user.getUsername().equals(input)) {
                        this.user = user;
                        System.out.println("\nLogin successful.");
                        loginSuccessful = true;
                        continue;
                    }
                }
                
                if (!loginSuccessful) {
                    System.out.println("\nUser not found. Please type again.\n");
                }
                
            }

           // System.out.println("User not found. Please type again.\n");
        }
    }
    
    public void newUser() {
        String input3 = "";
        
        while (!input3.equals("Y")) {
            String input = "";
            
            while (input.equals("")) {
                System.out.print("\nEnter new username: ");
                input = scanner.nextLine();
                
                if (input.equals("")) {
                    System.out.println("\nNo username entered. Please enter new username.\n");
                    continue;
                }
                
                break;
            }
            
            if (this.fileuserdao.getUsers().contains(input)) {
                System.out.println("\nUsername already exists. Please pick another username.\n");
                continue;
            }
            
            String input2 = "";
            
            while (input2.equals("")) {
                System.out.print("Enter name: ");
                input2 = scanner.nextLine();
                
                if (input2.equals("")) {
                    System.out.println("\nNo name entered. Please enter your name.\n");
                    continue;
                }
                
                break;
            }
            
            System.out.println("\nWill create new username '" + input + "' for user " + input2 + "?");
            
            input3 = "";
            
            while (true) {
                System.out.print("Type [Y/N]: ");

                input3 = scanner.nextLine();

                if (input3.equals("Y")) {
                    User newUser = new User(input, input2);
                    this.fileuserdao.newUser(newUser);
                    this.user = newUser;
                    // System.out.println("\nNew user successfully created!\n");
                    break;
                }
                
                if (input3.equals("N")) {
                    break;
                }
                
                System.out.println("Invalid input. Please type again.\n");
            }
        }
    }
    
    public void loggedIn() {
        while (true) {
            System.out.println("\nPress any key to play.");
            System.out.println("Insert 'Q' to logout and exit game.");
            
            String input = scanner.nextLine();
            
            if (input.equals("Q") || input.equals("q")) {
                System.out.println("\nThank you! See you next time!");
                break;
            }
            
            boolean playsx = playsx();
        
            tictactoeai.setplaysx(!playsx);
        
            play(playsx);
        }
    }
    
    public boolean playsx() {
        //boolean playsx = true;
        
        while (true) {
            System.out.println("\nWill you play X or O?\nX starts.\n");
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
        
        //this.board?
        while (!tictactoeai.boardCompleted() && tictactoeai.checkBoardForWinner() == 0) {
            //System.out.println(tictactoeai);
            
            System.out.println("\nPlease choose your next move.\n");
            
            //Row:
            
            System.out.print("Row: ");
            
            String rowstring = scanner.nextLine();
            
            if (!(rowstring.equals("1") || rowstring.equals("2") || rowstring.equals("3"))) {
                System.out.println("\nInvalid input. Please type again.\n");
                continue;
            }
            
            int row = Integer.valueOf(rowstring);
            
            //Column:
            
            System.out.print("Column: ");
            
            String columnstring = scanner.nextLine();
            
            if (!(columnstring.equals("1") || columnstring.equals("2") || columnstring.equals("3"))) {
                System.out.println("\nInvalid input. Please type again.\n");
                continue;
            }
            
            int column = Integer.valueOf(columnstring);
            
            //Next board:
            
            if (!(tictactoeai.getboard().charAt(3 * (row - 1) + column - 1) == '-')) {
                System.out.println("\nInvalid input. Board position already played. Please type again.\n");
                continue;
            }
            
            //Indexes correct?
            tictactoeai.setboard(tictactoeai.getboard().substring(0, 3 * (row - 1) + column - 1)
                    + mark + tictactoeai.getboard().substring(3 * (row - 1) + column, 9));
            
            tictactoeai.increasemoves();
            
            System.out.println("\nYour move:\n" + tictactoeai + "\n");
            
            
            //DO
            tictactoeai.nextMove();
            
            System.out.println("\nOpponent plays:\n" + tictactoeai);
        }
        
        if (tictactoeai.checkBoardForWinner() == 1 && tictactoeai.getplaysx() == true) {
            System.out.println("\nYou lose!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == 1 && tictactoeai.getplaysx() == false) {
            System.out.println("\nYOU WIN!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == -1 && tictactoeai.getplaysx() == true) {
            System.out.println("\nYOU WIN!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == -1 && tictactoeai.getplaysx() == false) {
            System.out.println("\nYou lose!\n");
        }
        
        if (tictactoeai.checkBoardForWinner() == 0) {
            System.out.println("\nDraw!\n");
        }
        
        tictactoeai.setboard("---------");
    }
}


