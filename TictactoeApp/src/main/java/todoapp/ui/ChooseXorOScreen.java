package todoapp.ui;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.domain.TictactoeAI;
import tictactoe.domain.ConnectFourAI;

public class ChooseXorOScreen {
    
    private TictactoeAI tictactoeAI;
    private ConnectFourAI connectFourAI;
    
    private PlayScreen playScreen;
    private ConnectFourPlayScreen connectFourPlayScreen;
    
    private boolean plays3x3Tictactoe;
    private boolean playsConnectFour;
    
    private Label pickXorOText; 
    private Button xButton;
    private Button oButton;
    
    private Scene chooseXorOScene;
    private Stage stage;
    
    public ChooseXorOScreen(TictactoeAI tictactoeAI, ConnectFourAI connectFourAI, Stage stage) {
        this.tictactoeAI = tictactoeAI;
        this.connectFourAI = connectFourAI;
        plays3x3Tictactoe = false;
        playsConnectFour = false;
        this.stage = stage;
        setScene();
    }
    
    public void setScene() {
        pickXorOText = new Label("Please choose X or O.\nX starts."); 
        xButton = new Button("X");
        oButton = new Button("O");
        
        GridPane pickXorOSetUp = new GridPane();        
        
        pickXorOSetUp.add(pickXorOText, 0, 0);
        pickXorOSetUp.add(xButton, 0, 1);
        pickXorOSetUp.add(oButton, 1, 1);
        
        pickXorOSetUp.setPrefSize(300, 180);
        pickXorOSetUp.setAlignment(Pos.CENTER);
        pickXorOSetUp.setVgap(10);
        pickXorOSetUp.setHgap(10);
        pickXorOSetUp.setPadding(new Insets(20, 20, 20, 20));
        
        chooseXorOScene = new Scene(pickXorOSetUp);
    }
    
    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }
    
    public void setConnectFourPlayScreen(ConnectFourPlayScreen connectFourPlayScreen) {
        this.connectFourPlayScreen = connectFourPlayScreen;
    }
    
    public void setButtonActions() {
        setXButtonAction();
        setOButtonAction();
    }
    
    public void setXButtonAction() {
        xButton.setOnAction((event) -> {
        
        if (this.plays3x3Tictactoe) {
            this.tictactoeAI.setplaysx(false);

            stage.setScene(this.playScreen.getScene());
        }

        if (this.playsConnectFour) {
            this.connectFourAI.setplaysx(false);

            stage.setScene(connectFourPlayScreen.getScene());
        }
        });
    }
    
    public void setOButtonAction() {
        oButton.setOnAction((event) -> {
        
        if (this.plays3x3Tictactoe) {
            this.tictactoeAI.setplaysx(true);

            Random randomInteger = new Random();
            int randomIndex = randomInteger.nextInt(9);

            this.tictactoeAI.setboard(this.tictactoeAI.getboard().substring(0, randomIndex) + 
                    "X" + this.tictactoeAI.getboard().substring(randomIndex + 1, 9));

              this.playScreen.getButtons()[randomIndex % 3][(randomIndex - randomIndex % 3) / 3].setText("X");

            stage.setScene(this.playScreen.getScene());
        }

        if (this.playsConnectFour) {
            this.connectFourAI.setplaysx(true);

            Random randomInteger = new Random();
            int randomXCoordinate = randomInteger.nextInt(7);
            String board = this.connectFourAI.getboardString();
            
            board = board.substring(0, 63 + randomXCoordinate + 1) + "X" + board.substring(63 + randomXCoordinate + 2, 81);
            
            this.connectFourAI.setboardString(board);

            this.connectFourPlayScreen.setFirstAIMoveGameplayButtons();
            stage.setScene(this.connectFourPlayScreen.getScene());
        }
        });
    }
    
    public Scene getScene() {
        return this.chooseXorOScene;
    }
    
    public void setPlays3x3Tictactoe(boolean plays3x3Tictactoe) {
        this.plays3x3Tictactoe = plays3x3Tictactoe;
    }
    
    public void setPlaysConnectFour(boolean playsConnectFour) {
        this.playsConnectFour = playsConnectFour;
    }
}