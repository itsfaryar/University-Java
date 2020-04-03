/*test inputs
 * test 1:

6 (1,2)0.7_(2,1)2_(3,3)1.5_(4,1)0.2_(5,4)0.8_(6,2)0.5
0-1_1-2_2-1_4-2

 * test 2:

4 (1,1)1_(2,3)1.5_(3,2)0.5_(4,4)1
0-2_2-1_3-0.5

///////////////////////
 */
package spaceShip;

import java.util.Scanner;
import spaceShip.Ship.ship_status;
public class Main {

	private static Ship sp;
	private static Scanner sysin=new Scanner(System.in);
	public static void main(String[] args) {
		double time=0;
		Meteorite[] MTR;
		Radar RD=null;
		boolean flg=true;
		while(flg) {
			try {
				System.out.println("Please Enter Meteorites' inputs.");
				MTR=createMeteorites(ignorSpaces(sysin.nextLine()));
				System.out.println("Please Enter Ship inputs.");
				sp=new Ship(ignorSpaces(sysin.nextLine()));
				sp.getSpeed();
				RD=new Radar(MTR, sp);
				RD.getRadarData(0);
				RD.getRadarView();
				flg=false;
			} catch (Exception e) {
				System.err.println("There is something wrong about your inputs! Please Try Again");
			}
		}
		while(true) {
			System.out.println("+==============================+");
			RD.refreshShipStatus();
			MTR=RD.getMeteorites();
			sp=RD.getShip();
			System.out.println(RD.getRadarData(time));
			showRadar(RD.getRadarView());
			if(sp.state==ship_status.SAFE) {
				double delta_t=sp.moveShip(1);
				for(int i=0;i<MTR.length;i++) {
					MTR[i].pos.y-=(MTR[i].speed)*delta_t;
				}
				time+=delta_t;
			}	
			else {
				break;
			}
		
		}
	}
	private static void showRadar(char[][]radar) {
		System.out.println("--------------------");
		for(int i=0;i<radar.length;i++) {
			for(int j=0;j<radar[i].length;j++) {
				System.out.print(radar[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------");
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
		double i_tmp=-1,j_tmp=-1;
		boolean got_i=false,got_j=false;
		double speed_tmp=-1;
		for(int k=0;k<inp.length() ;k++) {
			if(inp.charAt(k)=='_' ||inp.charAt(k)=='(' ||inp.charAt(k)==')'||inp.charAt(k)==',') {
				
				if(tmp.length()>0) {
					if(count==-1) {
						count=Integer.valueOf(tmp);
						MTR=new Meteorite[count];
					}
					else if(!got_i) {
						if(tmp.charAt(0)=='-') {
							i_tmp=Double.valueOf(tmp.substring(1))*-1;
						}
						else {
							i_tmp=Double.valueOf(tmp);
						}
						got_i=true;
					}
					else if(!got_j) {
						if(tmp.charAt(0)=='-') {
							j_tmp=Double.valueOf(tmp.substring(1))*-1;
						}
						else {
							j_tmp=Double.valueOf(tmp);
						}
						got_j=true;
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
			if(got_i && got_j && speed_tmp!=-1) {
				MTR[c]=new Meteorite(); 
				MTR[c].pos.x=i_tmp;
				MTR[c].pos.y=j_tmp;
				MTR[c].speed=speed_tmp;
				//System.out.println(MTR[c].i+":"+MTR[c].j+" -> "+MTR[c].speed);
				got_i=false;
				got_j=false;
				speed_tmp=-1;
				c++;
			}
		}
		return MTR;
	}
	
}
