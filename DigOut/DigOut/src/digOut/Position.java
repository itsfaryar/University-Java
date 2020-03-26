package digOut;
public class Position {
	public int i;
	public int j;
	public Position(int i,int j) {
		this.i=i;
		this.j=j;
	}
	public boolean equals(Position p) {
		if(i==p.i && j==p.j)return true;
		else return false;
	}
}
