package tictactoe.domain;

import java.util.ArrayList;
import java.util.List;

public class TictactoeAI {
    
    private String board;
    private boolean playsx;
    private int moves;
    
    public TictactoeAI(String board, boolean playsx) {
        this.board = board;
        this.playsx = playsx;
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
    
    public void increasemoves() {
        this.moves++;
    }
    
    
    //Requires modifying
    /*
    public void nextMove() {
        String mark = "X";
        
        if (!this.playsx) {
            mark = "O";
        }
        
        if (this.moves <= 1) {
            if (this.board.charAt(4) == '-') {
                this.board = this.board.substring(0, 4) + mark + this.board.substring(5, 9);
            } else {
                for (int i = 0; i < 9; i++) {
                    if (!(this.board.charAt(i) == '-')) {
                        if (i != 8) {
                            this.board = this.board.substring(0, i + 1) + mark + this.board.substring(i + 2, 9);
                            break;
                        }
                        
                        if (i == 8) {
                            this.board = this.board.substring(0, i - 1) + mark + this.board.substring(i, 9);
                            break; //
                        }
                    }
                }
            }
        } else {
        
            List<String> nextMoves = nextPossibleMoves(board, playsx);

            
            for (String nextMove : nextMoves) {
                System.out.println(nextMove);
            }
            
            
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

            /
            for (int i : nextMovesValues) {
                System.out.println(nextMovesValues[i]);
            }
            
            // !

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
        
        
        // !
        //System.out.println("Something wrong");
        
        }
        
    }
    */
    
    public void nextMove() {
        /*if (playsx) {
            System.out.println(max_value(this.board, -1, 1));
        } else {
            System.out.println(min_value(this.board, -1, 1));
        }*/
        
        List<String> nextMoves = nextPossibleMoves(board, playsx);
        
        /*for (String nextMove : nextMoves) {
                System.out.println(nextMove);
            }
        */

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
    }
    
    //Alpha-beta pruning
    
    public int max_value(String board, int alpha, int beta) {
        int winner = checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        if (boardCompleted(board)) {
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
        int winner = checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        
        if (boardCompleted(board)) {
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
    
    
    
    
    
    
    
    
    //MUOKKAA!
    public boolean boardCompleted() {
        return boardCompleted(this.board);
    }
    
    
    public boolean boardCompleted(String board) {
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-') {
                return false;
            }
        }
        
        return true;
    }
    
    
    
    
    
    
    
    
    
    public int checkBoardForWinner() {
        return checkBoardForWinner(this.board);
    }
    
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
    
    public String toString() {
        return this.board.substring(0, 3)
                + "\n" + this.board.substring(3, 6)
                + "\n" + this.board.substring(6, 9);
    }
    
}

