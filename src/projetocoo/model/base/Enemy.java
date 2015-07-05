package projetocoo.model.base;

import projetocoo.model.movements.MovementType;

public abstract class Enemy extends Shooter{
	private double angle;
	private double rv;
	private int nextEnemyDelay;
	private MovementType movementType;
	
	public Enemy(double radius, int nextEnemyDelay) {
		super();
		setRadius(radius);
		setNextEnemyDelay(nextEnemyDelay);
		setNumberProjectiles(200); //TODO: verificar posteriormente
		//TODO: set radius projectile
	}

	public void setNextEnemyDelay(int nextEnemyDelay) {
		this.nextEnemyDelay = nextEnemyDelay;
	}
	
	public void updatePosition(){
		this.movementType.updatePosition(this);
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public double getAngle() {
		return angle;
	}

	public double getRv() {
		return rv;
	}

	public int getNextEnemyDelay() {
		return nextEnemyDelay;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setRv(double rv) {
		this.rv = rv;
	}
	
	
	
}
