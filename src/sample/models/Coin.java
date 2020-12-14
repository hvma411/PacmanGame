package sample.models;

import javafx.scene.paint.Color;

public class Coin extends GameObject {

    private int coinId;

    public Coin(int x, int y, int coinId) {
        super(x, y, 20, 20, Color.GOLD);
        this.coinId = coinId;
    }

    public int getCoinId() {
        return coinId;
    }
}