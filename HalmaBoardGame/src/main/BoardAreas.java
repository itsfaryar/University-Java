package main;

public class BoardAreas {
	private Position[]area_1=new Position[10];
	private Position[]area_2=new Position[10];
	private Position[]area_3=new Position[10];
	private Position[]area_4=new Position[10];
	public BoardAreas() {
		area_1[0]=new Position(4,7);area_1[1]=new Position(5,6);area_1[2]=new Position(5,7);area_1[3]=new Position(6,5);area_1[4]=new Position(6,6);area_1[5]=new Position(6,7);area_1[6]=new Position(7,4);area_1[7]=new Position(7,5);area_1[8]=new Position(7,6);area_1[9]=new Position(7,7);
		area_2[0]=new Position(0,0);area_2[1]=new Position(0,1);area_2[2]=new Position(0,2);area_2[3]=new Position(0,3);area_2[4]=new Position(1,0);area_2[5]=new Position(1,1);area_2[6]=new Position(1,2);area_2[7]=new Position(2,0);area_2[8]=new Position(2,1);area_2[9]=new Position(3,0);
		area_3[0]=new Position(4,0);area_3[1]=new Position(5,0);area_3[2]=new Position(5,1);area_3[3]=new Position(6,0);area_3[4]=new Position(6,1);area_3[5]=new Position(6,2);area_3[6]=new Position(7,0);area_3[7]=new Position(7,1);area_3[8]=new Position(7,2);area_3[9]=new Position(7,3);
		area_4[0]=new Position(0,4);area_4[1]=new Position(0,5);area_4[2]=new Position(0,6);area_4[3]=new Position(0,7);area_4[4]=new Position(1,5);area_4[5]=new Position(1,6);area_4[6]=new Position(1,7);area_4[7]=new Position(2,6);area_4[8]=new Position(2,7);area_4[9]=new Position(3,7);
	}
	public Position[]getStarterPoints_one(Player pl){
		if(pl.getNumber()==1)return area_1;
		else if(pl.getNumber()==2)return area_2;
		else if(pl.getNumber()==3)return area_3;
		else if(pl.getNumber()==4)return area_4;
		else return null;
	}
}
