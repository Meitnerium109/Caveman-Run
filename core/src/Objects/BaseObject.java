package Objects;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.RunnerGame;

public class BaseObject extends Actor {

	public boolean removeMe = false;
	public double velocityXOffset = 0;
	public double velocityX = 0;
	public double velocityY = 0;
	public double accelerationX = 0;
	public static double defaultAccelerationY = -60;
	public double accelerationY = defaultAccelerationY;
	public HitBox hitbox;
	private boolean moveable;
	public boolean terrain = false;

	public BaseObject(int x, int y, int width, int height) {
		hitbox = new HitBox(x, y, width, height, this);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	public BaseObject(int x, int y, int width, int height, int hitboxOffsetX,
			int hitboxOffsetY, int hitboxWidth, int hitboxHeight) {
		hitbox = new HitBox(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth,
				hitboxHeight, this);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	public void moveTerrain(float delta) {
		setX(getX() - delta * RunnerGame.gameSpeed + (float) velocityXOffset);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		// Implements both accelerations
		if (moveable) {
			velocityX += accelerationX * delta;
			velocityY += accelerationY * delta;

			setX((float) (getX() + velocityX));
			setY((float) (getY() + velocityY));
		}

		if (terrain) {
			moveTerrain(delta);
		}

		hitbox.update();

		// Removes if below a certain thing
		if (getX() < -100) {
			removeMe = true;
		}
	}

	/**
	 * Always dispose an object when it is dead
	 */
	public void dispose() {

	}

	/**
	 * Kills the object
	 */
	public void kill() {
		removeMe = true;
		dispose();
		this.remove();
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
		hitbox.moveable = moveable;
	}

	public boolean getMoveable() {
		return moveable;
	}

	public void collideAction(String direction) {

	}

	/**
	 * Sets the object as terrain
	 */
	public void setAsTerrain() {
		setMoveable(false);
		terrain = true;
	}
}
