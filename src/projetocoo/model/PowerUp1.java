package projetocoo.model;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.PowerUp;

public class PowerUp1 extends PowerUp {

	public PowerUp1(double radius) {
		super(radius);
	}

	@Override
	public void boost(Player p) {

		p.setRadius(p.getRadius() / 2);

	}

	@Override
	public void spawn() {
		MainGame mainGame = MainGame.getInstance();
		double x, y;

		x = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
		y = -10.0;
		this.setPosition(x, y);
		this.setVelocity(0.0, 0.20 + Math.random() * 0.15);

		mainGame.setNextPowerUp1Delay(mainGame.getCurrentTime() + 5000);
		this.setActive(true);
	}

}
