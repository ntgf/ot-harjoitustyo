package tictactoe.domain;

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
        TictactoeAI tictactoeai = new TictactoeAI();
        tictactoeai.setplaysx(true);
        assertEquals(true, tictactoeai.getplaysx());
    }
    
    @Test
    public void setsPlaysXCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        tictactoeai.setplaysx(false);
        assertEquals(false, tictactoeai.getplaysx());
    }
    
    public void getsBoardCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        assertEquals("---------", tictactoeai.getboard());
    }
    
    @Test
    public void setsBoardCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        tictactoeai.setboard("X--------");
        assertEquals("X--------", tictactoeai.getboard());
    }
    
    @Test
    public void checksBoardCompletedCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XXOOXOXXO";
        tictactoeai.setboard(board);
        assertEquals(true, tictactoeai.boardCompleted());
    }
    
    @Test
    public void checksBoardNotCompletedCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "X-XOXOXXO";
        tictactoeai.setboard(board);
        assertEquals(false, tictactoeai.boardCompleted());
    }
    
    @Test
    public void checksBoardForXWinnerCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XXX------";
        tictactoeai.setboard(board);
        assertEquals(1, tictactoeai.checkBoardForWinner());
    }
    
    @Test
    public void checksBoardForOWinnerCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "O---O---O";
        tictactoeai.setboard(board);
        assertEquals(-1, tictactoeai.checkBoardForWinner());
    }
    
    @Test
    public void checksBoardForNoWinnerCorrectly() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XX-------";
        tictactoeai.setboard(board);
        assertEquals(0, tictactoeai.checkBoardForWinner());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysX() {
        TictactoeAI tictactoeai = new TictactoeAI();
        tictactoeai.setplaysx(true);
        String board = "---------";
        tictactoeai.setboard(board);
        assertEquals("X--------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysXToWin() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XX-------";
        tictactoeai.setboard(board);
        tictactoeai.setplaysx(true);
        assertEquals("XXX------", tictactoeai.nextMove());
    }
    
    
    public void nextMoveCorrectWhenPlaysXOnEasyMode() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XXXOOXOX-";
        tictactoeai.setboard(board);
        tictactoeai.setplaysx(true);
        tictactoeai.setdifficulty(1);
        assertEquals("XXXOOXOXX", tictactoeai.nextMove());
    }
    
    public void nextMoveCorrectWhenPlaysXOnMediumMode() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XXXOOXOX-";
        tictactoeai.setboard(board);
        tictactoeai.setplaysx(true);
        tictactoeai.setdifficulty(2);
        assertEquals("XXXOOXOXX", tictactoeai.nextMove());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysO() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "---------";
        tictactoeai.setboard(board);
        tictactoeai.setplaysx(false);
        assertEquals("O--------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysOToWin() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "OO-------";
        tictactoeai.setboard(board);
        tictactoeai.setplaysx(false);
        assertEquals("OOO------", tictactoeai.nextMove());
    }
    
    @Test
    public void nextPossibleMovesAreCorrect() {
        TictactoeAI tictactoeai = new TictactoeAI();
        tictactoeai.setplaysx(true);
        String board = "---------";
        List<String> nextpossiblemoves = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            nextpossiblemoves.add(board.substring(0, i) + "X" + board.substring(i + 1, 9));
        }
        
        assertEquals(nextpossiblemoves, tictactoeai.nextPossibleMoves());
    }
    
    @Test
    public void toStringIsCorrect() {
        TictactoeAI tictactoeai = new TictactoeAI();
        String board = "XXOXOOOXX";
        tictactoeai.setboard(board);
        assertEquals("XXOXOOOXX", tictactoeai.toString().substring(0, 3)
                + tictactoeai.toString().substring(4, 7)
                + tictactoeai.toString().substring(8, 11));
    }
}
