package spaceShip;

import java.text.DecimalFormat;

import spaceShip.Ship.ship_status;

public class Radar {
	private Meteorite[]MTR;
	private Ship sp;
	private Position min_pos;
	private Position max_pos;
	double safe_point=-1;
	public Radar(Meteorite[]MTR,Ship sp) {
		this.MTR=MTR;
		this.sp=sp;
		safe_point=getSafePoint();
		//this.radar_pos_calibrator=getMinPos();
	}
	public Meteorite[]getMeteorites(){
		return MTR;
	}
	public Ship getShip() {
		return sp;
	}
	public void refreshShipStatus() {
		if(sp.pos.x>safe_point)sp.state=ship_status.SAIFLY_PASSED;
		else {
			for(int i=0;i<MTR.length;i++) {
				if(MTR[i].pos.isEqual(sp.pos)) {
					sp.state=ship_status.CRASHED;
				}
			}
		}
	}
	public String getRadarData(double time) {
		String out=new String();
		out+="t="+String.format("%.2f",time)+" ";
		out+="R="+getCoordinate(sp.pos)+" ";
		for(int i=0;i<MTR.length;i++) {
			out+="M"+(i+1)+"="+getCoordinate(MTR[i].pos)+" ";
		}
		out+=sp.state;
		return out;
	}
	public char[][] getRadarView() {
		generateVirtualPositions();
		this.min_pos=getMinPos();
		this.max_pos=getMaxPos();
		char[][] radar=new char[rondInt(max_pos.y+1)][rondInt(max_pos.x+1)];
		for(int i=0;i<radar.length;i++) {
			for(int j=0;j<radar[i].length;j++) {
				radar[i][j]='.';
			}
		}
		
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].virtual_pos.x>=0 && MTR[i].virtual_pos.y>=0) {
				radar[rondInt(MTR[i].virtual_pos.y)][rondInt(MTR[i].virtual_pos.x)]='M';
			}
		}
		if(sp.virtual_pos.x>=0 && sp.virtual_pos.y>=0) {
			if(sp.state==ship_status.CRASHED) {
				radar[rondInt(sp.virtual_pos.y)][rondInt(sp.virtual_pos.x)]='X';
			}
			else {
				radar[rondInt(sp.virtual_pos.y)][rondInt(sp.virtual_pos.x)]='S';
			}
		}
		return radar;
	
	}
	public int convertTo1DPos(Position p) {
		return rondInt((p.x*max_pos.x)+p.y);
	}
	//////////////////////
	private String getCoordinate(Position p) {
		DecimalFormat f = new DecimalFormat("0.00");
		return "("+f.format(p.x)+","+f.format(p.y)+")";
	}
	private int rondInt(double inp) {
		return (int)(inp+0.5);
	}
	private void generateVirtualPositions() {
		this.min_pos=getMinPos();
		this.max_pos=getMaxPos();
		/*sp.virtual_pos.x=sp.pos.x-min_pos.x;
		sp.virtual_pos.y=(max_pos.y-min_pos.y)-sp.pos.y-min_pos.y;
		System.out.println("x:"+sp.virtual_pos.x+" "+"y:"+sp.virtual_pos.y);
		for(int i=0;i<MTR.length;i++) {
			MTR[i].virtual_pos.x=MTR[i].pos.x-min_pos.x;
			MTR[i].virtual_pos.y=(max_pos.y-min_pos.y)-MTR[i].pos.y-min_pos.y;
		}*/
		if(sp.pos.x>=0 &&sp.pos.y>=0) {
			sp.virtual_pos.x=sp.pos.x;
			sp.virtual_pos.y=(max_pos.y-sp.pos.y);
		}
		else {
			sp.virtual_pos.x=-1;
			sp.virtual_pos.y=-1;
		}
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x>=0 && MTR[i].pos.y>=0) {
				MTR[i].virtual_pos.x=MTR[i].pos.x;
				MTR[i].virtual_pos.y=(max_pos.y-MTR[i].pos.y);
			}
			else {
				MTR[i].virtual_pos.x=-1;
				MTR[i].virtual_pos.y=-1;
			}
		}
	}
	private double getSafePoint() {
		double out=-1;
		out=MTR[0].pos.x;
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x>out) {
				out=MTR[i].pos.x;
			}
		}
		return out;
	}
	private Position getMinPos() {
		Position pos=new Position();
		pos.y=sp.pos.y;
		pos.x=sp.pos.x;
		boolean flg=false;
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x<pos.x) {pos.x=MTR[i].pos.x;}
			if(MTR[i].pos.y<pos.y) {pos.y=MTR[i].pos.y;}
		}
		return pos;
	}
	private Position getMaxPos() {
		Position pos=new Position();
		pos.y=sp.pos.y;
		pos.x=sp.pos.x;
		boolean flg=false;
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x>pos.x) {pos.x=MTR[i].pos.x;}
			if(MTR[i].pos.y>pos.y) {pos.y=MTR[i].pos.y;}
		}
		return pos;
	}
}
