package com.mygdx.game;

import Objects.Player;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class RunnerGame implements ApplicationListener {
		
	private Stage stage;
	
	@Override
	public void create() {
		Player player = new Player(100, 100);
		stage = new Stage(new ExtendViewport(960, 540));
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(player);
		
	
		
	}

	@Override
	public void render() {	
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	public void update() {	
		
	}

	public void collisions() {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		//stage.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
