package tictactoe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The class responsible for the Connect Four game ai.
 */
public class ConnectFourAI {
    
    private String boardString;
    private boolean playsx;
    private ConnectFourBoardChecker boardchecker;
    private int difficulty;
    private Random random;
    
    public ConnectFourAI() {
        this.boardString = this.createBoardString();
        this.boardchecker = new ConnectFourBoardChecker();
        this.random = new Random();
    }
    
    public boolean getplaysx() {
        return this.playsx;
    }
    
    public void setplaysx(boolean playsx) {
        this.playsx = playsx;
    }
    public String getboardString() {
        return this.boardString;
    }
    
    public void setboardString(String board) {
        this.boardString = board;
    }
    
    public void setdifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    /**
     * The method checks whether the board is completed.
     *
     * @return Returns true if the board is completed, false otherwise.
     */
    public boolean boardStringCompleted() {
        return this.boardchecker.boardCompleted(this.boardString);
    }
    /**
     * The method checks the board for a winning pattern, in other words for a victory.
     *
     * @return Returns the number of winning patterns on the board, positive for x, negative for o.
     */
    public int checkBoardForWinningPattern() {
        return this.boardchecker.numberOfPatternInBoard(boardString, "XXXX") - this.boardchecker.numberOfPatternInBoard(boardString, "OOOO");
    }
    /**
     * The method makes the next move for the game ai.
     *
     * @return Returns the next game ai move as a String.
     */
    public String nextMove2() {
        List<String> nextMoves = nextPossibleStringMoves(boardString, playsx);
        
        int[] nextMovesValues = new int[nextMoves.size()];
        
        int depthLimit = 4;
        
        if (playsx) {
            for (int i = 0; i < nextMoves.size(); i++) {
                nextMovesValues[i] = minValue(nextMoves.get(i), -10000000, 10000000, depthLimit);
            }
        }

        if (!playsx) {
            for (int i = 0; i < nextMoves.size(); i++) {
                nextMovesValues[i] = maxValue(nextMoves.get(i), -10000000, 10000000, depthLimit);
            }
        }
        
        int randomindex = 0;
        
        if (nextMoves.size() > 0) {
            randomindex = random.nextInt(nextMoves.size());
        }
        
        if (this.difficulty == 1 && nextMoves.size() > 0) {
            int randomInteger = this.random.nextInt(2);
            
            if (randomInteger == 0) {
                this.boardString = nextMoves.get(randomindex);
                return this.boardString;
            }
        }
        
        if (this.difficulty == 2 && nextMoves.size() > 0) {
            int randomInteger = this.random.nextInt(4);
            
            if (randomInteger == 0) {
                this.boardString = nextMoves.get(randomindex);
                return this.boardString;
            }
        }
        
        int maxOrMinValue = 0;
        
        int maxValueIndex = randomindex;
        
        if (playsx) {
            for (int i = 0; i < nextMovesValues.length; i++) {
                if (nextMovesValues[i] > maxOrMinValue) {
                    maxOrMinValue = nextMovesValues[i];
                    maxValueIndex = i;
                }
            }
        }
        
        if (!playsx) {
            for (int i = 0; i < nextMovesValues.length; i++) {
                if (nextMovesValues[i] < maxOrMinValue) {
                    maxOrMinValue = nextMovesValues[i];
                    maxValueIndex = i;
                }
            }
        }
        
        this.boardString = nextMoves.get(maxValueIndex);
        
        return this.boardString;
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
     * @param   depthLimit  Depth limit for the alpha-beta pruning.
     *
     * @return Returns the maximum value of the board position.
     */
    public int maxValue(String board, int alpha, int beta, int depthLimit) {
        int boardValue = this.boardValue(board, playsx);
        
        if (depthLimit == 0) {
            return boardValue;
        }
        
        int numberOfWinningPatterns = this.boardchecker.numberOfPatternInBoard(board, "XXXX");
                
        if (numberOfWinningPatterns > 0) {
            return boardValue;
        }
        
        numberOfWinningPatterns = this.boardchecker.numberOfPatternInBoard(board, "OOOO");
        
        if (numberOfWinningPatterns > 0) {
            return boardValue;
        }
        
        if (this.boardchecker.boardCompleted(board)) {
            return this.boardValue(board, playsx);
        }
        
        int v = -10000000;
        
        for (String nextBoard: nextPossibleStringMoves(board, true)) {
            v = Math.max(v, minValue(nextBoard, alpha, beta, depthLimit - 1));
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
     * @param   depthLimit  Depth limit for the alpha-beta pruning.
     *
     * @return Returns the minimum value of the board position.
     */
    public int minValue(String board, int alpha, int beta, int depthLimit) { 
        int boardValue = this.boardValue(board, playsx);
        
        if (depthLimit == 0) {
            return boardValue;
        }
        
        int numberOfWinningPatterns = this.boardchecker.numberOfPatternInBoard(board, "XXXX");
                
        if (numberOfWinningPatterns > 0) {
            return boardValue;
        }
        
        numberOfWinningPatterns = this.boardchecker.numberOfPatternInBoard(board, "OOOO");
        
        if (numberOfWinningPatterns > 0) {
            return boardValue;
        }
        
        if (this.boardchecker.boardCompleted(board)) {
            return this.boardValue(board, playsx);
        }
        
        int v = 10000000;
        
        for (String nextBoard: nextPossibleStringMoves(board, false)) {
            v = Math.min(v, maxValue(nextBoard, alpha, beta, depthLimit - 1));
            beta = Math.min(beta, v);
            
            if (alpha >= beta) {
                return v;
            }
        }
        
        return v;
    }
    /**
     * The method returns the estimated board value as required by the depth-limited
     * alpha-beta pruning.
     *
     * @param   board  Board set-up as a String.
     * 
     * @param   playsx  Whether the game ai plays x.
     *
     * @return Returns the estimated board value.
     */
    public int boardValue(String board, boolean playsx) {
        int boardValue = this.boardXValue(board, playsx) + this.boardOValue(board, playsx);
        
        return boardValue;
    }
    /**
     * The method returns the estimated board value for the X's.
     *
     * @param   board  Board set-up as a String.
     * 
     * @param   playsx  Whether the game ai plays x.
     *
     * @return Returns the estimated board value for the X's.
     */
    public int boardXValue(String board, boolean playsx) {
        int boardXValue = 0;
        
        boardXValue += 1000000 * this.boardchecker.numberOfPatternInBoard(board, "XXXX");
        
        boardXValue += 10000000 * this.boardchecker.numberOfPatternInBoard(board, "-XXX-");
        
        if (!this.playsx) {
            boardXValue += 1000000000 * this.boardchecker.numberOfPatternInBoard(board, "-XXX-");
        }
        
        boardXValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "|XXX-");
        boardXValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "OXXX-");
        
        boardXValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "-XXX|");
        boardXValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "-XXXO");
        
