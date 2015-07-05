package projetocoo.model.base;

import java.util.ArrayList;
import java.util.List;

import projetocoo.model.projectile.Projectile;
import projetocoo.model.shooter.ActiveShooter;
import projetocoo.model.shooter.ShooterState;

public abstract class Shooter extends Element{
	private double explosionEnd;
	private double explosionStart;
	private long nextShot;
	private int numberProjectiles;
	private ArrayList<Projectile> projectiles;
	protected ShooterState state;
	
	
	public Shooter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shooter(double x, double y, double vx, double vy,
			double radius) {
		super(x, y, vx, vy, radius);
		this.state = new ActiveShooter();
	}

	public Shooter(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void checkCollision(List<? extends Element> elements){
		state = state.checkCollision(elements, this);
	}
	
	public void update(){
		state = state.update(this);
	}


	public void setNextShot(long nextShot){
		this.nextShot = nextShot;
	}
	
	public void setNumberProjectiles(int numberProjectiles){
		this.numberProjectiles = numberProjectiles;
	}

	public double getExplosionEnd() {
		return explosionEnd;
	}

	public void setExplosionEnd(double explosionEnd) {
		this.explosionEnd = explosionEnd;
	}

	public long getNextShot() {
		return nextShot;
	}

	public int getNumberProjectiles() {
		return numberProjectiles;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public int findFreeIndex(){
		// TODO
		return 0;
	}
	
	public ArrayList<Projectile> findFreeIndex(int size){
		// TODO
		return null;
	}

	public ShooterState getState() {
		return state;
	}

	public double getExplosionStart() {
		return explosionStart;
	}

	public void setExplosionStart(double explosionStart) {
		this.explosionStart = explosionStart;
	}
	
	

	
	
}
