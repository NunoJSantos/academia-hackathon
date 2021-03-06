package org.academiadecodigo.hackathon.golf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by codecadet on 26/07/2018.
 */
public class Toy {

    private Rectangle toy;
    private Texture toyImage;
    private int lifes;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Toy(){
        this.toy = new Rectangle();
        this.lifes = 3;
        toy.x = 20;
        toy.y = 768/2 - 120/2;
        toy.width = 50;
        toy.height = 120;
        toyImage = new Texture(Gdx.files.internal("toy.png"));
    }

    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) toy.y -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) toy.y += 200 * Gdx.graphics.getDeltaTime();
        if(toy.y < 16) toy.y = 15;
        if(toy.y > 768 - 120 - 80) toy.y = 768 - 120 - 80;
    }

    public void incrementScore(int increment){
        score += increment;
    }


    public Texture getToyImage() {
        return toyImage;
    }

    public Rectangle getToy() {
        return toy;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
}
