package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.TictactoeService;

public class PlayScreen {
    
    private TictactoeAI tictactoeAI;
    private TictactoeService tictactoeService;
    
    private LoggedInScreen loggedInScreen;
    private ChooseDifficultyScreen chooseDifficultyScreen;
    private ChooseXorOScreen chooseXorOScreen;
    
    private Button[][] buttons;
    private Label playModeLabel;
    private Button playAgainButton;
    private Button giveUpAndExitButton;
    private HBox gameplayPlayButtonsSetUp;
    private HBox afterGamePlayButtonsSetUp;
    private GridPane playSetUp;
    
    private Scene playScene;
    private Stage stage;
    
    public PlayScreen(TictactoeAI tictactoeAI, TictactoeService tictactoeService, Stage stage) {
        this.tictactoeAI = tictactoeAI;
        this.tictactoeService = tictactoeService;
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
        this.afterGamePlayButtonsSetUp = new HBox();
        afterGamePlayButtonsSetUp.setSpacing(100);
        afterGamePlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);       
        
        this.buttons = new Button[3][3];
        
        GridPane board = new GridPane();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
        
        playSetUp.setPrefSize(300, 180);
        playSetUp.setAlignment(Pos.CENTER);
        playSetUp.setVgap(10);
        playSetUp.setHgap(10);
        playSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        playScene = new Scene(playSetUp);
    }
    
    public void setButtonActions() {
        setBoardButtonActions();
        setplayAgainButtonAction();
        setGiveUpAndExitButtonAction();
    }
    
    public void setplayAgainButtonAction() {
        this.playAgainButton.setOnAction((event) -> {
            
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j].setText("");
            }
        }
        
        this.tictactoeAI.setboard("---------");
        
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
            
        if (!(this.tictactoeAI.checkBoardForWinner() != 0 || this.tictactoeAI.boardCompleted())) {
            this.tictactoeService.getLoggedInUser().increaseLosses();
            this.tictactoeService.updateUserToDatabase();
        }
            
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j].setText("");
            }
        }
        
        this.tictactoeAI.setboard("---------");
        
        this.chooseXorOScreen.setPlays3x3Tictactoe(false);
        
        playModeLabel.setText("");
        giveUpAndExitButton.setText("Give up");
        gameplayPlayButtonsSetUp.getChildren().removeAll(playAgainButton, giveUpAndExitButton);
        gameplayPlayButtonsSetUp.getChildren().addAll(giveUpAndExitButton);
        
        stage.setScene(this.loggedInScreen.getScene());
        
        return;
        });
    }
    
    public boolean buttonChoiceIsValid(Button button) {
        if (this.tictactoeAI.checkBoardForWinner() != 0 || this.tictactoeAI.boardCompleted()) {
            return false;
        }

        if (!button.getText().equals("")) {
            return false;
        }
        
        return true;
    }
    
    public int[] getPressedBoardButtonCoordinates(Button button) {
        int[] buttonCoordinates = new int[2];
        
        int buttonXCoordinate = 0;
        int buttonYCoordinate = 0;

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (this.buttons[k][l].equals(button)) {
                    buttonXCoordinate = k;
                    buttonYCoordinate = l;
                }
            }
        }
        
        buttonCoordinates[0] = buttonXCoordinate;
        buttonCoordinates[1] = buttonYCoordinate;
        
        return buttonCoordinates;
    }
    
    public String getStringBoardAfterPlayerMoves(String playerMark, int buttonXCoordinate, int buttonYCoordinate) {
        String tictactoeBoard = this.tictactoeAI.getboard();
        
        int playerMoveStringBoardIndex = 3 * buttonYCoordinate + buttonXCoordinate;

        String tictactoeBoardAfterPlayerMoves = tictactoeBoard.substring(0, playerMoveStringBoardIndex) +
                playerMark + tictactoeBoard.substring(playerMoveStringBoardIndex + 1, 9);
        
        return tictactoeBoardAfterPlayerMoves;
    }
    
    public void setTictactoeAINextMove(String board, String aiMark) {
        String tictactoeBoardAfterAIMoves = this.tictactoeAI.nextMove();
        
        int tictactoeAIMoveIndex = -1;

        for (int k = 0; k < 9; k++) {
            if (board.charAt(k) != tictactoeBoardAfterAIMoves.charAt(k)) {
                tictactoeAIMoveIndex = k;
            }
        }

        if (tictactoeAIMoveIndex >= 0) {
            this.buttons[tictactoeAIMoveIndex % 3][(tictactoeAIMoveIndex - tictactoeAIMoveIndex % 3) / 3].setText(aiMark);
        }
    }
    
    public void setBoardButtonActions() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = this.buttons[i][j];
                
                button.setOnAction((event) -> {
                
                if (!buttonChoiceIsValid(button)) {
                    return;
                }
                
                String playerMark = "X";
                String aiMark = "O";

                if (this.tictactoeAI.getplaysx()) {
                        playerMark = "O";
                        aiMark = "X";
                    }
                
                button.setText(playerMark);
                
                int[] pressedButtonCoordinates = getPressedBoardButtonCoordinates(button);
                
                int buttonXCoordinate = pressedButtonCoordinates[0];
                int buttonYCoordinate = pressedButtonCoordinates[1];              
                
                String tictactoeBoardAfterPlayerMoves = getStringBoardAfterPlayerMoves(playerMark, buttonXCoordinate, buttonYCoordinate);
                
                this.tictactoeAI.setboard(tictactoeBoardAfterPlayerMoves);
                
                setTictactoeAINextMove(tictactoeBoardAfterPlayerMoves, aiMark);
                
                String gameResult = checkForGameFinishedAndWinner();
                
                if (gameResult.equals("win") || gameResult.equals("lose")) {
                    this.tictactoeService.updateUserToDatabase();
                }
                });
            }
        }
    }
            
    public String checkForGameFinishedAndWinner() {
        int winner = this.tictactoeAI.checkBoardForWinner();
        boolean aiPlaysX = this.tictactoeAI.getplaysx();

        if ((winner == 1 && !aiPlaysX) || (winner == -1 && aiPlaysX)) {
            this.playModeLabel.setText("YOU WIN!");
            this.tictactoeService.getLoggedInUser().increaseWins();
            
            giveUpAndExitButton.setText("Exit");
            gameplayPlayButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
            gameplayPlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
            
            return "win";
        }

        if ((winner == 1 && aiPlaysX) || (winner == -1 && !aiPlaysX)) {
            this.playModeLabel.setText("YOU LOSE!");
            this.tictactoeService.getLoggedInUser().increaseLosses();
            
            giveUpAndExitButton.setText("Exit");
            gameplayPlayButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
            gameplayPlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
            
            return "lose";
        }

        if (this.tictactoeAI.boardCompleted()) {
            playModeLabel.setText("DRAW!");                 
            
            giveUpAndExitButton.setText("Exit");
            gameplayPlayButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
            gameplayPlayButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
            
            return "draw";
        }

        return "pending";
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
        return this.playScene;
    }
    
    public Button[][] getButtons() {
        return this.buttons;
    }
}