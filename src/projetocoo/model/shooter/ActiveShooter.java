package projetocoo.model.shooter;

import java.util.List;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.Enemy1;
import projetocoo.model.Enemy2;
import projetocoo.model.base.Element;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.Shooter;

public class ActiveShooter implements ShooterState {
	

	public ShooterState checkCollision(List<? extends Element> elements, Shooter shooter) {
		MainGame mainGame = MainGame.getInstance();

		for (Element e : elements) {

			if (shooter.checkCollision(e)) {
				shooter.setExplosionStart(mainGame.getCurrentTime());
				shooter.setExplosionEnd(2000 + mainGame.getCurrentTime());
				return new ExplodingShooter();
			}

		}
		return this;
	}
	

	public ShooterState update(Shooter shooter) {

		if (shooter instanceof Enemy) {

			/* verificando se inimigo saiu da tela */
			if (shooter instanceof Enemy1
					&& shooter.getY() > GameLib.HEIGHT + 10) {

				return new InactiveShooter();

			} else if (shooter instanceof Enemy2
					&& (shooter.getX() < -10 || shooter.getX() > GameLib.WIDTH + 10)) {

				return new InactiveShooter();

			} else {

				((Enemy) shooter).updatePosition();

			}

		}

		return this;
	}

}
