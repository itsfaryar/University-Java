package digOut;

import java.util.Random;


public class Monster {
	private enum Direction{UP,DOWN,RIGHT,LEFT}
	private Position monster_pos;
	static double r=100;
	static double l=100;
	static double u=100;
	static double d=100;
	static double s=400;
	static double p=60;
	static double regenerator=3;
	public Position getMonster_pos() {
		return monster_pos;
	}
	public void setMonster_pos(Position monster_pos) {
		this.monster_pos = monster_pos;
	}
	private static void normalizeOdds() {
		double tmp=0.0;
		if(r<100) {
			tmp=(100-r)/regenerator;
			r+=tmp;
			l-=tmp/3;
			u-=tmp/3;
			d-=tmp/3;
		}
		if(l<100) {
			tmp=(100-l)/regenerator;
			r-=tmp/3;
			l+=tmp;
			u-=tmp/3;
			d-=tmp/3;
		}
		if(u<100) {
			tmp=(100-u)/regenerator;
			r-=tmp/3;
			l-=tmp/3;
			u+=tmp;
			d-=tmp/3;
		}
		if(d<100) {
			tmp=(100-d)/regenerator;
			r-=tmp/3;
			l-=tmp/3;
			u-=tmp/3;
			d+=tmp;
		}
	}
	private static int getRandomNumberInts(int min, int max){
		int out;
		Random random = new Random();
		out=random.ints(min,(max+1)).findFirst().getAsInt();
		return out;
	}
	private static Direction checkContingency(int inp) {
		Direction dir=null;
		if(inp>=0&&inp<r) {
			dir=Direction.RIGHT;
		}
		else if(inp>=r&&inp<(r+l)) {
			dir=Direction.LEFT;
		}
		else if(inp>=(r+l)&&inp<(r+l+u)) {
			dir=Direction.UP;
		}
		else if(inp>=(r+l+u) && inp<(s)) {
			dir=Direction.DOWN;
		}
		return dir;
	}
	public void setNextRndPos(boolean[]permission) {//1for right/2forleft/3forup/4fordown
		normalizeOdds();
		
		while(true) {
			int rnd=getRandomNumberInts(1,(int)s);
			Direction dir=checkContingency(rnd);
			if(dir==Direction.RIGHT && permission[1]) {
				if(l>10) {
					r+=p/3;
					l-=p;
					u+=p/3;
					d+=p/3;
				}
				monster_pos.j++;
				break;
			}
			else if(dir==Direction.LEFT && permission[2]) {
				if(r>10) {
					r-=p;
					l+=p/3;
					u+=p/3;
					d+=p/3;
				}
				monster_pos.j--;
				break;
			}
			else if(dir==Direction.UP&& permission[3]) {
				if(d>10) {
					r+=p/3;
					l+=p/3;
					u+=p/3;
					d-=p;
				}
				monster_pos.i--;
				break;
			}
			else if(dir==Direction.DOWN&& permission[4]) {
				if(u>10) {
					r+=p/3;
					l+=p/3;
					u-=p;
					d+=p/3;
				}
				monster_pos.i++;
				break;
			}
			s=r+l+u+d;
		}
		
	}
}
