package digOut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

import digOut.Control.capital_Sensitivity;

public class Records {
	private Player records[]; 
	private int n;
	private int index;
	private static final String data_path="Data/digOut/Records.SVD";
	public Records() {
		index=-1;
		n=0;
		getData();
	}
	public String getPrintableRecords() {
		String out=new String();
		if(n==0) {
			out+="There is no records yet.\n";
		}
		else {
			out+="Name | Coins | ground size | Bombs ON? | Time\n";
			for(int i=0;i<n;i++) {
				out+=records[i].getName()+" ";
				out+=records[i].getCoins()+" ";
				out+=records[i].getBoard_size()+" ";
				out+=records[i].getIs_bomb_mode()+" ";
				out+=(LocalTime.ofSecondOfDay(records[i].getPlaying_time()))+" ";
				out+="\n";
			}
		}
		return out;
	}
	public void getData() {
		File info=new File(data_path);
		try {
			if(info.exists()) {
				Scanner saved_rec=new Scanner(info);
				n=saved_rec.nextInt();
				records=new Player[n];
				for(int i=0;i<n;i++) {
					records[i]=new Player("");
					saved_rec.next();
					records[i].setName(saved_rec.next());
					saved_rec.next();
					records[i].setCoins(saved_rec.nextInt());
					saved_rec.next();
					records[i].setBoard_size(saved_rec.nextInt());
					saved_rec.next();
					records[i].setIs_bomb_mode(saved_rec.nextBoolean());
					saved_rec.next();
					records[i].setPlaying_time(saved_rec.nextInt());
				}
				saved_rec.close();
				
			}
			else {
				info.getParentFile().mkdirs();
			}
		} catch (Exception e) {
		}
	}
	public Player getPreviousRecord(Player pl) {
		Player out=null;
		if(n>0) {
			if(index==-1) {
				for(int i=0;i<n;i++) {
					if(records[i].getBoard_size()==pl.getBoard_size() && records[i].getIs_bomb_mode()==pl.getIs_bomb_mode() && records[i].getCoins()==pl.getCoins()) {
						index=i;
						out=records[index];
						break;
					}
				}
			}
			else {
					out=records[index];
			}
			
		}
		return out;
	}
	public boolean saveDatas(Player pl) {
		boolean out;
		int tmp_n=n;
		getPreviousRecord(pl);
		if(index!=-1) {
			if(pl.getPlaying_time()<records[index].getPlaying_time()) {
				out=true;
				
			}
			else {out=false;}
		}
		else {tmp_n++;out=true;}
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(data_path);
			fileWriter.write(tmp_n+"\n");
			for(int i=0;i<tmp_n;i++) {
				if(i>=n ||(index==i && out)) {
					fileWriter.write("N "+pl.getName()+" C "+pl.getCoins()+" S "+pl.getBoard_size()+" B "+pl.getIs_bomb_mode()+" T "+pl.getPlaying_time()+"\n");
				}
				else fileWriter.write("N "+records[i].getName()+" C "+records[i].getCoins()+" S "+records[i].getBoard_size()+" B "+records[i].getIs_bomb_mode()+" T "+records[i].getPlaying_time()+"\n");
			}
		    fileWriter.close();
		} catch (IOException e) {}
		getData();
		return out;
	}
	
}
