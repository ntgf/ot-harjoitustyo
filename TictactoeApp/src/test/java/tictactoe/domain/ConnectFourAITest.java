package tictactoe.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.domain.ConnectFourAI;

public class ConnectFourAITest {
    
    @Test
    public void returnsPlaysXCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        connectFourAI.setplaysx(true);
        assertEquals(true, connectFourAI.getplaysx());
    }
    
    @Test
    public void setsPlaysXCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        connectFourAI.setplaysx(false);
        assertEquals(false, connectFourAI.getplaysx());
    }
    
    public void getsBoardCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        assertEquals("||||||||||-------||-------||-------||-------||-------||-------||-------||||||||||", connectFourAI.getboardString());
    }
    
    @Test
    public void setsBoardCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        connectFourAI.setboardString("||||||||||-------||---X---||-------||-------||-------||-------||-------||||||||||");
        assertEquals("||||||||||-------||---X---||-------||-------||-------||-------||-------||||||||||", connectFourAI.getboardString());
    }
    
    @Test
    public void checksBoardCompletedCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||||||||||";
        connectFourAI.setboardString(board);
        assertEquals(true, connectFourAI.boardStringCompleted());
    }
    
    @Test
    public void checksBoardNotCompletedCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||OO-OXXO||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||OOXOXXO||||||||||";
        connectFourAI.setboardString(board);
        assertEquals(false, connectFourAI.boardStringCompleted());
    }
    
    @Test
    public void checksBoardForAnXWinningPatternCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||-------||---XXXX||-------||-------||-------||-------||-------||||||||||";
        connectFourAI.setboardString(board);
        assertEquals(1, connectFourAI.checkBoardForWinningPattern());
    }
    
    @Test
    public void checksBoardForAnOWinningPatternCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||-------||---OOOO||-------||-------||-------||-------||-------||||||||||";
        connectFourAI.setboardString(board);
        assertEquals(-1, connectFourAI.checkBoardForWinningPattern());
    }
    
    @Test
    public void checksBoardForNoWinningPatternCorrectly() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||-------||---X-XX||-------||-------||-------||-------||-------||||||||||";
        connectFourAI.setboardString(board);
        assertEquals(0, connectFourAI.checkBoardForWinningPattern());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysX() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||-------||-------||-------||-------||-------||-------||XXX----||||||||||";
        connectFourAI.setplaysx(true);
        connectFourAI.setboardString(board);
        assertEquals("||||||||||-------||-------||-------||-------||-------||-------||XXXX---||||||||||", connectFourAI.nextMove2());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysO() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||-------||-------||-------||-------||-------||-------||OOO----||||||||||";
        connectFourAI.setplaysx(false);
        connectFourAI.setboardString(board);
        assertEquals("||||||||||-------||-------||-------||-------||-------||-------||OOOO---||||||||||", connectFourAI.nextMove2());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysXAndDifficultyEasy() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||XXXXXX-||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||";
        connectFourAI.setplaysx(true);
        connectFourAI.setdifficulty(1);
        connectFourAI.setboardString(board);
        assertEquals("||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||", connectFourAI.nextMove2());
    }
    
    @Test
    public void nextMoveCorrectWhenPlaysXAndDifficultyMedium() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||XXXXXX-||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||";
        connectFourAI.setplaysx(true);
        connectFourAI.setdifficulty(2);
        connectFourAI.setboardString(board);
        assertEquals("||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||", connectFourAI.nextMove2());
    }
    
    @Test
    public void toStringIsCorrect() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||";
        connectFourAI.setboardString(board);
        
        assertEquals("||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||", 
                connectFourAI.toString().substring(0, 9) +
                        connectFourAI.toString().substring(11, 20) +
                        connectFourAI.toString().substring(22, 31) +
                        connectFourAI.toString().substring(33, 42) +
                        connectFourAI.toString().substring(44, 53) +
                        connectFourAI.toString().substring(55, 64) +
                        connectFourAI.toString().substring(66, 75) +
                        connectFourAI.toString().substring(77, 86) +
                        connectFourAI.toString().substring(88, 97));
    }
    
    @Test
    public void toStringFromAParameterBoardIsCorrect() {
        ConnectFourAI connectFourAI = new ConnectFourAI();
        String board = "||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||";
        //connectFourAI.setboardString(board);
        String toStringBoard = connectFourAI.toString(board);
        
        assertEquals("||||||||||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||XXXXXXX||||||||||", 
                toStringBoard.substring(0, 9) +
                        toStringBoard.substring(11, 20) +
                        toStringBoard.substring(22, 31) +
                        toStringBoard.substring(33, 42) +
                        toStringBoard.substring(44, 53) +
                        toStringBoard.substring(55, 64) +
                        toStringBoard.substring(66, 75) +
                        toStringBoard.substring(77, 86) +
                        toStringBoard.substring(88, 97));
    }
}
