package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

//(Assets)

public class A {
	
	public static final Texture player = new Texture(Gdx.files.internal("badlogic.jpg"));
	
	public void dispose(){
		player.dispose();
	}
	
}
