package todoapp.ui;

import java.util.ArrayList;
import java.util.List;
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

public class MessageScreen {
    private TictactoeService tictactoeService;
    private List<String> messages;
    
    private LoggedInScreen loggedInScreen;
    
    private List<Label> messageTextLabels;
    private Label newMessageLabel;
    private TextField newMessageTextField;   
    private Button addNewMessageButton;
    private Button backFromMessageScreenButton;    
    private Label errorMessageLabel;    
    private HBox writeNewMessageSetUp;
    private HBox newMessageScreenButtonsSetUp;
    private GridPane messagesSetUp;
    
    private Scene messageScene;    
    private Stage stage;
    
    public MessageScreen(TictactoeService tictactoeService, Stage stage) {
        this.tictactoeService = tictactoeService;
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        initMessages();
        
        newMessageLabel = new Label("Message: ");
        newMessageTextField = new TextField();
        
        addNewMessageButton = new Button("Write new message");
        backFromMessageScreenButton = new Button("Back");
        
        errorMessageLabel = new Label("");
        
        writeNewMessageSetUp = new HBox();
        writeNewMessageSetUp.setSpacing(10);
        writeNewMessageSetUp.getChildren().addAll(newMessageLabel, newMessageTextField);
        
        newMessageScreenButtonsSetUp = new HBox();
        newMessageScreenButtonsSetUp.setSpacing(10);
        newMessageScreenButtonsSetUp.getChildren().addAll(this.addNewMessageButton, this.backFromMessageScreenButton);
        
        messagesSetUp = new GridPane();
        
        for (int i = this.messageTextLabels.size() - 1; i >= 0; i--) {
            messagesSetUp.add(this.messageTextLabels.get(i), 0, this.messageTextLabels.size() - 1 - i);
        }

        messagesSetUp.add(writeNewMessageSetUp, 0, this.messageTextLabels.size());
        messagesSetUp.add(newMessageScreenButtonsSetUp, 0, this.messageTextLabels.size() + 1);

        messagesSetUp.add(this.errorMessageLabel, 0, this.messageTextLabels.size() + 2);

        messagesSetUp.setPrefSize(300, 180);
        messagesSetUp.setAlignment(Pos.CENTER);
        messagesSetUp.setVgap(10);
        messagesSetUp.setHgap(10);
        messagesSetUp.setPadding(new Insets(20, 20, 20, 20));

        setButtonActions();
        
        messageScene = new Scene(messagesSetUp);
    }
    
    public void initMessages() {
        this.messages = this.tictactoeService.getLast10Messages();

        messageTextLabels = new ArrayList<>();
        
        for (String message : messages) {
            messageTextLabels.add(new Label(message));
        }
    }
    
    public void setButtonActions() {
        setAddNewMessageButtonAction();
        setBackFromMessageScreenButtonAction();
    }
    
    public void setAddNewMessageButtonAction() {
        addNewMessageButton.setOnAction((event) -> {
        
        String newMessage = this.newMessageTextField.getText().trim();
        
        if (newMessage.equals("")) {
            this.errorMessageLabel.setText("Empty message!");
            return;
        }
        
        this.tictactoeService.createNewMessage(newMessage);
        
        setScene();
        
        stage.setScene(messageScene);
        });
    }
    
    public void setBackFromMessageScreenButtonAction() {
        backFromMessageScreenButton.setOnAction((event) -> {
        
        this.newMessageTextField.setText("");
        this.errorMessageLabel.setText("");
        
        stage.setScene(this.loggedInScreen.getScene());
        });
    }
    
    public void setLoggedInScreen(LoggedInScreen loggedInScreen) {
        this.loggedInScreen = loggedInScreen;
    }
    
    public Scene getScene() {
        return this.messageScene;
    }
}