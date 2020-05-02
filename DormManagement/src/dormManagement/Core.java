package dormManagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Core implements Serializable{
	private Manager logined_manager;
	private ArrayList<Student>students;
	private ArrayList<Manager>managers;
	private ArrayList<Dorm>dorms;
	public Core() {
		students=new ArrayList<Student>();
		managers=new ArrayList<Manager>();
		dorms=new ArrayList<Dorm>();
		logined_manager=null;
	}
	//////////////////////manager
	public boolean is_logined() {
		if(logined_manager==null)return false;
		else return true;
	}
	public String getLoginedName() {
		return logined_manager.getName();
	}
	private boolean is_managerUsrnameDuplicate(String usr_name) {
		boolean res=false;
		for(int i=0;i<managers.size();i++) {
			if(managers.get(i).checkUserName(usr_name)) {
				res=true;
				break;
			}
		}
		return res;
	}
	public boolean addNewManager(String name,String usr_name,String password) {
		if(!is_managerUsrnameDuplicate(usr_name)) {
			managers.add(new Manager(name, usr_name, password));
			return true;
		}
		else {
			return false;
		}
	}
	public String getManagerNames() {
		String out=new String();
		for(int i=0;i<managers.size();i++,out+="\n") {
			out+=managers.get(i).getName();
		}
		return out;
	}
	public String getLoginedManagerDorm() {
		return logined_manager.getName();
	}
	public boolean setDorm(int index,String key) {
		return logined_manager.setDorm(key,dorms.get(index));
	}
	public boolean logIn(String usr_name,String password) {
		boolean res=false;
		for(int i=0;i<managers.size();i++) {
			if(managers.get(i).checkUserName(usr_name)) {
				if(managers.get(i).checkPass(password)) {
					logined_manager=managers.get(i);
					res=true;
					break;
				}
			}
		}
		return res;
	}
	public void logOut() {
		logined_manager=null;
	}
	////////////////////////////////////////
	//////////////////////////////Dorm
	public boolean is_dormLocked(int index) {
		return dorms.get(index).is_locked();
	}
	public int getDormsSize() {
		return dorms.size();
	}
	public String getDormNames() {
		String out=new String();
		for(int i=0;i<dorms.size();i++,out+="\n") {
			out+=(i+1)+"."+dorms.get(i).getName();
		}
		return out;
	}
	private boolean is_DormNameDuplicate(String usr_name) {
		boolean res=false;
		for(int i=0;i<managers.size();i++) {
			if(managers.get(i).checkUserName(usr_name)) {
				res=true;
				break;
			}
		}
		return res;
	}
	public boolean addDorm(String name,Dorm.types type) {
		if(!is_DormNameDuplicate(name)) {
			dorms.add(new Dorm(name, type));
			return true;
		}
		else return false;
	}
}
