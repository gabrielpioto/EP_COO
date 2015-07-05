package projetocoo.model.projectile;

import java.awt.Color;

import projetocoo.GameLib;
import projetocoo.model.base.Element;

public class Projectile extends Element{
	
	protected ProjectileState state;
	private Color color;
	double e_projectile_radius = 2.0;
	
	public Projectile(double x, double y, double vx, double vy, Color color) {
		super(x, y, vx, vy, 0);
		setColor(color);
		this.state = new InactiveProjectile();
	}

	private void setColor(Color color) {
		this.color = color;		
	}

	@Override
	public void draw() {
		GameLib.setColor(color);
		
		if(color == Color.GREEN){
			//caso seja do player sera uma reta verde
			GameLib.drawLine(getX(), getY() - 5, getX(), getY() + 5);
			GameLib.drawLine(getX() - 1, getY() - 3, getX() - 1, getY() + 3);
			GameLib.drawLine(getX() + 1, getY() - 3, getX() + 1, getY() + 3);
		}else{
			//senão sera um circulo vermelho 
			GameLib.drawCircle(getX(), getY(), e_projectile_radius);
		}
	}

	public ProjectileState getState() {
		return state;
	}
	
	
	public void setState(ProjectileState state) {
		this.state = state;
	}

	public void update(){
		state = this.state.update(this);
	}


}
