package projetocoo.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Element;
import projetocoo.model.base.Shooter;
import projetocoo.model.shooter.ExplodingShooter;

public class Player extends Shooter {
	private static final int NUMBER_PROJECTILES = 10;

	public Player(double x, double y, double vx, double vy, double radius,
			long nextShot) {
		super(x, y, vx, vy, radius);
		setNextShot(nextShot);
		List<PlayerProjectile> projectiles = new ArrayList<PlayerProjectile>();
		for (int i = 0; i < NUMBER_PROJECTILES; i++) {
			projectiles.add(new PlayerProjectile());
		}
		setProjectiles(projectiles);
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



	@Override
	public boolean checkCollision(Element e) {
		double dx = e.getX() - getX();
		double dy = e.getY() - getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		
		return dist < (getRadius() + e.getRadius()) * 0.8;
	}

}
