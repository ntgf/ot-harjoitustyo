package Tictactoe;

import java.util.ArrayList;
import java.util.List;


public class GameTree {
    
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
                nextMoves.add(board.substring(0, i) + mark + board.substring(i+1, board.length()));
            }
        }
        
        return nextMoves;
    }
    
    
}
