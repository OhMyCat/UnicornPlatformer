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
		
		Texture tex = Game.res.getTexture("bunny");
		sprites = TextureRegion.split(tex, 32, 32)[0];
	}
	
	public void setStayAnimation(){
		if(!stayAnim){
			setAnimation(sprites, 1/12f, 1);
			stayAnim = true;
			rRunAnim = false;
		}
	}
	
	public void setRRunAnimation(){
		if(!rRunAnim){
			setAnimation(sprites, 1/12f, 3);
			rRunAnim = true;
			stayAnim = false;
		}
	}
	
	public void collectCoins(){numCoins++;}
	public int getNumCoins(){return numCoins;}
	public void setTotalCoins(int i){totalCoins = i;}
	public int getTotalCoins(){return totalCoins;}

}
