package Objects;

import com.badlogic.gdx.graphics.g2d.Batch;

public class RollingBoulder extends Boulder{

	public float rotation;
	public float rotationSpeed = 1000;
	public float rollSpeed = -12;
	
	/**
	 * Always a type 2 boulder (the smaller one)
	 * @param x
	 * @param y
	 */
	public RollingBoulder(int x, int y, int type){
		super(x, y, type);
		
		rotation = 0;
		velocityX = rollSpeed;
		
		terrain = false;
		setMoveable(true);
		
		velocityY = 0;
		accelerationY = 0;
		
		if(type == 1){
			rotationSpeed = 180;
			hitbox = new HitBox((int)getX(), (int)getY(), (int)getWidth() - 20, (int)getHeight() - 20, this);
			
		} else if (type == 2){
			rotationSpeed = 180 * 4;
			hitbox = new HitBox((int)getX(), (int)getY(), (int)getWidth() - 10, (int)getHeight() - 15, this);
		}
		
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		rotation += rotationSpeed * delta;
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		if(type == 2){
			batch.draw(A.rollingBoulder, getX(), getY(), getWidth()/2,  getHeight()/2, getWidth(), getHeight(), 1f, 1f, rotation);
		} else if (type == 1){
			batch.draw(A.rollingBoulder2, getX(), getY(), getWidth()/2,  getHeight()/2, getWidth(), getHeight(), 1f, 1f, rotation);
		}
	}
}
