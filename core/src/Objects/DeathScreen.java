package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

/**
 * Just the death screen and stuff :D
 * Displays when the player dies and hides itself when the player wins.
 *
 */
public class DeathScreen extends Actor{

	public Array<Actor> actors = new Array<Actor>();
	
	public static int SCREEN_WIDTH = 500;
	public static int SCREEN_HEIGHT = 300;
	
	ShapeRenderer renderer = new ShapeRenderer();
	
	public Label scoreLabel;
	public Label newHighScore;
	public Label tapToRetry;
	public LabelStyle scoreLabelStyle;
	public BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
	
	public int score;
	public boolean highScore;
	
	public DeathScreen(int score, boolean highScore){ 
		
		
		this.score = score;
		font.setScale(1.3f);
		scoreLabelStyle = new LabelStyle(font, Color.WHITE);
		
		if(highScore){
			newHighScore = new Label("New High Score!!", scoreLabelStyle);
			newHighScore.setPosition(300, 350);
			newHighScore.setColor(0.7f, 1f, 0.3f, 1);
			
			actors.add(newHighScore);
		}
		
		scoreLabel = new Label("Score: " + score, scoreLabelStyle);
		scoreLabel.setPosition(335, 280);
		scoreLabel.setColor(Color.BLACK);
		
		tapToRetry = new Label("Tap To Retry", scoreLabelStyle);
		actors.add(tapToRetry);
		
		actors.add(scoreLabel);
		
	}
		
	@Override
	public void draw(Batch batch, float alpha){
		tapToRetry.setColor(Color.WHITE);
		tapToRetry.setPosition(330, 180);
	}
	
	public void dispose(){
		renderer.dispose();
		font.dispose();
	}
	
}
