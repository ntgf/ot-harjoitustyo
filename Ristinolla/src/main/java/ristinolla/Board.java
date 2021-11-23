package ristinolla;

public class Board {
    private String board;
    private Board previous;
    private Board next;
    
    public Board(String board, Board previous, Board next) {
        this.board = board;
        this.previous = previous;
        this.next = next;
    }
}
