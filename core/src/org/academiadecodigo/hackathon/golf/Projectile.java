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
        this.bottleImage = new Texture(Gdx.files.internal("moscatel.png"));
    }

    public void move() {
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();
            bottle.x -= 300 * Gdx.graphics.getDeltaTime();

        }
    }

    public void collisionDetection(Toy toy, Weapon weapon){
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();

            if (bottle.x < 0) {
                iter.remove();
            }

            if (bottle.overlaps(toy.getToy())) {
                bottle.set(1024, MathUtils.random(0, 768 - 38), 25, 39);
                toy.setLifes(toy.getLifes() - 1);
            }

            if (weapon.getWeapon().overlaps(bottle)) {

                bottle.set(1024, MathUtils.random(0, 768 - 38), 25, 39);
                weapon.getWeapon().set(1025, 400, 25,
                        30);

                weapon.getWeaponTexture().dispose();

            }
            if (toy.getLifes() == 0) {
                toy.getToyImage().dispose();
            }
        }
    }



    public void render(Weapon weapon) {


    }

    public Texture getBottleImage() {
        return bottleImage;
    }

    public Array<Rectangle> getBottles() {
        return bottles;
    }

    public void dispose() {
        bottleImage.dispose();
    }

    public void checkTime() {
        if (TimeUtils.nanoTime() - lastThrowTime > 1000000000) {
            spawnBottles();
        }
    }

    public void spawnBottles() {
        Rectangle bottle = new Rectangle();
        bottle.x = 1024;
        bottle.y = MathUtils.random(0, 768 - 38);
        bottle.width = 25;
        bottle.height = 38;
        bottles.add(bottle);
        lastThrowTime = TimeUtils.nanoTime();

    }

}
