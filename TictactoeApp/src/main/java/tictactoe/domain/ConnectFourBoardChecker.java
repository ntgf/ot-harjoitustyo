package tictactoe.domain;
/**
 * The class responsible for evaluating the Connect Four board.
 */
public class ConnectFourBoardChecker {
    /**
     * The method checks whether the board is completed.
     *
     * @param   board  The board to be checked as a String.
     * 
     * @return Returns true if the board is completed, false otherwise.
     */
    public boolean boardCompleted(String board) {
        if (board.contains("-")) {
            return false;
        }
        return true;
    }
    /**
     * The method returns the number of times the pattern can be found on the board.
     *
     * @param   board  The board as a String.
     * 
     * @param   pattern  The pattern to be searched for from the board.
     * 
     * @return Returns the number of times the pattern can be found on the board.
     */
    public int numberOfPatternInBoard(String board, String pattern) {
        int patternAmount = 0;
        
        patternAmount += numberOfPatternInAllRows(board, pattern) + 
                numberOfPatternInAllColumns(board, pattern) +
                numberOfPatternInAllMeaningfulUpRightDiagonals(board, pattern) +
                numberOfPatternInAllMeaningfulUpLeftDiagonals(board, pattern);
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * and on the all meaningful up-right diagonals, which are relevant for winning the game.
     * (Diagonals that have at least a length of 4).
     *
     * @param   board  The board as a String.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAllMeaningfulUpRightDiagonals(String board, String pattern) {
        int patternAmount = 0;
        
        for (int i = 0; i < 4; i++) {
            patternAmount += this.numberOfPatternInAnUpRightDiagonal(board, i, 8, pattern);
        }
        
        for (int j = 5; j < 8; j++) {
            patternAmount += this.numberOfPatternInAnUpRightDiagonal(board, 0, j, pattern);
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * and on the all meaningful up-left diagonals, which are relevant for winning the game.
     * (Diagonals that have at least a length of 4).
     *
     * @param   board  The board as a String.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAllMeaningfulUpLeftDiagonals(String board, String pattern) {
        int patternAmount = 0;
        
        for (int i = 0; i < 4; i++) {
            patternAmount += this.numberOfPatternInAnUpLeftDiagonal(board, i, 0, pattern);
        }
        
        for (int j = 1; j < 4; j++) {
            patternAmount += this.numberOfPatternInAnUpLeftDiagonal(board, 0, j, pattern);
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * and on an up-right diagonal.
     *
     * @param   board  The board as a String.
     * 
     * @param   startxcoordinate  The start x-coordinate of the diagonal.
     * 
     * @param   startycoordinate  The start y-coordinate of the diagonal.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAnUpRightDiagonal(String board, int startxcoordinate, int startycoordinate, String pattern) {
        int patternAmount = 0;
        String diagonalString = "";
        
        for (int i = startxcoordinate; i < 9; i++) {
            diagonalString += board.substring(9 * i + startycoordinate, 9 * i + startycoordinate + 1);
            startycoordinate--;
            
            if (startycoordinate < 0) {
                break;
            }
        }
        
        int startindex = 0;
        
        for (int i = 1; i < diagonalString.length() + 1; i++) {
            if (diagonalString.substring(startindex, i).contains(pattern)) {
                patternAmount++;
                startindex = i - pattern.length() + 1;
            }
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * and on an up-left diagonal.
     *
     * @param   board  The board as a String.
     * 
     * @param   startxcoordinate  The start x-coordinate of the diagonal.
     * 
     * @param   startycoordinate  The start y-coordinate of the diagonal.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAnUpLeftDiagonal(String board, int startxcoordinate, int startycoordinate, String pattern) {
        int patternAmount = 0;
        String diagonalString = "";
        
        for (int i = startxcoordinate; i < 9; i++) {
            diagonalString += board.substring(9 * i + startycoordinate, 9 * i + startycoordinate + 1);
            startycoordinate++;
            
            if (startycoordinate > 8) {
                break;
            }
        }
        
        int startindex = 0;
        
        for (int i = 1; i < diagonalString.length() + 1; i++) {            
            if (diagonalString.substring(startindex, i).contains(pattern)) {
                patternAmount++;
                startindex = i - pattern.length() + 1;
            }
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * in all rows.
     *
     * @param   board  The board as a String.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAllRows(String board, String pattern) {
        int patternAmount = 0;
        
        for (int i = 0; i < 9; i++) {
            patternAmount += numberOfPatternInARow(board, i, pattern);
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * in all columns.
     *
     * @param   board  The board as a String.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAllColumns(String board, String pattern) {
        int patternAmount = 0;
        
        for (int i = 0; i < 9; i++) {
            patternAmount += numberOfPatternInAColumn(board, i, pattern);
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * in a row.
     *
     * @param   board  The board as a String.
     *
     * @param   rowindex  The row to be searched from.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInARow(String board, int rowindex, String pattern) {
        int patternAmount = 0;
        
        String rowString = "";
        
        rowString += board.substring(9 * rowindex, 9 * rowindex + 9);
        
        int startindex = 0;
        
        for (int i = 1; i < 10; i++) {
            if (rowString.substring(startindex, i).contains(pattern)) {
                patternAmount++;
                startindex = i - pattern.length() + 1;
            }
        }
        
        return patternAmount;
    }
    /**
     * The method returns the number of times the pattern can be found on the board
     * in a column.
     *
     * @param   board  The board as a String.
     *
     * @param   columnindex  The column to be searched from.
     * 
     * @param   pattern  The pattern to be searched for.
     * 
     * @return Returns the number of times the pattern can be found.
     */
    public int numberOfPatternInAColumn(String board, int columnindex, String pattern) {
        int patternAmount = 0;
        String columnString = "";
        
        for (int i = 0; i < 9; i++) {
            columnString += board.substring(9 * i + columnindex, 9 * i + columnindex + 1);
        }
        
        int startindex = 0;
        
        for (int i = 1; i < 10; i++) {
            if (columnString.substring(startindex, i).contains(pattern)) {
                patternAmount++;
                startindex = i - pattern.length() + 1;
            }
        }
        
        return patternAmount;
    }
}