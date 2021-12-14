package todoapp.ui;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tictactoe.dao.FileUserDao;
import tictactoe.dao.UserDao;
import tictactoe.dao.FileMessageDao;
import tictactoe.dao.MessageDao;
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.User;
import java.util.Random;
import javafx.scene.Parent;

public class TodoUi extends Application {
    
    private TictactoeAI tictactoeAI;
    private TictactoeService tictactoeService;
    private Button[][] buttons;
    
    private LoginScreen loginScreen;
    private CreateNewUserScreen createNewUserScreen;
    private LoggedInScreen loggedInScreen;
    private ChooseXorOScreen chooseXorOScreen;
    private GamescoreScreen gamescoreScreen;
    private PlayScreen playScreen;
    
    //private MessageScreen messageScreen;
    
    private Scene loginScene;
    private Scene createNewUserScene;
    private Scene loggedInScene;
    private Scene pickXorOScene;
    private Scene playScene;
    private Scene gameScoreScene;
    
    private Scene messageScene;
    
    //private Stage stage; //needed? initiate

    @Override
    public void init() throws Exception {
        this.tictactoeAI = new TictactoeAI("---------", true);
        this.buttons = new Button[3][3];
        
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String file = properties.getProperty("userFile");
        
        UserDao userDao = new FileUserDao(file);
        
        //String messageFile = properties.getProperty("messageFle");
        
        //MessageDao messageDao = new FileMessageDao(messageFile);
        
        this.tictactoeService = new TictactoeService(userDao /*, messageDao*/);
        
        //this.tictactoeService = new TictactoeService(userDao, messageDao);
    }
    
    public Scene getloginScene() {
        return this.loginScene;
    }
    public Scene getcreateNewUserScene() {
        return this.createNewUserScene;
    }
    public Scene getloggedInScene() {
        return this.loggedInScene;
    }
    public Scene getpickXorOScene() {
        return this.pickXorOScene;
    }
    public Scene getplayScene() {
        return this.playScene;
    }
    public Scene getgameScoreScene() {
        return this.gameScoreScene;
    }
    /*
    public Stage getStage() {
        return this.stage;
    }*/
    
    public void initScreens(Stage stage) {
        this.initLoginScreen(stage);
        this.initCreateNewUserScreen(stage);
        this.initLoggedInScreen(stage);
        this.initChooseXorOScreen(stage);
        this.initPlayScreen(stage);
        this.initGamescoreScreen(stage);
        //this.initMessageScreen(stage);
    }
    
    public void initLoginScreen(Stage stage) {
        loginScreen = new LoginScreen(tictactoeService, stage);
        
        Parent loginScreenSetUp = loginScreen.getLoginSetUp();
        
        loginScene = new Scene(loginScreenSetUp);
    }
    
    public void initCreateNewUserScreen(Stage stage) {
        createNewUserScreen = new CreateNewUserScreen(tictactoeService, loginScreen, loginScene, stage);
        
        Parent createNewUserScreenSetUp = createNewUserScreen.getCreateNewUserSetUp();
        
        createNewUserScene = new Scene(createNewUserScreenSetUp);
        
        loginScreen.setCreateNewUserScene(createNewUserScene);
    }
    
    public void initLoggedInScreen(Stage stage) {
        loggedInScreen = new LoggedInScreen(tictactoeService, stage);
        
        Parent loggedInScreenSetUp = loggedInScreen.getLoggedInSetUp();
        
        loggedInScene = new Scene(loggedInScreenSetUp);
        
        //stage.setScene(loggedInScene);
        loginScreen.setLoggedInScreen(loggedInScreen); // Ok?
        loginScreen.setLoggedInScene(loggedInScene); //!! OK?
        loggedInScreen.setLoginScreen(loginScreen);
        loggedInScreen.setLoginScene(loginScene);
    }
    
    public void initChooseXorOScreen(Stage stage) {
        this.chooseXorOScreen = new ChooseXorOScreen(tictactoeAI, buttons, stage);
        
        Parent chooseXorOScreenSetUp = chooseXorOScreen.getChooseXorOSetUp();
        
        pickXorOScene = new Scene(chooseXorOScreenSetUp); //RENAME PICK
        this.loggedInScreen.setChooseXorOScene(pickXorOScene);
    }
    
    public void initPlayScreen(Stage stage) {
        playScreen = new PlayScreen(tictactoeAI, tictactoeService, buttons, loggedInScene, pickXorOScene, stage);
        
        Parent playScreenSetUp = playScreen.getPlayScreenSetUp();
        
        playScene = new Scene(playScreenSetUp);
        chooseXorOScreen.setPlayScene(playScene);
    }
    
