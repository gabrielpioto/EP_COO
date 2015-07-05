package projetocoo.model.movements;

import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.projectile.ActiveProjectile;
import projetocoo.model.projectile.Projectile;

public class Movement1 implements MovementType {

	private MainGame mainGame = MainGame.getInstance();

	public void updatePosition(Enemy e) {
		double x, y;
		Projectile p;

		x = e.getX() + e.getVx() * Math.cos(e.getAngle()) * mainGame.getDelta();
		y = e.getY() + e.getVy() * Math.sin(e.getAngle()) * mainGame.getDelta()
				* (-1.0);
		e.setAngle(e.getAngle() + e.getRv() * mainGame.getDelta());

		e.setPosition(x, y);

		if (mainGame.getCurrentTime() > e.getNextShot()
				&& e.getY() < mainGame.getPlayer().getY()) {
			int free = e.findFreeIndex();
			double vx, vy;

			if (free < e.getNumberProjectiles()) {
				p = e.getProjectiles().get(free);
				p.setPosition(e.getX(), e.getX());

				vx = Math.cos(e.getAngle()) * 0.45;
				vy = Math.sin(e.getAngle()) * 0.45 * (-1.0);

				p.setVelocity(vx, vy);

				p.setState(new ActiveProjectile());

				e.setNextShot((long) (mainGame.getCurrentTime() + 200 + Math
						.random() * 500));
			}
		}

	}

}
