package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    private Sound bottleCrash;

    public Projectile() {
        this.bottles = new Array<Rectangle>();
        this.bottleImage = new Texture(Gdx.files.internal("moscatel.png"));
        this.bottleCrash = Gdx.audio.newSound(Gdx.files.internal("bottleCrash.wav"));
    }

    public void move() {
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {

            Rectangle bottle = iter.next();
            bottle.x -= 200 * Gdx.graphics.getDeltaTime();

            if(bottle.y > 768 - 38 - 80){
                bottle.set(768-38-80,bottle.x,bottle.getWidth(),bottle.getHeight());
            }

        }
    }

    public void collisionWithToy(Toy toy){
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();


            if (bottle.x <= 16) {
                iter.remove();
            }

            if (bottle.overlaps(toy.getToy())) {
                bottle.set(1024, MathUtils.random(0, 768 - 38 - 80), 25, 39);
                toy.setLifes(toy.getLifes() - 1);

            }
            if (toy.getLifes() == 0) {
                toy.getToyImage().dispose();
            }
        }
    }

    public void collisionWithWeapons(Weapon weapon, Toy toy){
        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();

            if (bottle.x <= 16) {
                iter.remove();
            }

            if (weapon.getWeapon().overlaps(bottle)) {
                bottleCrash.play();
                toy.incrementScore(10);
                bottle.set(1024, MathUtils.random(0, 768 - 38 - 80), 30, 39);
                weapon.getWeapon().set(toy.getToy().getX(), toy.getToy().getY(), 30, 30);
                weapon.changeWeapon();
                weapon.setMoving(false);

                bottle.set(1024, MathUtils.random(0, 768 - 38-80), 25, 39);
                weapon.getWeapon().set(1025, 400, 25, 30);

                weapon.getWeaponTexture().dispose();

            }

        }
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
        bottle.y = MathUtils.random(15, 768 - 38);
        bottle.width = 25;
        bottle.height = 38;
        bottles.add(bottle);
        lastThrowTime = TimeUtils.nanoTime();

    }

}
