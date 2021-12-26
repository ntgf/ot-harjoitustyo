package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.domain.TictactoeService;

public class LoggedInScreen {
    private TictactoeService tictactoeService;
    
    private ChooseDifficultyScreen chooseDifficultyScreen;
    private ChooseXorOScreen chooseXorOScreen;
    private GamescoreScreen gamescoreScreen;
    private LoginScreen loginScreen;
    private MessageScreen messageScreen;
    
    private Label loggedInText;
    private Button playButton;
    private Button connectFourPlayButton;
    private Button checkGameScoreButton;
    private Button logoutButton;
    
    private Button messagesButton;
    
    private Scene loggedInScene;
    private Stage stage;
    
    public LoggedInScreen(TictactoeService tictactoeService,Stage stage) {
        this.tictactoeService = tictactoeService;
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        loggedInText = new Label("");
        playButton = new Button("Play Tic-tac-toe");
        connectFourPlayButton = new Button("Play Connect Four");
        checkGameScoreButton = new Button("Check Gamescore");
        messagesButton = new Button("Messages");
        logoutButton = new Button("Logout");
        
        GridPane loggedInSetUp = new GridPane();

        loggedInSetUp.add(loggedInText, 0, 0);
        loggedInSetUp.add(playButton, 0, 1);
        loggedInSetUp.add(connectFourPlayButton, 0, 2);
        loggedInSetUp.add(checkGameScoreButton, 0, 3);
        loggedInSetUp.add(this.messagesButton, 0, 4);
        loggedInSetUp.add(logoutButton, 0, 5);
        
        loggedInSetUp.setPrefSize(300, 180);
        loggedInSetUp.setAlignment(Pos.CENTER);
        loggedInSetUp.setVgap(10);
        loggedInSetUp.setHgap(10);
        loggedInSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        this.loggedInScene = new Scene(loggedInSetUp);
    }
    
    public Label getLoggedInText() {
        return this.loggedInText;
    }
    
    public void setLoginScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }
    
    public void setButtonActions() {
        setPlayButtonAction();
        setConnectFourPlayButtonAction();
        setCheckGameScoreButtonAction();
        setMessagesButtonAction();
        setLogoutButtonAction();
    }
    
    public void setPlayButtonAction() {
        playButton.setOnAction((event) -> {
        
        this.chooseDifficultyScreen.setPlays3x3Tictactoe(true);
        this.chooseXorOScreen.setPlays3x3Tictactoe(true);
        
        stage.setScene(this.chooseDifficultyScreen.getScene());
        });
    }
    
    public void setConnectFourPlayButtonAction() {
        connectFourPlayButton.setOnAction((event) -> {
        
        this.chooseDifficultyScreen.setPlays3x3Tictactoe(true);
        this.chooseXorOScreen.setPlaysConnectFour(true);
        
        stage.setScene(this.chooseDifficultyScreen.getScene());
        });
    }
    
    public void setCheckGameScoreButtonAction() {
        checkGameScoreButton.setOnAction((event) -> {
        
        int wins = this.tictactoeService.getLoggedInUser().getWins();
        int losses = this.tictactoeService.getLoggedInUser().getLosses();
        
        this.gamescoreScreen.getGameScoreEntryText().setText("GAME SCORE:\n\nWins: " + wins + "\t\tLosses: " + losses);
        
        stage.setScene(this.gamescoreScreen.getScene());
        });
    }
    
    public void setMessagesButtonAction() {
        messagesButton.setOnAction((event) -> {

        stage.setScene(this.messageScreen.getScene());
        });
    }
    
    public void setLogoutButtonAction() {
        logoutButton.setOnAction((event) -> {
            
        this.tictactoeService.logOut();
        
        this.loginScreen.getUsernameTextField().clear();
        this.loginScreen.getLoginErrorText().setText("Successfully logged out!\nEnter username to login.");
        
        stage.setScene(this.loginScreen.getScene());
        });
    }
    
    public void setChooseDifficultyScreen(ChooseDifficultyScreen chooseDifficultyScreen) {
        this.chooseDifficultyScreen = chooseDifficultyScreen;
    }
    
    public void setChooseXorOScreen(ChooseXorOScreen chooseXorOScreen) {
        this.chooseXorOScreen = chooseXorOScreen;
    }
    
    public void setGamescoreScreen(GamescoreScreen gamescoreScreen) {
        this.gamescoreScreen = gamescoreScreen;
    }
    
    public void setMessageScreen(MessageScreen messageScreen) {
        this.messageScreen = messageScreen;
    }
    
    public Scene getScene() {
        return this.loggedInScene;
    }
}