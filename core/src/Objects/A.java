package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//(Assets)

public class A {
	//Stores miscellaneous stuff
	
	public static final boolean debugging = true;
	
	public static final int gameWidth = 960;
	public static final int gameHeight = 540;
	//public static final Texture player = new Texture(Gdx.files.internal("badlogic.jpg"));
	public static final Texture rock1 = new Texture(Gdx.files.internal("Rock1.png"));
	public static final Texture boulder1 = new Texture(Gdx.files.internal("Boulder1.png"));
	public static final Texture boulder2 = new Texture(Gdx.files.internal("Boulder2.png"));
	public static final Texture bunnyHopAnimation = new Texture(Gdx.files.internal("BunnyHopAnimation.png"));
	public static final Texture bunnyHopAnimationL = new Texture(Gdx.files.internal("BunnyHopAnimationLeft.png"));
	public static final TextureRegion rollingBoulder = new TextureRegion(new Texture(Gdx.files.internal("Boulder1.png")));
	public static final TextureRegion rollingBoulder2 = new TextureRegion(new Texture(Gdx.files.internal("Boulder2.png")));
	public static final Texture floor = new Texture(Gdx.files.internal("Tiles.png"));
	public static final TextureRegion deadCaveman = new TextureRegion(new Texture(Gdx.files.internal("DeadCaveman.png")));
	public static final Texture background = new Texture(Gdx.files.internal("background.png"));
	/**
	 * Call this function when you end the game
	 */
	public static void dispose(){
		rock1.dispose();
		boulder1.dispose();
		bunnyHopAnimation.dispose();
	}
	
}
