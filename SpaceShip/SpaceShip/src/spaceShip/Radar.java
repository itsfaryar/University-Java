package spaceShip;

import java.text.DecimalFormat;

import spaceShip.Ship.ship_status;

public class Radar {
	private Meteorite[]MTR;
	private Ship sp;
	private Position min_pos;
	private Position max_pos;
	private int last_meteorite_i;
	public Radar(Meteorite[]MTR,Ship sp) {
		this.MTR=MTR;
		this.sp=sp;
		this.last_meteorite_i=getSafePoint();
		
		//this.radar_pos_calibrator=getMinPos();
	}
	public Meteorite[]getMeteorites(){
		return MTR;
	}
	public Ship getShip() {
		return sp;
	}
	public void refreshShipStatus() {
		//System.out.println("Safe Point: "+getCoordinate(MTR[last_meteorite_i].pos));
		if((sp.pos.x>MTR[last_meteorite_i].pos.x) || (sp.pos.x==MTR[last_meteorite_i].pos.x && sp.pos.y>MTR[last_meteorite_i].pos.y)) {
			sp.state=ship_status.SAIFLY_PASSED;
		}
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
		findAndSetMaxMin();
		//System.out.println("max_pos:"+getCoordinate(max_pos));
		//System.out.println("min_pos:"+getCoordinate(min_pos));
		generateVirtualPositions();
		char[][] radar=new char[rondInt((max_pos.y-min_pos.y)+1)][rondInt((max_pos.x)+1)];
		for(int i=0;i<radar.length;i++) {
			for(int j=0;j<radar[i].length;j++) {
				radar[i][j]='.';
			}
		}
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].virtual_pos.x>=0) {
				radar[(int)(MTR[i].virtual_pos.y)][(int)(MTR[i].virtual_pos.x)]='M';
			}
		}
		if(sp.virtual_pos.x>=0) {
			if(sp.state==ship_status.CRASHED) {
				radar[(int)(sp.virtual_pos.y)][(int)(sp.virtual_pos.x)]='X';
			}
			else {
				if(radar[(int)(sp.virtual_pos.y)][(int)(sp.virtual_pos.x)]=='M') {
					radar[(int)(sp.virtual_pos.y)][(int)(sp.virtual_pos.x)]='$';
				}
				else {
					radar[(int)(sp.virtual_pos.y)][(int)(sp.virtual_pos.x)]='S';
				}
				
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
		return (inp>=0)? (int)(inp+0.5):(int)(inp-0.5);
	}
	private void generateVirtualPositions()
	{
		sp.virtual_pos.x=rondInt(sp.pos.x);
		sp.virtual_pos.y=rondInt(max_pos.y)-rondInt(sp.pos.y);
		for(int i=0;i<MTR.length;i++) {
			MTR[i].virtual_pos.x=rondInt(MTR[i].pos.x);
			MTR[i].virtual_pos.y=rondInt(max_pos.y)-rondInt(MTR[i].pos.y);
		}
	}
	private int getSafePoint() {
		int out=0;
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x>MTR[out].pos.x) {
				out=i;
			}
		}
		return out;
	}
	private void findAndSetMaxMin() {
		max_pos=new Position(sp.pos);
		min_pos=new Position(sp.pos);
		for(int i=0;i<MTR.length;i++) {
			if(MTR[i].pos.x<min_pos.x) {min_pos.x=MTR[i].pos.x;}
			if(MTR[i].pos.y<min_pos.y) {min_pos.y=MTR[i].pos.y;}
			if(MTR[i].pos.x>max_pos.x) {max_pos.x=MTR[i].pos.x;}
			if(MTR[i].pos.y>max_pos.y) {max_pos.y=MTR[i].pos.y;}
		}
	}
	
}
