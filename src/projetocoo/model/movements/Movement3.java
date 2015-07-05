package projetocoo.model.movements;

import projetocoo.MainGame;
import projetocoo.model.Player;
import projetocoo.model.base.Enemy;

public class Movement3 implements MovementType{
	
	
	@Override
	public void updatePosition(Enemy e) {
		MainGame mainGame = MainGame.getInstance();
		Player p = mainGame.getPlayer();
		double x, y;
		
		x = e.getX() + e.getVx() * Math.cos(e.getAngle()) * mainGame.getDelta();
		y = e.getY() + e.getVy() * Math.sin(e.getAngle()) * mainGame.getDelta()
				* (-1.0);
		System.out.println(e.getAngle());
		e.setAngle(Math.atan((e.getY() - p.getY()) / (p.getX() - e.getX())));

		e.setPosition(x, y);
		
	}

}
