package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.*;
import com.sun.java_cup.internal.runtime.Scanner;

import java.lang.reflect.Array;

public class ToyGame implements Screen {
    SpriteBatch batch;
    Texture img;
    private Toy toy;
    private SensualWoman sensualWoman;
    private OrthographicCamera camera;
    private Weapon weapon;
    private int hitWoman = 0;

	private TheGame game;

    private Projectile bottles;

	public ToyGame(TheGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024,768);
		batch = new SpriteBatch();
		toy = new Toy();
		sensualWoman = new SensualWoman();
		weapon = new Weapon(toy);

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
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(weapon.getWeaponTexture(),weapon.getWeapon().getX(),weapon.getWeapon().getY());
		game.batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());
		game.batch.draw(sensualWoman.getWomanImage(), ((float)(sensualWoman.getSensualWoman().getX())) ,((float)sensualWoman.getSensualWoman().getY()) );

        for(Rectangle bottle: bottles.getBottles()){
            game.batch.draw(bottles.getBottleImage(), bottle.x, bottle.y);
        }
		game.batch.draw(sensualWoman.getWomanImage(), ((sensualWoman.getSensualWoman().getX())) ,(sensualWoman.getSensualWoman().getY()) );
		game.batch.end();
		camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        bottles.checkTime();


        toy.move();
        weapon.move(toy);
        sensualWoman.move();
        bottles.move();

        bottles.collisionDetection(toy, weapon);

        if (weapon.getWeapon().overlaps(sensualWoman.getSensualWoman())) {
            hitWoman++;
        }
        if (hitWoman >= 3) {
            sensualWoman.dispose();
        }

		if(toy.getLifes() <= 0) {
			game.setScreen(new GameOverScreen(game));
		}

	}
	
	@Override
	public void dispose () {
        bottles.dispose();
	}
}
