package com.mygdx.game;
import com.badlogic.gdx.Game;


//Attribution for bunny: PixelFarm (https://bitbucket.org/tebruno99/pixelfarm) Stephen "Redshrike" Challener

public class BaseGame extends Game{

	Game game;
	
	@Override
	public void create(){
		game = this;
		setScreen(new MainMenu(this));
	}
	
	@Override 
	public void dispose(){
		
	}
	
	@Override 
	public void render(){
		super.render();
	}
	
	@Override
	public void resize(int width, int height){
		
	}
	
}
