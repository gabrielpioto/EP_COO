package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.movements.Movement2;
import projetocoo.model.shooter.ActiveShooter;
import projetocoo.model.shooter.ExplodingShooter;

public class Enemy2 extends Enemy {

	private static double SPAWN_X = GameLib.WIDTH * 0.20;

	public Enemy2(double radius) {
		super(radius);
		setMovementType(new Movement2());
	}

	@Override
	public void draw() {
		if (getState() instanceof ExplodingShooter) {

			double alpha = (MainGame.getInstance().getCurrentTime() - getExplosionStart())
					/ (getExplosionEnd() - getExplosionStart());
			GameLib.drawExplosion(getX(), getY(), alpha);
		}

		if (getState() instanceof ActiveShooter) {

			GameLib.setColor(Color.MAGENTA);
			GameLib.drawDiamond(getX(), getY(), getRadius());
		}

	}


	@Override
	public void spawn() {
		MainGame mainGame = MainGame.getInstance();
		double x, y,enemyCount;

		x = SPAWN_X;
		y = -10.0;
		this.setPosition(x, y);
		this.setVelocity(0.42, 0);
		this.setAngle((3 * Math.PI) / 2);
		this.setRv(0.0);
		this.activateProjectiles();
		this.setNextShot(mainGame.getCurrentTime() + 500);
		mainGame.setNextEnemy1Delay(mainGame.getCurrentTime() + 500);
		
		enemyCount = Enemy.getActiveEnemiesCount(mainGame.getEnemies2());
		
		this.setState(new ActiveShooter());

		if (enemyCount < 10) {
			
			mainGame.setNextEnemy2Delay(mainGame.getCurrentTime() + 120);
			
		} else {

			enemyCount = 0;
			SPAWN_X = Math.random() > 0.5 ? GameLib.WIDTH * 0.2
					: GameLib.WIDTH * 0.8;
			mainGame.setNextEnemy2Delay((long) (mainGame.getCurrentTime() +  3000 + Math.random() * 3000));
		}

	}

}
