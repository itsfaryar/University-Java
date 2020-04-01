package spaceShip;


public class Position {
	public double x;
	public double y;
	public Position(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public Position() {
	}
	public boolean isEqual(Position p) {
		if(p.x==x && p.y==y)return true;
		else return false;
	}
}
