package Objects;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Bone extends BaseObject{

	public Bone(int x, int y, int width, int height){
		super(x, y, width, height);
		setMoveable(true);
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(A.rock1, getX(), getY());
	}
}
