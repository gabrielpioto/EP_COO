package projetocoo.model.shooter;

import java.util.List;

import projetocoo.MainGame;
import projetocoo.model.Player;
import projetocoo.model.base.Element;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.Shooter;

public class ExplodingShooter implements ShooterState {
	

	public ShooterState checkCollision(List<? extends Element> elements, Shooter shooter) {
		return this;
	}

	public ShooterState update(Shooter shooter) {
		MainGame mainGame = MainGame.getInstance();
		
		if (mainGame.getCurrentTime() > shooter.getExplosionEnd()) {
			//Enemy
			if (shooter instanceof Enemy) {
				return new InactiveShooter();
			//Player	
			} else if (shooter instanceof Player) {
				return new ActiveShooter();				
			}
			
		}
		return this;
	}

}
