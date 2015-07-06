package projetocoo.model.base;

public abstract class Element implements Drawable{

	private double x;
	private double y;
	private double vx;
	private double vy;
	private double radius;

	public Element() {
		this( 0.0, 0.0);
	}
	
	public Element( double x, double y) {
		this(x, y, 0.0, 0.0, 0.0);
	}
	
	public Element(double x, double y, double vx, double vy, double radius) {
		setPosition(x, y);
		setVelocity(vx,vy);
		setRadius(radius);
	}	

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}	
	
	public abstract void draw();

}
