package projetocoo.model.movements;

import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.Projectile;
import projetocoo.model.projectile.ActiveProjectile;

public class Movement1 implements MovementType {

	public void updatePosition(Enemy e) {
		MainGame mainGame = MainGame.getInstance();
		double x, y;
		
		x = e.getX() + e.getVx() * Math.cos(e.getAngle()) * mainGame.getDelta();
		y = e.getY() + e.getVy() * Math.sin(e.getAngle()) * mainGame.getDelta()
				* (-1.0);
		e.setAngle(e.getAngle() + e.getRv() * mainGame.getDelta());

		e.setPosition(x, y);

		if (mainGame.getCurrentTime() > e.getNextShot()
				&& e.getY() < mainGame.getPlayer().getY()) {
			Projectile free = e.findFreeIndex();
			double vx, vy;

			if (free != null) {
				free.setPosition(e.getX(), e.getX());

				vx = Math.cos(e.getAngle()) * 0.45;
				vy = Math.sin(e.getAngle()) * 0.45 * (-1.0);

				free.setVelocity(vx, vy);

				free.setState(new ActiveProjectile());

				e.setNextShot((long) (mainGame.getCurrentTime() + 200 + Math
						.random() * 500));
			}
		}

	}

}
