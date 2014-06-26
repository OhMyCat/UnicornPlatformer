package com.game.handlers;

public class MyInput {
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	public static boolean touchClick,touchDown;
	
	public static final int NUM_KEYS = 4;
	public static final int BUTTON1 = 0;
	public static final int BUTTON2 = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	static{
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update(){	
		for(int i = 0; i < NUM_KEYS; i++){
			pkeys[i] = keys[i];	
		}
		if(touchClick)touchClick = false;
	}
	
	public static void setTouchDown(){
		touchClick = true;
		touchDown = true;
	}
	
	public static void setKey(int i, boolean b){keys[i] = b;}
	public static boolean isDown(int i){return keys[i];}
	public static boolean isPressed(int i){return keys[i] && !pkeys[i];}
	public static void setTouchUp(){touchDown = false;}
	public static boolean isTouchClicked(){return touchClick;}

}