package ristinolla;

import java.util.Scanner;

public class TUI {
    
    private TictactoeAI tictactoeai;
    private Scanner scanner;
    
    public TUI(TictactoeAI tictactoeai, Scanner scanner) {
        this.tictactoeai = tictactoeai;
        this.scanner = scanner;
    }
    
    public void start() {
        //Login
        //Choose xs or os
        //Print win or lose
        //Play again?
        //Logout, Quit
        boolean playsx = playsx();
        
        tictactoeai.setplayesx(!playsx);
        
        play(playsx);
        
        //System.out.println(tictactoeai.nextMove("X--XO----", playsx));
    }
    
    public boolean playsx() {
        //boolean playsx = true;
        
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
        
        //this.board?
        while (!tictactoeai.boardCompleted() && tictactoeai.check() == 0) {
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
        
        if (tictactoeai.check() == 1 && tictactoeai.getplayesx() == true) {
            System.out.println("\nYou lose!");
        }
        
        if (tictactoeai.check() == 1 && tictactoeai.getplayesx() == false) {
            System.out.println("\nYOU WIN!");
        }
        
        if (tictactoeai.check() == -1 && tictactoeai.getplayesx() == true) {
            System.out.println("\nYOU WIN!");
        }
        
        if (tictactoeai.check() == -1 && tictactoeai.getplayesx() == false) {
            System.out.println("\nYou lose!");
        }
        
        if (tictactoeai.check() == 0) {
            System.out.println("\nDraw!");
        }
    }
}
