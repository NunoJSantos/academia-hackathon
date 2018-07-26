package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import java.awt.*;

/**
 * Created by codecadet on 26/07/2018.
 */
public class SensualWoman {

    private Rectangle sensualWoman;
    private Texture womenImage;

    public SensualWoman() {
        this.sensualWoman = new Rectangle();
        sensualWoman.x = 20;
        sensualWoman.y = 768 / 2 - 64 / 2;
        sensualWoman.width = 128;
        sensualWoman.height = 128;
        womenImage = new Texture(Gdx.files.internal("sharonStone.jpg"));

    }

    public void move() {
        sensualWoman.y = MathUtils.random(0, 768-128);
    }

    public Texture getWomanImage() {
        return womenImage;
    }

    public Rectangle getSensualWoman() {
        return sensualWoman;
    }
}




