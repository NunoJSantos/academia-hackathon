package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by codecadet on 26/07/2018.
 */
public class Weapon {

    private Rectangle weapon;
    private Texture weaponTexture;


    private boolean moving = false;

    public Weapon(Toy toy) {


    int i = (int) Math.floor(Math.random() * 2) + 1;
        weaponTexture = new Texture(Gdx.files.internal("weapon" + i + ".png"));
        this.weapon = new Rectangle();
        weapon.x = toy.getToy().getX() + 15;

        weapon.y = toy.getToy().getY() + 75;
        weapon.width = 30;
        weapon.height = 30;

    }

    public void move(Toy toy) {

        weapon.x += 500 * Gdx.graphics.getDeltaTime();

        if (weapon.x > 1024) {
           weaponTexture.dispose();

        }

    }

    public Texture getWeaponTexture() {
        return weaponTexture;
    }

    public Rectangle getWeapon() {
        return weapon;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void changeWeapon() {
        int i = (int) Math.floor(Math.random() * 2) + 1;
        weaponTexture = new Texture(Gdx.files.internal("weapon" + i + ".png"));

    }
}