        boardXValue += 2000 * this.boardchecker.numberOfPatternInBoard(board, "-X-XX-");
        boardXValue += 2000 * this.boardchecker.numberOfPatternInBoard(board, "-XX-X-");
        
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "|X-XX-");
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "OX-XX-");
        
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "|XX-X-");
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "OXX-X-");
        
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-XX-X|");
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-XX-XO");
        
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-X-XX|");
        boardXValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-X-XXO");
        
        boardXValue += 500 * this.boardchecker.numberOfPatternInBoard(board, "-X-X-");
        
        boardXValue += 200 * this.boardchecker.numberOfPatternInBoard(board, "-XX-");
        
        boardXValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "|X-X-");
        boardXValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "OX-X-");
        
        boardXValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "-X-X|");
        boardXValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "-X-XO");
        
        boardXValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "|XX--");
        boardXValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "OXX--");
        
        boardXValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "--XX|");
        boardXValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "--XXO");

        boardXValue += 5 * this.boardchecker.numberOfPatternInBoard(board, "-X--X-");
        
        boardXValue += this.boardchecker.numberOfPatternInBoard(board, "|X--X-");
        boardXValue += this.boardchecker.numberOfPatternInBoard(board, "OX--X-");
        
        boardXValue += this.boardchecker.numberOfPatternInBoard(board, "-X--X|");
        boardXValue += this.boardchecker.numberOfPatternInBoard(board, "-X--XO");
        
        return boardXValue;
    }
    /**
     * The method returns the estimated board value for the O's.
     *
     * @param   board  Board set-up as a String.
     * 
     * @param   playsx  Whether the game ai plays x.
     *
     * @return Returns the estimated board value for the O's.
     */
    public int boardOValue(String board, boolean playsx) {
        int boardOValue = 0;
        
        boardOValue += 1000000 * this.boardchecker.numberOfPatternInBoard(board, "OOOO");

        boardOValue += 10000000 * this.boardchecker.numberOfPatternInBoard(board, "-OOO-");

        if (this.playsx) {
            boardOValue += 1000000000 * this.boardchecker.numberOfPatternInBoard(board, "-OOO-");
        }

        boardOValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "|OOO-");
        boardOValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "XOOO-");
        
        boardOValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "-OOO|");
        boardOValue += 5000 * this.boardchecker.numberOfPatternInBoard(board, "-OOOX");
        
        boardOValue += 2000 * this.boardchecker.numberOfPatternInBoard(board, "-O-OO-");
        boardOValue += 2000 * this.boardchecker.numberOfPatternInBoard(board, "-OO-O-");

        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "|O-OO-");
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "XO-OO-");
        
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "|OO-O-");
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "XOO-O-");
        
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-OO-O|");
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-OO-X");
        
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-O-OO|");
        boardOValue += 1000 * this.boardchecker.numberOfPatternInBoard(board, "-O-OOX");

        boardOValue += 500 * this.boardchecker.numberOfPatternInBoard(board, "-O-O-");
        
        boardOValue += 200 * this.boardchecker.numberOfPatternInBoard(board, "-OO-");
        
        boardOValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "|O-O-");
        boardOValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "XO-O-");
        
        boardOValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "-O-O|");
        boardOValue += 100 * this.boardchecker.numberOfPatternInBoard(board, "-O-OX");

        boardOValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "|OO--");
        boardOValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "XOO--");
        
        boardOValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "--OO|");
        boardOValue += 20 * this.boardchecker.numberOfPatternInBoard(board, "--OOX");

        boardOValue += 5 * this.boardchecker.numberOfPatternInBoard(board, "-O--O-");
        
        boardOValue += this.boardchecker.numberOfPatternInBoard(board, "|O--O-");
        boardOValue += this.boardchecker.numberOfPatternInBoard(board, "XO--O-");
        
        boardOValue += this.boardchecker.numberOfPatternInBoard(board, "-O--O|");
        boardOValue += this.boardchecker.numberOfPatternInBoard(board, "-O--OX");
        
        boardOValue = -boardOValue;

        return boardOValue;
    }
    /**
     * The method returns the next possible moves as String.
     *
     * @return Returns the next possible moves as a list of String values.
     */
    public List<String> nextPossibleStringMoves() {
        return nextPossibleStringMoves(this.boardString, this.playsx);
    }
    /**
     * The method returns the next possible moves as String for the given board.
     *
     * @return Returns the next possible moves for the given board as a list of String values.
     */
    public List<String> nextPossibleStringMoves(String board, boolean playsx) {       
        List<String> nextMoves = new ArrayList<>();
        
        String mark = "X";
        
        if (!playsx) {
            mark = "O";
        }
        
        for (int j = 1; j < 8; j++) {
            for (int i = 7; i >= 0; i--) {
                String boardPlacement = board.substring(9 * i + j, 9 * i + j + 1);
                
                if (boardPlacement.equals("-")) {
                    String nextMove = board.substring(0, 9 * i + j) + mark + board.substring(9 * i + j + 1, 81);
                    nextMoves.add(nextMove);
                    break;
                }
            }
        }
        
        return nextMoves;
    }
    /**
     * The method creates and returns a Connect Four board as a String.
     *
     * @return Returns a Connect Four board as a String.
     */
    public String createBoardString() {
        String boardString = "|||||||||";
        
        for (int i = 0; i < 7; i++) {
            boardString += "|-------|";
        }
        
        boardString += "|||||||||";
        
        return boardString;
    }
    /**
     * The method returns the current board as a String.
     *
     * @return Returns the current board as a String.
     */
    public String toString() {
        String boardString = "";

        for (int i = 0; i < 9; i++) {
            boardString += this.boardString.substring(9 * i, 9 * i + 9) + "\n";
            boardString += "\n";
        }

        return boardString;
    }
    /**
     * The method returns the given board as a String.
     **
     * @param   board  Game board as a String.
     *
     * @return Returns the given board as a String.
     */
    public String toString(String board) {
        String boardString = "";

        for (int i = 0; i < 9; i++) {
            boardString += board.substring(9 * i, 9 * i + 9) + "\n";
            boardString += "\n";
        }

        return boardString;
    }
}
