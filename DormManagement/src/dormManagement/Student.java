package dormManagement;

import java.io.Serializable;

public class Student implements Serializable{
	private String name;
	private String student_number;
	private String studing_subject;
	private String year_of_entrance;
	private int debt=0;
	private Room room=null;
	
	public Student(String name,String student_number,String studing_subject,String year_of_entrance,int debt) {
		this.name=name;
		this.student_number=student_number;
		this.studing_subject=studing_subject;
		this.year_of_entrance=year_of_entrance;
		this.debt=debt;
		
	}
	public String getName() {
		return name;
	}
	public String getStudenNumber() {
		return student_number;
	}
	public String getStuding_subject() {
		return studing_subject;
	}
	public String getYear_of_entrance() {
		return year_of_entrance;
	}
	public int getDebt() {
		return debt;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void increaseDebt(int inp) {
		this.debt+=inp;
	}
	public void decreaseDebt(int inp) {
		this.debt-=inp;
	}
	public void editInfo(String new_name,String new_studing_subject,String new_year_of_entrance,int new_debt) {
		if(new_name!=null)this.name=new_name;
		if(new_studing_subject!=null)this.studing_subject=new_studing_subject;
		if(new_year_of_entrance!=null)this.year_of_entrance=new_year_of_entrance;
		if(new_debt>=0)this.debt=new_debt;
	}
	public String toString() {
		String out=new String();
		out+=name+" | ";
		out+=student_number+" | ";
		out+=studing_subject+" | ";
		out+=year_of_entrance+" | ";
		if(room==null) {
			out+="RoomNumber:--"+"     ";
		}
		else out+="RoomNumber:"+room.getNumber()+"     ";
		out+="debt:"+debt+"     ";
		return out;
	}
}
