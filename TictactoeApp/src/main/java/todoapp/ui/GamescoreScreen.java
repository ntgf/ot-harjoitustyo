package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GamescoreScreen {
    private LoggedInScreen loggedInScreen;
    
    private Label gameScoreEntryText;
    private Button backFromGameScoreButton;
    
    private Scene gamescoreScene;
    private Stage stage;
    
    public GamescoreScreen(Stage stage) {
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        gameScoreEntryText = new Label("");
        backFromGameScoreButton = new Button("Back");
        
        GridPane gameScoreSetUp = new GridPane();        
        
        gameScoreSetUp.add(gameScoreEntryText, 0, 0);
        gameScoreSetUp.add(backFromGameScoreButton, 0, 2);
        
        gameScoreSetUp.setPrefSize(300, 180);
        gameScoreSetUp.setAlignment(Pos.CENTER);
        gameScoreSetUp.setVgap(10);
        gameScoreSetUp.setHgap(10);
        gameScoreSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        this.gamescoreScene = new Scene(gameScoreSetUp);
    }
    
    public void setButtonActions() {
        setBackFromGameScoreButtonAction();
    }
    
    public void setBackFromGameScoreButtonAction() {
        backFromGameScoreButton.setOnAction((event) -> {
        
        stage.setScene(this.loggedInScreen.getScene());
        });
    }
    
    public void setLoggedInScreen(LoggedInScreen loggedInScreen) {
        this.loggedInScreen = loggedInScreen;
    }
    
    public Label getGameScoreEntryText() {
        return this.gameScoreEntryText;
    }
    
    public Scene getScene() {
        return this.gamescoreScene;
    }
}