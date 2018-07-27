package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by codecadet on 26/07/2018.
 */
public class TheGame extends Game{


    public SpriteBatch batch;
    public BitmapFont font;
    private String userName;
    private Persistence persistence;


    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
        this.persistence = new Persistence();
    }

    public void render(){
        super.render();     //This is necessary
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Persistence getPersistence() {
        return persistence;
    }
}
