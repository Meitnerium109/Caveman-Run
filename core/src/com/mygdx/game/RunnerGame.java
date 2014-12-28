package com.mygdx.game;

import Objects.A;
import Objects.BaseObject;
import Objects.Boulder;
import Objects.Bunny;
import Objects.DeadCaveman;
import Objects.DeathScreen;
import Objects.Floor;
import Objects.ImageObject;
import Objects.Player;
import Objects.Rock;
import Objects.RollingBoulder;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class RunnerGame implements Screen {

	// Scoring/Timing/Metreing/Whatevering
	BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

	public double score = 0;
	public int highScore = 0;
	public int scorePerSecond = 30;
	public int scorePerObstacle = 30;
	Label scoreText;
	LabelStyle style = new LabelStyle();
	Label highscoreText;

	protected Stage stage;
	Game game;
	Button jumpButton;
	Button shootButton;
	DeathScreen deathScreen;

	// Stuff on screen
	Player player;
	public boolean playerDead = false;
	DeadCaveman deadCaveman;
	ImageObject background;

	Array<Floor> floors = new Array<Floor>();
	Array<Rock> rocks = new Array<Rock>();
	Array<Boulder> boulders = new Array<Boulder>();
	Array<RollingBoulder> rBoulders = new Array<RollingBoulder>();
	Array<Bunny> bunnies = new Array<Bunny>();

	// Variables
	// public static int gameSpeed = 0;
	public static int gameSpeed = 600; // How fast the tiles are moving
	public static int originalGameSpeed = 600;

	public static Sound jumpSound;
	public static Sound deathSound;
	
	Texture deathTexture = new Texture(Gdx.files.internal("GameOverScreen.png"));
	
	public RunnerGame(Game game) {
		this.game = game;
	}

	@Override
	public void dispose() {
		stage.dispose();
		player.dispose();
		for (Floor floor : floors) {
			floor.dispose();
		}
		jumpSound.dispose();
		deathSound.dispose();
		font.dispose();
	}
	
	@Override
	public void show() {
	
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("Jump Sound Effect.mp3"));
		deathSound = Gdx.audio.newSound(Gdx.files.internal("Death Sound.mp3"));
		
		stage = new Stage(new ExtendViewport(A.gameWidth, A.gameHeight));
		Gdx.input.setInputProcessor(stage);

		background = new ImageObject(0, 0, A.background);
		stage.addActor(background);

		deadCaveman = new DeadCaveman(1000, 100);
		stage.addActor(deadCaveman);

		player = new Player(250, 600);

		// The three cycled floors
		createFloor(0, 0);
		createFloor(Floor.FLOOR_WIDTH, 0);
		createFloor(Floor.FLOOR_WIDTH / 2, 0);
		createFloor(Floor.FLOOR_WIDTH / 2 + Floor.FLOOR_WIDTH, 0);

		stage.addActor(player);

		initializeAndroidControls();

		// STUFF FOR SCORING/TIMING/METREING/WHATEVER
		font.setScale(0.8f);
		style.font = font;
		style.fontColor = Color.BLACK;

		scoreText = new Label("SCORE: " + score, style);
		scoreText.setPosition(800, 500);
		stage.addActor(scoreText);

		highscoreText = new Label("High Score: " + highScore, style);
		highscoreText.setPosition(500, 500);
		stage.addActor(highscoreText);
	}

	/**
	 * Counts the number of seconds until the next obstacle
	 */
	public double obstacleTimer = 0f;

	public void generateObstacles(float delta) {
		if (!playerDead) {
			obstacleTimer -= delta;
			if (obstacleTimer <= 0) {
				double seed = Math.random();

				if (seed < 0.11f) {
					obstacleTimer = Math.random() * 0.5f + 1f;
					createRollingBoulder(1800, Floor.FLOOR_HEIGHT - 10, 1);
				} else if (seed < 0.22f) {
					obstacleTimer = Math.random() * 0.8f + 1.2f;
					createBoulder(1300, Floor.FLOOR_HEIGHT - 10, 1);
				} else if (seed < 0.33f) {
					obstacleTimer = Math.random() * 0.8f + 1.2;
					createBoulder(1300, Floor.FLOOR_HEIGHT - 10, 2);
				} else if (seed < 0.44) {
					obstacleTimer = Math.random() * 0.5f + 1.5f;
					createBunny(960, Floor.FLOOR_HEIGHT - 10).stateTime = (float) Math
							.random();
				} else if (seed < 0.55) {
					obstacleTimer = Math.random() * 0.5f + 1f;
					createRollingBoulder(1800, Floor.FLOOR_HEIGHT - 10, 2);
				} else if (seed < 0.66) {
					obstacleTimer = Math.random() * 1f + 1.4f;
					createRollingBoulder(1800, Floor.FLOOR_HEIGHT - 10, 2);
					createRollingBoulder(1900, Floor.FLOOR_HEIGHT - 10, 2);
				} else if (seed < 0.77) {
					obstacleTimer = Math.random() * 1f + 1.4f;
					createRollingBoulder(1800, Floor.FLOOR_HEIGHT - 10, 2);
					createRollingBoulder(2200, Floor.FLOOR_HEIGHT - 10, 2);
				} else if (seed < 0.88) {

					obstacleTimer = Math.random() * 1f + 1f;
					createBoulder(1300, Floor.FLOOR_HEIGHT - 10, 1);
					createBoulder(1700, Floor.FLOOR_HEIGHT - 10, 1);
				} else {
					obstacleTimer = Math.random() * 1f + 1f;
					createBunny(960, Floor.FLOOR_HEIGHT - 10).stateTime = (float) Math
							.random();
					createBunny(960 + 25, Floor.FLOOR_HEIGHT - 10).stateTime = (float) Math
							.random();
					createBunny(960 + 50, Floor.FLOOR_HEIGHT - 10).stateTime = (float) Math
							.random();
				}

			}
		}
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		controls();
		collisions();
		generateObstacles(delta);
		stage.draw();

		if (!playerDead) {
			score += scorePerSecond * delta;
			// Update text stuff
			scoreText.setText("Score: " + (int) (Math.floor(score)));
		}

	}

	public boolean isTouched = false;

	public void controls() {
		// Reset Death
		stage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (playerDead) {
					restartGame();
				}
				return true;
			}

		});

		if (Gdx.input.isKeyJustPressed(Keys.X)) {
			throwRock((int) player.getX() + Rock.ROCK_CREATE_OFFSET_X,
					(int) player.getY() + Rock.ROCK_CREATE_OFFSET_Y, 0,
					player.velocityY);
		}
	}
	
	// Controls what should happen after the player dies
	// This function should be called when the player dies
	public void playerDeath() {
		if (!playerDead) {
			deathSound.play();
			playerDead = true;
			resetDeadCaveman((int) player.getX(), (int) player.getY());
			gameSpeed = 0;
			player.setPosition(960, 1000);

			deathBackground = new ImageObject(0, 0, deathTexture);
			stage.addActor(deathBackground);

			if(score > highScore){
				highScore = (int)score;
				highscoreText.setText("High Score: " + highScore);
			}
			
		}
	}

	ImageObject deathBackground;
	public void createDeathScreen(boolean isHighscore) {

		deathScreen = new DeathScreen((int) Math.floor(score), isHighscore);

		stage.addActor(deathScreen);

		// Adds every actor in deathScreen;;; All the scene2d ui elements
		// basically
		for (Actor actor : deathScreen.actors) {
			stage.addActor(actor);
		}

	}

	public void removeDeathScreen() {
		deathScreen.remove();
		for (Actor actor : deathScreen.actors) {
			actor.remove();
		}

		deathScreen.dispose();
		deathScreen = null;
	}

	// Restarts the game. Score is zero and stuff
	public void restartGame() {
		deathBackground.remove();
		gameSpeed = originalGameSpeed;
		resetDeadCaveman(-100, -64);
		playerDead = false;
		score = 0;

		// Removes all excess obstacles
		for (int i = 0; i < boulders.size; i++) {
			Boulder boulder = boulders.get(i);
			boulders.removeIndex(i);
			boulder.remove();
		}

		// Removes all excess obstacles
		for (int i = 0; i < boulders.size; i++) {
			Boulder boulder = boulders.get(i);
			boulders.removeIndex(i);
			boulder.remove();
		}


		// Removes all excess obstacles
		for (int i = 0; i < boulders.size; i++) {
			Boulder boulder = boulders.get(i);
			boulders.removeIndex(i);
			boulder.remove();
		}


		// Removes all excess obstacles
		for (int i = 0; i < boulders.size; i++) {
			Boulder boulder = boulders.get(i);
			boulders.removeIndex(i);
			boulder.remove();
		}


		for (int i = 0; i < rBoulders.size; i++) {
			RollingBoulder rboulder = rBoulders.get(i);
			rBoulders.removeIndex(i);
			rboulder.remove();
		}

		for (int i = 0; i < rBoulders.size; i++) {
			RollingBoulder rboulder = rBoulders.get(i);
			rBoulders.removeIndex(i);
			rboulder.remove();
		}
		for (int i = 0; i < rBoulders.size; i++) {
			RollingBoulder rboulder = rBoulders.get(i);
			rBoulders.removeIndex(i);
			rboulder.remove();
		}
		for (int i = 0; i < bunnies.size; i++) {
			Bunny bunny = bunnies.get(i);
			bunnies.removeIndex(i);
			bunny.remove();
		}
		for (int i = 0; i < bunnies.size; i++) {
			Bunny bunny = bunnies.get(i);
			bunnies.removeIndex(i);
			bunny.remove();
		}
		for (int i = 0; i < bunnies.size; i++) {
			Bunny bunny = bunnies.get(i);
			bunnies.removeIndex(i);
			bunny.remove();
		}

		resetPlayer();
	}

	public void resetPlayer() {
		player.setPosition(250, Floor.FLOOR_HEIGHT);
	}

	public void collisions() {

		/**
		 * Collision with the floor
		 */

		for (int i = 0; i < bunnies.size; i++) {
			Bunny bunny = bunnies.get(i);

			if (bunny.removeMe) {
				bunnies.removeIndex(i);
				bunny.kill();
			} else if (bunny.dead) {
				score += scorePerObstacle;
				bunny.kill();
				bunnies.removeIndex(i);
			} else {
				// Player collision
				if (bunny.hitbox.overlaps(player.hitbox)) {
					playerDeath();
				}

				for (int j = 0; j < rocks.size; j++) {
					Rock rock = rocks.get(j);
					if (rock.hitbox.overlaps(bunny.hitbox)) {
						rock.kill();
						rocks.removeIndex(j);
						bunny.damage(Rock.damage);
					}
				}
			}
		}

		for (Floor floor : floors) {

			player.collideAction(player.hitbox.collide(floor));
			deadCaveman.hitbox.collide(floor);

			for (int i = 0; i < rocks.size; i++) {
				Rock rock = rocks.get(i);

				if (rock.hitbox.overlaps(floor.hitbox)) {
					rocks.removeIndex(i);
					rock.kill();
					rock.remove();
				}
			}
		}

		for (int j = 0; j < rBoulders.size; j++) {
			RollingBoulder rBoulder = rBoulders.get(j);

			if (rBoulder.removeMe) {
				score += scorePerObstacle;
				rBoulder.kill();
				rBoulders.removeIndex(j);
			}

			if (rBoulder.hitbox.overlaps(player.hitbox)) {
				playerDeath();
			}

			for (int i = 0; i < rocks.size; i++) {
				Rock rock = rocks.get(i);
				if (rock.hitbox.overlaps(rBoulder.hitbox)) {
					rock.kill();
					rocks.removeIndex(i);
				}
			}
		}

		for (int j = 0; j < boulders.size; j++) {
			Boulder boulder = boulders.get(j);

			if (boulder.removeMe) {
				score += scorePerObstacle;
				boulder.kill();
				boulders.removeIndex(j);
			}
			if (boulder.hitbox.overlaps(player.hitbox)) {
				playerDeath();
			}

			for (int i = 0; i < rocks.size; i++) {
				Rock rock = rocks.get(i);
				if (rock.hitbox.overlaps(boulder.hitbox)) {
					rock.kill();
					rocks.removeIndex(i);
				}
			}
		}

	}

	// Checks for collision between two things and does stuff to them if there
	// is a collision
	// Only call if collision works both ways

	/**
	 * IF ONLY ONE THING COLLIDES, CALL THE THING THAT CAN BE MOVED AS THE FIRST
	 * PARAMETER
	 */
	public void collide(BaseObject obj1, BaseObject obj2) {
		obj1.hitbox.collide(obj2);
		obj2.hitbox.collide(obj1);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		MainMenu.backgroundMusic.play();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	public void spawnFloors() {

	}

	/**
	 * Creation methods
	 */

	public void resetDeadCaveman(int x, int y) {
		deadCaveman.setPosition(x, y);
		deadCaveman.rotation = -90;
		deadCaveman.velocityY = 0;
	}

	public void createFloor(int x, int y) {
		Floor floor = new Floor(x, y);
		stage.addActor(floor);
		floors.add(floor);
	}

	public void createRock(int x, int y) {
		Rock rock = new Rock(x, y);
		stage.addActor(rock);
		rocks.add(rock);
	}

	public void createRollingBoulder(int x, int y, int type) {
		RollingBoulder rollingBoulder = new RollingBoulder(x, y, type);
		stage.addActor(rollingBoulder);
		rBoulders.add(rollingBoulder);
	}

	public void createBoulder(int x, int y, int type) {
		Boulder boulder = new Boulder(x, y, type);
		stage.addActor(boulder);
		boulders.add(boulder);
	}

	public void throwRock(int x, int y, double xOffset, double yOffset) {
		Rock rock = new Rock(x, y, xOffset, yOffset);
		stage.addActor(rock);
		rocks.add(rock);
	}

	public void initializeAndroidControls() {
		jumpButton = new Button(new TextButtonStyle());
		jumpButton.setBounds(0, 0, 960 / 2, 540);
		jumpButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(!player.touchingDown){
					jumpSound.play(1.0f);
				}
				player.touchingDown = true;
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.touchingDown = false;
			}
		});
		stage.addActor(jumpButton);

		shootButton = new Button(new TextButtonStyle());
		shootButton.setBounds(960 / 2, 0, 960 / 2, 540);
		shootButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				shoot();
				return true;
			}
		});
		stage.addActor(shootButton);

	}

	public Bunny createBunny(int x, int y) {
		Bunny bunny = new Bunny(x, y);
		stage.addActor(bunny);
		bunnies.add(bunny);

		return bunny;
	}

	public void shoot() {
		throwRock((int) player.getX() + Rock.ROCK_CREATE_OFFSET_X,
				(int) player.getY() + Rock.ROCK_CREATE_OFFSET_Y, 0,
				player.velocityY);
	}

	public void androidControls() {

	}

}
