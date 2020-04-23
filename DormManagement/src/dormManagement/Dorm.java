package dormManagement;

public class Dorm {
	public enum types{FOR_BOYS,FOR_GIRLS}
	private String name;
	private types type;
	private int count_of_blocks=-1;
	private Manager mngr;
	private int count_of_people=-1;
	private Block[] blocks;
	public Dorm(String name,types type,int count_of_blocks,Manager mngr,int count_of_people,Block[]blocks) {
		this.name=name;
		this.type=type;
		this.count_of_blocks=count_of_blocks;
		this.mngr=mngr;
		this.count_of_people=count_of_people;
		this.blocks=blocks;
		initBlocks();
		
	}
	private void initBlocks() {
		if(blocks!=null) {
			for(int i=0;i<blocks.length;i++) {
				blocks[i].setDorm(this);
			}
		}
	}
}
