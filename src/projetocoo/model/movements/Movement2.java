package projetocoo.model.movements;

import java.util.ArrayList;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.Projectile;
import projetocoo.model.projectile.ActiveProjectile;

public class Movement2 implements MovementType {

	public void updatePosition(Enemy e) {
		MainGame mainGame = MainGame.getInstance();
		double x = e.getX(), y = e.getY();
		boolean shootNow = false;
		double previousY = e.getY();

		x += e.getVx() * Math.cos(e.getAngle()) * mainGame.getDelta();
		y += e.getVy() * Math.sin(e.getAngle()) * mainGame.getDelta() * (-1.0);
		e.setPosition(x, y);
		e.setAngle(e.getAngle() + e.getRv() * mainGame.getDelta());

		double threshold = GameLib.HEIGHT * 0.30;

		if (previousY < threshold && e.getY() >= threshold) {

			if (e.getX() < GameLib.WIDTH / 2)
				e.setRv(0.003);
			else
				e.setRv(-0.003);
		}

		if (e.getRv() > 0 && Math.abs(e.getAngle() - 3 * Math.PI) < 0.05) {
			e.setRv(0.0);
			e.setAngle(3 * Math.PI);
			shootNow = true;
		}

		if (e.getRv() < 0 && Math.abs(e.getAngle()) < 0.05) {
			e.setRv(0.0);
			e.setAngle(0.0);
			shootNow = true;
		}

		if (shootNow) {
			int k = 0;
			double[] angles = { Math.PI / 2 + Math.PI / 8, Math.PI / 2,
					Math.PI / 2 - Math.PI / 8 };
			
			ArrayList<Projectile> freeArray = (ArrayList<Projectile>) e.findFreeIndex(angles.length);

			for (Projectile p : freeArray) {

				double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI
						/ 12;
				double vx = Math.cos(a);
				double vy = Math.sin(a);

				p.setPosition(e.getX(), e.getY());
				p.setVelocity(vx * 0.30, vy * 0.30);
				p.setState(new ActiveProjectile());
				k++;
			}

		}

	}

}
