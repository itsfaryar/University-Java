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
		out+="Count of Students: "+dorm.getStudents().size()+"\n";
		for(int i=0;i<dorm.getStudents().size();i++,out+='\n') {
			out+=dorm.getStudents().get(i).toString();
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
				if(std.getRoom()!=null)std.getRoom().removeStudent(std);
				room.addStudent(std);
				res=true;
			}
		}
		return res;
	}
	public String roomMates_toString(Student std) {
		String out=new String("--");
		if(std!=null) {
			if(std.getRoom()!=null) {
				Room room=std.getRoom();
				out=new String();
				int index=room.getStudents().indexOf(std);
				for(int i=0;i<room.getStudents().size();i++) {
					if(i!=index) {
						out+=room.getStudents().get(i).getName()+"("+room.getStudents().get(i).getStudenNumber()+")";
						if((i>1)&&((i%4)==0))out+="\n";
						else out+="\t";
					}
				}
			}
		}
		return out;
	}
	public String roomMembers_toString(String block_num,String room_num) {
		String out=new String("--");
		Block block=dorm.getBlockAtNumber(block_num);
		if(block!=null) {
			Room room=block.getRoomAtNumber(room_num);
			if(room!=null) {
				if(room.getStudents()!=null) {
					out=new String();
					for(int i=0;i<room.getStudents().size();i++) {
						out+=(i+1)+"."+room.getStudents().get(i).getName()+"("+room.getStudents().get(i).getStudenNumber()+")";
						if((i>1)&&((i%4)==0))out+="\n";
						else out+="\t";
					}
				}
			}
		}
		return out;
	}
	public boolean removeAllMembersFromRoom(String block_num,String room_num) {
		boolean res=false;
		Block block=dorm.getBlockAtNumber(block_num);
		if(block!=null) {
			Room room=block.getRoomAtNumber(room_num);
			if(room!=null) {
				room.removeAllStudent();
				res=true;
			}
		}
		return res;
		
	}
	public void removeAllStudentsFromDorm() {
		for(int i=0;i<dorm.getBlocks().size();i++) {
			Block block=dorm.getBlocks().get(i);
			for(int j=0;j<block.getRooms().size();j++) {
				Room room=block.getRooms().get(j);
				room.removeAllStudent();
			}
		}
	}
	public String getSupervisorForRoom(String block_num,String room_num) {
		String out=new String("--");
		Block block=dorm.getBlockAtNumber(block_num);
		if(block!=null) {
			Room room=block.getRoomAtNumber(room_num);
			if(room!=null) {
				if(room.getRoom_supervisor()!=null) {
					out=room.getRoom_supervisor().toString();
			
				}
			}
		}
		return out;
	}
	public boolean setSupervisorForRoom(String block_num,String room_num,int index) {
		boolean res=false;
		Block block=dorm.getBlockAtNumber(block_num);
		if(block!=null) {
			Room room=block.getRoomAtNumber(room_num);
			if(room!=null && index>=0 && index<room.getStudents().size()) {
				room.setRoom_supervisor(index);
				res=true;
			}
		}
		return res;
	}
	public boolean getRents(boolean rent_for_all,String block_num,String room_num) {
		boolean res=false;
		if(rent_for_all) {
			for(int i=0;i<dorm.getBlocks().size();i++) {
				Block block=dorm.getBlocks().get(i);
				for(int j=0;j<block.getRooms().size();j++) {
					Room room=block.getRooms().get(j);
					room.takeRents();
				}
			}
			res=true;
		}
		else {
			
			Block block=dorm.getBlockAtNumber(block_num);
			if(block!=null) {
				Room room=block.getRoomAtNumber(room_num);
				if(room!=null) {
					room.takeRents();
					res=true;
				}
			}
			
		}
		return res;
	}
}
