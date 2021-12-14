package tictactoe.ui;

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
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.User;
import java.util.Random;

public class TodoUi extends Application {
    
    private TictactoeAI tictactoeAI;
    private TictactoeService tictactoeService;
    private Button[][] buttons;

    @Override
    public void init() throws Exception {
        this.tictactoeAI = new TictactoeAI("---------", true);
        this.buttons = new Button[3][3];
        
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String file = properties.getProperty("userFile");
        
        UserDao userDao = new FileUserDao(file);
        
        this.tictactoeService = new TictactoeService(userDao);
    }

    @Override
    public void start(Stage stage) {
        
        // Login screen:
        
        Label instructionLabel = new Label("Enter Username to Login:");
        TextField usernameTextField = new TextField();
        Button loginButton = new Button("Login");
        Button createNewUserButton = new Button("Create new user");
        Button exitButton = new Button("Exit");
        Label loginErrorText = new Label("");
        
        HBox loginButtonsSetUp = new HBox();
        //loginButtonsSetUp.setPadding(new Insets(20, 20, 20, 20));
        loginButtonsSetUp.setSpacing(10);
        
        loginButtonsSetUp.getChildren().addAll(loginButton, createNewUserButton, exitButton);
        
        GridPane loginSetUp = new GridPane();

        loginSetUp.add(instructionLabel, 0, 0);
        loginSetUp.add(usernameTextField, 0, 1);
        loginSetUp.add(loginButtonsSetUp, 0, 2);
        loginSetUp.add(loginErrorText, 0, 3);

        loginSetUp.setPrefSize(300, 180); // CHANGE
        loginSetUp.setAlignment(Pos.CENTER);
        loginSetUp.setVgap(10);
        loginSetUp.setHgap(10);
        loginSetUp.setPadding(new Insets(20, 20, 20, 20));

        Scene loginScene = new Scene(loginSetUp);

        stage.setScene(loginScene);
        
        
        
        
        
        
        
        //Create new user screen:
        
        Label newUserNameText = new Label("New Username: ");
        TextField newUsernameTextField = new TextField();
        Label nameText = new Label("Name: ");
        TextField nameTextField = new TextField();
        Button finalizeCreatingNewUserButton = new Button("Create");
        Button backFromCreatingNewUserButton = new Button("Back");
        Label createNewUserErrorText = new Label("");
        
        HBox createNewUserButtonsSetUp = new HBox();
        //loginButtonsSetUp.setPadding(new Insets(20, 20, 20, 20));
        createNewUserButtonsSetUp.setSpacing(10);
        createNewUserButtonsSetUp.getChildren().addAll(finalizeCreatingNewUserButton, backFromCreatingNewUserButton);
        
        GridPane createNewUserSetUp = new GridPane();
        
        createNewUserSetUp.add(newUserNameText, 0, 1);
        createNewUserSetUp.add(newUsernameTextField, 1, 1);
        createNewUserSetUp.add(nameText, 0, 2);
        createNewUserSetUp.add(nameTextField, 1, 2);
        //createNewUserSetUp.add(finalizeCreatingNewUserButton, 1, 3);
        createNewUserSetUp.add(createNewUserButtonsSetUp, 1, 3);
        createNewUserSetUp.add(createNewUserErrorText, 1, 4);
        
        createNewUserSetUp.setPrefSize(300, 180); // CHANGE
        createNewUserSetUp.setAlignment(Pos.CENTER);
        createNewUserSetUp.setVgap(10);
        createNewUserSetUp.setHgap(10);
        createNewUserSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        Scene createNewUserScene = new Scene(createNewUserSetUp);
        
        
        
        
        //Logged in screen:
        //Label loggedInText = new Label("Successfully logged in!"); // "Welcome <name>!" // ""
        Label loggedInText = new Label("");
        Button playButton = new Button("Play");
        Button checkGameScoreButton = new Button("Check Gamescore");
        Button logoutButton = new Button("Logout");
        
        GridPane loggedInSetUp = new GridPane();

        loggedInSetUp.add(loggedInText, 0, 0);
        loggedInSetUp.add(playButton, 0, 1);
        loggedInSetUp.add(checkGameScoreButton, 0, 2);
        loggedInSetUp.add(logoutButton, 0, 3);
        
        loggedInSetUp.setPrefSize(300, 180); // CHANGE
        loggedInSetUp.setAlignment(Pos.CENTER);
        loggedInSetUp.setVgap(10);
        loggedInSetUp.setHgap(10);
        loggedInSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        Scene loggedInScene = new Scene(loggedInSetUp);
        
        
        
        
        
        
        //Pick x or o screen:
        Label pickXorOText = new Label("Please choose X or O.\nX starts."); 
        Button xButton = new Button("X");
        Button oButton = new Button("O");
        
        GridPane pickXorOSetUp = new GridPane();        
        
        pickXorOSetUp.add(pickXorOText, 0, 0);
        pickXorOSetUp.add(xButton, 0, 1);
        pickXorOSetUp.add(oButton, 1, 1);
        
        pickXorOSetUp.setPrefSize(300, 180); // CHANGE
        pickXorOSetUp.setAlignment(Pos.CENTER);
        pickXorOSetUp.setVgap(10);
        pickXorOSetUp.setHgap(10);
        pickXorOSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        Scene pickXorOScene = new Scene(pickXorOSetUp);
        
        
        
        
        
        
        //Play screen:
        Label playModeLabel = new Label(""); //You play " + this.tictactoeAI.getplaysx());  / Good Luck!      
        GridPane board = new GridPane();
        Button playAgainButton = new Button("Play again");
        Button giveUpAndExitButton = new Button("Give up");
        
        //playModeLabel.setAlignment(Pos.CENTER);       MOVE TO CENTER
        
        
        //ADD AFTERPLAY SET UP / SCREEN / / / CREATE ANOTHER
        
        HBox playButtonsSetUp = new HBox();
        //loginButtonsSetUp.setPadding(new Insets(20, 20, 20, 20));
        playButtonsSetUp.setSpacing(100);
        playButtonsSetUp.getChildren().addAll(/*playAgainButton, */giveUpAndExitButton);
        
        GridPane playSetUp = new GridPane();
        
        playSetUp.add(playModeLabel, 0, 0);
        playSetUp.add(board, 0, 1);
        playSetUp.add(playButtonsSetUp, 0, 2);
        
        playSetUp.setPrefSize(300, 180); // CHANGE
        playSetUp.setAlignment(Pos.CENTER);
        playSetUp.setVgap(10);
        playSetUp.setHgap(10);
        playSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button newButton = new Button("");
                newButton.setMinSize(70, 70);
                
                this.buttons[i][j] = newButton;
                board.add(newButton, i + 1, j + 1);
                
                newButton.setOnAction((event) -> {
                    
                if(this.tictactoeAI.checkBoardForWinner() != 0 || this.tictactoeAI.boardCompleted()) {
                    return;
                }
                    
                if (!newButton.getText().equals("")) {
                    //ERROr text wrong move position already played
                    return;
                }
        
                String playerMark = "X";
                String aiMark = "O";

                if (this.tictactoeAI.getplaysx()) {
                        playerMark = "O";
                        aiMark = "X";
                        /* Move to chooses O
                        Random randomInteger = new Random();
                        int randomIndex = randomInteger.nextInt(9);
                        
                        this.tictactoeAI.setboard(this.tictactoeAI.getboard().substring(0, randomIndex) + 
                                aiMark + this.tictactoeAI.getboard().substring(randomIndex, randomIndex + 1));
                        
                        this.buttons[randomIndex % 3][(randomIndex - randomIndex % 3) / 3].setText(aiMark);
                        */
                    }
                
                newButton.setText(playerMark);
                
                //NEEDS UPDATING (THE AI METHOD, RETURN NEXT MOVE AS COORDINATES INSTEAD OF A STRING
                String tictactoeBoard = this.tictactoeAI.getboard();
                    System.out.println(tictactoeBoard); //!!!
                //double playerMoveCoordinate = newButton.getLayoutX();
                int newButtonXCoordinate = 0;
                int newButtonYCoordinate = 0;
                        
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (this.buttons[k][l].equals(newButton)) {
                            //Coord correct way?
                            newButtonXCoordinate = k;
                            newButtonYCoordinate = l;
                            System.out.println(k + ", " + l + "\n");
                        }
                    }
                }
                //Correct?
                int playerMoveStringBoardIndex = 3 * newButtonYCoordinate + newButtonXCoordinate;
                //int playerMoveStringBoardIndex = 3 * newButtonXCoordinate + newButtonYCoordinate;
                    System.out.println(playerMoveStringBoardIndex + "\n"); //!!
                
                String tictactoeBoardAfterPlayerMoves = tictactoeBoard.substring(0, playerMoveStringBoardIndex) +
                        playerMark + tictactoeBoard.substring(playerMoveStringBoardIndex + 1, 9);
                System.out.println(tictactoeBoardAfterPlayerMoves); //!!!
                
                this.tictactoeAI.setboard(tictactoeBoardAfterPlayerMoves);
                
                String tictactoeBoardAfterAIMoves = this.tictactoeAI.nextMove();
                    System.out.println(tictactoeBoardAfterAIMoves); //!!!
                    System.out.println(""); //!!!
                int tictactoeAIMoveIndex = -1;
                
                for (int k = 0; k < 9; k++) {
                    if (tictactoeBoardAfterPlayerMoves.charAt(k) != tictactoeBoardAfterAIMoves.charAt(k)) {
                        tictactoeAIMoveIndex = k;
                    }
                }
                
                if (/*!this.tictactoeAI.boardCompleted()*/ tictactoeAIMoveIndex >= 0) {
                    this.buttons[tictactoeAIMoveIndex % 3][(tictactoeAIMoveIndex - tictactoeAIMoveIndex % 3) / 3].setText(aiMark);
                }
                
                if (this.tictactoeAI.checkBoardForWinner() == 1) {
                    if(this.tictactoeAI.getplaysx()) {
                        //System.out.println("YOU LOSE!"); //!!
                        playModeLabel.setText("YOU LOSE!");
                        //INcrease losses
                        this.tictactoeService.getLoggedInUser().increaseLosses();
                        giveUpAndExitButton.setText("Exit");
                        playButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
                        playButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
                        //playSetUp.add(playButtonsSetUp, 0, 2);
                        //Scene playScene = new Scene(playSetUp);
                    } else {
                        playModeLabel.setText("YOU WIN!"); //!!
                        //INcrease wins
                        this.tictactoeService.getLoggedInUser().increaseWins();
                        giveUpAndExitButton.setText("Exit");
                        playButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
                        playButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
                        //playSetUp.add(playButtonsSetUp, 0, 2);
                        //Scene playScene = new Scene(playSetUp);
                    }
                }
                
                if (this.tictactoeAI.checkBoardForWinner() == -1) {
                    if(this.tictactoeAI.getplaysx()) {
                        playModeLabel.setText("YOU WIN!"); //!!
                        //INcrease losses
                        this.tictactoeService.getLoggedInUser().increaseWins();
                        giveUpAndExitButton.setText("Exit");
                        playButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
                        playButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
                        //playSetUp.add(playButtonsSetUp, 0, 2);
                        //Scene playScene = new Scene(playSetUp);
                    } else {
                        playModeLabel.setText("YOU LOSE!"); //!!
                        //INcrease wins
                        this.tictactoeService.getLoggedInUser().increaseLosses();
                        giveUpAndExitButton.setText("Exit");
                        playButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
                        playButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
                        //playSetUp.add(playButtonsSetUp, 0, 2);
                        //Scene playScene = new Scene(playSetUp); //UPDate instead of new?
                    }
                }
                
                if (this.tictactoeAI.boardCompleted()) {
                    playModeLabel.setText("DRAW!");
                    giveUpAndExitButton.setText("Exit");
                    playButtonsSetUp.getChildren().removeAll(giveUpAndExitButton);
                    playButtonsSetUp.getChildren().addAll(playAgainButton, giveUpAndExitButton);
                    //playSetUp.add(playButtonsSetUp, 0, 2);
                    //Scene playScene = new Scene(playSetUp);
                }
                
                this.tictactoeService.writeUsersToFile();
                });
            }
        }
        
        Scene playScene = new Scene(playSetUp);
        
        
        
        
        
        //Gamescore screen:
        
        Label gameScoreEntryText = new Label("");
        Button backFromGameScoreButton = new Button("Back");
        
        GridPane gameScoreSetUp = new GridPane();        
        
        gameScoreSetUp.add(gameScoreEntryText, 0, 0);
        gameScoreSetUp.add(backFromGameScoreButton, 0, 2);
        
        gameScoreSetUp.setPrefSize(300, 180); // CHANGE
        gameScoreSetUp.setAlignment(Pos.CENTER);
        gameScoreSetUp.setVgap(10);
        gameScoreSetUp.setHgap(10);
        gameScoreSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        Scene gameScoreScene = new Scene(gameScoreSetUp);
        
        
        
        
        
        //Button actions:
        
        //Set createNewUser screen:
        createNewUserButton.setOnAction((event) -> {

        stage.setScene(createNewUserScene);
        });
        
        //Set backFromCreateNewUser screen
        backFromCreatingNewUserButton.setOnAction((event) -> {
        
        newUsernameTextField.clear();    
        nameTextField.clear();
        
        usernameTextField.clear();
        loginErrorText.setText("");
        
        stage.setScene(loginScene);
        });
        
        
        //Create new user & set backFromCreateNewUserScreen
        finalizeCreatingNewUserButton.setOnAction((event) -> {
            
        String newUsername = newUsernameTextField.getText().trim();
        String name = nameTextField.getText().trim();
        
        if (newUsername.equals("")) {
            createNewUserErrorText.setText("No username entered.\nPlease enter an username.");
            return;
        }
        
        if (name.equals("")) {
            createNewUserErrorText.setText("No name entered.\nPlease enter your name.");
            return;
        }
        
        if (!(this.tictactoeService.usernameExists(/*newUsernameTextField.getText().trim())*/ newUsername) == null)) {
            createNewUserErrorText.setText("Username already exists!\nPlease pick another username.");
            return;
        }
        
        this.tictactoeService.createUser(newUsername, name);
        
        usernameTextField.clear();
        loginErrorText.setText("Username successfully created!");
        
        stage.setScene(loginScene);
        });
        
        //Set loggedIn screen:
        loginButton.setOnAction((event) -> {
        
        String inputUsername = usernameTextField.getText().trim();
        
        User user = this.tictactoeService.usernameExists(inputUsername);
        
        if (user == null) {
          loginErrorText.setText("Username not found!\nPlease re-enter username.");
          return;
        }
        
        this.tictactoeService.setLoggedInUser(user);
        
        loggedInText.setText("Welcome " + user.getName() + "!");

        stage.setScene(loggedInScene);
        });
        
        
        
        
        
        //Set chooseXorO screen:
        playButton.setOnAction((event) -> {
        
        stage.setScene(pickXorOScene);
        });
        
        
        //SET PLAYSCENE
        //Plays x / Chooses x:
        xButton.setOnAction((event) -> {
        
        this.tictactoeAI.setplaysx(false);
        stage.setScene(playScene);
        });
        
        //Plays o:
        oButton.setOnAction((event) -> {
        
        this.tictactoeAI.setplaysx(true);
        
        Random randomInteger = new Random();
        int randomIndex = randomInteger.nextInt(9);
            System.out.println(randomIndex); //!!

        this.tictactoeAI.setboard(this.tictactoeAI.getboard().substring(0, randomIndex) + 
                "X" + this.tictactoeAI.getboard().substring(randomIndex + 1, 9));

        this.buttons[randomIndex % 3][(randomIndex - randomIndex % 3) / 3].setText("X");
        
        stage.setScene(playScene);
        });
        
        // Play again:
        playAgainButton.setOnAction((event) -> {
            
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j].setText("");
            }
        }
        
        this.tictactoeAI.setboard("---------");
        
        playModeLabel.setText(""); //You play x, good luck!
        giveUpAndExitButton.setText("Give up");
        playButtonsSetUp.getChildren().removeAll(playAgainButton, giveUpAndExitButton);
        playButtonsSetUp.getChildren().addAll(giveUpAndExitButton);
                   
        stage.setScene(pickXorOScene);
        });   
        
        
        //Exit game:
        giveUpAndExitButton.setOnAction((event) -> {
            
        if (!(this.tictactoeAI.checkBoardForWinner() != 0 || this.tictactoeAI.boardCompleted())) {
            this.tictactoeService.getLoggedInUser().increaseLosses();
            this.tictactoeService.writeUsersToFile();
        }
            
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j].setText("");
            }
        }
        
        this.tictactoeAI.setboard("---------");
        
        playModeLabel.setText(""); //You play x, good luck!
        giveUpAndExitButton.setText("Give up");
        playButtonsSetUp.getChildren().removeAll(playAgainButton, giveUpAndExitButton);
        playButtonsSetUp.getChildren().addAll(giveUpAndExitButton);
                   
        stage.setScene(loggedInScene);
        });   
        
        
        //Set checkGameScore screen:
        checkGameScoreButton.setOnAction((event) -> {
        
        int wins = this.tictactoeService.getLoggedInUser().getWins();
        int losses = this.tictactoeService.getLoggedInUser().getLosses();
        
        gameScoreEntryText.setText("GAME SCORE:\n\nWins: " + wins + "\t\tLosses: " + losses);
            
        stage.setScene(gameScoreScene);
        });
        
        
        
        //Set back from GameScoreScene -> loggedInScene
        backFromGameScoreButton.setOnAction((event) -> {
        
        stage.setScene(loggedInScene);
        });
        
        
        //Set logoutScene -> loginScene
        logoutButton.setOnAction((event) -> {
         
        usernameTextField.clear(); //OK?
        loginErrorText.setText("Successfully logged out!\nEnter username to login.");
        
        stage.setScene(loginScene);
        });
        
        
        
        /*
        //Exit
        exitButton.setOnAction((event) -> {
            
        //stop();
        });
        */
        
        
        
        stage.setTitle("TicTacToe");
        stage.show();
    }
    
    
    /*
    @Override
    public void stop() {
      // tee lopetustoimenpiteet täällä
      System.out.println("sovellus sulkeutuu");
    }   
    */
    
    
    

    public static void main(String[] args) {
        launch(TodoUi.class);
    }
}

//Successfully logout top second?