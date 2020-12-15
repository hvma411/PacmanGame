package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class YouWonStageController {

    private String userName;
    @FXML
    private Text score;
    @FXML
    private Button newGameBtn;
    @FXML
    private Button backToMenuBtn;

    @FXML
    public void initialize() { }

    @FXML
    private void handleButtonClick(ActionEvent event) throws Exception {
        if (event.getSource() == backToMenuBtn) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent main = loader.load();

            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainStage.setScene(new Scene(main, 600, 600));

        } else if (event.getSource() == newGameBtn) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newGameFirstStage.fxml"));
            Parent newGameUserCreate = loader.load();

            Stage newGameUserStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newGameUserStage.setScene(new Scene(newGameUserCreate, 600, 600));
        }
    }

    public void setTextScore(int score) {
        this.score.setText(Integer.toString(score));
    }

    public static void post(int score) throws Exception {
//        final String userName = Controller.userName;
        final String userName = "UserNameFromInput";
        final int scoreToWrite = score;

        try{
            Connection con = getConnection();
            PreparedStatement posted = con.prepareStatement("INSERT INTO users (userName, score) VALUES ('"+userName+"', '"+scoreToWrite+"')");

            posted.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
        finally {
            System.out.println("Insert Completed.");
        }
    }

    public static Connection getConnection() throws Exception{
        try{
            String url = "jdbc:mysql://localhost/usersdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "root";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e) {
            System.out.println("Didn't connected" + e);
        }
        return null;
    }
}
