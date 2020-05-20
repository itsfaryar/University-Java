package main;

public class Position {
	public int i;
	public int j;
	public Position(int i,int j) {
		this.i=i;
		this.j=j;
	}
	public boolean equals(Object obj) {
		Position p=(Position)obj;
		if(p.i==this.i&&p.j==this.j)return true;
		else return false;
	}
	public boolean equals(int i,int j) {
		if(i==this.i&&j==this.j)return true;
		else return false;
	}
}
