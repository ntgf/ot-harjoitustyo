package tictactoe.domain;
/**
 * The class responsible for evaluating the 3x3 tic-tac-toe board.
 */
public class BoardChecker {
    /**
     * The method checks whether the given board is completed.
     *
     * @param   board  Game board as a String.
     *
     * @return Returns true if the given board is completed, false otherwise.
     */
    public boolean boardCompleted(String board) {
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-') {
                return false;
            }
        }
        
        return true;
    }
    /**
     * The method checks whether the given board has a winner.
     *
     * @param   board  Game board as a String.
     *
     * @return Returns 1 if X's win, -1 if O's, and 0 if no winner.
     */
    public int checkBoardForWinner(String board) {
        int winner = checkAllRowsForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        winner = checkAllColumnsForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        winner = checkDiagonalsForWinner(board);
        
        return winner;
    }
    /**
     * The method checks whether any row on the given board has a winner.
     *
     * @param   board  Game board as a String.
     *
     * @return Returns 1 if X's win, -1 if O's, and 0 if no winner.
     */
    public int checkAllRowsForWinner(String board) {
        
        for (int i = 1; i < 4; i++) {
            int winner = checkRowForWinner(board, i);
            
            if (winner == 1) {
                return 1;
            }
            
            if (winner == -1) {
                return -1;
            }
        }
        
        return 0;
    }
    /**
     * The method checks whether any column on the given board has a winner.
     *
     * @param   board  Game board as a String.
     *
     * @return Returns 1 if X's win, -1 if O's, and 0 if no winner.
     */
    public int checkAllColumnsForWinner(String board) {
        
        for (int i = 1; i < 4; i++) {
            int winner = checkColumnForWinner(board, i);
            
            if (winner == 1) {
                return 1;
            }
            
            if (winner == -1) {
                return -1;
            }
        }
        
        return 0;
    }
    /**
     * The method checks whether any diagonal on the given board has a winner.
     *
     * @param   board  Game board as a String.
     *
     * @return Returns 1 if X's win, -1 if O's, and 0 if no winner.
     */
    public int checkDiagonalsForWinner(String board) {
        
        //Check diagonal one:
        
        int xs = 0;
        int os = 0;
        
        for (int i = 0; i < 9; i += 4) {
            
            if (board.charAt(i) == 'X') {
                xs++;
            }
            
            if (board.charAt(i) == 'O') {
                os++;
            }
        }
         
        if (xs == 3) {
            return 1;
        }
        
        if (os == 3) {
            return -1;
        }
        
        //Check diagonal two:
        
        xs = 0;
        os = 0;
        
        for (int i = 2; i < 7; i += 2) {
            
            if (board.charAt(i) == 'X') {
                xs++;
            }
            
            if (board.charAt(i) == 'O') {
                os++;
            }
        }
         
        if (xs == 3) {
            return 1;
        }
        
        if (os == 3) {
            return -1;
        }
        
        return 0;
    }
    /**
     * The method checks whether a row on the given board has a winner.
     *
     * @param   board  Game board as a String.
     *
     * @param   row  Row to be searched from.
     *
     * @return Returns 1 if X's win, -1 if O's, and 0 if no winner.
     */
    public int checkRowForWinner(String board, int row) {
        int xs = 0;
        int os = 0;
        
        for (int i = 0; i < 3; i++) {
            
            if (board.charAt(3 * (row - 1) + i) == 'X') {
                xs++;
            }
            
            if (board.charAt(3 * (row - 1) + i) == 'O') {
                os++;
            }
        }
         
        if (xs == 3) {
            return 1;
        }
        
        if (os == 3) {
            return -1;
        }
        
        return 0;
    }
    /**
     * The method checks whether a column on the given board has a winner.
     *
     * @param   board  Game board as a String.
     *
     * @param   column  Column to be searched from.
     *
     * @return Returns 1 if X's win, -1 if O's, and 0 if no winner.
     */
    public int checkColumnForWinner(String board, int column) {
        int xs = 0;
        int os = 0;
        
        for (int i = column - 1; i < 9; i += 3) {
            
            if (board.charAt(i) == 'X') {
                xs++;
            }         
            
            if (board.charAt(i) == 'O') {
                os++;
            }
        }
        
        if (xs == 3) {
            return 1;
        }
        
        if (os == 3) {
            return -1;
        }
        
        return 0;
    }
}