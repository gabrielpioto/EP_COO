package projetocoo.model.base;

import java.util.ArrayList;
import java.util.List;

import projetocoo.model.projectile.ActiveProjectile;
import projetocoo.model.projectile.InactiveProjectile;
import projetocoo.model.shooter.InactiveShooter;
import projetocoo.model.shooter.ShooterState;

public abstract class Shooter extends Element implements Updatable{
	private long explosionEnd = 0;
	private long explosionStart = 0;
	private long explosionDelay;
	private long nextShot;
	private List<? extends Projectile> projectiles;
	protected ShooterState state = new InactiveShooter();

	public Shooter() {
		super();
	}

	public Shooter(double x, double y, double vx, double vy, double radius) {
		super(x, y, vx, vy, radius);
	}

	public Shooter(double x, double y) {
		super(x, y);
	}
	
	public abstract boolean checkCollision(Element e);

	public void collide(List<? extends Element> elements) {
		state = state.checkCollision(elements, this);
	}

	public void update() {
		state = state.update(this);
	}

	public void setNextShot(long nextShot) {
		this.nextShot = nextShot;
	}
	
	public long getExplosionDelay() {
		return explosionDelay;
	}

	public void setExplosionDelay(long explosionDelay) {
		this.explosionDelay = explosionDelay;
	}

	public double getExplosionEnd() {
		return explosionEnd;
	}

	public void setExplosionEnd(long explosionEnd) {
		this.explosionEnd = explosionEnd;
	}

	public long getNextShot() {
		return nextShot;
	}

	public List<? extends Projectile> getProjectiles() {
		return projectiles;
	}
	
	protected void setProjectiles(List<? extends Projectile> projectiles){
		this.projectiles = projectiles;
	}

	public Projectile findFreeIndex() {
		for (Projectile p : projectiles) {
			if (p.getState() instanceof InactiveProjectile) {
				return p;
			}
		}
		return null;
	}

	public List<Projectile> findFreeIndex(int maxSize) {
		int size=0;
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		for (Projectile p : this.projectiles) {
			if (p.getState() instanceof InactiveProjectile) {
				projectiles.add(p);
				size++;
				if(size>=maxSize){
					return projectiles;
				}
			}
		}
		return projectiles;
	}

	public ShooterState getState() {
		return state;
	}

	public double getExplosionStart() {
		return explosionStart;
	}

	public void setExplosionStart(long explosionStart) {
		this.explosionStart = explosionStart;
	}

	public void activateProjectiles() {
		for (Projectile p : projectiles) {
			p.setState(new ActiveProjectile());
		}
	}
	
	public void setState(ShooterState state) {
		this.state = state;
	}

}
