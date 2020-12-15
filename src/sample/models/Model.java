package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Controller;
import sample.Main;
import sample.YouWonStageController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    int sumOfCoins = 0;

    int width;
    int height;

    // Creating objects to play with

    private Player player = new Player(120, 120);
    private Monster monster = new Monster(270, 270, 60, 60);
    private List<Wall> walls = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();

    /**
     * The constructor creates the playing field with all objects.
     *
     * @param width
     * @param height
     *
     */

    public Model(int width, int height) {
        this.width = width;
        this.height = height;

        init(width, height);
        initWalls();
    }

    public Player getPlayer() { return player; }

    public Monster getMonster() { return monster; }

    public List<Wall> getWall() {
        return walls;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    /*
     * Arrangement of objects on the playing field
     */

    private void init(int width, int height) {
        Random positionsGenerator = new Random(System.currentTimeMillis());
        int numberOfCoins = 10;
        int coinDiameter = 10;
        for (int i = 0; i < numberOfCoins; i++) {
            int x = positionsGenerator.nextInt(width - coinDiameter) + coinDiameter / 2;
            int y = positionsGenerator.nextInt(height - coinDiameter) + coinDiameter / 2;
            Coin coin = new Coin(x, y, i + 1);
            this.coins.add(coin);
        }
    }

    // Function that creates the walls in our game;
    private void initWalls() {
        int walls = 8;
        int heightOfWall = 5;
        int widthOfWall = 600;
        int widthOfSmallerWall = 500;
        int positionX = 0;
        int positionY = 0;
        int rightWallsPosX = 300;
        int rightWallsPosY = 300;

        for (int i = 0; i < walls; i++) {
            if (positionY <= 250) {
                Wall horizontal = new Wall(positionX, positionY, widthOfWall, heightOfWall);
                this.walls.add(horizontal);

                Wall vertical = new Wall(positionX, positionY + 50, heightOfWall, widthOfSmallerWall);
                this.walls.add(vertical);

                positionY = positionY + 50;
                widthOfWall = widthOfWall - 100;
                positionX = positionX + 50;
                widthOfSmallerWall = widthOfSmallerWall - 100;

            } else if (positionY > 250) {
                Wall horizontal = new Wall(positionX, positionY, widthOfWall, heightOfWall);
                this.walls.add(horizontal);

                Wall verticalRight = new Wall(rightWallsPosX, rightWallsPosY + 50, heightOfWall, widthOfSmallerWall);
                this.walls.add(verticalRight);

                positionY = positionY + 50;
                widthOfWall = widthOfWall + 100;
                positionX = positionX - 50;
                rightWallsPosX = rightWallsPosX + 50;
                rightWallsPosY = rightWallsPosY - 50;
                widthOfSmallerWall = widthOfSmallerWall + 100;
            }
        }
    }

    private boolean playerObjectCollider(Player player, GameObject otherObject) {
        boolean horizontalCollide = false;

        int squareLeft = player.getX();
        int squareRight = player.getX() + player.getWidth();

        int otherObjectLeft = otherObject.getX();
        int otherObjectRight = otherObject.getX() + otherObject.getWidth();

        if (squareRight > otherObjectLeft && squareLeft < otherObjectRight) {
            horizontalCollide = true;
        }

        boolean verticalCollide = false;

        int squareTop = player.getY();
        int squareBottom = player.getY() + player.getHeight();

        int otherObjectTop = otherObject.getY();
        int otherObjectBottom = otherObject.getY() + otherObject.getHeight();

        if (squareBottom > otherObjectTop && squareTop < otherObjectBottom) {
            verticalCollide = true;
        }

        boolean collide = horizontalCollide && verticalCollide;

        return collide;
    }

    private Coin coinsCollider() {
        for (Coin coin : coins) {
            if (playerObjectCollider(player, coin)) {
                return coin;
            }
        }
        return null;
    }

    private boolean wallCollider(Player player) {
        for (Wall wall : walls) {
            if (playerObjectCollider(player, wall))
            return true;
        }
        return false;
    }

    private boolean monsterCollider(Player player) {
        return playerObjectCollider(player, monster);
    }

    public void update(long deltaMillis) throws Exception {
        Coin hitCoin = coinsCollider();
        if (hitCoin != null) {
            sumOfCoins = sumOfCoins + hitCoin.getCoinId();
            coins.remove(hitCoin);
        }

        if (sumOfCoins == 55) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/youWonStage.fxml"));
            Parent gameFinished = loader.load();

            YouWonStageController controller = loader.getController();

            Controller.timer.stop();

            controller.setTextScore(sumOfCoins);

            controller.post(sumOfCoins);

            Main.primaryStage.setScene(new Scene(gameFinished, 600, 600));
        }
    }

    public boolean canMove(int x, int y) {
        Player dummyPlayer = new Player(x, y);
        if (wallCollider(dummyPlayer)) {
            return false;
        }

        if (monsterCollider(dummyPlayer)) {
            return false;
        }
        return true;
    }
}