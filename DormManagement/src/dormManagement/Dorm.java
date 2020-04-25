package dormManagement;

import java.io.Serializable;
import java.util.ArrayList;
public class Dorm implements Serializable{
	public enum types{FOR_BOYS,FOR_GIRLS}
	private String name;
	private types type;
	private int count_of_blocks=-1;
	private Manager mngr;
	private ArrayList<Block> blocks;
	public Dorm(String name,types type,Manager mngr) {
		this.name=name;
		this.type=type;
		this.setMngr(mngr);
		this.blocks=new ArrayList<Block>();
		count_of_blocks=blocks.size();
	}
	public String getName() {
		return name;
	}
	public Manager getMngr() {
		return mngr;
	}
	public void setMngr(Manager mngr) {
		this.mngr = mngr;
	}
	public int getCount_of_blocks() {
		return count_of_blocks;
	}
	public types getType() {
		return type;
	}
	public Block getLastBlock() {
        return blocks != null && !blocks.isEmpty() ? blocks.get(blocks.size() - 1) : null;
    }
	private boolean is_Duplicate(String inp) {
		boolean res=false;
		for(int i=0;i<blocks.size();i++) {
			if(blocks.get(i).getNumber().equals(inp)) {
				res=true;
			}
		}
		return res;
	}
	public boolean addNewBlock(String number,int count_of_floors) {
		if(!is_Duplicate(number)) {
			blocks.add(new Block(this,number, count_of_floors));
			count_of_blocks=blocks.size();
			return true;
		}
		else {
			return false;
		}
	}
	
}