    public void initGamescoreScreen(Stage stage) {
        gamescoreScreen = new GamescoreScreen(stage);
        
        Parent gamescoreScreenSetUp = gamescoreScreen.getGamescoreScreenSetUp();
        
        gameScoreScene = new Scene(gamescoreScreenSetUp);
        
        this.gamescoreScreen.setLoggedInScene(loggedInScene);
        
        this.loggedInScreen.setGamescoreScreen(gamescoreScreen);
        this.loggedInScreen.setGamescoreScene(gameScoreScene);
    }
    
//    public void initMessageScreen(Stage stage) {
//        messageScreen = new MessageScreen(tictactoeService, stage);
//        
//        Parent messageScreenSetUp = messageScreen.getMessageScreenSetUp();
//        
//        messageScene = new Scene(messageScreenSetUp);
//        
//        this.messageScreen.setLoggedInScene(loggedInScene);
//    }
            
    @Override
    public void start(Stage stage) {
        //this.stage = stage; //needed?
        
        initScreens(stage);
        /*
        // Login screen:
        loginScreen = new LoginScreen(tictactoeService, stage);
        
        Parent loginScreenSetUp = loginScreen.getLoginSetUp();
        
        loginScene = new Scene(loginScreenSetUp);
        */
        /*
        //Create new user screen:
        createNewUserScreen = new CreateNewUserScreen(tictactoeService, loginScreen, loginScene, stage);
        
        Parent createNewUserScreenSetUp = createNewUserScreen.getCreateNewUserSetUp();
        
        createNewUserScene = new Scene(createNewUserScreenSetUp);
        
        loginScreen.setCreateNewUserScene(createNewUserScene); //!! OK?
        */
        
        /*
        //Logged in screen:
        loggedInScreen = new LoggedInScreen(tictactoeService, stage);
        
        Parent loggedInScreenSetUp = loggedInScreen.getLoggedInSetUp();
        
        loggedInScene = new Scene(loggedInScreenSetUp);
        
        //stage.setScene(loggedInScene);
        loginScreen.setLoggedInScreen(loggedInScreen); // Ok?
        loginScreen.setLoggedInScene(loggedInScene); //!! OK?
        loggedInScreen.setLoginScreen(loginScreen);
        loggedInScreen.setLoginScene(loginScene);
        */
        
        /*
        //Pick x or o screen:
        this.chooseXorOScreen = new ChooseXorOScreen(tictactoeAI, buttons, stage);
        
        Parent chooseXorOScreenSetUp = chooseXorOScreen.getChooseXorOSetUp();
        
        pickXorOScene = new Scene(chooseXorOScreenSetUp); //RENAME PICK
        this.loggedInScreen.setChooseXorOScene(pickXorOScene);
        */
        
        //PlayScreen 
//        /*
//        playScreen = new PlayScreen(tictactoeAI, tictactoeService, buttons, loggedInScene, pickXorOScene, stage);
//        
//        Parent playScreenSetUp = playScreen.getPlayScreenSetUp();
//        
//        /*Scene*/ playScene = new Scene(playScreenSetUp);
//        chooseXorOScreen.setPlayScene(playScene); //!! OK?
//        
        
//        //GamescoreScreen 
//        gamescoreScreen = new GamescoreScreen(stage);
//        
//        Parent gamescoreScreenSetUp = gamescoreScreen.getGamescoreScreenSetUp();
//        
//        gameScoreScene = new Scene(gamescoreScreenSetUp);
//        
//        this.gamescoreScreen.setLoggedInScene(loggedInScene);
//        
//        this.loggedInScreen.setGamescoreScreen(gamescoreScreen);
//        this.loggedInScreen.setGamescoreScene(gameScoreScene);
        
        
        setButtonActions();
        
        stage.setScene(loginScene);
        
        stage.setTitle("TicTacToe");
        stage.show();
    }
    
    //init screen dependencies
    
    public void setButtonActions() {
        this.chooseXorOScreen.setButtonActions();
        this.createNewUserScreen.setButtonActions();
        this.gamescoreScreen.setButtonActions();
        this.loggedInScreen.setButtonActions();
        this.loginScreen.setButtonActions();
        this.playScreen.setButtonActions();
    }
    
    /*
    @Override
    public void stop() {
      System.out.println("sovellus sulkeutuu");
    }   
    */
    
    public static void main(String[] args) {
        launch(TodoUi.class);
    }
}