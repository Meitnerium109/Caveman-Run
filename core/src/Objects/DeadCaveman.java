package Objects;

import com.badlogic.gdx.graphics.g2d.Batch;

public class DeadCaveman extends BaseObject {

	public static int DEAD_CAVEMAN_HEIGHT = 40;
	public static int DEAD_CAVEMAN_WIDTH = 64;
	
	public float rotation = -90;
	public float rotationSpeed = 500;
	
	public DeadCaveman(int x, int y){
		super(x, y, DEAD_CAVEMAN_WIDTH, DEAD_CAVEMAN_HEIGHT);
		setMoveable(true);
		velocityY = 0;
		accelerationY = -50;
		
		//setAsTerrain();
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(A.deadCaveman, getX(), getY(), getWidth(), 0, getWidth(), getHeight(), 1f, 1f, rotation);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(rotation < 0){
			rotation += rotationSpeed * delta;
		} else {
			rotation = 0;
		}
	}
	
}
