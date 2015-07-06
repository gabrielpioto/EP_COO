package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.model.base.Projectile;
import projetocoo.model.projectile.InactiveProjectile;

public class EnemyProjectile extends Projectile{

	public EnemyProjectile() {
		super(2.0);
	}
	
	@Override
	public void draw() {
		GameLib.setColor(Color.RED);
		GameLib.drawCircle(getX(), getY(), getRadius());
	}
	
	

}
