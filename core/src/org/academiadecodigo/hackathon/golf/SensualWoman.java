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
    int life = 10;

    public SensualWoman() {
        this.sensualWoman = new Rectangle();
        sensualWoman.x = 920;
        sensualWoman.y = 768 / 2 - 64 / 2;
        sensualWoman.width = 56;
        sensualWoman.height = 157;
        womenImage = new Texture(Gdx.files.internal("jessica1_600x600.png"));
    }

    public void move() {

        i++;

        if (i % 80 == 0) {
            sensualWoman.y = MathUtils.random(0, 768 - 248);
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

    public int getLife() {
        return life;
    }

    public void hitWoman(){
        life--;
    }
}




