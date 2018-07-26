package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class ToyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    private Array<Rectangle> bottles;
    private long lastThrowTime;

    private Texture bottleImage;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bottleImage = new Texture(Gdx.files.internal("bottle.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

        bottles = new Array<Rectangle>();
        spawnBottles();


    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        //batch.begin();
        //batch.draw(bottleImage, 0, 0);
        //batch.end();

        if (TimeUtils.nanoTime() - lastThrowTime > 1000000000) {
            spawnBottles();
        }

        for (Iterator<Rectangle> iter = bottles.iterator(); iter.hasNext(); ) {
            Rectangle bottle = iter.next();
            bottle.x -= 200 * Gdx.graphics.getDeltaTime();

            if (bottle.x < 0) {
                iter.remove();
            }
        }

        batch.begin();
        for(Rectangle bottle: bottles){
            batch.draw(bottleImage, bottle.x, bottle.y);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bottleImage.dispose();
    }

    private void spawnBottles() {
        Rectangle bottle = new Rectangle();
        bottle.x = 800;
        bottle.y = MathUtils.random(0, 480 - 39);
        bottle.width = 30;
        bottle.height = 39;
        bottles.add(bottle);
        lastThrowTime = TimeUtils.nanoTime();

    }
}
