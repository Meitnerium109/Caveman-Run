package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bunny extends Enemy{

	//Animation stuff
	Animation animation;
	Texture texture;
	TextureRegion[] frames;
	TextureRegion currentFrame;

	public static final int FRAME_WIDTH = 66;
	public static final int FRAME_HEIGHT = 58;
	private static final int FRAMES_PER_SECOND = 12;
	private static final int FRAME_COLS = 8;
	private static final int FRAME_ROWS = 1;
	public float stateTime;
	
	public Bunny(int x, int y){
		super(x, y, 33, 29);
		setAsTerrain();
		health = 1;
		
		velocityXOffset = 2.5;
		
		texture = A.bunnyHopAnimation;
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS, texture.getHeight()/FRAME_ROWS);
		frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		
		int index = 0;
		
		for(int i = 0; i < FRAME_COLS; i++){
			frames[index++] = tmp[0][i];
		}
		
		animation = new Animation(1/(float)FRAMES_PER_SECOND, frames);
		stateTime = 0f;
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = animation.getKeyFrame(stateTime, true);
		
		batch.draw(currentFrame, getX(), getY());
		
	}
	
	@Override
	public void dispose(){
		
	}
	
	
}
