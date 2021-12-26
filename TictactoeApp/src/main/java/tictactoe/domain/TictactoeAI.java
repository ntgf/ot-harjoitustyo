package tictactoe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The class responsible for game ai on 3x3 tic-tac-toe board.
 */
public class TictactoeAI {
    
    private String board;
    private boolean playsx;
    private BoardChecker boardchecker;
    private int difficulty;
    private Random random;
    
    public TictactoeAI() {
        this.board = "---------";
        this.boardchecker = new BoardChecker();
        this.random = new Random();
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
    
    public void setdifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    /**
     * The method checks whether the board is completed.
     *
     * @return True if board is completed, false otherwise.
     */
    public boolean boardCompleted() {
        return this.boardchecker.boardCompleted(this.board);
    }
    /**
     * The method checks for a winner.
     *
     * @return Returns integer 1 if X wins, -1 if O wins, and 0 if there is no winner.
     */
    public int checkBoardForWinner() {
        return this.boardchecker.checkBoardForWinner(this.board);
    }
    /**
     * The method returns the next move given by the game ai as a String.
     *
     * @return Returns next game ai move as a String.
     */
    public String nextMove() {
        List<String> nextMoves = nextPossibleMoves(board, playsx);
        
        int[] nextMovesValues = new int[nextMoves.size()];

        if (playsx) {
            for (int i = 0; i < nextMoves.size(); i++) {
                nextMovesValues[i] = minValue(nextMoves.get(i), -1, 1);
            }
        }

        if (!playsx) {
            for (int i = 0; i < nextMoves.size(); i++) {
                nextMovesValues[i] = maxValue(nextMoves.get(i), -1, 1);
            }
        }
        
        if (this.difficulty == 1 && nextMoves.size() > 0) {
            int randomInteger = this.random.nextInt(2);
            
            if (randomInteger == 0) {
                this.board = nextMoves.get(this.random.nextInt(nextMoves.size()));
                return this.board;
            }
        }
        
        if (this.difficulty == 2 && nextMoves.size() > 0) {
            int randomInteger = this.random.nextInt(4);
            
            if (randomInteger == 0) {
                this.board = nextMoves.get(this.random.nextInt(nextMoves.size()));
                return this.board;
            }
        }
        
        if (playsx) {
            int checker = 0;

            for (int i = 0; i < nextMoves.size(); i++) {
                //note whos turn, here xs and +1!
                if (nextMovesValues[i] == 1) {
                    this.board = nextMoves.get(i);
                    checker++;
                    break;
                }
            }

            if (checker == 0) {

                for (int i = 0; i < nextMoves.size(); i++) {
                    //note whos turn, here xs and +1!
                    if (nextMovesValues[i] == 0) {
                        this.board = nextMoves.get(i);
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
                    checker++;
                    break;
                }
            }

            if (checker == 0) {

                for (int i = 0; i < nextMoves.size(); i++) {
                    //note whos turn, here xs and +1!
                    if (nextMovesValues[i] == 0) {
                        this.board = nextMoves.get(i);
                        break;
                    }
                }
            }
        }
        
        return this.board;
    }
    /**
     * The method gives the maximum value for the given board position in the min-max algorithm.
     *
     * @param   board  Board set-up as a String.
     * 
     * @param   alpha  Lower bound for the alpha-beta pruning.
     * 
     * @param   beta  Upper bound for the alpha-beta pruning.
     *
     * @return Returns the maximum value of the board position.
     */
    public int maxValue(String board, int alpha, int beta) {
        int winner = this.boardchecker.checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        if (this.boardchecker.boardCompleted(board)) {
            return winner;
        }
        
        int v = -1;
        
        for (String nextBoard: nextPossibleMoves(board, true)) {
            v = Math.max(v, minValue(nextBoard, alpha, beta));
            alpha = Math.max(alpha, v);
            
            if (alpha >= beta) {
                return v;
            }
        }
        
        return v;
    }
    /**
     * The method gives the minimum value for the given board position in the min-max algorithm.
     *
     * @param   board  Board set-up as a String.
     * 
     * @param   alpha  Lower bound for the alpha-beta pruning.
     * 
     * @param   beta  Upper bound for the alpha-beta pruning.
     *
     * @return Returns the minimum value of the board position.
     */
    public int minValue(String board, int alpha, int beta) {
        int winner = this.boardchecker.checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        if (this.boardchecker.boardCompleted(board)) {
            return winner;
        }
        
        int v = 1;
        
        for (String nextBoard: nextPossibleMoves(board, false)) {
            v = Math.min(v, maxValue(nextBoard, alpha, beta));
            beta = Math.min(beta, v);
            
            if (alpha >= beta) {
                return v;
            }
        }
        
        return v;
    }
    /**
     * The method returns a String list of all the next possible moves for the current board set-up.
     *
     * @return Returns String list of next possible moves.
     */
    public List<String> nextPossibleMoves() {
        return nextPossibleMoves(this.board, this.playsx);
    }
    /**
     * The method returns a String list of all the next possible moves for the given board set-up.
     *
     * @param   board  Starting board position as String.
     * 
     * @param   playsx  Whether the game ai is to play x.
     * 
     * @return Returns String list of next possible moves.
     */
    public List<String> nextPossibleMoves(String board, boolean playsx) {
        List<String> nextMoves = new ArrayList<String>();
        
        String mark = "X";
        
        if (!playsx) {
            mark = "O";
        }
        
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-') {
                nextMoves.add(board.substring(0, i) + mark + board.substring(i + 1, board.length()));
            }
        }
        
        return nextMoves;
    }
    /**
     * The method returns a String list of all the next possible moves for the given board set-up.
     *
     * @return Returns the board in a 2-dimensional String presentation.
     */
    public String toString() {
        return this.board.substring(0, 3)
                + "\n" + this.board.substring(3, 6)
                + "\n" + this.board.substring(6, 9);
    }
}