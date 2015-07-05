package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Shooter;
import projetocoo.model.shooter.ExplodingShooter;
import projetocoo.model.shooter.ShooterState;

public class Player extends Shooter {

	public Player(double x, double y, double vx, double vy, double radius,
			long nextShot) {
		super(x, y, vx, vy, radius);
		setNextShot(nextShot);
		setNumberProjectiles(10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		if (getState() instanceof ExplodingShooter) {
			double alpha = (MainGame.getInstance().getCurrentTime() - getExplosionStart())
					/ (getExplosionEnd() - getExplosionStart());
			GameLib.drawExplosion(getX(), getY(), alpha);
		} else {

			GameLib.setColor(Color.BLUE);
			GameLib.drawPlayer(getX(), getY(), getRadius());
		}
	}

}
