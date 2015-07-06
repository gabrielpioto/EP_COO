package projetocoo.model.base;

import java.awt.Color;
import java.util.List;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.Player;

public abstract class PowerUp extends Element implements Updatable{
	private boolean active = false;
	private Color color;

	public PowerUp() {
		super();
	}

	public PowerUp(double radius) {
		super();
		this.setRadius(radius);
	}

	public PowerUp(double x, double y, double vx, double vy, double radius) {
		super(x, y, vx, vy, radius);
	}

	public PowerUp(double x, double y) {
		super(x, y);
	}

	@Override
	public void draw() {
		if (active) {
			GameLib.setColor(color);
			GameLib.drawDiamond(getX(), getY(), getRadius());
		}
	}
	
	protected void setColor(Color color){
		this.color = color;
	}

	public abstract void boost(Player p);

	public abstract void spawn();

	public static void Spawn(List<? extends PowerUp> powerUps,
			long nextPowerUpDelay) {

		MainGame mainGame = MainGame.getInstance();

		if (mainGame.getCurrentTime() > nextPowerUpDelay) {

			for (PowerUp p : powerUps) {

				if (!p.isActive()) {

					p.spawn();
					return;
				}

			}
		}

	}

	public void update() {
		if (active) {
			Player p = MainGame.getInstance().getPlayer();
			if (checkCollision(p)) {
				boost(p);
				this.active = false;
			} else {
				updatePosition();
			}
		}
	}

	private void updatePosition() {
		boolean outY = this.getY() > GameLib.HEIGHT + 10 || this.getY() < -10;
		boolean outX = this.getX() > GameLib.WIDTH + 10 || this.getX() < -10;
		if (outX || outY) {
			this.setActive(false);
			return;
		}

		MainGame mainGame = MainGame.getInstance();
		double y = this.getY();
		y += this.getVy() * mainGame.getDelta();
		this.setPosition(this.getX(), y);
	}

	private boolean checkCollision(Player p) {
		double dx = p.getX() - getX();
		double dy = p.getY() - getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		return dist < (getRadius() + p.getRadius()) * 0.8;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
