package projetocoo.model.shooter;

import java.util.List;

import projetocoo.GameLib;
import projetocoo.MainGame;
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
			boolean outY = shooter.getY() > GameLib.HEIGHT + 10 || shooter.getY() < -10;
			boolean outX = shooter.getX() > GameLib.WIDTH + 10 || shooter.getX() < -10;
			if(outX || outY) return new InactiveShooter();
			((Enemy) shooter).updatePosition();

		}

		return this;
	}

}
