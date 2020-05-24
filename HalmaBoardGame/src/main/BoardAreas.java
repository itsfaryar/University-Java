package main;

import java.util.ArrayList;

public class BoardAreas {
	private ArrayList<Position>area_1=new ArrayList<Position>();
	private ArrayList<Position>area_2=new ArrayList<Position>();
	private ArrayList<Position>area_3=new ArrayList<Position>();
	private ArrayList<Position>area_4=new ArrayList<Position>();
	public BoardAreas(int w,int n) {
		
		for(int i=w-1;i>w-n-1;i--) {
			for(int j=w-1;j>w-1-n-(i-w+1);j--) {
				area_1.add(new Position(i,j));
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n-i;j++) {
				area_2.add(new Position(i,j));
			}
		}
		for(int i=w-1;i>w-n-1;i--) {
			for(int j=0;j<n+(i-w+1);j++) {
				area_3.add(new Position(i,j));
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=w-1;j>w-1-n+i;j--) {
				area_4.add(new Position(i,j));
			}
		}
		
	}
	public int calTawCounts(int n) {
		int out=0;
		for(;n>0;n--)out+=n;
		return out;
	}
	public ArrayList<Position>getStarterPoints(Player pl){
		if(pl.getNumber()==1)return area_1;
		else if(pl.getNumber()==2)return area_2;
		else if(pl.getNumber()==3)return area_3;
		else if(pl.getNumber()==4)return area_4;
		else return null;
	}
	public ArrayList<Position>getWinnerArea(Player pl){
		if(pl.getNumber()==1)return area_2;
		else if(pl.getNumber()==2)return area_1;
		else if(pl.getNumber()==3)return area_4;
		else if(pl.getNumber()==4)return area_3;
		else return null;
	}
}
