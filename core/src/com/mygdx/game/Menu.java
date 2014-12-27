package com.mygdx.game;

import Objects.A;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Menu implements Screen{

	Stage stage;
	
	//For the label
	Label label;
	
	static BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
	public static final LabelStyle style = new LabelStyle(font, Color.BLACK);
	
	//For the button
	TextureAtlas buttonAtlas;
	TextButtonStyle buttonStyle;
	TextButton button;
	Skin skin;
	Game game;
	
	public Menu(Game game){
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
			font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
			
			stage = new Stage(new ExtendViewport(A.gameWidth, A.gameHeight));
			
			//For the label
			label = new Label("Hello World", style);
			label.setPosition(100, 100);
			
			stage.addActor(label);
		
			//For the button
			skin = new Skin();
			buttonAtlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
			skin.addRegions(buttonAtlas);
			
			buttonStyle = new TextButtonStyle();
			buttonStyle.up = skin.getDrawable("button");
			buttonStyle.over = skin.getDrawable("buttonPressed");
			buttonStyle.down = skin.getDrawable("buttonPressed");
			buttonStyle.font = font;
			
			button = new TextButton("Click", buttonStyle);
			button.setPosition(250, 250);
			button.addListener(new InputListener(){
				@Override 
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
					game.setScreen(new RunnerGame(game));
					return true;
				}
			});
			
			stage.addActor(button);
			Gdx.input.setInputProcessor(stage);
			
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {	
		Gdx.gl20.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		button.setPosition(440, 250);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		skin.dispose();
		buttonAtlas.dispose();
		stage.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	

}
