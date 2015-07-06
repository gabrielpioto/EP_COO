package projetocoo.model.base;

import projetocoo.model.projectile.InactiveProjectile;
import projetocoo.model.projectile.ProjectileState;

public abstract class Projectile extends Element implements Updatable{
	
	protected ProjectileState state;
	
	public Projectile(double radius) {
		super();
		setRadius(radius);
		setState(new InactiveProjectile());
	}

	public ProjectileState getState() {
		return state;
	}
	
	public void setState(ProjectileState state) {
		this.state = state;
	}

	public void update(){
		state = this.state.update(this);
	}


}
