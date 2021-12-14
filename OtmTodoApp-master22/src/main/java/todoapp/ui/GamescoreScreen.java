package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GamescoreScreen {
    private Label gameScoreEntryText;
    private Button backFromGameScoreButton;
    
    private Scene loggedInScene;
    
    private Stage stage;
    
    public GamescoreScreen(Stage stage) {
        gameScoreEntryText = new Label("");
        backFromGameScoreButton = new Button("Back");
        
        this.stage = stage;
    }
    
    public Parent getGamescoreScreenSetUp() {
        //Label
        /*
        gameScoreEntryText = new Label("");
        backFromGameScoreButton = new Button("Back");
        */
        GridPane gameScoreSetUp = new GridPane();        
        
        gameScoreSetUp.add(gameScoreEntryText, 0, 0);
        gameScoreSetUp.add(backFromGameScoreButton, 0, 2);
        
        gameScoreSetUp.setPrefSize(300, 180); // CHANGE
        gameScoreSetUp.setAlignment(Pos.CENTER);
        gameScoreSetUp.setVgap(10);
        gameScoreSetUp.setHgap(10);
        gameScoreSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        //Scene gameScoreScene = new Scene(gameScoreSetUp);
        
        //setButtonActions();
        
        return gameScoreSetUp;
    }
    
    public void setButtonActions() {
        //setCheckGameScoreButtonAction();
        setBackFromGameScoreButtonAction();
    }
    /* wrong class
    public void setCheckGameScoreButtonAction() {
        checkGameScoreButton.setOnAction((event) -> {
        
        int wins = this.tictactoeService.getLoggedInUser().getWins();
        int losses = this.tictactoeService.getLoggedInUser().getLosses();
        
        gameScoreEntryText.setText("GAME SCORE:\n\nWins: " + wins + "\t\tLosses: " + losses);
            
        stage.setScene(gameScoreScene);
        });
    }
    */
    public void setBackFromGameScoreButtonAction() {
        backFromGameScoreButton.setOnAction((event) -> {
        
        stage.setScene(loggedInScene);
        });
    }
    
    public void setLoggedInScene(Scene loggedInScene) {
        this.loggedInScene = loggedInScene;
    }
    
    public Label getGameScoreEntryText() {
        return this.gameScoreEntryText;
    }
}
