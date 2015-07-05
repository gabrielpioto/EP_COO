package projetocoo.model.shooter;

import java.util.List;

import projetocoo.MainGame;
import projetocoo.model.Player;
import projetocoo.model.base.Element;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.Shooter;

public class ExplodingShooter implements ShooterState {
	private MainGame mainGame = MainGame.getInstance();

	public ShooterState checkCollision(List<? extends Element> elements, Shooter shooter) {
		return this;
	}

	public ShooterState update(Shooter shooter) {

		if (mainGame.getCurrentTime() > shooter.getExplosionEnd()) {
			//Enemy
			if (shooter.getClass().isInstance(Enemy.class)) {
				
				return new InactiveShooter();
			//Player	
			} else if (shooter.getClass().isInstance(Player.class)) {
				
				return new ActiveShooter();
				
			}
			
		}
		return new InactiveShooter();
	}

}
