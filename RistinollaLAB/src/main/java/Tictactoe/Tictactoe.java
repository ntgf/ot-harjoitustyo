
package Tictactoe;

import java.util.Scanner;

public class Tictactoe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        TictactoeAI tictactoeai = new TictactoeAI("---------", true);
        
        TUI tui = new TUI(tictactoeai, scanner);
        
        tui.start();
    }
    
}
