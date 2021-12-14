//package todoapp.ui;
//
//import java.util.List;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import tictactoe.domain.TictactoeService;
//
//public class MessageScreen {
////    private Label gameScoreEntryText;
////    private Button backFromGameScoreButton;
////    
////    private Scene loggedInScene;
//    private TictactoeService tictactoeService;
//    private List<String> messages;
//    
//    private Label message1Text;
//    private Label message2Text;
//    private Label message3Text;
//    private Label message4Text;
//    private Label message5Text;
//    private Label message6Text;
//    private Label message7Text;
//    private Label message8Text;
//    private Label message9Text;
//    private Label message10Text;
//    
//    private Label newMessageLabel;
//    private TextField newMessageTextField;
//    
//    private Button addNewMessageButton;
//    private Button backFromMessageScreenButton;
//    
//    private Scene loggedInScene;
//    
//    private Stage stage;
//    
//    public MessageScreen(TictactoeService tictactoeService, Stage stage) {
//        this.tictactoeService = tictactoeService;
//        
//        this.messages = this.tictactoeService.getMessages();
//        
//        int messagesSize = this.messages.size();
//        
//        //messageOneText = new Label("");
//        message1Text = new Label(this.messages.get(messagesSize - 10));
//        message2Text = new Label(this.messages.get(messagesSize - 9));
//        message3Text = new Label(this.messages.get(messagesSize - 8));
//        message4Text = new Label(this.messages.get(messagesSize - 7));
//        message5Text = new Label(this.messages.get(messagesSize - 6));
//        message6Text = new Label(this.messages.get(messagesSize - 5));
//        message7Text = new Label(this.messages.get(messagesSize - 4));
//        message8Text = new Label(this.messages.get(messagesSize - 3));
//        message9Text = new Label(this.messages.get(messagesSize - 2));
//        message10Text = new Label(this.messages.get(messagesSize - 1));
//        
//        newMessageLabel = new Label("Message: ");
//        newMessageTextField = new TextField();
//        
//        addNewMessageButton = new Button("Add new message");
//        backFromMessageScreenButton = new Button("Back");
//        
//        this.stage = stage;
//    }
//    
//    public Parent getMessageScreenSetUp() {
//        HBox writeNewMessageSetUp = new HBox();
//        writeNewMessageSetUp.setSpacing(10);
//        
//        writeNewMessageSetUp.getChildren().addAll(newMessageLabel, newMessageTextField);
//        
//        
//        HBox newMessageScreenButtonsSetUp = new HBox();
//        newMessageScreenButtonsSetUp.setSpacing(10);
//        
//        newMessageScreenButtonsSetUp.getChildren().addAll(this.addNewMessageButton, this.backFromMessageScreenButton);
//        
//        
//        GridPane messagesSetUp = new GridPane();
//
//        messagesSetUp.add(this.message1Text, 0, 0);
//        messagesSetUp.add(this.message2Text, 0, 1);
//        messagesSetUp.add(this.message3Text, 0, 2);
//        messagesSetUp.add(this.message4Text, 0, 3);
//        messagesSetUp.add(this.message5Text, 0, 4);
//        
//        messagesSetUp.add(this.message6Text, 0, 5);
//        messagesSetUp.add(this.message7Text, 0, 6);
//        messagesSetUp.add(this.message8Text, 0, 7);
//        messagesSetUp.add(this.message9Text, 0, 8);
//        messagesSetUp.add(this.message10Text, 0, 9);
//        
//        messagesSetUp.add(writeNewMessageSetUp, 0, 10);
//        messagesSetUp.add(newMessageScreenButtonsSetUp, 0, 11);
//
//        messagesSetUp.setPrefSize(300, 180); // CHANGE
//        messagesSetUp.setAlignment(Pos.CENTER);
//        messagesSetUp.setVgap(10);
//        messagesSetUp.setHgap(10);
//        messagesSetUp.setPadding(new Insets(20, 20, 20, 20));
//        
//        //setButtonActions();
//        
//        return messagesSetUp;
//
//        //Scene loginScene = new Scene(loginSetUp);
//        //return loginScene; //return scene?
//
//        //stage.setScene(loginScene);
//    }
//    
//    public void setButtonActions() {
//        //setCheckGameScoreButtonAction();
//        //setBackFromGameScoreButtonAction();
//        
//        setAddNewMessageButtonAction();
//        setBackFromMessageScreenButtonAction();
//    }
//    /* wrong class
//    public void setCheckGameScoreButtonAction() {
//        checkGameScoreButton.setOnAction((event) -> {
//        
//        int wins = this.tictactoeService.getLoggedInUser().getWins();
//        int losses = this.tictactoeService.getLoggedInUser().getLosses();
//        
//        gameScoreEntryText.setText("GAME SCORE:\n\nWins: " + wins + "\t\tLosses: " + losses);
//            
//        stage.setScene(gameScoreScene);
//        });
//    }
//    */
//    public void setAddNewMessageButtonAction() {
//        backFromMessageScreenButton.setOnAction((event) -> {
//        
//        String newMessage = this.newMessageTextField.getText().trim();
//        
//        this.tictactoeService.createNewMessage(newMessage);
//        
//        this.messages = this.tictactoeService.getMessages();
//        
//        int messagesSize = this.messages.size();
//        
//        message1Text = new Label(this.messages.get(messagesSize - 10));
//        message2Text = new Label(this.messages.get(messagesSize - 9));
//        message3Text = new Label(this.messages.get(messagesSize - 8));
//        message4Text = new Label(this.messages.get(messagesSize - 7));
//        message5Text = new Label(this.messages.get(messagesSize - 6));
//        message6Text = new Label(this.messages.get(messagesSize - 5));
//        message7Text = new Label(this.messages.get(messagesSize - 4));
//        message8Text = new Label(this.messages.get(messagesSize - 3));
//        message9Text = new Label(this.messages.get(messagesSize - 2));
//        message10Text = new Label(this.messages.get(messagesSize - 1));
//        
//        
//        
//        
//        stage.setScene(loggedInScene);
//        });
//    }
//    
//    public void setBackFromMessageScreenButtonAction() {
//        backFromMessageScreenButton.setOnAction((event) -> {
//        
//        stage.setScene(loggedInScene);
//        });
//    }
//    
//    public void setLoggedInScene(Scene loggedInScene) {
//        this.loggedInScene = loggedInScene;
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
////    public void setBackFromGameScoreButtonAction() {
////        backFromGameScoreButton.setOnAction((event) -> {
////        
////        stage.setScene(loggedInScene);
////        });
////    }
////    
////    public void setLoggedInScene(Scene loggedInScene) {
////        this.loggedInScene = loggedInScene;
////    }
////    
////    public Label getGameScoreEntryText() {
////        return this.gameScoreEntryText;
////    }
//}
