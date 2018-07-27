package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.Gdx.input;

/**
 * Created by codecadet on 27/07/2018.
 */
public class IntroLevelOne implements Screen {

    private OrthographicCamera camera;
    private Texture backGroundImage;
    private Rectangle background;
    private TheGame game;

    public IntroLevelOne(TheGame game) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        this.backGroundImage = new Texture(Gdx.files.internal("menupics/kisses.png"));
        this.background = new Rectangle();
        this.game = game;
    }


    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backGroundImage, background.x, background.y);
        game.batch.end();


        if (input.isKeyPressed(Input.Keys.SPACE)) {

            game.setScreen((new InstructionsScreen(game)));
        }
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
    public void dispose() {
        backGroundImage.dispose();
    }
}
