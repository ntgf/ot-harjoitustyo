package todoapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
    
    private Scene loginScene;
    private Stage stage;
    
    public CreateNewUserScreen(TictactoeService tictactoeService, LoginScreen loginScreen, Scene loginScene, Stage stage) {
        this.tictactoeService = tictactoeService;
        this.loginScreen = loginScreen;
        this.loginScene = loginScene;
        this.stage = stage;
        
        newUserNameText = new Label("New Username: ");
        newUsernameTextField = new TextField();
        nameText = new Label("Name: ");
        nameTextField = new TextField();
        finalizeCreatingNewUserButton = new Button("Create");
        backFromCreatingNewUserButton = new Button("Back");
        createNewUserErrorText = new Label("");
    }
    
    public Parent getCreateNewUserSetUp() {
        /*
        Label newUserNameText = new Label("New Username: ");
        newUsernameTextField = new TextField();
        Label nameText = new Label("Name: ");
        nameTextField = new TextField();
        finalizeCreatingNewUserButton = new Button("Create");
        /*Button*/ //backFromCreatingNewUserButton = new Button("Back");
        //createNewUserErrorText = new Label("");
        
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
        
        //Scene createNewUserScene = new Scene(createNewUserSetUp);
        
        //setButtonActions();
        
        return createNewUserSetUp;
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
        
        if (!(this.tictactoeService.usernameExists(/*newUsernameTextField.getText().trim())*/ newUsername) == null)) {
            createNewUserErrorText.setText("Username already exists!\nPlease pick another username.");
            return;
        }
        
        this.tictactoeService.createUser(newUsername, name);
        
        this.loginScreen.getUsernameTextField().clear();
        this.loginScreen.getLoginErrorText().setText("Username successfully created!");
        /*
        usernameTextField.clear();
        loginErrorText.setText("Username successfully created!");
        */
        stage.setScene(loginScene);
        });
    }
    
    public void setBackFromCreatingNewUserButtonAction() {
        backFromCreatingNewUserButton.setOnAction((event) -> {
        
        newUsernameTextField.clear();    
        nameTextField.clear();
        
        this.loginScreen.getUsernameTextField().clear();
        this.loginScreen.getLoginErrorText().setText("");
        
        //usernameTextField.clear(); ? HUOM import parameter login screen
        //loginErrorText.setText(""); SAME
        
        stage.setScene(loginScene);
        });
    }
}
