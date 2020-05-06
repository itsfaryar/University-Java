package dormManagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Block implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9132692150185654560L;
	private String number;
	private int count_of_floors;
	private ArrayList<Room> rooms=new ArrayList<Room>();
	private Dorm dorm=null;
	public Block(Dorm dorm,String number,int count_of_floors) {
		this.dorm=dorm;
		this.number=number;
		this.setCount_of_floors(count_of_floors);
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
	public String rooms_toString() {
		String out=new String();
		for(int i=0;i<rooms.size();i++) {
			out+=rooms.get(i).getNumber();
			if((i>1)&&((i%3)==0))out+="\n";
			else out+="\t";
		}
		return out;
	}
	public Room getLastroom() {
        return rooms != null && !rooms.isEmpty() ? rooms.get(rooms.size() - 1) : null;
    }
	public int checkAddedRoomFloor() {
		if(getLastroom()==null)return 1;
		else{
			if(count_of_floors>=getLastroom().getFloor_number()) {
				return count_of_floors;
			}
			else {
				return getLastroom().getFloor_number();
			}
		}
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
		if(!is_Duplicate(number) && capacity>0) {
			rooms.add(new Room(this,number,floor_number,capacity,rent_price));
			count_of_floors=checkAddedRoomFloor();
			return true;
		}
		else {
			return false;
		}
	}
	public ArrayList<Room> getRooms(){
		return rooms;
	}
	public Room getRoomAtNumber( String room_num) {
		Room out =null;
		for(int i=0;i<rooms.size();i++) {
			if(rooms.get(i).getNumber().equals(room_num)) {
				out=rooms.get(i);
				break;
			}
		}
		return out;	
	}
	public boolean removeRoom(String number) {
		Room room=getRoomAtNumber(number);
		boolean res=false;
		if(room!=null){
			res=rooms.remove(room);
			count_of_floors=checkAddedRoomFloor();
		}
		return res;
	}
	
}
