package com.game.entities;

import static com.game.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Bullet {
	
	private Player player;
	private World world;
	private Array<Body> bullets;
	
	public Bullet(Player player) {
		this.player = player;
	}
	
	public void render(SpriteBatch batch){
		batch.begin();
		
		batch.end();
	}
	
	public void addBullet(){
		bullets = new Array<Body>();
	}
	
	public void createBulletBody(){
		
	}

}
