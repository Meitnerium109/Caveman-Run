package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Player extends Actor{
	
	public Player(int x, int y){
		setX(x);
		setY(y);
		
		setBounds(getX() + 20, getY() + 20, A.player.getWidth() - 40, A.player.getHeight() - 40);
		
		addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				System.out.println("TOUCHED");
				return true;
			}
		});
		
	}
	
	@Override
	public void draw (Batch batch, float alpha){
		batch.draw(A.player, getX(), getY());
		
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		setX(getX() + 50 * delta);
	}
	
}
