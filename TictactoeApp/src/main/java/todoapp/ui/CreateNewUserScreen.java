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

public class CreateNewUserScreen {
    
    private TictactoeService tictactoeService;
    
    private LoginScreen loginScreen;
    
    private Label newUserNameText;
    private TextField newUsernameTextField;
    private Label nameText;
    private TextField nameTextField;
    private Button finalizeCreatingNewUserButton;
    private Button backFromCreatingNewUserButton;
    private Label createNewUserErrorText;
    
    private Scene createNewUserScene;
    private Stage stage;
    
    public CreateNewUserScreen(TictactoeService tictactoeService, Stage stage) {
        this.tictactoeService = tictactoeService;
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        newUserNameText = new Label("New Username: ");
        newUsernameTextField = new TextField();
        nameText = new Label("Name: ");
        nameTextField = new TextField();
        finalizeCreatingNewUserButton = new Button("Create");
        backFromCreatingNewUserButton = new Button("Back");
        createNewUserErrorText = new Label("");
        
        HBox createNewUserButtonsSetUp = new HBox();
        createNewUserButtonsSetUp.setSpacing(10);
        createNewUserButtonsSetUp.getChildren().addAll(finalizeCreatingNewUserButton, backFromCreatingNewUserButton);
        
        GridPane createNewUserSetUp = new GridPane();
        
        createNewUserSetUp.add(newUserNameText, 0, 1);
        createNewUserSetUp.add(newUsernameTextField, 1, 1);
        createNewUserSetUp.add(nameText, 0, 2);
        createNewUserSetUp.add(nameTextField, 1, 2);
        createNewUserSetUp.add(createNewUserButtonsSetUp, 1, 3);
        createNewUserSetUp.add(createNewUserErrorText, 1, 4);
        
        createNewUserSetUp.setPrefSize(300, 180);
        createNewUserSetUp.setAlignment(Pos.CENTER);
        createNewUserSetUp.setVgap(10);
        createNewUserSetUp.setHgap(10);
        createNewUserSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        createNewUserScene = new Scene(createNewUserSetUp);
    }
    
    public void setButtonActions() {
        setFinalizeCreatingNewUserButtonAction();
        setBackFromCreatingNewUserButtonAction();
    }
    
    public void setFinalizeCreatingNewUserButtonAction() {
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
        
        if (!(this.tictactoeService.usernameExists(newUsername) == null)) {
            createNewUserErrorText.setText("Username already exists!\nPlease pick another username.");
            return;
        }
        
        this.tictactoeService.createUser(newUsername, name);
        
        this.loginScreen.getUsernameTextField().clear();
        this.loginScreen.getLoginErrorText().setText("Username successfully created!");
        
        this.nameTextField.setText("");
        this.newUsernameTextField.setText("");
        
        stage.setScene(this.loginScreen.getScene());
        });
    }
    
    public void setBackFromCreatingNewUserButtonAction() {
        backFromCreatingNewUserButton.setOnAction((event) -> {
        
        newUsernameTextField.clear();    
        nameTextField.clear();
        createNewUserErrorText.setText("");
        
        this.loginScreen.getUsernameTextField().clear();
        this.loginScreen.getLoginErrorText().setText("");
        
        stage.setScene(loginScreen.getScene());
        });
    }
    
    public void setLoginScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }
    
    public Scene getScene() {
        return this.createNewUserScene;
    }
}