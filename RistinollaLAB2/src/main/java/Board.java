import java.util.List;

public class Board {
    
    private String board;
    private int value;
    private Board previous;
    private List<Board> nextBoards;
    
    public Board(String board, Board previous /*, Board next*/) {
        this.board = board;
        this.previous = previous;
        //this.next = next;
    }
    
    public String getBoard() {
        return this.board;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setNextBoards(List<Board> nextBoards) {
        this.nextBoards = nextBoards;
    }
    
    public List<Board> getNextBoards() {
        return this.nextBoards;
    }
    
    public String toString() {
        return this.board.substring(0, 3)
                + "\n" + this.board.substring(3, 6)
                + "\n" + this.board.substring(6, 9);
    }
}

