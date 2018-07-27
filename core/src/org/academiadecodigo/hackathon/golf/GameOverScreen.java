package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.input;

public class GameOverScreen implements Screen {

    final TheGame game;

    private OrthographicCamera camera;

    private Texture backgroundImage;
    private Rectangle background;
    TextField nameField;
    int score;


    public GameOverScreen(TheGame game, int score) throws MalformedURLException {
        this.game = game;
        this.score = score;

        //persistHighScore();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        backgroundImage = new Texture(Gdx.files.internal("gameover.png"));
        URL url = new URL("file://localhost/Users/codecadet/workspace/academia-hackathon/core/assets/toyvomit.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        JFrame f = new JFrame("Ó TOY!!! NÃO CAIAS!!!");
        f.getContentPane().add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        background = new Rectangle();
        background.x = 0;
        background.y = 0;


        //UserTextInputListener listener = new UserTextInputListener();
        //Gdx.input.getTextInput(listener, "Enter your name!", "Initial", "AAA");

        //TODO maybe use this to log the scores
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.CHARTREUSE;
        String endText = "You Loose!! Your Score: " + score;
        nameField = new TextField(endText, style);
        nameField.setText(endText);
        nameField.setWidth(500);
        nameField.setX(1024 / 2);
        nameField.setY(700);





    }

    private synchronized void persistHighScore() {

        Persistence persistence = new Persistence();
        persistence.createConnection();
        persistence.insertScore(game.getUserName(), score);
        persistence.close();

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
        nameField.draw(game.batch, 10f);

        game.batch.end();

        if (input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
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
        backgroundImage.dispose();
    }
}
