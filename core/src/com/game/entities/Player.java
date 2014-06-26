package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.game.Game;

public class Player extends B2DSprite{
	
	private int numCoins;
	private int totalCoins;
	public static TextureRegion[] sprites;
	private boolean stayAnim, rRunAnim;
	
	public Player(Body body) {
		
		super(body);
		
		Texture tex = Game.res.getTexture("uni");
		sprites = TextureRegion.split(tex, 32, 32)[0];
		
		setAnimation(sprites, 1 / 12f);
		
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();
	}
	
	public void collectCoins(){numCoins++;}
	public int getNumCoins(){return numCoins;}
	public void setTotalCoins(int i){totalCoins = i;}
	public int getTotalCoins(){return totalCoins;}

}
