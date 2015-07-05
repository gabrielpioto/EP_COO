package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.movements.Movement1;
import projetocoo.model.shooter.ActiveShooter;
import projetocoo.model.shooter.ExplodingShooter;

public class Enemy1 extends Enemy {
	
	public Enemy1(double radius) {
		super(radius);
		setMovementType(new Movement1());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		if (getState() instanceof ExplodingShooter) {
			double alpha = (MainGame.getInstance().getCurrentTime() - getExplosionStart())
					/ (getExplosionEnd() - getExplosionStart());
			GameLib.drawExplosion(getX(), getY(), alpha);
		}
		if (getState() instanceof ActiveShooter) {
			GameLib.setColor(Color.CYAN);
			GameLib.drawCircle(getX(), getY(), getRadius());
		}

	}

	@Override
	public void spawn(){
		MainGame mainGame = MainGame.getInstance();
		double x,y;

		x = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
		y = -10.0;
		this.setPosition(x, y);
		this.setVelocity(0.0, 0.20 + Math.random() * 0.15);
		this.setAngle(3 * Math.PI / 2);
		this.setRv(0.0);
		//this.activateProjectiles();
		this.setNextShot(mainGame.getCurrentTime() + 500);
		mainGame.setNextEnemy1Delay(mainGame.getCurrentTime() + 500);
		
		this.setState(new ActiveShooter());
	}
}
