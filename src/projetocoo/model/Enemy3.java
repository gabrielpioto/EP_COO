package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Enemy;
import projetocoo.model.movements.Movement3;
import projetocoo.model.shooter.ActiveShooter;

public class Enemy3 extends Enemy {

	public Enemy3() {
		super(30.0);
		setMovementType(new Movement3());
	}

	@Override
	public void spawn() {
		MainGame mainGame = MainGame.getInstance();
		double x, y;

		x = -10;
		y = 80;
		this.setPosition(x, y);
		this.setVelocity(0.1, 0.1);
		Player p = mainGame.getPlayer();
		
		this.setAngle(Math.atan((getY() - p.getY()) / (p.getX() - getX())));
		this.setRv(0.0);
		// this.activateProjectiles();
		this.setNextShot(mainGame.getCurrentTime() + 300);
		mainGame.setNextEnemy3Delay(mainGame.getCurrentTime() + 300);

		this.setState(new ActiveShooter());

	}

	@Override
	public void draw() {
		GameLib.setColor(Color.BLUE);
		GameLib.fillRect(getX(), getY(), getRadius(), getRadius());
	}

}
