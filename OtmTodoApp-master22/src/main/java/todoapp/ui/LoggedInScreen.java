package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.domain.TictactoeService;

public class LoggedInScreen {
    private TictactoeService tictactoeService;
    
    private GamescoreScreen gamescoreScreen;
    private LoginScreen loginScreen;
    
    private Label loggedInText;
    private Button playButton;
    private Button checkGameScoreButton;
    private Button logoutButton;
    
    private Button messagesButton;
    
    //private Scene createNewUserScene;
    //private Scene loggedInScene;
    private Scene gamescoreScene;
    private Scene loginScene;
    private Scene chooseXorOScene;
    
    private Scene messagesScene;
    
    private Stage stage;
    
    public LoggedInScreen(TictactoeService tictactoeService,Stage stage) {
        this.tictactoeService = tictactoeService;
        this.stage = stage;
        
        loggedInText = new Label("");
        playButton = new Button("Play");
        checkGameScoreButton = new Button("Check Gamescore");
        
        messagesButton = new Button("Messages");
        
        logoutButton = new Button("Logout");
    }
    
    public Parent getLoggedInSetUp() {
        /*
        Label loggedInText = new Label("");
        Button playButton = new Button("Play");
        Button checkGameScoreButton = new Button("Check Gamescore");
        Button logoutButton = new Button("Logout");
        */
        GridPane loggedInSetUp = new GridPane();

        loggedInSetUp.add(loggedInText, 0, 0);
        loggedInSetUp.add(playButton, 0, 1);
        loggedInSetUp.add(checkGameScoreButton, 0, 2);
        
        loggedInSetUp.add(this.messagesButton, 0, 3);
        
        loggedInSetUp.add(logoutButton, 0, 4);
        
        loggedInSetUp.setPrefSize(300, 180); // CHANGE
        loggedInSetUp.setAlignment(Pos.CENTER);
        loggedInSetUp.setVgap(10);
        loggedInSetUp.setHgap(10);
        loggedInSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        //Scene loggedInScene = new Scene(loggedInSetUp);
        //setButtonActions();
        
        return loggedInSetUp;
    }
    
    public Label getLoggedInText() {
        return this.loggedInText;
    }
    
    public void setLoginScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }
    
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }
    
    
    
    
    public void setMessagesScene(Scene messagesScene) {
        this.messagesScene = messagesScene;
    }
    
    
    
    
    public void setButtonActions() {
        setPlayButtonAction();
        setCheckGameScoreButtonAction();
        
        setMessagesButtonAction();
        
        setLogoutButtonAction();
    }
    
    public void setPlayButtonAction() {
        playButton.setOnAction((event) -> {
        
        stage.setScene(chooseXorOScene);
        });
    }
    
    public void setCheckGameScoreButtonAction() {
        checkGameScoreButton.setOnAction((event) -> {
        
        int wins = this.tictactoeService.getLoggedInUser().getWins();
        int losses = this.tictactoeService.getLoggedInUser().getLosses();
        
        this.gamescoreScreen.getGameScoreEntryText().setText("GAME SCORE:\n\nWins: " + wins + "\t\tLosses: " + losses);
        
        //gameScoreEntryText.setText("GAME SCORE:\n\nWins: " + wins + "\t\tLosses: " + losses);
            
        stage.setScene(gamescoreScene);
        });
    }
    
    
    
    
    
    public void setMessagesButtonAction() {
        messagesButton.setOnAction((event) -> {
            stage.setScene(messagesScene);
            });
    }
    
    
    
    
    
    public void setLogoutButtonAction() {
        logoutButton.setOnAction((event) -> {
            
        this.tictactoeService.logOut();
        
        this.loginScreen.getUsernameTextField().clear();
        this.loginScreen.getLoginErrorText().setText("Successfully logged out!\nEnter username to login.");
         
        //usernameTextField.clear(); //OK?
        //loginErrorText.setText("Successfully logged out!\nEnter username to login.");
        
        stage.setScene(loginScene);
        });
    }
    
    public void setChooseXorOScene(Scene chooseXorOScene) {
        this.chooseXorOScene = chooseXorOScene;
    }
    
    public void setGamescoreScreen(GamescoreScreen gamescoreScreen) {
        this.gamescoreScreen = gamescoreScreen;
    }
    
    public void setGamescoreScene(Scene gamescoreScene) {
        this.gamescoreScene = gamescoreScene;
    }
}
