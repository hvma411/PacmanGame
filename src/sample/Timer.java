package sample;

import sample.models.Model;
import javafx.animation.AnimationTimer;

import java.io.IOException;

public class Timer extends AnimationTimer {

    private Model model;
    private Graphics graphics;

    long lastMillis = -1;

    public Timer(Model model, Graphics graphics) {
        this.model = model;
        this.graphics = graphics;
    }

    @Override
    public void handle(long now) {
        long millis = now / 1000000;

        long deltaMillis = 0;

        if (lastMillis != -1) {
            deltaMillis = millis - lastMillis;
        }
        try {
            this.model.update(deltaMillis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lastMillis = millis;

        graphics.draw();

    }


}