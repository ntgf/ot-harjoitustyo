package tictactoe.domain;

public class BoardChecker {
    
    /*
    public boolean boardCompleted() {
        return boardCompleted(this.board);
    }*/
    
    public boolean boardCompleted(String board) {
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-') {
                return false;
            }
        }
        
        return true;
    }
    
    /*
    public int checkBoardForWinner() {
        return checkBoardForWinner(this.board);
    }*/
    
    public int checkBoardForWinner(String board) {
        //Method 1:
        //return checkAllRowsForWinner(board) + checkAllColumnsForWinner(board) + checkDiagonalsForWinner(board);
        
        //Method 2 (faster on average):
        //Check rows:
        int winner = checkAllRowsForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        //Check columns:
        winner = checkAllColumnsForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        //Check diagonals:
        winner = checkDiagonalsForWinner(board);
        
        return winner;
        
        /*if (winner != 0) {
            return winner;
        }
        
        return 0; */
    }
    
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

