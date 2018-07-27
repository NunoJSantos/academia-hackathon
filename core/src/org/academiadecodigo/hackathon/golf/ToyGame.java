package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Array;

public class ToyGame implements Screen {
    SpriteBatch batch;
    Texture img;
    private Toy toy;
    private SensualWoman sensualWoman;
    private OrthographicCamera camera;
    private com.badlogic.gdx.utils.Array<Weapon> weapons;
    private int hitWoman = 0;
    private Laser laser;
    private boolean createWeapon;
    private int countWeapon = 0;

    private TheGame game;

    private Projectile bottles;

    public ToyGame(TheGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        batch = new SpriteBatch();
        toy = new Toy();
        sensualWoman = new SensualWoman();
        laser = new Laser(sensualWoman);
        laser.spawnLasers();

        weapons = new com.badlogic.gdx.utils.Array<>();
        bottles = new Projectile();
        bottles.spawnBottles();

        //img = new Texture(name of grid); --> put image of background
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());
        game.batch.draw(sensualWoman.getWomanImage(), ((float) (sensualWoman.getSensualWoman().getX())), ((float) sensualWoman.getSensualWoman().getY()));


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (!createWeapon)weapons.add(new Weapon(toy));
            createWeapon = true;
        }

        if (createWeapon == true) {
            countWeapon++;

        }

        if(countWeapon >= 60){

            countWeapon = 0;
            createWeapon = false;
        }

        for (Weapon weapon : weapons) {

            game.batch.draw(weapon.getWeaponTexture(), weapon.getWeapon().getX(), weapon.getWeapon().getY());

        }

        for (Rectangle bottle : bottles.getBottles()) {
            game.batch.draw(bottles.getBottleImage(), bottle.x, bottle.y);
        }
        for (Rectangle lasers : laser.getLasers()) {
            game.batch.draw(laser.getLaserImage(), lasers.x, lasers.y);
        }
        game.batch.draw(sensualWoman.getWomanImage(), ((sensualWoman.getSensualWoman().getX())), (sensualWoman.getSensualWoman().getY()));
        game.batch.end();
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        bottles.checkTime();
        laser.checkTime();

        if (weapons.size != 0) {

            for (Weapon weapon : weapons) {
                weapon.move(toy);
            }
        }
        laser.move();
        toy.move();
        sensualWoman.move();
        bottles.move();

        for (Weapon weapon : weapons) {

            bottles.collisionDetection(toy, weapon);
        }
        laser.collisionDetection(toy);

        for (Weapon weapon : weapons) {
            if (weapon.getWeapon().overlaps(sensualWoman.getSensualWoman())) {
                hitWoman++;
                weapon.getWeapon().set(1025, toy.getToy().getY(), 30, 30);
                weapon.getWeaponTexture().dispose();
            }
        }
        if (hitWoman >= 3) {
            sensualWoman.dispose();
        }
    }

    @Override
    public void dispose() {
        bottles.dispose();
    }
}
