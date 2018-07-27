package org.academiadecodigo.hackathon.golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class ToyGame implements Screen {
    SpriteBatch batch;
    private Toy toy;
    private SensualWoman sensualWoman;
    private OrthographicCamera camera;
    private Weapon weapon;
    private Laser laser;

    private TheGame game;

	private Texture life1image;
	private Rectangle life1;

	private Texture life2image;
	private Rectangle life2;

	private Texture life3image;
	private Rectangle life3;

	private Texture topBarImage;
	private Rectangle topbar;

	private Texture[] enemyLifesImages;
	private Rectangle[] enemyLifes;


	int initialPosition = 990;

    private Projectile bottles;

    public ToyGame(TheGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        batch = new SpriteBatch();
        toy = new Toy();
        sensualWoman = new SensualWoman();
        weapon = new Weapon(toy);
        laser = new Laser(sensualWoman);
        laser.spawnLasers();

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

		for(int i = 0; i < sensualWoman.getLife(); i++){
			enemyLifesImages[i] = new Texture(Gdx.files.internal("gamepics/lifeEnemy.png"));
			enemyLifes[i] = new Rectangle();
			enemyLifes[i].x = initialPosition;
			enemyLifes[i].y = 720;
			initialPosition -= 22;
		}




		this.topBarImage = new Texture(Gdx.files.internal("gamepics/topbar.png"));
		this.topbar= new Rectangle();

		life1.x = 20;
		life1.y = 710;
		life2.x = 50;
		life2.y = 710;
		life3.x = 80;
		life3.y = 710;

		topbar.x = 0;
		topbar.y = 688;


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
        game.batch.draw(weapon.getWeaponTexture(), weapon.getWeapon().getX(), weapon.getWeapon().getY());
        game.batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());
        game.batch.draw(sensualWoman.getWomanImage(), ((float) (sensualWoman.getSensualWoman().getX())), ((float) sensualWoman.getSensualWoman().getY()));

        game.batch.draw(topBarImage, topbar.x, topbar.y);

        if(toy.getLifes() >= 1) {
            game.batch.draw(life1image, life1.x, life1.y);
        }
        if(toy.getLifes()>= 2) {
            game.batch.draw(life2image, life2.x, life2.y);
        }
        if(toy.getLifes() >= 3) {
            game.batch.draw(life3image, life3.x, life3.y);
        }

        for (int i = 0; i < sensualWoman.getLife(); i ++){
            game.batch.draw(enemyLifesImages[i], enemyLifes[i].x, enemyLifes[i].y);
        }

        game.batch.draw(weapon.getWeaponTexture(),weapon.getWeapon().getX(),weapon.getWeapon().getY());
        game.batch.draw(toy.getToyImage(), toy.getToy().getX(), toy.getToy().getY());
        game.batch.draw(sensualWoman.getWomanImage(), ((float)(sensualWoman.getSensualWoman().getX())) ,((float)sensualWoman.getSensualWoman().getY()) );


        for (Rectangle bottle : bottles.getBottles()) {
            game.batch.draw(bottles.getBottleImage(), bottle.x, bottle.y);
        }
        for (Rectangle lasers : laser.getLasers()) {
            game.batch.draw(laser.getLaserImage(), lasers.x,lasers.y);
        }
        game.batch.draw(sensualWoman.getWomanImage(), ((sensualWoman.getSensualWoman().getX())), (sensualWoman.getSensualWoman().getY()));
        game.batch.end();
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        bottles.checkTime();

        laser.checkTime();

        laser.move();

        toy.move();
        weapon.move(toy);
        sensualWoman.move();
        bottles.move();

        bottles.collisionDetection(toy, weapon);
        laser.collisionDetection(toy);

        if (weapon.getWeapon().overlaps(sensualWoman.getSensualWoman())) {
            sensualWoman.hitWomen();
            weapon.getWeapon().set(toy.getToy().getX(), toy.getToy().getY(), 30, 30);
            weapon.setMoving(false);
        }
        if (sensualWoman.getLife() <= 0) {
            sensualWoman.dispose();
            game.setScreen(new GameOverScreen(game));
        }

		if(toy.getLifes() <= 0) {
			game.setScreen(new GameOverScreen(game));
		}

	}
	
	@Override
	public void dispose () {
        bottles.dispose();
    }
}
