public class Tictactoe {

    public static void main(String[] args) {
        GameTree gametree = new GameTree();
        
        Board board = gametree.createGameTree();
        
        System.out.println(board.getValue());
        
        System.out.println("--");
        
        for (Board nextBoard : board.getNextBoards()) {
            System.out.print("Child 1:\nValue: ");
            System.out.println(nextBoard.getValue());
            System.out.println("");
            System.out.println(nextBoard);
            System.out.println("\n--\n");
            
            for (Board nextnextBoard : nextBoard.getNextBoards()) {
                System.out.print("Child 2:\nValue: ");
                System.out.println(nextnextBoard.getValue());
                System.out.println("");
                System.out.println(nextnextBoard);
                System.out.println("\n--\n");
            }
        }
    }
    
}
