package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.movements.Movement1;
import projetocoo.model.shooter.ActiveShooter;
import projetocoo.model.shooter.ExplodingShooter;

public class Enemy1 extends Enemy {

	public Enemy1(double radius, int nextEnemyDelay) {
		super(radius, nextEnemyDelay);
		setMovementType(new Movement1());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		if (getState() instanceof ExplodingShooter) {
			double alpha = (MainGame.getInstance().getCurrentTime() - getExplosionStart())
					/ (getExplosionEnd() - getExplosionStart());
			GameLib.drawExplosion(getX(), getY(), alpha);
		}
		if (getState() instanceof ActiveShooter) {
			GameLib.setColor(Color.CYAN);
			GameLib.drawCircle(getX(), getY(), getRadius());
		}

	}

}
