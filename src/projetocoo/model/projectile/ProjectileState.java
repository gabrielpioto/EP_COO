package projetocoo.model.projectile;

import projetocoo.model.base.Projectile;

public interface ProjectileState {
	ProjectileState update(Projectile projectile);
}
