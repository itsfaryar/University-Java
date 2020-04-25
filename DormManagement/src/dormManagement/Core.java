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
	private boolean is_manager_usrname_duplicate(String usr_name) {
		boolean res=false;
		for(int i=0;i<managers.size();i++) {
			if(managers.get(i).equals(usr_name)) {
				res=true;
				break;
			}
		}
		return res;
	}
	public boolean addNewManager(String name,String usr_name,String password) {
		if(!is_manager_usrname_duplicate(usr_name)) {
			managers.add(new Manager(name, usr_name, password));
			return true;
		}
		else {
			return false;
		}
	}
	public String getManagersName() {
		String out=new String();
		for(int i=0;i<managers.size();i++,out+="\n") {
			out+=managers.get(i).getName();
		}
		return out;
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
}
