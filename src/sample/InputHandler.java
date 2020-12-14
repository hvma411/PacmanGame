package sample;

import sample.models.GameObject;
import sample.models.Model;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {

    private Model model;

    public InputHandler(Model model) {
        this.model = model;
    }

    public void onKeyPressed(KeyEvent event) {

        GameObject player = model.getPlayer();
        if(event.getCode() == KeyCode.RIGHT) {
            if (model.canMove(player.getX() + 10, player.getY())) {
                player.setX(player.getX() + 10);
            }
        }
        if(event.getCode() == KeyCode.LEFT) {
            if (model.canMove(player.getX() - 10, player.getY())) {
                player.setX(player.getX() - 10);
            }
        }
        if(event.getCode() == KeyCode.UP) {
            if (model.canMove(player.getX(), player.getY() - 10)) {
                player.setY(player.getY() - 10);
            }
        }
        if(event.getCode() == KeyCode.DOWN) {
            if (model.canMove(player.getX(), player.getY() + 10)) {
                player.setY(player.getY() + 10);
            }
        }

    }

}