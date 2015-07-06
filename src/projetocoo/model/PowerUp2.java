package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.PowerUp;

public class PowerUp2 extends PowerUp{
	
	public PowerUp2(double radius) {
		super(radius);
		setColor(Color.GREEN);
	}
	
	@Override
	public void boost(Player p) {
		p.setNextShotDelay(p.getNextShotDelay() - 30);		
	}

	@Override
	public void spawn() {
		MainGame mainGame = MainGame.getInstance();
		double x, y;

		x = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
		y = -10.0;
		this.setPosition(x, y);
		this.setVelocity(0.0, 0.20 + Math.random() * 0.15);

		mainGame.setNextPowerUp2Delay(mainGame.getCurrentTime() + 5000);
		this.setActive(true);		
	}
	

}
