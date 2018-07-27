package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.net.MalformedURLException;

public class ToyGame implements Screen {

    SpriteBatch batch;
    private Toy toy;
    private SensualWoman sensualWoman;
    private OrthographicCamera camera;
    private com.badlogic.gdx.utils.Array<Weapon> weapons;
    private Laser laser;
    private boolean createWeapon;
    private int countWeapon = 0;
    private Music themeGame;
    private Sound womanScream;
    private Sound toySpeech;

    private TheGame game;

    private Texture life1image;
    private Rectangle life1;

    private Texture life2image;
    private Rectangle life2;

    private Texture life3image;
    private Rectangle life3;


    private Texture[] enemyLifesImages;
    private Rectangle[] enemyLifes;
    private Texture background;

    private int counter = 0;
    private long time = TimeUtils.millis();


    int initialPosition = 990;

    private Projectile bottles;

    public ToyGame(TheGame game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("background.jpg"));


        themeGame = Gdx.audio.newMusic(Gdx.files.internal("themeGame.wav"));
        womanScream = Gdx.audio.newSound(Gdx.files.internal("womanScream.wav"));
        toySpeech = Gdx.audio.newSound(Gdx.files.internal("toySpeech.wav"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        batch = new SpriteBatch();
        toy = new Toy();
        sensualWoman = new SensualWoman();
        laser = new Laser(sensualWoman);
        laser.spawnLasers();

        weapons = new com.badlogic.gdx.utils.Array<Weapon>();
        themeGame.setLooping(true);
        themeGame.play();
        bottles = new Projectile();
        bottles.spawnBottles();

        this.life1image = new Texture(Gdx.files.internal("gamepics/life.png"));
        this.life1 = new Rectangle();
        this.life2image = new Texture(Gdx.files.internal("gamepics/life.png"));
        this.life2 = new Rectangle();
        this.life3image = new Texture(Gdx.files.internal("gamepics/life.png"));
        this.life3 = new Rectangle();

        this.enemyLifesImages = new Texture[sensualWoman.getLife()];
        this.enemyLifes = new Rectangle[sensualWoman.getLife()];

        for (int i = 0; i < sensualWoman.getLife(); i++) {
            enemyLifesImages[i] = new Texture(Gdx.files.internal("gamepics/kiss.png"));
            enemyLifes[i] = new Rectangle();
            enemyLifes[i].x = initialPosition;
            enemyLifes[i].y = 720;
            initialPosition -= 22;
        }

        life1.x = 20;
        life1.y = 710;
        life2.x = 50;
        life2.y = 710;
        life3.x = 80;
        life3.y = 710;


        //img = new Texture(name of grid); --> put image of background

    }

    @Override
    public void show() {

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
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.batch.draw(background, 0, 0);

        game.batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());
        if (TimeUtils.timeSinceMillis(time) > 9999) {
            game.batch.draw(sensualWoman.getWomanImage(), sensualWoman.getSensualWoman().getX(), sensualWoman.getSensualWoman().getY());
        }

        game.font.draw(game.batch, "Score: " + toy.getScore(), 485, 740);

        if (toy.getLifes() >= 1) {
            game.batch.draw(life1image, life1.x, life1.y);
        }
        if (toy.getLifes() >= 2) {
            game.batch.draw(life2image, life2.x, life2.y);
        }
        if (toy.getLifes() >= 3) {
            game.batch.draw(life3image, life3.x, life3.y);
        }


        for (int i = 0; i < sensualWoman.getLife(); i++) {
            game.batch.draw(enemyLifesImages[i], enemyLifes[i].x, enemyLifes[i].y);
        }

        game.batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (!createWeapon) weapons.add(new Weapon(toy));
            toySpeech.play();
            createWeapon = true;
        }

        if (createWeapon == true) {
            countWeapon++;

        }

        if (countWeapon >= 60) {

            countWeapon = 0;
            createWeapon = false;
        }

        for (Weapon weapon : weapons) {

            game.batch.draw(weapon.getWeaponTexture(), weapon.getWeapon().getX(), weapon.getWeapon().getY());

        }

        for (Rectangle bottle : bottles.getBottles()) {
            game.batch.draw(bottles.getBottleImage(), bottle.x, bottle.y);
        }
        for (Rectangle lasers : laser.getLasers()) {
            game.batch.draw(laser.getLaserImage(), lasers.x, lasers.y);
        }
        game.batch.end();
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        bottles.checkTime();

        laser.checkTime();

        if (weapons.size != 0) {

            for (Weapon weapon : weapons) {
                weapon.move(toy);
            }
        }
        laser.move();
        toy.move();
        sensualWoman.move();
        bottles.move();

        for (Weapon weapon : weapons) {
            bottles.collisionWithWeapons(weapon, toy);
        }

        bottles.collisionWithToy(toy);

        laser.collisionDetection(toy);

        for (Weapon weapon : weapons) {
            if (weapon.getWeapon().overlaps(sensualWoman.getSensualWoman())) {
                womanScream.play();
                sensualWoman.hitWoman();
                toy.incrementScore(100);
                //System.out.println("shot her");
                weapon.getWeapon().set(0, -200, 30, 30);
                weapon.getWeaponTexture().dispose();
            }
        }
        if (sensualWoman.getLife() <= 0) {
            sensualWoman.dispose();

            game.setScreen(new WinScreen(game, toy.getScore()));

        }

        if (toy.getLifes() <= 0) {


            try {
                themeGame.stop();
                game.setScreen(new GameOverScreen(game, toy.getScore()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void dispose() {
        bottles.dispose();
    }
}
