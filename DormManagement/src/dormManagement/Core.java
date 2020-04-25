package dormManagement;

import java.util.ArrayList;

public class Core {
	private ArrayList<Student>students;
	private ArrayList<Manager>managers;
	private ArrayList<Dorm>dorms;
	public Core() {
		students=new ArrayList<Student>();
		managers=new ArrayList<Manager>();
		dorms=new ArrayList<Dorm>();
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
}
