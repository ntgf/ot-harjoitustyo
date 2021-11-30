package tictactoe.tui;

import java.util.Scanner;
import tictactoe.dao.FileUserDao; //
import tictactoe.domain.TictactoeAI;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        TictactoeAI tictactoeai = new TictactoeAI("---------", true);
        
        FileUserDao fileuserdao = new FileUserDao("users.txt"); //
        
        TUI tui = new TUI(tictactoeai, scanner, fileuserdao, null);
        
        tui.start();
    }
    
}
