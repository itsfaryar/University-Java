package dormManagement;

import java.io.Serializable;

public class Manager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 917969565123275841L;
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
	public boolean setDorm(String key, Dorm inp) {
		boolean res=inp.setMngr(this, key);
		if(res)dorm=inp;
		return res;
	}
	public String unsetDorm(boolean lock) {
		String key=dorm.unsetMngr(lock);
		dorm=null;
		return key;
	}
	public Dorm getDorm() {
		return dorm;
	}
	///////////////////
	private boolean is_studentNumberDuplicate(String student_number) {
		boolean done=false;
		for(int i=0;i<dorm.getStudents().size();i++) {
			if(dorm.getStudents().get(i).getStudenNumber().equals(student_number)) {
				done=true;
				break;
			}
		}
		return done;
	}
	public boolean addNewStudent(String name,String student_number,String studing_subject,String year_of_entrance,int debt) {
		boolean done=false;
		if(!is_studentNumberDuplicate(student_number)) {
			dorm.getStudents().add(new Student(name, student_number, studing_subject, year_of_entrance, debt));
			done=true;
		}
		return done;
	}
	public String Students_toString() {
		String out=new String();
		for(int i=0;i<dorm.getStudents().size();i++,out+='\n') {
			out+=dorm.getStudents().toString();
		}
		return out;
	}
	public Student getStudent(int index) {
		return dorm.getStudents().get(index);
	}
	public int searchStudentByStdNumber(String stdnumber) {
		int index=-1;
		for(int i=0;i<dorm.getStudents().size();i++) {
			if(dorm.getStudents().get(i).getStudenNumber().equals(stdnumber)) {
				index=i;
				break;
			}
		}
		return index;
	}
	public boolean eraseStudent(Student std) {
		return dorm.getStudents().remove(std);
	}
	public boolean eraseStudent(String stdnumber) {
		int index=searchStudentByStdNumber(stdnumber);
		if(index==-1) {
			return false;
		}
		else{
			dorm.getStudents().remove(index);
			return true;
		}
	}
	public boolean editStudent(Student std,String new_name,String new_studing_subject,String new_year_of_entrance,int new_debt) {
		int index=dorm.getStudents().indexOf(std);
		if(index==-1) return false;
		else {
			dorm.getStudents().get(index).editInfo(new_name,new_studing_subject, new_year_of_entrance, new_debt);
			return true;
		}
	}
	public boolean editStudent(String stdnumber,String new_name,String new_studing_subject,String new_year_of_entrance,int new_debt) {
		int index=searchStudentByStdNumber(stdnumber);
		if(index==-1) return false;
		else {
			dorm.getStudents().get(index).editInfo(new_name, new_studing_subject, new_year_of_entrance, new_debt);
			return true;
		}
	}
	public String freeRooms_Tostring() {
		String out=new String();
		
		for(int i=0;i<dorm.getBlocks().size();i++) {
			Block block=dorm.getBlocks().get(i);
			for(int j=0;j<block.getRooms().size();j++) {
				Room room=block.getRooms().get(j);
				if(room.getCapacity()>=1) {
					out+=block.getNumber()+":"+room.getNumber()+"("+room.getCapacity()+")\n";
				}
			}
		}
		return out;
	}
	
	public boolean chooseRoom(String block_num,String room_num,Student std) {
		boolean res=false;
		Block block=dorm.getBlockAtNumber(block_num);
		if(block!=null) {
			Room room=block.getRoomAtNumber(room_num);
			if(room!=null) {
				std.getRoom().removeStudent(std);
				room.addStudent(std);
				res=true;
			}
		}
		return res;
	}
}
