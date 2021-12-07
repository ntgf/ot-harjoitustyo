package tictactoe.domain;

import java.util.ArrayList;
import java.util.List;

public class TictactoeAI {
    
    private String board;
    private boolean playsx;
    private BoardChecker boardchecker;
    private int moves;
    
    public TictactoeAI(String board, boolean playsx) {
        this.board = board;
        this.playsx = playsx;
        this.boardchecker = new BoardChecker();
        this.moves = 0;
    }
    
    public boolean getplaysx() {
        return this.playsx;
    }
    
    public void setplaysx(boolean playsx) {
        this.playsx = playsx;
    }
    
    public String getboard() {
        return this.board;
    }
    
    public void setboard(String board) {
        this.board = board;
    }
    
    public int getmoves() {
        return this.moves;
    }
    
    public void increasemoves() {
        this.moves++;
    }
    
    public boolean boardCompleted() {
        return this.boardchecker.boardCompleted(this.board);
    }
    
    public int checkBoardForWinner() {
        return this.boardchecker.checkBoardForWinner(this.board);
    }
    
    public String nextMove() {
        List<String> nextMoves = nextPossibleMoves(board, playsx);
        
        int[] nextMovesValues = new int[nextMoves.size()];

        if (playsx) {
            for (int i = 0; i < nextMoves.size(); i++) {
                nextMovesValues[i] = min_value(nextMoves.get(i), -1, 1);
            }
        }

        if (!playsx) {
            for (int i = 0; i < nextMoves.size(); i++) {
                nextMovesValues[i] = max_value(nextMoves.get(i), -1, 1);
            }
        }

        if (playsx) {
            int checker = 0;

            for (int i = 0; i < nextMoves.size(); i++) {
                //note whos turn, here xs and +1!
                if (nextMovesValues[i] == 1) {
                    this.board = nextMoves.get(i);
                    this.moves++;
                    checker++;
                    break;
                }
            }

            if (checker == 0) {

                for (int i = 0; i < nextMoves.size(); i++) {
                    //note whos turn, here xs and +1!
                    if (nextMovesValues[i] == 0) {
                        this.board = nextMoves.get(i);
                        this.moves++;
                        break;
                    }
                }
            }    
        }

        if (!playsx) {
            int checker = 0;

            for (int i = 0; i < nextMoves.size(); i++) {
                //note whos turn, here xs and +1!
                if (nextMovesValues[i] == -1) {
                    this.board = nextMoves.get(i);
                    this.moves++;
                    checker++;
                    break;
                }
            }

            if (checker == 0) {

                for (int i = 0; i < nextMoves.size(); i++) {
                    //note whos turn, here xs and +1!
                    if (nextMovesValues[i] == 0) {
                        this.board = nextMoves.get(i);
                        this.moves++;
                        break;
                    }
                }
            }
        }
        
        return this.board;
    }
    
    //Alpha-beta pruning
    public int max_value(String board, int alpha, int beta) {
        int winner = this.boardchecker.checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        if (this.boardchecker.boardCompleted(board)) {
            return winner;
        }
        
        int v = -1;
        
        for (String nextBoard: nextPossibleMoves(board, true)) {
            v = Math.max(v, min_value(nextBoard, alpha, beta));
            alpha = Math.max(alpha, v);
            
            if (alpha >= beta) {
                return v;
            }
        }
        
        return v;
    }
    
    public int min_value(String board, int alpha, int beta) {
        int winner = this.boardchecker.checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        if (this.boardchecker.boardCompleted(board)) {
            return winner;
        }
        
        int v = 1;
        
        for (String nextBoard: nextPossibleMoves(board, false)) {
            v = Math.min(v, max_value(nextBoard, alpha, beta));
            beta = Math.min(beta, v);
            
            if (alpha >= beta) {
                return v;
            }
        }
        
        return v;
    }
    //Alpha-beta pruning over
    
    // Lisays, ok?
    public List<String> nextPossibleMoves() {
        return nextPossibleMoves(this.board, this.playsx);
    }
    
    public List<String> nextPossibleMoves(String board, boolean playsx) {
        //Case if Completed
        
        //ArrayList ok?
        List<String> nextMoves = new ArrayList<String>();
        
        String mark = "X";
        
        if (!playsx) {
            mark = "O";
        }
        
        //Case if completed?
        
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-') {
                //Indexes ok?
                nextMoves.add(board.substring(0, i) + mark + board.substring(i + 1, board.length()));
            }
        }
        
        return nextMoves;
    }
    
    public String toString() {
        return this.board.substring(0, 3)
                + "\n" + this.board.substring(3, 6)
                + "\n" + this.board.substring(6, 9);
    }
    
}


