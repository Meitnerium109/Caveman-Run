package Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageObject extends Actor {

	Texture texture;
	
	public ImageObject(int x, int y, Texture texture){
		
		setX(x);
		setY(y);
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
		
		this.texture = texture;
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(texture, getX(), getY());
	}
}
