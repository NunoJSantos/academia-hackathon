package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import javafx.embed.swing.SwingFXUtils;



import static com.badlogic.gdx.Gdx.input;

/**
 * Created by codecadet on 27/07/2018.
 */
public class InstructionsScreen implements Screen{

    private TheGame game;

    private OrthographicCamera camera;

    private Texture bannerTexture;
    private Rectangle rectangle;


    public InstructionsScreen(TheGame game){
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,1024,768);

        bannerTexture = new Texture(Gdx.files.internal("instructions banner.jpg"));

        rectangle = new Rectangle();
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bannerTexture, rectangle.x, rectangle.y);

        game.batch.end();

        if (input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new ToyGame( game));
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

    }
}
