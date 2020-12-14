package sample;

import javafx.scene.canvas.GraphicsContext;
import sample.models.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    // CHANGE NAME OF THE GAME TO EGG COLLECTOR

    private static final double WIDTH = 1200;
    private static final double HEIGHT = 750;

    @FXML
    private Button exitBtn ;
    @FXML
    private Button topScoresBtn;
    @FXML
    private Button newGameBtn;
    @FXML
    private Button backToMenuBtn;
    @FXML
    private Button startGameBtn;
    @FXML
    private TextField nameInput;

    private double newY, newX = 0;

    private Object KeyEvent;

    private int pacmanSpeed = 5;

    Canvas canvas = new Canvas(WIDTH, HEIGHT);

    Circle pacman = new Circle(25.0f, Color.YELLOW);

    public static final int WIDTHH = 600;
    public static final int HEIGHTT = 600;

    private sample.Timer timer;



    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
        if (event.getSource() == exitBtn) {

            exitBtn.getScene().getWindow().hide();

        } else if (event.getSource() == topScoresBtn) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("topScorePage.fxml"));
            Parent topScorePage = loader.load();

            Stage topScorePageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            topScorePageStage.setScene(new Scene(topScorePage, 600, 600));

        } else if (event.getSource() == backToMenuBtn) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent main = loader.load();

            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            mainStage.setScene(new Scene(main, 600, 600));

        } else if (event.getSource() == newGameBtn) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("newGameFirstStage.fxml"));
            Parent newGameUserCreate = loader.load();

            Stage newGameUserStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            newGameUserStage.setScene(new Scene(newGameUserCreate, 600, 600));

        } else if (event.getSource() == startGameBtn) {

            Stage newGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            GraphicsContext gcText = canvas.getGraphicsContext2D();
//            gcText.setLineWidth(1.0);
//            gcText.setFill(Color.WHITE);
//
//            gcText.strokeText("SCORE:", 60, 60);

            Group group = new Group();
            group.getChildren().addAll(canvas);

            Scene scene = new Scene(group);

            newGameStage.setScene(scene);

            Model model = new Model(WIDTHH, HEIGHTT);
            Graphics graphics = new Graphics(model, canvas.getGraphicsContext2D());

            sample.InputHandler inputHandler = new sample.InputHandler(model);

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    inputHandler.onKeyPressed(event);
                }
            });

            timer = new sample.Timer(model, graphics);
            timer.start();

        }
    }

}




