package Objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Class that handles collision between objects Do hit detection after
 * everything has been moved already or else it will scre wup hugely
 * 
 * @author Amdrew
 *
 */
public class HitBox extends Rectangle {

	public BaseObject obj;
	public boolean moveable = true;
	public int xOffset = 0;
	public int yOffset = 0;

	public HitBox(int x, int y, int width, int height, BaseObject obj) {
		super(x, y, width, height);
		this.obj = obj;
	}

	public void update() {
		x = (int) (obj.getX() + xOffset);
		y = (int) (obj.getY() + yOffset);
	}

	/**
	 * Returns which direction the first hitbox collides with the input hitbox
	 * @param rect
	 * @return
	 */
	public String collide(BaseObject rect) {

		if (this.overlaps(rect.hitbox)) {

			if (obj.getMoveable()) {
				if (obj.getY() < rect.hitbox.getY() + rect.hitbox.getHeight()
						&& obj.velocityY < 0) {
					obj.setY((float) (rect.hitbox.getY() + rect.hitbox
							.getHeight()));
					obj.velocityY = 0;
					return "up";
				} /**else if (obj.getY() > rect.hitbox.getY() && obj.velocityY > 0) {
					obj.setY((float) (rect.hitbox.getY()));
					obj.velocityY = 0;

				}*/

				if (obj.getX() > rect.hitbox.getX()
						&& obj.velocityX > 0) {
					obj.setX((float) (rect.hitbox.getX()));
					obj.velocityX = 0;
					return "left";
				} else if (obj.getX() < rect.hitbox.getX() + rect.hitbox.getWidth() && obj.velocityX < 0) {
					obj.setX((float) (rect.hitbox.getX()));
					obj.velocityX = 0;
					return "right";
				}
			}

		}
		
		return "no collision";
	}

}
