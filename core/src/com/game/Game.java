package com.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.handlers.ResourceManager;
import com.game.handlers.GameStateManager;
import com.game.handlers.MyInput;
import com.game.handlers.MyInputProcessor;

public class Game extends ApplicationAdapter {
	
	public static final String TITLE = "Platformer";
	public static int W_WIDTH;
	public static int W_HEIGHT;
	
	public static final float STEP = 1/60f;
	private float accum;
	
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	private BitmapFont font;
	
	private GameStateManager gsm;
	public static ResourceManager res;
	
	public SpriteBatch getSpriteBatch(){return batch;};
	public OrthographicCamera getCamera(){return cam;};
	public OrthographicCamera getHUDCamera(){return hudCam;};
	
	public void create () {
		
		
		W_HEIGHT = Gdx.graphics.getHeight();
		W_WIDTH = Gdx.graphics.getWidth();
		
		font = new BitmapFont();

		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new ResourceManager();
		res.loadTexture("images/bunny.png", "bunny");
		res.loadTexture("images/coin.png", "coin");
		res.loadTexture("images/hud.png", "hud");
		res.loadTexture("images/smalluni.png", "uni");
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, W_WIDTH, W_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, W_WIDTH, W_HEIGHT);
		
		gsm = new GameStateManager(this);

	}

	public void render () {
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP){
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
		}

		// draw FPS
	    batch.begin();
	    font.draw(batch,"fps: " + Gdx.graphics.getFramesPerSecond(),Game.W_WIDTH - 50, Game.W_HEIGHT - 10);
	    batch.end(); 
	}
	
	public void dispose () {
		gsm.dispose();
	}
	
}
