package dormManagement;

public class Student {
	private String name;
	private String student_number;
	private String studing_subject;
	private String year_of_entrance;
	private int debt;
	private Room room=null;
	
	public Student(String name,String student_number,String studing_subject,String year_of_entrance,int debt) {
		this.name=name;
		this.student_number=student_number;
		this.studing_subject=studing_subject;
		this.year_of_entrance=year_of_entrance;
		this.debt=debt;
		
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
	public void editInfo(String new_name,String new_student_number,String new_studing_subject,String new_year_of_entrance) {
		if(new_name!=null)this.name=new_name;
		if(new_student_number!=null)this.student_number=new_student_number;
		if(new_studing_subject!=null)this.studing_subject=new_studing_subject;
		if(new_year_of_entrance!=null)this.year_of_entrance=new_year_of_entrance;
	}
}
