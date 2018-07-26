package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by codecadet on 26/07/2018.
 */
public class Projectile {
    private Array<Rectangle> bottles;

    private Texture bottleImage;
    private long lastThrowTime;

    public Projectile() {
        this.bottles = new Array<>();
        this.bottleImage = new Texture(Gdx.files.internal("bottle.jpg"));
    }

    public void render() {
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();
            bottle.x -= 200 * Gdx.graphics.getDeltaTime();

            if (bottle.x < 0) {
                iter.remove();
            }
        }
    }

    public Texture getBottleImage() {
        return bottleImage;
    }

    public Array<Rectangle> getBottles() {
        return bottles;
    }

    public void dispose(){
        bottleImage.dispose();
    }

    public void checkTime(){
        if (TimeUtils.nanoTime() - lastThrowTime > 1000000000) {
            spawnBottles();
        }
    }

    public void spawnBottles() {
        Rectangle bottle = new Rectangle();
        bottle.x = 1024;
        bottle.y = MathUtils.random(0, 768 - 39);
        bottle.width = 30;
        bottle.height = 39;
        bottles.add(bottle);
        lastThrowTime = TimeUtils.nanoTime();

    }

}
