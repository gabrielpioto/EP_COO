package projetocoo.model.shooter;

import java.util.List;

import projetocoo.model.base.Element;
import projetocoo.model.base.Shooter;

public interface ShooterState {
	ShooterState checkCollision(List<? extends Element> elements,Shooter shooter);
	ShooterState update(Shooter shooter);
}
