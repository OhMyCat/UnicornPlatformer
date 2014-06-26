package com.game.states;

import static com.game.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Game;
import com.game.entities.Bullet;
import com.game.entities.Player;
import com.game.handlers.GameStateManager;
import com.game.handlers.MyContactListener;
import com.game.handlers.MyInput;

public class Play extends GameState{
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private OrthographicCamera b2dCam;
	private MyContactListener cl;
	
	private TiledMap tiledMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Player player;
	private Bullet bullet;
	
	public Play(GameStateManager gsm) {
		
		super(gsm);
		
		// set up box2d stuff
		world = new World(new Vector2(0,-10), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();
		
		// create player
		createPlayer();
		
		// create tiles
		createTiles();
		
		// create bullet
		bullet = new Bullet(player);

		// set up b2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.W_WIDTH / PPM, Game.W_HEIGHT / PPM);
	}

	public void handleInput(){
		
		// player jump
		if(MyInput.isPressed(MyInput.BUTTON1)){
				player.getBody().applyForceToCenter(0, 200, true);
		}
		if(MyInput.isTouchClicked())player.getBody().applyForceToCenter(0, 200, true);
		
		// player move
		if(MyInput.isDown(MyInput.RIGHT)){
				player.getBody().setTransform(
				player.getBody().getPosition().x + 3 / PPM,
				player.getBody().getPosition().y,
				player.getBody().getAngle()
		   );
		}
		if(MyInput.isDown(MyInput.LEFT)){
				player.getBody().setTransform(
				player.getBody().getPosition().x - 3 / PPM,
				player.getBody().getPosition().y,
				player.getBody().getAngle()
		   );	
		}
	}
	
	public void update(float dt){
		
		handleInput();
		
		world.step(dt, 6, 2);
		
		player.update(dt);
		
	}
	
	public void render(){
		// clear screen
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
	    cam.update();
	    b2dCam.update();
	    
	    // draw player
	    batch.setProjectionMatrix(cam.combined);
	    player.render(batch);
	    
	    // draw bullet
	    bullet.render(batch);
	    
	    // draw tiled map
	    tmr.setView(cam);
	    tmr.render();
	    
	    
	    // draw box2d world
	    b2dr.render(world, b2dCam.combined);
	    
	}
	
	public void dispose(){
		Game.res.disposeTexture("bunny");
		world.dispose();
		tmr.dispose();
		tiledMap.dispose();
		b2dr.dispose();
	}
	
	private void createPlayer() {
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		// create player
		bdef.position.set(160 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		//bdef.linearVelocity.set(1, 0);
		Body body = world.createBody(bdef);
		
		shape.setAsBox(14 / PPM, 14 / PPM);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("player");
		
		// create foot sensor
		shape.setAsBox(13 / PPM, 4 / PPM, new Vector2(0, -14 / PPM), 0);
		fdef.isSensor = true;
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("foot");
		
		player = new Player(body);
		
		player.setRRunAnimation();

	}
	
	private void createTiles() {
		
		tiledMap = new TmxMapLoader().load("map/level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);
		tileSize = tiledMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("Platform");
		createLayer(layer);
		
	}
	
	private void createLayer(TiledMapTileLayer layer){
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		for(int row = 0; row < layer.getTileHeight(); row++){
			for(int col = 0; col < layer.getTileWidth(); col++){
				
				// get cells
				Cell cell = layer.getCell(col, row);
				
				// check cell 
				if(cell == null)continue;
				if(cell.getTile() == null)continue;
				
				// create platform body and fixture
				bdef.type = BodyType.StaticBody;
				bdef.position.set(
						(col + 0.5f) * tileSize / PPM,
						(row + 0.5f) * tileSize / PPM
				);
				
				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM - 2/PPM);
				v[2] = new Vector2(tileSize / 2 / PPM , tileSize / 2 / PPM - 2/PPM);
				cs.createChain(v);
				fdef.friction = 0;
				fdef.shape = cs;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef).setUserData("ground");
			}
		}
	}
}
