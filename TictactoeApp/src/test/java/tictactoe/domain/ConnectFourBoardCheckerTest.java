package tictactoe.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectFourBoardCheckerTest {
    
    private ConnectFourBoardChecker connectFourBoardChecker;
    
    @Before
    public void setUp() {
        this.connectFourBoardChecker = new ConnectFourBoardChecker();
    }
    
    @Test
    public void checksBoardCompletedCorreclty() {
        String board = "||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||";
        assertEquals(true, this.connectFourBoardChecker.boardCompleted(board));
    }
    
    @Test
    public void checksBoardNotCompletedCorreclty() {
        String board = "||||||||||XX-XXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||";
        assertEquals(false, this.connectFourBoardChecker.boardCompleted(board));
    }
    
    @Test
    public void checksNumberOfPatternInBoardCorrectly() {
        String board = "||||||||||-------||-------||-------||-------||-------||-------||-XXXX--||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInBoard(board, "XXXX"));
    }
    
    @Test
    public void checksNumberOfDifferingPatternInBoardCorrectly() {
        String board = "||||||||||-------||-------||-------||-------||-------||-------||-XXXX--||||||||||";
        assertEquals(2, connectFourBoardChecker.numberOfPatternInBoard(board, "XXX"));
    }
    
    @Test
    public void checksNumberOfNonExistingPatternInBoardCorrectly() {
        String board = "||||||||||-------||-------||-------||-------||-------||-------||-XXXX--||||||||||";
        assertEquals(0, connectFourBoardChecker.numberOfPatternInBoard(board, "OOO"));
    }
    
    @Test
    public void checksNumberOfPatternInAllMeaningfulUpRightDiagonalsCorrectly() {
        String board = "||||||||||-------||-------||-------||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAllMeaningfulUpRightDiagonals(board, "XXXX"));
    }
    
    @Test
    public void checksNumberOfDifferingPatternInAllMeaningfulUpRightDiagonalsCorrectly() {
        String board = "||||||||||-------||-------||-------||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(2, connectFourBoardChecker.numberOfPatternInAllMeaningfulUpRightDiagonals(board, "XXX"));
    }
    
    @Test
    public void checksNumberOfNonExistingPatternInAllMeaningfulUpRightDiagonalsCorrectly() {
        String board = "||||||||||-------||-------||-------||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(0, connectFourBoardChecker.numberOfPatternInAllMeaningfulUpRightDiagonals(board, "X-XX"));
    }
    
    @Test
    public void checksNumberOfPatternInAllMeaningfulUpLeftDiagonalsCorrectly() {
        String board = "||||||||||X------||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAllMeaningfulUpLeftDiagonals(board, "XXXX"));
    }
    
    @Test
    public void checksNumberOfDifferingPatternInAllMeaningfulUpLeftDiagonalsCorrectly() {
        String board = "||||||||||X------||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(2, connectFourBoardChecker.numberOfPatternInAllMeaningfulUpLeftDiagonals(board, "XXX"));
    }
    
    @Test
    public void checksNumberOfNonExistingPatternInAllMeaningfulUpLeftDiagonalsCorrectly() {
        String board = "||||||||||X------||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(0, connectFourBoardChecker.numberOfPatternInAllMeaningfulUpLeftDiagonals(board, "OX"));
    }
    
    @Test
    public void checksNumberOfPatternInAnUpRightDiagonalCorrectly() {
        String board = "||||||||||-------||-------||-------||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAnUpRightDiagonal(board, 0, 8, "XXXX"));
    }
    
    @Test
    public void checksNumberOfDifferingPatternInAnUpRightDiagonalCorrectly() {
        String board = "||||||||||-------||-------||-------||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(3, connectFourBoardChecker.numberOfPatternInAnUpRightDiagonal(board, 0, 8, "XX"));
    }
    
    @Test
    public void checksNumberOfNonExistingPatternInAnUpRightDiagonalCorrectly() {
        String board = "||||||||||-------||-------||-------||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(0, connectFourBoardChecker.numberOfPatternInAnUpRightDiagonal(board, 0, 8, "OX"));
    }
    
    @Test
    public void checksNumberOfPatternInAnUpLeftDiagonalCorrectly() {
        String board = "||||||||||X------||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAnUpLeftDiagonal(board, 0, 0, "XXXX"));
    }
    
    @Test
    public void checksNumberOfDifferingPatternInAnUpLeftDiagonalCorrectly() {
        String board = "||||||||||X------||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(4, connectFourBoardChecker.numberOfPatternInAnUpLeftDiagonal(board, 0, 0, "X"));
    }
    
    @Test
    public void checksNumberOfNonExistingPatternInAnUpLeftDiagonalCorrectly() {
        String board = "||||||||||X------||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(0, connectFourBoardChecker.numberOfPatternInAnUpLeftDiagonal(board, 0, 0, "OOX"));
    }
    
    @Test
    public void checksNumberOfPatternInAllRowsCorrectly() {
        String board = "||||||||||XXXX---||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAllRows(board, "XXXX"));
    }
    
    @Test
    public void checksNumberOfDifferingPatternInAllRowsCorrectly() {
        String board = "||||||||||XXXX---||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(2, connectFourBoardChecker.numberOfPatternInAllRows(board, "XXX"));
    }
    
    @Test
    public void checksNumberOfNonExistingPatternInAllRowsCorrectly() {
        String board = "||||||||||XXXX---||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(0, connectFourBoardChecker.numberOfPatternInAllRows(board, "XOX"));
    }
    
    @Test
    public void checksNumberOfPatternInAllColumnsCorrectly() {
        String board = "||||||||||XXXX---||XX-----||X-X----||X--X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAllColumns(board, "XXXX"));
    }
    
    @Test
    public void checksNumberOfPatternInARowCorrectly() {
        String board = "||||||||||XXXX---||-X-----||--X----||---X---||--X----||-X-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInARow(board, 1, "XXXX"));
    }
    
    @Test
    public void checksNumberOfPatternInAColumnCorrectly() {
        String board = "||||||||||XXXX---||-X-----||--X----||X--X---||X-X----||XX-----||X------||||||||||";
        assertEquals(1, connectFourBoardChecker.numberOfPatternInAColumn(board, 1, "XXXX"));
    }
}
