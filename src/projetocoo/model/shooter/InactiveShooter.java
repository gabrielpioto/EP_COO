package projetocoo.model.shooter;

import java.util.List;

import projetocoo.model.base.Element;
import projetocoo.model.base.Shooter;

public class InactiveShooter implements ShooterState {

	public ShooterState checkCollision(List<? extends Element> elements,
			Shooter shooter) {
		return this;
	}

	public ShooterState update(Shooter shooter) {
		return this;
	}

}
