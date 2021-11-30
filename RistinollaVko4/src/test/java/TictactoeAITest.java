import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.domain.TictactoeAI;

public class TictactoeAITest {
    
    @Test
    public void checksBoardCompletedCorrectly() {
        String board = "XXOOXOXXO";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(true, tictactoeai.boardCompleted(board));
    }
    
    @Test
    public void checksBoardForXsCorrectly() {
        String board = "XXX------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkBoardForWinner(board));
    }
    
    @Test
    public void checksAllRowsForXsCorrectly() {
        String board = "XXX------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkAllRowsForWinner(board));
    }
    
    @Test
    public void checksAllColumnsForXsCorrectly() {
        String board = "X--X--X--";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkAllColumnsForWinner(board));
    }
    
    @Test
    public void checksFirstDiagonalXsCorrectly() {
        String board = "X---X---X";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkDiagonalsForWinner(board));
    }
    
    
    
    @Test
    public void checksFirstRowXsCorrectly() {
        String board = "XXX------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkRowForWinner(board, 1));
    }
    /*
    @Test
    public void checksFirstRowOsCorrectly() {
        String board = "OOO------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkRowForWinner(board, -1));
    }
    */
    @Test
    public void checksFirstColumnXsCorrectly() {
        String board = "X--X--X--";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkColumnForWinner(board, 1));
    }
    
    
    
    /*
    @Test
    public void toStringIsCorrect() {
        String board = "X--X--X--";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals("X--\nX--\nX--", tictactoeai);
    }
    */
}
