package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tictactoe.domain.ConnectFourAI;
import tictactoe.domain.TictactoeService;

public class ConnectFourPlayScreen {
    
    private ConnectFourAI connectFourAI;
    private TictactoeService tictactoeService;
    private Button[][] buttons;
    
    private ChooseDifficultyScreen chooseDifficultyScreen;
    private ChooseXorOScreen chooseXorOScreen;
    private LoggedInScreen loggedInScreen;
    
    private Label playModeLabel;
    private Button playAgainButton;
    private Button giveUpAndExitButton;
    private HBox gameplayPlayButtonsSetUp;
    private GridPane playSetUp;
    
    private Scene connectFourPlayscene;
    
    private Stage stage;
    
    public ConnectFourPlayScreen(ConnectFourAI connectFourAI, TictactoeService tictactoeService, Stage stage) {
        this.connectFourAI = connectFourAI;
        this.tictactoeService = tictactoeService;
        this.buttons = new Button[7][7];
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        this.playModeLabel = new Label("");
        playAgainButton = new Button("Play again");
        giveUpAndExitButton = new Button("Give up");
        this.gameplayPlayButtonsSetUp = new HBox();
        gameplayPlayButtonsSetUp.setSpacing(100);
        gameplayPlayButtonsSetUp.getChildren().addAll(giveUpAndExitButton);
        
        GridPane board = new GridPane();
        
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Button newButton = new Button("");
                newButton.setMinSize(70, 70);
                
                this.buttons[i][j] = newButton;
                board.add(newButton, i + 1, j + 1);
            }
        }
        
        this.playSetUp = new GridPane();
        
        playSetUp.add(playModeLabel, 0, 0);
        playSetUp.add(board, 0, 1);
        playSetUp.add(gameplayPlayButtonsSetUp, 0, 2);
        
        playSetUp.setAlignment(Pos.CENTER);
        playSetUp.setVgap(10);
        playSetUp.setHgap(10);
        playSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        this.connectFourPlayscene = new Scene(playSetUp);
    }
    
    public void setButtonActions() {
        setBoardButtonActions();
        setplayAgainButtonAction();
        setGiveUpAndExitButtonAction();
    }
    
    public void setplayAgainButtonAction() {
        this.playAgainButton.setOnAction((event) -> {
            
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                this.buttons[i][j].setText("");
            }
        }
        
        this.connectFourAI.setboardString(this.connectFourAI.createBoardString());
        
        playModeLabel.setText("");
        
        giveUpAndExitButton.setText("Give up");
        gameplayPlayButtonsSetUp.getChildren().removeAll(playAgainButton, giveUpAndExitButton);
        gameplayPlayButtonsSetUp.getChildren().addAll(giveUpAndExitButton);
        
        stage.setScene(this.chooseDifficultyScreen.getScene());
        
        return;
        });  
    }
    
    public void setGiveUpAndExitButtonAction() {
        this.giveUpAndExitButton.setOnAction((event) -> {
        
        if (this.connectFourAI.checkBoardForWinningPattern() == 0 && !this.connectFourAI.boardStringCompleted()) {
            this.tictactoeService.getLoggedInUser().increaseLosses();
            this.tictactoeService.updateUserToDatabase();
        }
        
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                this.buttons[i][j].setText("");
            }
        }
        
        this.connectFourAI.setboardString(this.connectFourAI.createBoardString());
        
        this.chooseXorOScreen.setPlaysConnectFour(false);
        
        playModeLabel.setText("");
        giveUpAndExitButton.setText("Give up");
        gameplayPlayButtonsSetUp.getChildren().removeAll(playAgainButton, giveUpAndExitButton);
        gameplayPlayButtonsSetUp.getChildren().addAll(giveUpAndExitButton);
        
        stage.setScene(this.loggedInScreen.getScene());
        
        return;
        });
    }
    
    public boolean buttonChoiceIsValid(Button button) {
        if (this.connectFourAI.checkBoardForWinningPattern() != 0 || this.connectFourAI.boardStringCompleted()) {
            return false;
        }
        
        if (!button.getText().equals("")) {
            this.playModeLabel.setText("Board position already played!");
            return false;
        }
        
        this.playModeLabel.setText("");
        
        int[] buttonCoordinates = getPressedBoardButtonCoordinates(button);
        
        if (buttonCoordinates[1] == 0) {
            return true;
        }

        String playedMoveUnderPlayerMove = this.connectFourAI.getboardString().substring(9 * (7 - buttonCoordinates[1] + 1) + buttonCoordinates[0] + 1, 9 * (7 - buttonCoordinates[1] + 1) + buttonCoordinates[0] + 2);
        
        if (playedMoveUnderPlayerMove.equals("-")) {
            this.playModeLabel.setText("You must play on the bottom of the board or on top of a played board position!");
            return false;
        }
        
        this.playModeLabel.setText("");
        
        return true;
    }
    
    public int[] getPressedBoardButtonCoordinates(Button button) {
        int[] buttonCoordinates = new int[2];
        
        int buttonXCoordinate = 0;
        int buttonYCoordinate = 0;

        for (int k = 0; k < 7; k++) {
            for (int l = 0; l < 7; l++) {
                if (this.buttons[k][l].equals(button)) {
                    buttonXCoordinate = k;
                    buttonYCoordinate = l;
                }
            }
        }
        
        buttonCoordinates[0] = buttonXCoordinate;
        buttonCoordinates[1] = 6 - buttonYCoordinate;

        return buttonCoordinates;
    }
    
    public String getBoardAfterPlayerMoves(String playerMark, int buttonXCoordinate, int buttonYCoordinate) {       
        String board = this.connectFourAI.getboardString();

        board = board.substring(0, 9 * (7 - buttonYCoordinate) + buttonXCoordinate + 1) + playerMark + board.substring(9 * (7 - buttonYCoordinate) + buttonXCoordinate + 2, 81);
  
        return board;
    }

    public void setGameAINextMove(String board, String aiMark) {
        String boardAfterAIMoves = this.connectFourAI.nextMove2();;

        for (int i = 0; i < 81; i++) {
            if (!board.subSequence(i, i + 1).equals(boardAfterAIMoves.subSequence(i, i + 1))) {
                this.buttons[i % 9 - 1][(i - i % 9) / 9 - 1].setText(aiMark);
            }
        }
    }
    
    public void setBoardButtonActions() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Button button = this.buttons[i][j];
                
                button.setOnAction((event) -> {
                
                if (!buttonChoiceIsValid(button)) {
                    return;
                }
                
                String playerMark = "X";
                String aiMark = "O";

                if (this.connectFourAI.getplaysx()) {
                        playerMark = "O";
                        aiMark = "X";
                    }
                
                button.setText(playerMark);
                
                int[] pressedButtonCoordinates = getPressedBoardButtonCoordinates(button);
                
                int buttonXCoordinate = pressedButtonCoordinates[0];
                int buttonYCoordinate = pressedButtonCoordinates[1];      
                               
                String boardAfterPlayerMoves = this.getBoardAfterPlayerMoves(playerMark, buttonXCoordinate, buttonYCoordinate);
                
                this.connectFourAI.setboardString(boardAfterPlayerMoves);
                
                setGameAINextMove(boardAfterPlayerMoves, aiMark);
                
                String gameResult = checkForGameFinishedAndWinner();
                
                if (gameResult.equals("win") || gameResult.equals("lose")) {
                    this.tictactoeService.updateUserToDatabase();
                }
                });
            }
        }
    }
    
    public String checkForGameFinishedAndWinner() {
        int winner = this.connectFourAI.checkBoardForWinningPattern();
        
        boolean aiPlaysX = this.connectFourAI.getplaysx();

        if ((winner > 0 && !aiPlaysX) || (winner < 0 && aiPlaysX)) {
            this.playModeLabel.setText("YOU WIN!"); //!!
            this.tictactoeService.getLoggedInUser().increaseWins();
            
            giveUpAndExitButton.setText("Exit");
            gameplayPlayButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
            gameplayPlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
            
            return "win";
        }

        if ((winner > 0 && aiPlaysX) || (winner < 0 && !aiPlaysX)) {
            this.playModeLabel.setText("YOU LOSE!");
            this.tictactoeService.getLoggedInUser().increaseLosses();
            
            giveUpAndExitButton.setText("Exit");
            gameplayPlayButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
            gameplayPlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
            
            return "lose";
        }

        if (this.connectFourAI.boardStringCompleted()) {
            playModeLabel.setText("DRAW!");                 
            
            giveUpAndExitButton.setText("Exit");
            gameplayPlayButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
            gameplayPlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
            
            return "draw";
        }

        return "pending";
    }
    
    public void setFirstAIMoveGameplayButtons() {  
        String board = this.connectFourAI.getboardString();
        
        for (int i = 0; i < 7; i++) {
            if (board.charAt(64 + i) != '-') {
                this.buttons[i][6].setText("X");
            }
        }
    }
    
    public void setLoggedInScreen(LoggedInScreen loggedInScreen) {
        this.loggedInScreen = loggedInScreen;
    }
    
    public void setChooseDifficultyScreen(ChooseDifficultyScreen chooseDifficultyScreen) {
        this.chooseDifficultyScreen = chooseDifficultyScreen;
    }
    
    public void setChooseXorOScreen(ChooseXorOScreen chooseXorOScreen) {
        this.chooseXorOScreen = chooseXorOScreen;
    }
    
    public Scene getScene() {
        return this.connectFourPlayscene;
    }
}