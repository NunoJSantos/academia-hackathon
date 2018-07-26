package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ToyGame extends ApplicationAdapter {
    SpriteBatch batch;

    private OrthographicCamera camera;
    private Projectile bottles;


    @Override
    public void create() {
        batch = new SpriteBatch();

        bottles = new Projectile();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

        bottles.spawnBottles();

    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        bottles.checkTime();


        bottles.render();

        batch.begin();
        for(Rectangle bottle: bottles.getBottles()){
            batch.draw(bottles.getBottleImage(), bottle.x, bottle.y);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bottles.dispose();

    }


}
