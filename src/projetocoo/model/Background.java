package projetocoo.model;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.MainGame;
import projetocoo.model.base.Element;

public class Background extends Element {

	private double count;

	MainGame game = MainGame.getInstance();

	double background_count = 0.0;
	Color color;

	/*
	 * background_count serve para marcar o quanto a estrela andou no tempo
	 * delta dada uma velocidade predeterminada "getVy() * game.getDelta()" o
	 * quanto ela andou � somado ao Y dela para ela ir descendo ao chegar no
	 * final da tela ela volta para o topo ( % gameLib.HEIGHT)
	 */

	public Background(double x, double y, double speed, double count, int size, Color color) {
		super(x, y, speed, 0.0, size);
		setColor(color);
		setCount(count);
	}

	private void setColor(Color color) {
		this.color = color;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getCount() {
		return count;
	}

	@Override
	public void draw() {

		GameLib.setColor(color);
		count += getVy() * game.getDelta();
		GameLib.fillRect(getX(), (getY() + count) % GameLib.HEIGHT,
				getRadius(), getRadius());

	}

}
