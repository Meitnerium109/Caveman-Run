package Objects;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Rock extends BaseObject{

	public static final int ROCK_CREATE_OFFSET_X = 50;
	public static final int ROCK_CREATE_OFFSET_Y = 12;
	public static final int ROCK_WIDTH = 16;
	public static final int ROCK_HEIGHT = 16;
	public static final int ROCK_SPEED = 40;
	public static final int ROCK_THROW_SPEED = 2;
	public static final float ROCK_DRAG = 30;
	public static final int damage = 1;
	
	public Rock(int x, int y){
		super(x, y, ROCK_WIDTH, ROCK_HEIGHT);
		setMoveable(true);
		
		hitbox = new HitBox((int)getX() - 3, (int)getY() - 3, (int)getWidth() + 6, (int)getHeight() + 6, this);
		velocityX = ROCK_SPEED;
		velocityY = ROCK_THROW_SPEED;
	}
	
	public Rock(int x, int y, double velocityXOffset, double velocityYOffset){
		super(x, y, ROCK_WIDTH, ROCK_HEIGHT);
		setMoveable(true);
		accelerationY = -20;
		velocityX = ROCK_SPEED;// + velocityXOffset;
		velocityY = ROCK_THROW_SPEED;// + velocityYOffset/2;
		
		hitbox = new HitBox((int)getX() - 6, (int)getY() - 6, (int)getWidth() + 12, (int)getHeight() + 12, this);
	}
	
	
	
	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(A.rock1, getX(), getY());
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		velocityX -= ROCK_DRAG * delta;
	}
}
