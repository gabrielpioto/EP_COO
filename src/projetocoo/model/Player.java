package projetocoo.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Element;
import projetocoo.model.base.Projectile;
import projetocoo.model.base.Shooter;
import projetocoo.model.projectile.ActiveProjectile;
import projetocoo.model.shooter.ActiveShooter;
import projetocoo.model.shooter.ExplodingShooter;

public class Player extends Shooter {
	private static final int NUMBER_PROJECTILES = 10;
	private static final int MIN_NEXT_SHOT_DELAY = 60;
	private static final int MIN_RADIUS = 5;
	private long nextShotDelay = 300;

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

	public long getNextShotDelay() {
		return nextShotDelay;
	}

	public void setNextShotDelay(long nextShotDelay) {

		this.nextShotDelay = (nextShotDelay < MIN_NEXT_SHOT_DELAY) ? MIN_NEXT_SHOT_DELAY
				: nextShotDelay;
	}
	
	@Override
	public void setRadius(double radius) {
		super.setRadius((radius < MIN_RADIUS) ? MIN_RADIUS : radius);
	}
	
	@Override
	public void setPosition(double x, double y) {
		if(x < 0.0) x = 0.0;
		if(x >= GameLib.WIDTH) x = GameLib.WIDTH - 1;
		if(y < 25.0) y = 25.0;
		if(y >= GameLib.HEIGHT) y = GameLib.HEIGHT - 1;
		super.setPosition(x, y);
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

	public void updatePosition() {
		MainGame mainGame = MainGame.getInstance();

		if (this.getState() instanceof ActiveShooter) {
			double x = this.getX(), y = this.getY();
			if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
				x -= mainGame.getDelta() * this.getVx();
			if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
				x += mainGame.getDelta() * this.getVx();

			if (GameLib.iskeyPressed(GameLib.KEY_UP))
				y -= mainGame.getDelta() * this.getVy();
			if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
				y += mainGame.getDelta() * this.getVy();

			this.setPosition(x, y);

			if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

				if (mainGame.getCurrentTime() > this.getNextShot()) {

					Projectile free = this.findFreeIndex();

					if (free != null) {
						y -= 2 * this.getRadius();
						free.setPosition(x, y);
						free.setVelocity(0.0, -1.0);
						free.setState(new ActiveProjectile());
						this.setNextShot(mainGame.getCurrentTime()
								+ nextShotDelay);
					}
				}
			}

		}
		if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) mainGame.pauseResume();
		
	}

}
