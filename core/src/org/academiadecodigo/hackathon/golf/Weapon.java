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
    private Sound toySpeech;


    private boolean moving = false;

    public Weapon(Toy toy) {

        int i = (int) Math.floor(Math.random() * 2) + 1;
        weaponTexture = new Texture(Gdx.files.internal("weapon" + i + ".png"));
        toySpeech = Gdx.audio.newSound(Gdx.files.internal("toySpeech.wav"));
        this.weapon = new Rectangle();
        weapon.x = toy.getToy().getX();
        weapon.y = toy.getToy().getY();
        weapon.width = 30;
        weapon.height = 30;

    }

    public void move(Toy toy) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            toySpeech.play();
            moving = true;
            System.out.println("weapon created");
        }

        if (weapon.x > 1024) {
            int i = (int) Math.floor(Math.random() * 2) + 1;
            weaponTexture.dispose();
            weaponTexture = new Texture(Gdx.files.internal("weapon" + i + ".png"));
            moving = false;
        }

        if (moving) {
            weapon.x += 800 * Gdx.graphics.getDeltaTime();
            return;

        }

        weapon.x = toy.getToy().getX();
        weapon.y = toy.getToy().getY();

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
