package com.mygdx.game;

import Objects.ImageObject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Instructions implements Screen{

	Game game;
	Stage stage;
	Button button;
	
	public Instructions(Game game){
		this.game = game;
	}

	public ImageObject object;
	Texture instructionsPicture = new Texture(Gdx.files.internal("instructions.png"));
	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(960, 540));
		object = new ImageObject(0, 0, instructionsPicture);
		stage.addActor(object);
		
		ButtonStyle style = new ButtonStyle();
		
		button = new Button(style);
		button.setWidth(960);
		button.setHeight(540);
		button.setX(0);
		button.setY(0);
		
		button.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				game.setScreen(new MainMenu(game));
				return true;
			}
			
		});
		
		stage.addActor(button);
		Gdx.input.setInputProcessor(stage);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		instructionsPicture.dispose();	
	}
}
