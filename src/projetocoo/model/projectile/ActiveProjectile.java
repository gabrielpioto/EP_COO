package projetocoo.model.projectile;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Projectile;

public class ActiveProjectile implements ProjectileState {
	private MainGame mainGame = MainGame.getInstance();

	public ProjectileState update(Projectile projectile) {

		double x = projectile.getX(), y = projectile.getY();

		/* verificando se proj�til saiu da tela */
		if (projectile.getY() < 0 || projectile.getY() > GameLib.HEIGHT) {
			return new InactiveProjectile();
		} else {
			x += projectile.getVx() * mainGame.getDelta();
			y += projectile.getVy() * mainGame.getDelta();
			projectile.setPosition(x, y);
		}

		return this;
	}
}
