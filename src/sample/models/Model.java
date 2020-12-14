package sample.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    int width;
    int height;

    private Player player = new Player(100, 100);
    private List<Wall> walls = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();

    /**
     * The constructor creates the playing field with all objects.
     *
     * @param width
     * @param height
     */
    public Model(int width, int height) {
        this.width = width;
        this.height = height;

        init(width, height);
        initWalls(100, 200);
    }

    public Player getPlayer() {
        return player;
    }

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
        int numberOfCoins = 60;
        int coinDiameter = 60;
        for (int i = 0; i < numberOfCoins; i++) {
            int x = positionsGenerator.nextInt(width - coinDiameter) + coinDiameter / 2;
            int y = positionsGenerator.nextInt(height - coinDiameter) + coinDiameter / 2;
            Coin coin = new Coin(x, y, i + 1);
            this.coins.add(coin);
        }
    }

    private void initWalls(int width, int height) {
        int numberOfWalls = 16;
        int heightOfWall = 600;
        int widthOfWall = 5;
        int positionX = 0;
        int positionY = 0;

        for (int i = 0; i < numberOfWalls; i++) {
            if (i % 2 == 0) {
                Wall wall = new Wall(positionX, positionY, heightOfWall, widthOfWall);
                this.walls.add(wall);
                positionY = positionY + 60;
                positionX = positionX + 30;


            } else if (i % 2 != 0) {
                Wall wall = new Wall(positionX, positionY + 40, widthOfWall, heightOfWall - 50);
                this.walls.add(wall);
                positionX = positionX + 30;
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

    public void update(long deltaMillis) {

        Coin hitCoin = coinsCollider();
        if (hitCoin != null) {
            System.out.println("KA-SHING! " + hitCoin.getCoinId());
            coins.remove(hitCoin);
        }
    }

    public boolean canMove(int x, int y) {
        Player dummyPlayer = new Player(x, y);
        if (wallCollider(dummyPlayer)) {

            return false;
        }
        return true;
    }

}