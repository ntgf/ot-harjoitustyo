import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.domain.TictactoeAI;

public class TictactoeAITest {
    
    @Test
    public void returnsPlaysXCorrectly() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(true, tictactoeai.getplaysx());
    }
    
    @Test
    public void setsPlaysXCorrectly() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        tictactoeai.setplaysx(false);
        
        assertEquals(false, tictactoeai.getplaysx());
    }
    
    public void getBoardFunctionsCorrectly() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals("---------", tictactoeai.getboard());
    }
    
    @Test
    public void setsBoardCorrectly() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        tictactoeai.setboard("X--------");
        
        assertEquals("X--------", tictactoeai.getboard());
    }
    
    @Test
    public void getmovesFunctionsCorrectly() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(0, tictactoeai.getmoves());
    }
    
    @Test
    public void increasesMovesCorrectly() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        tictactoeai.increasemoves();
        
        assertEquals(1, tictactoeai.getmoves());
    }
    
    @Test
    public void checksBoardCompletedCorrectly() {
        String board = "XXOOXOXXO";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(true, tictactoeai.boardCompleted());
    }
    
    @Test
    public void checksBoardForXWinnerCorrectly() {
        String board = "XXX------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkBoardForWinner());
    }
    /*
    @Test
    public void checksAllRowsForXsCorrectly() {
        String board = "XXX------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkAllRowsForWinner());
    }
    /*
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
    
    @Test
    public void checksFirstColumnXsCorrectly() {
        String board = "X--X--X--";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkColumnForWinner(board, 1));
    }
    */
    
    @Test
    public void nextMoveCorrectWhenPlaysX() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals("X--------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysXToWin() {
        String board = "XX-------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals("XXX------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysO() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, false);
        
        assertEquals("O--------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysOToWin() {
        String board = "OO-------";
        TictactoeAI tictactoeai = new TictactoeAI(board, false);
        
        assertEquals("OOO------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextPossibleMovesAreCorrect() {
        String board = "---------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        List<String> nextpossiblemoves = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            nextpossiblemoves.add(board.substring(0, i) + "X" + board.substring(i + 1, 9));
        }
        
        assertEquals(nextpossiblemoves, tictactoeai.nextPossibleMoves());
    }
    
    
    
    @Test
    public void toStringIsCorrect() {
        String board = "XXOXOOOXX";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        //System.out.println("X--\nX--\nX--");
        
        assertEquals("XXOXOOOXX", tictactoeai.toString().substring(0, 3)
                + tictactoeai.toString().substring(4, 7)
                + tictactoeai.toString().substring(8, 11));
    }
    
}
