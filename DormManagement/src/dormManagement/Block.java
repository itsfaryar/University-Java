package dormManagement;

import java.util.ArrayList;

public class Block {
	private String number;
	private int count_of_floors;
	private ArrayList<Room> rooms;
	private Dorm dorm=null;
	public Block(Dorm dorm,String number,int count_of_floors) {
		this.dorm=dorm;
		this.number=number;
		this.setCount_of_floors(count_of_floors);
		this.rooms=new ArrayList<Room>();
	}
	public Dorm getDorm() {
		return dorm;
	}
	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}
	public String getNumber() {
		return number;
	}
	public void increaseFloor() {
		setCount_of_floors(getCount_of_floors() + 1);
	}
	public int getCount_of_floors() {
		return count_of_floors;
	}
	public void setCount_of_floors(int count_of_floors) {
		this.count_of_floors = count_of_floors;
	}
	private boolean is_Duplicate(String inp) {
		boolean res=false;
		for(int i=0;i<rooms.size();i++) {
			if(rooms.get(i).getNumber().equals(inp)) {
				res=true;
			}
		}
		return res;
	}
	public boolean addRoom(String number,int floor_number,int capacity,int rent_price) {
		if(!is_Duplicate(number) && capacity>0 && floor_number<=count_of_floors) {
			rooms.add(new Room(this,number,floor_number,capacity,rent_price));
			return true;
		}
		else {
			return false;
		}
	}
	
}
