package com.game.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	private static int animationLength;
	
	public Animation(){}
	
	public Animation(TextureRegion[] frames){
		this(frames, 1 / 12f, frames.length);
	}
	
	public Animation(TextureRegion[] frames, float delay, int aLength){
		setFrames(frames, delay, aLength);
	}
	
	public void setFrames(TextureRegion[] frames, float delay, int aLength){
		this.frames = frames;
		this.delay = delay;
		time = 0;
		currentFrame = 0;
		timesPlayed = 0;
		animationLength = aLength;
	}
	
	public void update(float dt){
		if(delay <= 0) return;
		time += dt;
		while(time >= delay){
			step(animationLength);
		}
	}
	
	private void step(int length){
		time -= delay;
		currentFrame++;
		if(currentFrame == length){
			currentFrame = 0;
			timesPlayed++;
		}
	}
	
	public TextureRegion getFrame(){return frames[currentFrame];}
	public int getTimesPlayed(){return timesPlayed;}

}
