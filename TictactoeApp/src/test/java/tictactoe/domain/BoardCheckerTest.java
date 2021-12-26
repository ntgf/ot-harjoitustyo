package tictactoe.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardCheckerTest {
    
    private BoardChecker boardChecker;
    
    @Before
    public void setUp() {
        this.boardChecker = new BoardChecker();
    }
    
    @Test
    public void checksBoardCompletedCorreclty() {
        String board = "XXXXXXXXX";
        assertEquals(true, this.boardChecker.boardCompleted(board));
    }
    
    @Test
    public void checksBoardNotCompletedCorreclty() {
        String board = "XXXXX--XX";
        assertEquals(false, this.boardChecker.boardCompleted(board));
    }
    
    @Test
    public void checksBoardForWinnerXCorrectly() {
        String board = "XXX------";
        assertEquals(1, this.boardChecker.checkBoardForWinner(board));
    }
    
    @Test
    public void checksBoardForWinnerOCorrectly() {
        String board = "OOO------";
        assertEquals(-1, this.boardChecker.checkBoardForWinner(board));
    }
    
    @Test
    public void checksBoardForNoWinnerOCorrectly() {
        String board = "O-O------";
        assertEquals(0, this.boardChecker.checkBoardForWinner(board));
    }
    
    @Test
    public void checksAllRowsForWinnerXCorrectly() {
        String board = "XXX------";
        assertEquals(1, this.boardChecker.checkAllRowsForWinner(board));
    }
    
    @Test
    public void checksAllRowsForWinnerOCorrectly() {
        String board = "OOO------";
        assertEquals(-1, this.boardChecker.checkAllRowsForWinner(board));
    }
    
    @Test
    public void checksAllRowsForNoWinnerOCorrectly() {
        String board = "O-O------";
        assertEquals(0, this.boardChecker.checkAllRowsForWinner(board));
    }
    
    @Test
    public void checksAllColumnsForWinnerXCorrectly() {
        String board = "X--X--X--";
        assertEquals(1, this.boardChecker.checkAllColumnsForWinner(board));
    }
    
    @Test
    public void checksAllColumnsForWinnerOCorrectly() {
        String board = "O--O--O--";
        assertEquals(-1, this.boardChecker.checkAllColumnsForWinner(board));
    }
    
    @Test
    public void checksAllColumnsForNoWinnerXCorrectly() {
        String board = "X--X-----";
        assertEquals(0, this.boardChecker.checkAllColumnsForWinner(board));
    }
    
    @Test
    public void checksDiagonalsForWinnerXCorrectly() {
        String board = "X---X---X";
        assertEquals(1, this.boardChecker.checkDiagonalsForWinner(board));
    }
    
    @Test
    public void checksDiagonalsForWinnerOCorrectly() {
        String board = "O---O---O";
        assertEquals(-1, this.boardChecker.checkDiagonalsForWinner(board));
    }
    
    @Test
    public void checksDiagonalsForNoWinnerCorrectly() {
        String board = "----O---O";
        assertEquals(0, this.boardChecker.checkDiagonalsForWinner(board));
    }
    
    @Test
    public void checksRowForWinnerXCorrectly() {
        String board = "XXX------";
        assertEquals(1, this.boardChecker.checkRowForWinner(board, 1));
    }
    
    @Test
    public void checksRowForWinnerOCorrectly() {
        String board = "OOO------";
        assertEquals(-1, this.boardChecker.checkRowForWinner(board, 1));
    }
    
    @Test
    public void checksRowForNoWinnerCorrectly() {
        String board = "O-O------";
        assertEquals(0, this.boardChecker.checkRowForWinner(board, 1));
    }
    
    @Test
    public void checksColumnForWinnerXCorrectly() {
        String board = "X--X--X--";
        assertEquals(1, this.boardChecker.checkColumnForWinner(board, 1));
    }
    
    @Test
    public void checksColumnForWinnerOCorrectly() {
        String board = "O--O--O--";
        assertEquals(-1, this.boardChecker.checkColumnForWinner(board, 1));
    }
    
    @Test
    public void checksColumnForNoWinnerCorrectly() {
        String board = "O-----O--";
        assertEquals(0, this.boardChecker.checkColumnForWinner(board, 1));
    }
}
