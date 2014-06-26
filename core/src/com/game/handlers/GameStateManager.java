package com.game.handlers;

import java.util.Stack;

import com.game.Game;
import com.game.states.GameState;
import com.game.states.Play;

public class GameStateManager {
	
	private Game game;
	
	private Stack<GameState> GameStates;
	
	public static final int PLAY = 912837;
	
	public GameStateManager(Game game){
		this.game = game;
		GameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public Game game(){return game;};
	
	public void update(float dt){
		GameStates.peek().update(dt);
	}
	
	public void render(){
		GameStates.peek().render();
	}
	
	private GameState getState(int state){
		if(state == PLAY) return new Play(this);
		return null;
	}
	
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		GameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = GameStates.pop();
		g.dispose();
	}
	
	public void dispose(){
		GameStates.peek().dispose();
	}

}
