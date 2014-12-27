package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenu extends RunnerGame{

	LabelStyle labelStyle;
	TextButtonStyle buttonStyle;
	TextButton startButton;
	TextButton instructionsButton;
	Skin skin;
	BitmapFont font = new BitmapFont(Gdx.files.internal("MenuFont.fnt"));
	BitmapFont bigFont = new BitmapFont(Gdx.files.internal("BigMenuFont.fnt"));
	
	Label caveman;
	Label andrew;
	public static Music backgroundMusic;
	
	public MainMenu(Game game){
		super(game);
	}

	@Override
	public void show(){
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Movement Proposition.mp3"));
		backgroundMusic.setVolume(0.3f);
		backgroundMusic.play();
		
		super.show();
		
		scoreText.setPosition(-100, -100);
		highscoreText.setPosition(-100, -100);
		
		font.setColor(Color.BLACK);
		
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = font;
		buttonStyle.fontColor = Color.BLACK;
		
		startButton = new TextButton("Start", buttonStyle);
		startButton.setPosition(420, 250);
		startButton.addListener(new InputListener(){
		
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				game.setScreen(new RunnerGame(game));
				return true;
			}
			
		});
		
		stage.addActor(startButton);
		
		instructionsButton = new TextButton("Instructions", buttonStyle);
		instructionsButton.setPosition(350,  180);
		instructionsButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				game.setScreen(new Instructions(game));
				return true;
			}
			
		});
		stage.addActor(instructionsButton);
		
		labelStyle = new LabelStyle(bigFont, Color.BLACK);
		
		caveman = new Label("Caveman Run", labelStyle);
		
		labelStyle = new LabelStyle(font, Color.BLACK);
		
		andrew = new Label("Andrew Lau. Music and Sound Effects from Incompetech.com and http://www.freesfx.co.uk/", labelStyle);
		andrew.setFontScale(0.3f);
		stage.addActor(caveman);
		stage.addActor(andrew);
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		//controls();
		//collisions();
		player.setMoveable(false);
		player.setPosition(-100, -100);
		generateObstacles(delta);
		stage.draw();
	
		/**if (!playerDead) {
			score += scorePerSecond * delta;
			// Update text stuff
			scoreText.setText("Score: " + (int) (Math.floor(score)));
		}*/
		
		caveman.setPosition(260, 370);
		//run.setPosition(300, 400);
		
	}
	
}
