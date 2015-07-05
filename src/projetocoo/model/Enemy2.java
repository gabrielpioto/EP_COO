package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.movements.Movement1;
import projetocoo.model.movements.Movement2;
import projetocoo.model.shooter.ActiveShooter;
import projetocoo.model.shooter.ExplodingShooter;

public class Enemy2 extends Enemy {

	private int count;
	private double spawnX;

	public Enemy2(double radius, int nextEnemyDelay, double spawnX, int count) {
		super(radius, nextEnemyDelay);
		setCount(count);
		setSpawnX(spawnX);
		setMovementType(new Movement2());
	}

	@Override
	public void draw() {
		if (getState() instanceof  ExplodingShooter) {

			double alpha = (MainGame.getInstance().getCurrentTime() - getExplosionStart())
					/ (getExplosionEnd() - getExplosionStart());
			GameLib.drawExplosion(getX(), getY(), alpha);
		}

		if (getState() instanceof ActiveShooter) {

			GameLib.setColor(Color.MAGENTA);
			GameLib.drawDiamond(getX(), getY(), getRadius());
		}

	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setSpawnX(double spawnX) {
		this.spawnX = spawnX;
	}

}
