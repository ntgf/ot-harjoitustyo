package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tictactoe.domain.TictactoeService;
import tictactoe.domain.User;

public class LoginScreen {
    
    private TictactoeService tictactoeService;
    
    private CreateNewUserScreen createNewUserScreen;
    private LoggedInScreen loggedInScreen;
    
    private Label instructionLabel;
    private TextField usernameTextField;
    private Button loginButton;
    private Button createNewUserButton;
    private Button exitButton;
    private Label loginErrorText;
    
    private Scene loginScene;
    private Stage stage;
    
    public LoginScreen(TictactoeService tictactoeService, Stage stage) {
        this.tictactoeService = tictactoeService;
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        instructionLabel = new Label("Enter Username to Login:");
        usernameTextField = new TextField();
        loginButton = new Button("Login");
        createNewUserButton = new Button("Create new user");
        exitButton = new Button("Exit");
        loginErrorText = new Label("");
        
        HBox loginButtonsSetUp = new HBox();
        
        loginButtonsSetUp.setSpacing(10);
        
        loginButtonsSetUp.getChildren().addAll(loginButton, createNewUserButton, exitButton);
        
        GridPane loginSetUp = new GridPane();

        loginSetUp.add(instructionLabel, 0, 0);
        loginSetUp.add(usernameTextField, 0, 1);
        loginSetUp.add(loginButtonsSetUp, 0, 2);
        loginSetUp.add(loginErrorText, 0, 3);

        loginSetUp.setPrefSize(300, 180);
        loginSetUp.setAlignment(Pos.CENTER);
        loginSetUp.setVgap(10);
        loginSetUp.setHgap(10);
        loginSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        loginScene = new Scene(loginSetUp);
    }
    
    public void setButtonActions() {
        setCreateNewUserButtonAction();
        setLoginButtonAction();
        //setExitButtonACtion;
    }
    
    public void setCreateNewUserButtonAction() {
        createNewUserButton.setOnAction((event) -> {

        stage.setScene(this.createNewUserScreen.getScene());
        });
    }
    
    public void setLoginButtonAction() {
        loginButton.setOnAction((event) -> {
        
        String inputUsername = usernameTextField.getText().trim();
        
        User user = this.tictactoeService.usernameExists(inputUsername);
        
        if (user == null) {
          loginErrorText.setText("Username not found!\nPlease re-enter username.");
          return;
        }
        
        this.tictactoeService.setLoggedInUser(user);
        
        this.loggedInScreen.getLoggedInText().setText("Welcome " + user.getName() + "!");

        stage.setScene(this.loggedInScreen.getScene());
        });
    }
    
    public void setExitButtonAction() {
        exitButton.setOnAction((event) -> {
        
        //Action, stop
        });
    }
    
    public TextField getUsernameTextField() {
        return this.usernameTextField;
    }
    
    public Label getLoginErrorText() {
        return this.loginErrorText;
    }
    
    public void setCreateNewUserScreen(CreateNewUserScreen createNewUserScreen) {
        this.createNewUserScreen = createNewUserScreen;
    }
    
    public void setLoggedInScreen(LoggedInScreen loggedInScreen) {
        this.loggedInScreen = loggedInScreen;
    }
    
    public Scene getScene() {
        return this.loginScene;
    }
}