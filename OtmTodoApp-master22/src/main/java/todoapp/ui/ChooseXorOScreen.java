package todoapp.ui;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.domain.TictactoeAI;

public class ChooseXorOScreen {
    
    private TictactoeAI tictactoeAI;
    private Button[][] buttons;
    
    private PlayScreen playScreen;
    
    private Label pickXorOText; 
    private Button xButton;
    private Button oButton;
    
    private Scene playScene;
    private Stage stage;
    
    public ChooseXorOScreen(TictactoeAI tictactoeAI, Button[][] buttons, /*Scene playScene, */Stage stage) {
        this.tictactoeAI = tictactoeAI;
        this.buttons = buttons;
        this.stage = stage;
        
        pickXorOText = new Label("Please choose X or O.\nX starts."); 
        xButton = new Button("X");
        oButton = new Button("O");
    }
    
    public Parent getChooseXorOSetUp() {
        /*
        pickXorOText = new Label("Please choose X or O.\nX starts."); 
        xButton = new Button("X");
        oButton = new Button("O");
        */
        GridPane pickXorOSetUp = new GridPane();        
        
        pickXorOSetUp.add(pickXorOText, 0, 0);
        pickXorOSetUp.add(xButton, 0, 1);
        pickXorOSetUp.add(oButton, 1, 1);
        
        pickXorOSetUp.setPrefSize(300, 180); // CHANGE
        pickXorOSetUp.setAlignment(Pos.CENTER);
        pickXorOSetUp.setVgap(10);
        pickXorOSetUp.setHgap(10);
        pickXorOSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        //Scene pickXorOScene = new Scene(pickXorOSetUp);
        //setButtonActions();
        
        return pickXorOSetUp;
    }
    
    public void setPlayScene(Scene playScene) {
        this.playScene = playScene;
    }
    
    //Needed?
    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }
    
    public void setButtonActions() {
        setXButtonAction();
        setOButtonAction();
    }
    
    public void setXButtonAction() {
        xButton.setOnAction((event) -> {
        
        this.tictactoeAI.setplaysx(false);
        
        
        stage.setScene(playScene);
        });
    }
    
    public void setOButtonAction() {
        oButton.setOnAction((event) -> {
        
        this.tictactoeAI.setplaysx(true);
        
        Random randomInteger = new Random();
        int randomIndex = randomInteger.nextInt(9);
            //System.out.println(randomIndex); //!!

        this.tictactoeAI.setboard(this.tictactoeAI.getboard().substring(0, randomIndex) + 
                "X" + this.tictactoeAI.getboard().substring(randomIndex + 1, 9));

        this.buttons[randomIndex % 3][(randomIndex - randomIndex % 3) / 3].setText("X");
        
        stage.setScene(playScene);
        });
    }
}
