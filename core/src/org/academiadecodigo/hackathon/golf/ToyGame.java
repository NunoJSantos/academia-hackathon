package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ToyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Toy toy;
	private SensualWoman sensualWoman;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024,768);
		batch = new SpriteBatch();
		toy = new Toy();
		sensualWoman = new SensualWoman();


		//img = new Texture(name of grid); --> put image of background
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());
		batch.draw(sensualWoman.getWomanImage(), ((float)(sensualWoman.getSensualWoman().getX())) ,((float)sensualWoman.getSensualWoman().getY()) );
		batch.end();
		camera.update();

		toy.move();

		sensualWoman.move();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
