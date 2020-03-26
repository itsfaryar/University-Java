package spaceShip;


public class Radar {
	private Meteorite[]MTR;
	private Ship sp;
	private Position min_pos;
	private Position max_pos;
	public Radar(Meteorite[]MTR,Ship sp) {
		this.MTR=MTR;
		this.sp=sp;
		//this.radar_pos_calibrator=getMinPos();
	}
	public Meteorite[]getMeteorites(){
		return MTR;
	}
	public Ship getShip() {
		return sp;
	}
	public char[][] getRadarView() {
		generateVirtualPositions();
		char[][] radar=new char[max_pos.y+1][max_pos.x+1];
		for(int i=0;i<radar.length;i++) {
			for(int j=0;j<radar[i].length;j++) {
				radar[i][j]='#';
			}
		}
		radar[sp.virtual_pos.y][sp.virtual_pos.x]='S';
		for(int i=0;i<MTR.length;i++) {
			radar[MTR[i].virtual_pos.y][MTR[i].virtual_pos.x]='M';
		}
		return radar;
	
	}
	public int convertTo1DPos(Position p) {
		return (p.x*max_pos.x)+p.y;
	}
	private void generateVirtualPositions() {
		this.min_pos=getMinPos();
		this.max_pos=getMaxPos();
		sp.virtual_pos.x=sp.pos.x-min_pos.x;
		sp.virtual_pos.y=(max_pos.y-min_pos.y)-sp.pos.y-min_pos.y;
		for(int i=0;i<MTR.length;i++) {
			MTR[i].virtual_pos.x=MTR[i].pos.x-min_pos.x;
			MTR[i].virtual_pos.y=(max_pos.y-min_pos.y)-MTR[i].pos.y-min_pos.y;
		}
		
		
	}
	private Position getMinPos() {
		Position pos=new Position();
		pos.y=sp.pos.y;
		pos.x=sp.pos.x;
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x<pos.x)pos.x=MTR[i].pos.x;
			if(MTR[i].pos.y<pos.y)pos.y=MTR[i].pos.y;
		}
		System.out.println("min:"+"x:"+pos.x+" "+"y:"+pos.y);
		return pos;
	}
	private Position getMaxPos() {
		Position pos=new Position();
		pos.y=sp.pos.y;
		pos.x=sp.pos.x;
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x>pos.x)pos.x=MTR[i].pos.x;
			if(MTR[i].pos.y>pos.y)pos.y=MTR[i].pos.y;
		}
		System.out.println("MAx:"+"x:"+pos.x+" "+"y:"+pos.y);
		return pos;
	}
}
