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
        this.bottles = new Array<Rectangle>();
        this.bottleImage = new Texture(Gdx.files.internal("bottle.jpg"));
    }

    public void move() {
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();
            bottle.x -= 200 * Gdx.graphics.getDeltaTime();

        }
    }

    public void collisionDetection(Toy toy, Weapon weapon){
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();

            if (bottle.x < 0) {
                iter.remove();
            }

            if (bottle.overlaps(toy.getToy())) {
                bottle.set(1024, MathUtils.random(0, 768 - 39), 30, 39);
                toy.setLifes(toy.getLifes() - 1);
            }

            if (weapon.getWeapon().overlaps(bottle)) {

                bottle.set(1024, MathUtils.random(0, 768 - 39), 30, 39);
                weapon.getWeapon().set(toy.getToy().getX(), toy.getToy().getY(), 30, 30);
                weapon.changeWeapon();
                weapon.setMoving(false);

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
        bottle.y = MathUtils.random(0, 768 - 39);
        bottle.width = 30;
        bottle.height = 39;
        bottles.add(bottle);
        lastThrowTime = TimeUtils.nanoTime();

    }

}
