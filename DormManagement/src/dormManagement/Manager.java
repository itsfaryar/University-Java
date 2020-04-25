package dormManagement;

public class Manager {
	private String name;
	private String usr_name;
	private String password;
	private Dorm dorm=null;
	public Manager(String name,String usr_name,String password) {
		this.name=name;
		this.usr_name=usr_name;
		this.password=password;
	}
	public String getName() {
		return name;
	}
	public boolean checkUserName(String usr) {
		if(usr.equals(usr_name)) {
			return true;
		}
		else return false;
	}
	public boolean checkPass(String pass) {
		if(password.equals(pass)) {
			return true;
		}
		else return false;
	}
	
}
