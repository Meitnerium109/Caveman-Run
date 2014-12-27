package Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.RunnerGame;

public class Floor extends BaseObject{

	public static final int FLOOR_HEIGHT = 64;
	public static final int FLOOR_WIDTH = 2176;
	
	ShapeRenderer renderer = new ShapeRenderer();
	
	public Floor(int x, int y){
		super(x, y, FLOOR_WIDTH, FLOOR_HEIGHT);
		setAsTerrain();
		velocityX = 0;
	}
	
	@Override 
	public void draw(Batch batch, float alpha){
		batch.draw(A.floor, getX(), getY());
	}
	
	@Override 
	public void act(float delta){
		super.act(delta);
		
		if(getX() <= -2176){
			setX(2176);
		}
	}
	
	
	@Override
	public void dispose(){
		super.dispose();
		renderer.dispose();
	}
	
}
