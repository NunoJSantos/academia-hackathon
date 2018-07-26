package org.academiadecodigo.hackathon.golf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by codecadet on 26/07/2018.
 */
public class Toy {

    private Rectangle toy;
    private Texture toyImage;


    public Toy(){
        this.toy = new Rectangle();
        toy.x = 20;
        toy.y = 768/2 - 64/2;
        toy.width = 64;
        toy.height = 64;
        toyImage = new Texture(Gdx.files.internal("toy.jpg"));
    }

    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) toy.y -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) toy.y += 200 * Gdx.graphics.getDeltaTime();
        if(toy.y < 0) toy.y = 0;
        if(toy.y > 768 - 64) toy.y = 768 - 64;
    }


    public Texture getToyImage() {
        return toyImage;
    }

    public Rectangle getToy() {
        return toy;
    }
}
