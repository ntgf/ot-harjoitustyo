package todoapp.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import tictactoe.dao.FileUserDao;
import tictactoe.dao.UserDao;
import tictactoe.dao.FileMessageDao;
import tictactoe.dao.MessageDao;
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.ConnectFourAI;

public class TodoUi extends Application {
    
    private TictactoeAI tictactoeAI;
    private ConnectFourAI connectFourAI;
    private TictactoeService tictactoeService;
    
    private LoginScreen loginScreen;
    private CreateNewUserScreen createNewUserScreen;
    private LoggedInScreen loggedInScreen;
    private ChooseDifficultyScreen chooseDifficultyScreen;
    private ChooseXorOScreen chooseXorOScreen;
    private GamescoreScreen gamescoreScreen;
    private PlayScreen playScreen;
    private ConnectFourPlayScreen connectFourPlayScreen;    
    private MessageScreen messageScreen;
    
    @Override
    public void init() throws Exception {
        initAIs();
        initGameService();
    }
    
    public void initAIs() {
        this.tictactoeAI = new TictactoeAI();
        this.connectFourAI = new ConnectFourAI();
    }
    
    public void initGameService() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String usersDatabaseAddress = properties.getProperty("usersDatabaseAddress");
        UserDao userDao = new FileUserDao(usersDatabaseAddress);
        
        String messageDatabaseAddress = properties.getProperty("messagesDatabaseAddress");
        MessageDao messageDao = new FileMessageDao(messageDatabaseAddress);
        
        this.tictactoeService = new TictactoeService(userDao, messageDao);
    }
    
    @Override
    public void start(Stage stage) {
        initScreens(stage);
        setButtonActions();
        stage.setScene(this.loginScreen.getScene());
        stage.setTitle("TicTacToe");
        stage.show();
    }
    
    public void initScreens(Stage stage) {
        initLoginScreen(stage);
        initCreateNewUserScreen(stage);
        initLoggedInScreen(stage);
        initChooseXorOScreen(stage);
        initPlayScreen(stage);
        initConnectFourPlayScreen(stage);
        initGamescoreScreen(stage);
        initMessageScreen(stage);
        initChooseDifficultyScreen(stage);
    }
    
    public void initLoginScreen(Stage stage) {
        loginScreen = new LoginScreen(tictactoeService, stage);
    }
    
    public void initCreateNewUserScreen(Stage stage) {
        createNewUserScreen = new CreateNewUserScreen(tictactoeService, stage);
        createNewUserScreen.setLoginScreen(loginScreen);
        loginScreen.setCreateNewUserScreen(createNewUserScreen);
    }
    
    public void initLoggedInScreen(Stage stage) {
        loggedInScreen = new LoggedInScreen(tictactoeService, stage);
        loginScreen.setLoggedInScreen(loggedInScreen);
        loggedInScreen.setLoginScreen(loginScreen);
    }
    
    public void initChooseXorOScreen(Stage stage) {
        this.chooseXorOScreen = new ChooseXorOScreen(tictactoeAI, connectFourAI, stage);
        loggedInScreen.setChooseXorOScreen(chooseXorOScreen);
    }
    
    public void initPlayScreen(Stage stage) {
        playScreen = new PlayScreen(tictactoeAI, tictactoeService, stage);
        playScreen.setLoggedInScreen(loggedInScreen);
        playScreen.setChooseXorOScreen(chooseXorOScreen);     
        chooseXorOScreen.setPlayScreen(playScreen);
    }
    
    public void initConnectFourPlayScreen(Stage stage) {
        connectFourPlayScreen = new ConnectFourPlayScreen(connectFourAI, tictactoeService, stage);       
        this.connectFourPlayScreen.setChooseXorOScreen(chooseXorOScreen);
        this.connectFourPlayScreen.setLoggedInScreen(loggedInScreen);
        this.chooseXorOScreen.setConnectFourPlayScreen(connectFourPlayScreen);
    }
    
    public void initGamescoreScreen(Stage stage) {
        gamescoreScreen = new GamescoreScreen(stage);
        this.gamescoreScreen.setLoggedInScreen(loggedInScreen);   
        this.loggedInScreen.setGamescoreScreen(gamescoreScreen);
    }
    
    public void initMessageScreen(Stage stage) {
        messageScreen = new MessageScreen(tictactoeService, stage);
        this.messageScreen.setLoggedInScreen(loggedInScreen);
        this.loggedInScreen.setMessageScreen(messageScreen);
    }
    
    public void initChooseDifficultyScreen(Stage stage) {
        chooseDifficultyScreen = new ChooseDifficultyScreen(tictactoeAI, connectFourAI, stage);
        this.chooseDifficultyScreen.setChooseXOrOScreen(chooseXorOScreen);
        this.loggedInScreen.setChooseDifficultyScreen(chooseDifficultyScreen);
        this.playScreen.setChooseDifficultyScreen(chooseDifficultyScreen);
        this.connectFourPlayScreen.setChooseDifficultyScreen(chooseDifficultyScreen);
    }
         
    public void setButtonActions() {
        this.chooseXorOScreen.setButtonActions();
        this.createNewUserScreen.setButtonActions();
        this.gamescoreScreen.setButtonActions();
        this.loggedInScreen.setButtonActions();
        this.loginScreen.setButtonActions();
        this.playScreen.setButtonActions();
        this.connectFourPlayScreen.setButtonActions();
        this.messageScreen.setButtonActions();
        this.chooseDifficultyScreen.setButtonActions();
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