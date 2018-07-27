package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;

import static com.badlogic.gdx.Gdx.input;


/**
 * Created by codecadet on 26/07/2018.
 */
public class MainMenuScreen implements Screen {

    final TheGame game;

    private OrthographicCamera camera;

    private Texture backgroundImage;
    private Rectangle background;

    private Texture banner1Image;
    private Rectangle banner1;

    private Texture startImage;
    private Rectangle start;


    public MainMenuScreen(TheGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        this.backgroundImage = new Texture(Gdx.files.internal("menupics/toymenu.jpg"));
        this.startImage = new Texture(Gdx.files.internal("menupics/start.png"));
        this.background = new Rectangle();
        this.start = new Rectangle();

        banner1 = new Rectangle();

        background.x = 0;
        background.y = 0;

        start.x = 100;
        start.y = 150;

        banner1.x = 0;
        banner1.y = 0;

        this.banner1Image = new Texture(Gdx.files.internal("menupics/kisses.png"));


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
        game.batch.draw(backgroundImage, background.x, background.y);
        game.batch.draw(startImage, start.x, start.y);

        //game.font.draw(game.batch, "O Jogo do TOY!!!!", 100, 150);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new IntroLevelOne(game));
        }

    }

        /*if(Gdx.input.isTouched()){
            game.setScreen(new ToyGame(game));
            dispose();
        }*/


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
        backgroundImage.dispose();
        startImage.dispose();
    }
}
