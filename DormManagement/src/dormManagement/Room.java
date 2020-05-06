package dormManagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1083905778735998356L;
	private String number;
	private int capacity;
	private int floor_number;
	private int rent_price;
	private Student std;
	private ArrayList<Student>students;
	private Student room_supervisor;
	private Block block; 
	public Room(Block block,String number,int floor_number,int capacity,int rent_price) {
		this.block=block;
		this.number=number;
		this.floor_number=floor_number;
		this.capacity=capacity;
		this.rent_price=rent_price;
		students=new ArrayList<Student>();
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getCapacity() {
		return capacity;
	}
	public void increaseCapacity() {
		this.capacity += 1;
	}
	public void decreaseCapacity() {
		if(this.capacity>1)this.capacity -= 1;
	}
	public int getFloor_number() {
		return floor_number;
	}
	public int getRent_price() {
		return rent_price;
	}
	public void setRent_price(int rent_price) {
		this.rent_price = rent_price;
	}
	public Block getBlock() {
		return block;
	}
	public Student getRoom_supervisor() {
		return room_supervisor;
	}
	public void setRoom_supervisor(Student room_supervisor) {
		this.room_supervisor = room_supervisor;
	}
	public void getRents() {
		for(int i=0;i<students.size();i++) {
			students.get(i).increaseDebt(rent_price);
		}
	}
	public boolean addStudent(Student student) {
		if(students==null)students=new ArrayList<Student>();
		if(capacity>0 && student!=null) {
			students.add(student);
			student.setRoom(this);
			capacity--;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean removeStudent(Student student) {
		int index=students.indexOf(student);
		if(index!=-1) {
			student.setRoom(null);
			students.remove(index);
			capacity++;
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
}
