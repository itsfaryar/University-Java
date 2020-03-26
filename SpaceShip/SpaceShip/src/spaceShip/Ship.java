package spaceShip;

public class Ship {
	enum ship_status{SAFE,CRASHED}
	public Position pos;
	public Position virtual_pos;
	public ship_status state;

	private String speed_parameters;
	public Ship(String speed_parameters) {
		this.speed_parameters='_'+speed_parameters+'_';
		this.pos=new Position();
		this.virtual_pos=new Position();
	}
	@SuppressWarnings("unused")
	public double getSpeed() {
		int x_pos=-1;
		double speed=-1;
		double pre_speed=-1;
		int pre_x=-1;
		String raw_num=new String();
		for(int k=0;k<speed_parameters.length();k++) {
			if(speed_parameters.charAt(k)=='_' || speed_parameters.charAt(k)=='-') {
				
				if(raw_num.length()>0) {
					if(x_pos==-1) {
						x_pos=Integer.valueOf(raw_num);
					
					}
					else if(speed==-1) {
						speed=Double.valueOf(raw_num);
					}
				}
				raw_num=new String();
			}
			else {
				
					raw_num+=speed_parameters.charAt(k);
					
				
			}
			if(x_pos!=-1 && speed!=-1) {
				if(x_pos==pos.x) {
					pre_x=x_pos;
					pre_speed=speed;
					break;
				}
				else if(x_pos>pos.x&&pre_speed!=-1) {
					
						speed=pre_speed;
						break;
					}
				else {
					pre_x=x_pos;
					pre_speed=speed;
					x_pos=-1;
					speed=-1;
				}
				
			}
		}
		if(speed==-1) {
			speed=pre_speed;
		}
		return speed;
	}
}
