package tictactoe.tui;

import java.io.IOException;
import java.util.Scanner;
import tictactoe.dao.FileUserDao; //
import tictactoe.domain.TictactoeAI;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        TictactoeAI tictactoeai = new TictactoeAI("---------", true);
        
        TUI tui = new TUI(tictactoeai, scanner);
        
        tui.start();
    }
    
}
