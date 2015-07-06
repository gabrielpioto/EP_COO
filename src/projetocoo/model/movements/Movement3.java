package projetocoo.model.movements;

import projetocoo.MainGame;
import projetocoo.model.Player;
import projetocoo.model.base.Enemy;

public class Movement3 implements MovementType{
	
	
	public void updatePosition(Enemy e) {
		MainGame mainGame = MainGame.getInstance();
		Player p = mainGame.getPlayer();
		double x, y;
		
		x = e.getX() + e.getVx() * Math.cos(e.getAngle()) * mainGame.getDelta();
		y = e.getY() + e.getVy() * Math.sin(e.getAngle()) * mainGame.getDelta()
				* (-1.0);
//		System.out.println(e.getAngle());
		double angle = Math.atan((e.getY() - p.getY()) / (p.getX() - e.getX())); 
		if(p.getX() < e.getX()) angle -= Math.PI;
		e.setAngle(angle);

		e.setPosition(x, y);
		
	}

}
