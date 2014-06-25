package com.game.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter{
	
	public boolean keyDown(int k){
		if(k == Keys.Z){
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		if(k == Keys.X){
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		if(k == Keys.LEFT){
			MyInput.setKey(MyInput.LEFT, true);
		}
		if(k == Keys.RIGHT){
			MyInput.setKey(MyInput.RIGHT, true);
		}
		return true;
	}
	
	public boolean keyUp(int k){
		if(k == Keys.Z){
			MyInput.setKey(MyInput.BUTTON1, false);
		}
		if(k == Keys.X){
			MyInput.setKey(MyInput.BUTTON2, false);
		}
		if(k == Keys.LEFT){
			MyInput.setKey(MyInput.LEFT, false);
		}
		if(k == Keys.RIGHT){
			MyInput.setKey(MyInput.RIGHT, false);
		}
		return true;
	}
	
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		MyInput.setTouchDown();
		return true;	
	}
	
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		MyInput.setTouchUp();
		return false;
	}

}
