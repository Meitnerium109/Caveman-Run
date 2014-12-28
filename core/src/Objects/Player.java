package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.RunnerGame;

public class Player extends BaseObject {

	public static int jumpSpeed = 14;
	public static int minJumpSpeed = jumpSpeed;
	public static float fallSpeed = -7;

	Animation playerAnimation;
	Texture playerTexture;
	TextureRegion[] frames;
	TextureRegion currentFrame;
	
	private static final int frameSize = 64;
	private static final int FRAMES_PER_SECOND = 13;
	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 4;
	private static float stateTime;
	
	public Player(int x, int y) {
		super(x, y, 64 - 10, 64);
		setMoveable(true);

		setBounds(getX(), getY(), getWidth(), getHeight());
		
		playerTexture = new Texture(Gdx.files.internal("RunningCaveman.png"));
		
		TextureRegion[][] tmp = TextureRegion.split(playerTexture, playerTexture.getWidth()/FRAME_COLS, 
				playerTexture.getHeight()/FRAME_ROWS);
		 frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		
		int index = 0;
		 for(int i = 0; i < FRAME_ROWS; i++){
			 for(int j = 0; j < FRAME_COLS; j++){
				 frames[index++] = tmp[i][j];
			 }
		 }
		 
		 playerAnimation = new Animation(1/(float)FRAMES_PER_SECOND, frames);
		 stateTime = 0f;
		 
	}

	@Override
	public void draw(Batch batch, float alpha) {
		//For the animation Stuff
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = playerAnimation.getKeyFrame(stateTime, true);
	
		batch.draw(currentFrame, getX(), getY());
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		//For the animation
	
		jump();
		
	}

	public float jumpTime = 0f;
	public static float jumpTimeLimit = 0.15f;
	public static float minJumpTime = 0.01f;
	
	//Variables for touching down
	public boolean touchingDown = false;
	
	public void jump() {
		if(Gdx.input.isKeyJustPressed(Keys.Z)){
			RunnerGame.jumpSound.play();
		}
		
		if(jumpTime >= 0 && ((Gdx.input.isKeyPressed(Keys.Z)) || touchingDown)){
			velocityY = minJumpSpeed;
			jumpTime += Gdx.graphics.getDeltaTime();
			if(jumpTime > jumpTimeLimit){
				jumpTime = -1;
			}
		} else {
			jumpTime = -1;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void collideAction(String direction) {
		super.collideAction(direction);
		if (direction.equals("up")) {
			jumpTime = 0;
		}
	}

}
