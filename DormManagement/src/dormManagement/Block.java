package dormManagement;

public class Block {
	private int number;
	private int number_of_floors;
	private Room[] rooms;
	private Dorm dorm;
	public Block(int number,int number_of_floors,Room[]rooms) {
		this.number=number;
		this.number_of_floors=number_of_floors;
		this.rooms=rooms;
	}
	public Dorm getDorm() {
		return dorm;
	}
	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}
	private void initrooms() {
		if(rooms!=null) {
			for(int i=0;i<rooms.length;i++) {
				rooms[i].se(this);
			}
		}
	}
}
