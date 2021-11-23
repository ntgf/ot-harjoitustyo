import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ristinolla.TictactoeAI;

public class TictactoeAITest {
    
    @Test
    public void checksFirstRowXsCorrectly() {
        String board = "XXX------";
        TictactoeAI tictactoeai = new TictactoeAI(board, true);
        
        assertEquals(1, tictactoeai.checkrow(board, 1));
    }
}
