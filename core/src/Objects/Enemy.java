package Objects;

public class Enemy extends BaseObject{

	//The number of hits that it takes to kill the enemy
	public int health = 0;
	public boolean dead = false;
	
	public Enemy(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public void damage(int damage){
		health -= damage;
		
		System.out.println("HELLO THERE");
		if(health <= 0){
			
			kill();
		}
	}
	
	//The enemy dies. Whatever it needs to do when it dies goes in here
	@Override
	public void kill(){
		super.kill();
		dead = true;
		System.out.println("DEAD");
	}

}
