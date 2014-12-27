package Objects;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Boulder extends BaseObject{

	public int type;
	
	public Boulder(int x, int y, int type){
		super(x, y, 64, 64);
	
		this.type = type;
		
		if(type == 1){
			setWidth(128);
			setHeight(128);
			hitbox = new HitBox((int)getX(), (int)getY(), (int)getWidth() - 40, (int)getHeight() - 30, this);
		} else if (type == 2){
			setWidth(64);
			setHeight(64);
			hitbox = new HitBox(x + 4, y + 4, A.boulder2.getWidth() - 15, A.boulder2.getHeight() - 15, this);
		}
		
		setAsTerrain();
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		if(type == 1){
			batch.draw(A.boulder1, getX(), getY());
		} else if (type == 2){
			batch.draw(A.boulder2, getX(), getY());
		}
	}
}
