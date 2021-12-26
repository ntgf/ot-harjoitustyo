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
import tictactoe.domain.TictactoeAI;

public class ChooseDifficultyScreen {
    
    private TictactoeAI tictactoeAI;
    private ConnectFourAI connectFourAI;
    
    private ChooseXorOScreen chooseXorOScreen;
    
    private boolean plays3x3Tictactoe;
    private boolean playsConnectFour;
    
    private Label chooseDifficultyText;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;

    private Scene chooseDifficultyScene;
    private Stage stage;
    
    public ChooseDifficultyScreen(TictactoeAI tictactoeAI, ConnectFourAI connectFourAI, Stage stage) {
        this.tictactoeAI = tictactoeAI;
        this.connectFourAI = connectFourAI;
        plays3x3Tictactoe = false;
        playsConnectFour = false;
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        chooseDifficultyText = new Label("Choose difficulty.");
        
        easyButton = new Button("Easy");
        mediumButton = new Button("Medium");
        hardButton = new Button("Hard");
        
        HBox difficultyButtonsSetUp = new HBox();
        difficultyButtonsSetUp.setSpacing(10);
        difficultyButtonsSetUp.getChildren().addAll(easyButton, mediumButton, hardButton);
        
        GridPane chooseDifficultySetUp = new GridPane();        
        
        chooseDifficultySetUp.add(chooseDifficultyText, 0, 0);
        chooseDifficultySetUp.add(difficultyButtonsSetUp, 0, 1);
        
        chooseDifficultySetUp.setPrefSize(300, 180);
        chooseDifficultySetUp.setAlignment(Pos.CENTER);
        chooseDifficultySetUp.setVgap(10);
        chooseDifficultySetUp.setHgap(10);
        chooseDifficultySetUp.setPadding(new Insets(20, 20, 20, 20));
        
        chooseDifficultyScene = new Scene(chooseDifficultySetUp);
    }
    
    public void setChooseXOrOScreen(ChooseXorOScreen chooseXorOScreen) {
        this.chooseXorOScreen = chooseXorOScreen;
    }
    
    public void setButtonActions() {  
        setEasyButtonAction();
        setMediumButtonAction();
        setHardButtonAction();
    }
    
    public void setEasyButtonAction() {
        easyButton.setOnAction((event) -> {
        
        setGameAIDifficulty(1);
        });
    }
    
    public void setMediumButtonAction() {
        mediumButton.setOnAction((event) -> {
        
        setGameAIDifficulty(2);
        });
    }
    
    public void setHardButtonAction() {
        hardButton.setOnAction((event) -> {
        
        setGameAIDifficulty(3);
        });
    }
    
    public void setGameAIDifficulty(int difficulty) {
        if (this.plays3x3Tictactoe) {
            this.tictactoeAI.setdifficulty(difficulty);

            stage.setScene(chooseXorOScreen.getScene());
        }

        if (this.playsConnectFour) {
            this.connectFourAI.setdifficulty(difficulty);

            stage.setScene(chooseXorOScreen.getScene());
        }
    }
    
    public Scene getScene() {
        return this.chooseDifficultyScene;
    }
    
    public void setPlays3x3Tictactoe(boolean plays3x3Tictactoe) {
        this.plays3x3Tictactoe = plays3x3Tictactoe;
    }
    
    public void setPlaysConnectFour(boolean playsConnectFour) {
        this.playsConnectFour = playsConnectFour;
    }
}