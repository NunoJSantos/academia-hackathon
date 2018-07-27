package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by codecadet on 26/07/2018.
 */
public class Laser {

    private Array<Rectangle> lasers;

    private Texture laserImage;
    private long lastThrowTime;
    private SensualWoman woman;
    private Rectangle laserRec;
    private Sound laserEffect;

    public Laser(SensualWoman woman) {
        this.lasers = new Array<Rectangle>();
        this.laserImage = new Texture(Gdx.files.internal("laser.png"));
        this.woman = woman;
        this.laserEffect = Gdx.audio.newSound(Gdx.files.internal("laserEffect.wav"));

    }

    public void move() {
        for (Iterator<Rectangle> iter = lasers.iterator(); iter.hasNext(); ) {
            Rectangle laser = iter.next();
            laser.x -= 400 * Gdx.graphics.getDeltaTime();

        }
    }

    public void collisionDetection(Toy toy) {
        for (Iterator<Rectangle> iter = lasers.iterator(); iter.hasNext(); ) {
            Rectangle laser = iter.next();

            if (laser.x < 16) {
                iter.remove();
            }

            if (laser.overlaps(toy.getToy())) {
                laser.set(-80, woman.getSensualWoman().getY() + 120, 30, 39);
                toy.setLifes(toy.getLifes() - 1);
            }


            if (toy.getLifes() == 0) {
                toy.getToyImage().dispose();
            }
        }
    }


    public void render(Weapon weapon) {

    }

    public Texture getLaserImage() {
        return laserImage;
    }


    public Array<Rectangle> getLasers() {
        return lasers;
    }


    public void dispose() {
        laserImage.dispose();
    }

    public void checkTime() {
        if (TimeUtils.nanoTime() - lastThrowTime > 1000000000) {
            spawnLasers();
        }
    }

    public void spawnLasers() {
        laserRec = new Rectangle();
        laserRec.x = woman.getSensualWoman().getX() - 30;
        laserRec.y = woman.getSensualWoman().getY() + 120;
        laserRec.width = 80;
        laserRec.height = 20;
        lasers.add(laserRec);

        laserEffect.play(1);
        //laserEffect.setVolume(1, );
        lastThrowTime = TimeUtils.nanoTime();

    }
}

