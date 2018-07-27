package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GameOverScreen implements Screen {

    final TheGame game;

    private OrthographicCamera camera;

    //TODO resize or change image file
    private Texture backgroundImage;
    private Rectangle background;
    TextField nameField;

    public GameOverScreen(TheGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        backgroundImage = new Texture(Gdx.files.internal("gameover.png"));

        background = new Rectangle();
        background.x = 0;
        background.y = 0;

        //UserTextInputListener listener = new UserTextInputListener();
        //Gdx.input.getTextInput(listener, "Enter your name!", "Initial", "AAA");

        //TODO maybe use this to log the scores
        /*
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.CHARTREUSE;
        nameField = new TextField("asdf", style);
        nameField.setText("Test");
        nameField.setWidth(150);
        nameField.setX(20);
        nameField.setY(400);
        */


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

        game.batch.draw(backgroundImage, background.x, background.y);
        game.font.draw(game.batch, "GAME OVER", 100, 150);
        //nameField.draw(game.batch, 10f);

        game.batch.end();

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
        backgroundImage.dispose();
    }
}
