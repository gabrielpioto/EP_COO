package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.model.base.Projectile;

public class PlayerProjectile extends Projectile{

	public PlayerProjectile() {
		super(0.0);
	}
	
	@Override
	public void draw() {
		GameLib.setColor(Color.GREEN);
		GameLib.drawLine(getX(), getY() - 5, getX(), getY() + 5);
		GameLib.drawLine(getX() - 1, getY() - 3, getX() - 1, getY() + 3);
		GameLib.drawLine(getX() + 1, getY() - 3, getX() + 1, getY() + 3);
	}
	

}
