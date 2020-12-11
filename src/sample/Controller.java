package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

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

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
        if (event.getSource() == exitBtn) {

            exitBtn.getScene().getWindow().hide();

        } else if (event.getSource() == topScoresBtn) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("topScorePage.fxml"));
            Parent topScorePage = loader.load();

            Stage topScorePageStage = (Stage)((Node) event.getSource()).getScene().getWindow();

            topScorePageStage.setScene(new Scene(topScorePage, 600, 600));

        } else if (event.getSource() == backToMenuBtn) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent main = loader.load();

            Stage mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();

            mainStage.setScene(new Scene(main, 600, 600));
        } else if (event.getSource() == newGameBtn) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newGameFirstStage.fxml"));
            Parent newGameUserCreate = loader.load();

            Stage newGameUserStage = (Stage)((Node) event.getSource()).getScene().getWindow();

            newGameUserStage.setScene(new Scene(newGameUserCreate, 600, 600));
        } else if (event.getSource() == startGameBtn) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameStage.fxml"));
            Parent newGame = loader.load();

            Stage newGameStage = (Stage)((Node) event.getSource()).getScene().getWindow();

            newGameStage.setScene(new Scene(newGame, 600, 600));

            System.out.println(nameInput.getText());
        }

    }

}
