package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.Game;

public class HUD {
	
	private Player player;
	
	private TextureRegion buttonJ;
	
	public HUD(Player player) {
		
		this.player = player;
		
		Texture tex = Game.res.getTexture("hud");  // 41 41 
		
		buttonJ = new TextureRegion(tex, 0, 25, 41, 41);
		
	}
	
	public void render(SpriteBatch batch){
		
		batch.begin();
		batch.draw(buttonJ,Game.W_WIDTH - Game.W_WIDTH/100*10 - buttonJ.getRegionWidth(),Game.W_HEIGHT/100*10);
		batch.end();
		
	}

}
