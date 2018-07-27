package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by codecadet on 26/07/2018.
 */
public class SensualWoman {

    private Rectangle sensualWoman;
    private Texture womenImage;
    int i = 80;

    public SensualWoman() {
        this.sensualWoman = new Rectangle();
        sensualWoman.x = 850;
        sensualWoman.y = 768 / 2 - 64 / 2;
        sensualWoman.width = 128;
        sensualWoman.height = 128;
        womenImage = new Texture(Gdx.files.internal("sharonStone.jpg"));
    }

    public void move() {

        i++;

        if (i % 80 == 0) {
            sensualWoman.y = MathUtils.random(0, 768 - 208);
        }
    }

    public Texture getWomanImage() {
        return womenImage;
    }

    public Rectangle getSensualWoman() {
        return sensualWoman;
    }

    public void dispose(){
        womenImage.dispose();
        sensualWoman.set(-130,0,0,0);
    }
}




