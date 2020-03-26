package digOut;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Control {
	private static final String data_path="Data/digOut/Bind_Keys.KBD";
	public enum capital_Sensitivity{OFF,_ON};
	public capital_Sensitivity control_mode=capital_Sensitivity._ON;
	public char MOVE_UP='U';
	public char MOVE_DOWN='D';
	public char MOVE_RIGHT='R';
	public char MOVE_LEFT='L';
	public char DIG_UP='u';
	public char DIG_DOWN='d';
	public char DIG_RIGHT='r';
	public char DIG_LEFT='l';
	public Control() {
		getSetting();
	}
	public String getData_path() {
		return data_path;
	}
	public String getControlInfo() {
		String out=new String();
		out+="Key bind:\n";
		out+="Moving :"+MOVE_UP+": Up | "+MOVE_DOWN+":Down | "+MOVE_LEFT+":Left | "+MOVE_RIGHT+":Right\n";
		out+="Digging:"+DIG_UP+": Up | "+DIG_DOWN+":Down | "+DIG_LEFT+":Left | "+DIG_RIGHT+":Right\n";
		if(control_mode==capital_Sensitivity._ON)out+="Sencetivity to capital input=ON\n";
		else out+="Sencetivity to capital input=OFF\n";
		return out;
	}
	//////////////////////////////////
	public void getSetting() {
		File info=new File(data_path);
		try {
			if(info.exists()) {
				Scanner usr_setting=new Scanner(info);
				usr_setting.next();
				char tmp=usr_setting.next().charAt(0);
				if(tmp=='1')control_mode=capital_Sensitivity._ON;
				else if(tmp=='0')control_mode=capital_Sensitivity.OFF;
				usr_setting.next();
				MOVE_UP=usr_setting.next().charAt(0);
				usr_setting.next();
				MOVE_DOWN=usr_setting.next().charAt(0);
				usr_setting.next();
				MOVE_RIGHT=usr_setting.next().charAt(0);
				usr_setting.next();
				MOVE_LEFT=usr_setting.next().charAt(0);
				usr_setting.next();
				DIG_UP=usr_setting.next().charAt(0);
				usr_setting.next();
				DIG_DOWN=usr_setting.next().charAt(0);
				usr_setting.next();
				DIG_RIGHT=usr_setting.next().charAt(0);
				usr_setting.next();
				DIG_LEFT=usr_setting.next().charAt(0);
				usr_setting.close();
				
			}
			else {
				info.getParentFile().mkdirs();
				saveSetting();
			}
		} catch (Exception e) {
		}
	}
	public void saveSetting() {
	    FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(data_path);
			fileWriter.write(generateSavingData());
		    fileWriter.close();
		} catch (IOException e) {}
	    
	}
	/////////////////////////////////
	private String generateSavingData() {
		String data=new String();
		data+="CAPS_SENCETIVITY: ";
		if(control_mode==capital_Sensitivity._ON)data+="1\n";
		else data+="0\n";
		data+="MOVE_UP: "+MOVE_UP+"\n"+"MOVE_DOWN: "+MOVE_DOWN+"\n"+"MOVE_RIGHT: "+MOVE_RIGHT+"\n"+"MOVE_LEFT: "+MOVE_LEFT+"\n";
		data+="DIG_UP: "+DIG_UP+"\n"+"DIG_DOWN: "+DIG_DOWN+"\n"+"DIG_RIGHT: "+DIG_RIGHT+"\n"+"DIG_LEFT: "+DIG_LEFT;
		return data;
	}
}
