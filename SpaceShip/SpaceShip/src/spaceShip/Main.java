package spaceShip;

import java.util.Scanner;
public class Main {
	 //			 			3 (1,1)3.4_(2,3)1_(1,2)4
	private static Ship sp;//	0-2_2-1
	private static Scanner sysin=new Scanner(System.in);
	public static void main(String[] args) {
		Meteorite[] MTR;
		System.out.println("Please Enter Meteorites' inputs.");
		MTR=createMeteorites(ignorSpaces(sysin.nextLine()));
		System.out.println("Please Enter Ship inputs.");
		sp=new Ship(ignorSpaces(sysin.nextLine()));
		Radar RD=new Radar(MTR, sp);
		showRadar(RD.getRadarView());
		MTR=RD.getMeteorites();
		sp=RD.getShip();
		System.out.println("============");
		System.out.println(sp.pos.x);
		printMeteoriteValues(MTR);
		System.out.println("============");
		System.out.println(sp.virtual_pos.x);
		for(int i=0;i<MTR.length;i++) {
			System.out.println(MTR[i].virtual_pos.x+":"+MTR[i].virtual_pos.y+" --> "+MTR[i].speed);
		}
	}
	private static void showRadar(char[][]radar) {
		for(int i=0;i<radar.length;i++) {
			for(int j=0;j<radar[i].length;j++) {
				System.out.print(radar[i][j]);
			}
			System.out.println();
		}
	}
	@SuppressWarnings("unused")
	private static void printMeteoriteValues(Meteorite[] MTR) {
		for(int i=0;i<MTR.length;i++) {
			System.out.println(MTR[i].pos.x+":"+MTR[i].pos.y+" --> "+MTR[i].speed);
		}
	}
	private static String ignorSpaces(String inp) {
		String out=new String();
		for(int i=0;i<inp.length();i++) {
			if(inp.charAt(i)!=' ') {
				out+=inp.charAt(i);
			}
		}
		return out;
	}
	private static Meteorite[] createMeteorites(String inp) {
		Meteorite[] MTR = null;
		int c=0;
		inp='_'+inp+'_';
		String tmp=new String();
		
		int count=-1;
		int i_tmp=-1,j_tmp=-1;
		double speed_tmp=-1;
		for(int k=0;k<inp.length() ;k++) {
			if(inp.charAt(k)=='_' || inp.charAt(k)=='-'||inp.charAt(k)=='(' ||inp.charAt(k)==')'||inp.charAt(k)==',') {
				
				if(tmp.length()>0) {
					if(count==-1) {
						count=Integer.valueOf(tmp);
						MTR=new Meteorite[count];
					}
					else if(i_tmp==-1) {
						i_tmp=Integer.valueOf(tmp);
					
					}
					else if(j_tmp==-1) {
						j_tmp=Integer.valueOf(tmp);
					}
					else if(speed_tmp==-1) {
						speed_tmp=Double.valueOf(tmp);
					}
				}
				tmp=new String();
			}
			else {
					tmp+=inp.charAt(k);
			}
			if(i_tmp!=-1 && j_tmp!=-1 && speed_tmp!=-1) {
				MTR[c]=new Meteorite(); 
				MTR[c].pos.x=i_tmp;
				MTR[c].pos.y=j_tmp;
				MTR[c].speed=speed_tmp;
				//System.out.println(MTR[c].i+":"+MTR[c].j+" -> "+MTR[c].speed);
				i_tmp=-1;
				j_tmp=-1;
				speed_tmp=-1;
				c++;
			}
		}
		return MTR;
	}
	
}
