import java.util.ArrayList;
import java.util.List;


public class GameTree {
    
    private Board board;
    private BoardChecker boardchecker;
    
    public GameTree() {
        this.boardchecker = new BoardChecker();
    }
    
    public GameTree(Board board) {
        this.board = board;
        this.boardchecker = new BoardChecker();
    }
    
    
    
    
    //void?
    public Board createGameTree() {
        //name ok?
        String boardString = "---------";
        
        Board board = new Board(boardString, null /*, null*/);
        
        //
        int rootValue = max_value(board);
        
        board.setValue(rootValue);
        
        return board;
    }
    
    
    public List<Board> nextPossibleMoves(Board board, boolean playsx) {
        //Case if Completed
        
        //ArrayList ok?
        List<Board> nextMoves = new ArrayList<Board>();
        
        String mark = "X";
        
        if (!playsx) {
            mark = "O";
        }
        
        String boardString = board.getBoard();
        
        //Case if completed?
        
        for (int i = 0; i < 9; i++) {
            if (boardString.charAt(i) == '-') {
                //Indexes ok?
                nextMoves.add(new Board(boardString.substring(0, i) + mark + boardString.substring(i+1, boardString.length()), board));
            }
        }
        
        return nextMoves;
    }
    
    
    //MinMax algorithm:
    
    public int max_value(Board board /*, int alpha, int beta*/) {
        //board.getBoard()??
        int winner = boardchecker.checkBoardForWinner(board.getBoard());
        
        if (winner != 0) {
            return winner;
        }
        //board.getBoard()??
        if (boardchecker.boardCompleted(board.getBoard())) {
            return winner;
        }
        
        int v = -1;
        
        List<Board> nextPossibleMoves = nextPossibleMoves(board, true);
        
        for (Board nextBoard: nextPossibleMoves) {
            int nextBoardValue = min_value(nextBoard);
            
            nextBoard.setValue(nextBoardValue);
            
            v = Math.max(v, nextBoardValue /*min_value(nextBoard /*, alpha, beta)*/);
            
            /*
            alpha = Math.max(alpha, v);
            
            if (alpha >= beta) {
                return v;
            }
            */
        }
        
        board.setNextBoards(nextPossibleMoves);
        
        return v;
    }
    
    public int min_value(Board board /*, int alpha, int beta*/) {
        //board.getBoard()??
        int winner = boardchecker.checkBoardForWinner(board.getBoard());
        
        if (winner != 0) {
            return winner;
        }
        //board.getBoard()??
        if (boardchecker.boardCompleted(board.getBoard())) {
            return winner;
        }
        
        int v = 1;
        
        /*
        for (String nextBoard: nextPossibleMoves(board, false)) {
            v = Math.min(v, max_value(nextBoard, alpha, beta));
            beta = Math.min(beta, v);
            
            if (alpha >= beta) {
                return v;
            }
        }
        */
        
        List<Board> nextPossibleMoves = nextPossibleMoves(board, false);
        
        for (Board nextBoard: nextPossibleMoves) {
            int nextBoardValue = max_value(nextBoard);
            
            nextBoard.setValue(nextBoardValue);
            
            v = Math.min(v, nextBoardValue /*max_value(nextBoard /*, alpha, beta)*/);
            
            /*
            beta = Math.min(beta, v);
            
            if (alpha >= beta) {
                return v;
            }
            */
        }
        
        board.setNextBoards(nextPossibleMoves);
        
        return v;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public void nextMove() {
        /*if (playsx) {
            System.out.println(max_value(this.board, -1, 1));
        } else {
            System.out.println(min_value(this.board, -1, 1));
        }*
        
        List<String> nextMoves = nextPossibleMoves(board, playsx);
        
        /*for (String nextMove : nextMoves) {
                System.out.println(nextMove);
            }
        *

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
    */
    
    
    
    
    
    
    /*
    
    //Alpha-beta pruning
    
    public int max_value(String board, int alpha, int beta) {
        int winner = boardchecker.checkBoardForWinner(board);
        
        if (winner != 0) {
            return winner;
        }
        //
        if (boardchecker.boardCompleted(board)) {
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
    
    */
    
    
    
    
    
    /*
    
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
    */
    
}
