package digOut;

import java.util.Random;

import digOut.Player.Status;

import java.lang.Math; 

public class GameBoard {
	
	public enum Directions {RIGHT,LEFT,UP,DOWN,NONE};
	public enum Display_Mode{ANSI_COLORFULL,ASCII}
	private Display_Mode display;
	private static final char ROCK='@';
	private static final char GROUND='#';
	private static final char SPACE=' ';
	private static final char COIN='$';	
	private static final char PLAYER='!';
	private static final char MONSTER='M';
	private static final char BOMB='B';
	private static final String RST           ="\u001b[0m";
	private static final String COLORED_ROCK  ="\u001b[48;5;8m"  +" "+RST;
	private static final String COLORED_GROUND="\u001b[48;5;94m" +" "+RST;
	private static final String COLORED_SPACE ="\u001b[48;5;232m"+" "+RST;
	private static final String COLORED_COIN  ="\u001b[38;5;3m"  +"$"+RST;
	private static final String COLORED_PLAYER="\u001b[48;5;232m"+"\u001b[38;5;33m" +"!"+RST;
	private static final String COLORED_BOMB="\u001b[48;5;196m" +" "+RST;
	private boolean bomb;
	private boolean first_peek=true;
	private boolean bomb_visibility=false;
	private int W;
	private int H;
	private int coins_n=0;
	private int space_n=0;
	private int bomb_n=0;
	private int monster_n=0;
	private char[]board;
	char[] board_tmp;
	private Player pl;
	private Monster[]monsters; 
	public GameBoard(Player pl,int width,int height,int coins,boolean bomb,Display_Mode dis_mode){
		if(pl==null) {
			
		}
		else {
			this.W=width;
			this.H=height;
			this.pl=pl;
			this.display=dis_mode;
			this.bomb=bomb;
			coins_n=coins;
			board=new char[W*H];
			pl.setBoard_size(W*H);
			pl.setIs_bomb_mode(bomb);
			initBoard();
			
		}
	}
	public int getbomb_n() {
		return bomb_n;
	}
	public boolean is_bomb() {
		return bomb;
	}
	public boolean isFirst_peek() {
		return first_peek;
	}
	public void setFirst_peek(boolean first_peek) {
		this.first_peek = first_peek;
	}
	public boolean isBombVisibility() {
		return bomb_visibility;
	}
	public void setBombVisibility(boolean bomb_visibility) {
		this.bomb_visibility = bomb_visibility;
	}
	public int getcoins_n() {
		return coins_n;
	}
	public int getWidth() {
		return W;
	}
	public int getHeight() {
		return H;
	}
	public Player getPlayer() {
		return pl;
	}
	public char[] getBoard(){
		return board;
	}
	public char getBoardAt(Position p) {
		return board[(p.i*W)+p.j];
	}
	public char getBoardTmpAt(Position p) {
		return board_tmp[(p.i*W)+p.j];
	}
	public void setBoardAt(char input,Position p) {
		board[(p.i*W)+p.j]=input;
	}
	public char getProcessedBoardAt(Position p) {
		char out=getBoardAt(p);
		if(p.equals(pl.getPlayer_pos())) {out=PLAYER;}
		else {
			if(monsters!=null) {
				for(int i=0;i<monster_n;i++) {
					if(p.equals(monsters[i].getMonster_pos())) {
						out=MONSTER;
						break;
					}
				}
			}
			
		}
		return out;
	}
	public String getPrintableBoard(){
		String out=new String();
		String tmp=new String(" ");
		char proccesed_board_at;
		for(int i=0;i<W;i++) {
			tmp+='=';
		}
		out+=tmp+"\n";
		if(display==Display_Mode.ASCII) {
			for(int i=0;i<W*H;i++) {
				proccesed_board_at=getProcessedBoardAt(convertTo2DPos(i));
				if(i==0) out+='|';
				else if(i%(W)==0 && i!=0)out+="|\n|";
				if(!bomb_visibility&&proccesed_board_at==BOMB) {
					out+=GROUND;
				}
				else {
					out+=proccesed_board_at;
				}
			}
		}
		else {
			for(int i=0;i<W*H;i++) {
				proccesed_board_at=getProcessedBoardAt(convertTo2DPos(i));
				if(i==0) out+='|';
				else if(i%(W)==0 && i!=0)out+="|\n|";
				
				switch (proccesed_board_at) {
				case ROCK:
					out+=COLORED_ROCK;
					break;
				case GROUND:
					out+=COLORED_GROUND;
					break;
				case SPACE:
					out+=COLORED_SPACE;
					break;
				case COIN:
					out+=COLORED_COIN;
					break;
				case PLAYER:
					out+=COLORED_PLAYER;
					break;
				case BOMB:
					if(bomb_visibility)out+=COLORED_BOMB;
					else out+=COLORED_GROUND;
					break;
				default:
					out+=proccesed_board_at;
					break;
				}
			}
		}
		out+="|\n"+tmp;
		return out;
	}
	//Public Functions
	///////////////////////////////
	public void checkForWinner() {
		if(coins_n==pl.getCoins()) {
			pl.setStatus(Status.WINNER);
		}
	}
	public void changeDisplayMode() {
		if(display==Display_Mode.ASCII)display=Display_Mode.ANSI_COLORFULL;
		else display=Display_Mode.ASCII;
	}
	public void moveMonster() {
		board_tmp=board.clone();
		for(int i=0;i<monster_n;i++) {
			boolean[]permission=new boolean[5];//1forRight/2Forleft/3ForUp/4ForDown
			Position p=monsters[i].getMonster_pos();
			if(p.equals(pl.getPlayer_pos())) {
				pl.setStatus(Player.Status.DEAD);
				pl.setDeath_reason("Killed by a monster");
				pl.setPlayer_pos(new Position(-1,-1));
			}
			else {
				p.j++;
				permission[1]=checkPosToGo(p,MONSTER);
				p.j--;
				p.j--;
				permission[2]=checkPosToGo(p,MONSTER);
				p.j++;
				p.i--;
				permission[3]=checkPosToGo(p,MONSTER);
				p.i++;
				p.i++;
				permission[4]=checkPosToGo(p,MONSTER);
				p.i--;
				monsters[i].setNextRndPos(permission);
				board_tmp[convertTo1DPos(monsters[i].getMonster_pos())]=MONSTER;
				if(monsters[i].getMonster_pos().equals(pl.getPlayer_pos())) {
					pl.setStatus(Player.Status.DEAD);
					pl.setDeath_reason("Killed by a monster");
					pl.setPlayer_pos(new Position(-1,-1));
				}
			}
		}
	}
	public boolean movePlayer(Directions dir) {
		boolean res=false;
		Position tmp_p=new Position(pl.getPlayer_pos().i,pl.getPlayer_pos().j);	
		switch (dir) {
		case UP:
			tmp_p.i--;
			break;
		case DOWN:
			tmp_p.i++;
			break;
		case RIGHT:
			tmp_p.j++;
			break;
		case LEFT:
			tmp_p.j--;
			break;
		default:
			break;
		}
		res=checkPosToGo(tmp_p,PLAYER);
		if(res)pl.setPlayer_pos(tmp_p);
		return res;
	}
	public boolean dig(Directions dir) {
		boolean res=false;
		Position tmp_p=new Position(pl.getPlayer_pos().i,pl.getPlayer_pos().j);
		switch (dir) {
		case UP:
			tmp_p.i--;
			break;
		case DOWN:
			tmp_p.i++;
			break;
		case RIGHT:
			tmp_p.j++;
			break;
		case LEFT:
			tmp_p.j--;
			break;
		default:
			break;
		}
		res=checkPosToDig(tmp_p);
		if(res) {
			setBoardAt(SPACE,tmp_p);
			dropFallingRocks(tmp_p);
		}
		return res;
	}
	public Position convertTo2DPos(int inp) {
		return new Position((int)(inp/W),inp%W);
	}
	public int convertTo1DPos(Position p) {
		return (p.i*W)+p.j;
	}
	//Private Functions
	////////////////////////////////////
	private boolean checkPosToGo(Position p,char role) {
		boolean res=false;
		if(role==PLAYER) {
			if((p.i>=0) && (p.i<H) && (p.j>=0) && (p.j<W)) {
				char tmp=getBoardAt(p);
				if(tmp=='$') {
					pl.setCoins(pl.getCoins()+1);	
					setBoardAt(SPACE,p);
					res=true;
				}
				else if(tmp==' ') {
					res=true;
				}
			}
		}
		else if(role==MONSTER) {
			if((p.i>=0) && (p.i<H) && (p.j>=0) && (p.j<W)) {
				char tmp=board_tmp[convertTo1DPos(p)];
				if(tmp==' ') {
					res=true;
				}
			}
		}
		return res;
	}
	private boolean checkPosToDig(Position p) {
		boolean res=false;
	
		if((p.i>=0) && (p.i<H) && (p.j>=0) && (p.j<W)) {
			char tmp=getBoardAt(p);
			if(tmp==GROUND) {
				res=true;
			}
			if(tmp==BOMB) {
				bomb_visibility=true;
				pl.setStatus(Player.Status.DEAD);
				pl.setDeath_reason("Killed by a bomb");
			}
		}
		return res;
	}
	private void dropFallingRocks(Position p) {
		boolean done=true;
		p.i--;
		if((p.i>=0) && (p.i<H) && (p.j>=0) && (p.j<H)) {
			char tmp=getBoardAt(p);
			if(tmp==ROCK) {
				setBoardAt(SPACE,p);
				done=false;
			}
		}
		while(!done) {
			p.i++;
			if((p.i>=0) && (p.i<H) && (p.j>=0) && (p.j<H)) {
				char tmp=getBoardAt(p);
				if(tmp==SPACE || tmp==COIN) {
					setBoardAt(SPACE,p);
				}
				else {
					p.i--;
					setBoardAt(ROCK,p);
					done=true;
				}
				if(p.equals(pl.getPlayer_pos())) {
					pl.setStatus(Status.DEAD);
					pl.setDeath_reason("Killed by a rock");
					pl.setPlayer_pos(new Position(-1,-1));
				}
			}
			else {
				p.i--;
				setBoardAt(ROCK,p);
				done=true;
			}
		}
		
	}
	private static int getRandomNumberInts(int min, int max){
		int out;
		Random random = new Random();
		out=random.ints(min,(max+1)).findFirst().getAsInt();
		return out;
	}
	private void initBoard() {
		generateGround(800,200);
		generateCoins();
		generateRocks();
		generatePlayer();
		generateMonsters();
		if(bomb)generateBombs();
	}
	private void FillBoardWith(char ch) {
		for(int i=0;i<W*H;i++)board[i]=ch;
	}
	private void placeEmptyArea(Position p) {
		boolean done=false;
		Position p_tmp=new Position(p.i, p.j);
		setBoardAt(SPACE,p);
		while(!done) {
			int dir=getRandomNumberInts(1,4);
			done=true;
			if(dir==1 && p.j<W-1) {//Right Direction
				p.j++;
			}
			else if(dir==2 && p.j>0) {//Left Direction
				p.j--;
			}
			else if(dir==3 && p.i>0) {//Up Direction
				p.i--;
			}
			else if(dir==4 && p.i<H-1) {//Down Direction
				p.i++;
			}
			else {
				done=false;
			}
			if(done==true && getBoardAt(p)==SPACE) {
				p.i=p_tmp.i;
				p.j=p_tmp.j;
				done=false;
			}
		}
		setBoardAt(SPACE,p);
	}
	private Position checkPosAround(int i,int j) {
		int c=0;
		Position next_empty_pos=new Position(-1,-1);
		if((j+1)<W-1) {//Right Direction
			if(getBoardAt(new Position(i, j+1))==SPACE) {
				c++;
				next_empty_pos.i=i;
				next_empty_pos.j=j+1;
			}
		}
		if((j-1)>0) {//Left Direction
			if(getBoardAt(new Position(i, j-1))==SPACE) {
				c++;
				next_empty_pos.i=i;
				next_empty_pos.j=j-1;
			}
		}
		if((i-1)>0) {//Up Direction
			if(getBoardAt(new Position(i-1, j))==SPACE) {
				c++;
				next_empty_pos.i=i-1;
				next_empty_pos.j=j;
			}
		}
		if((i+1)<H-1) {//Down Direction
			if(getBoardAt(new Position(i+1, j))==SPACE) {
				c++;
				next_empty_pos.i=i+1;
				next_empty_pos.j=j;
			}
		}
		if(c!=1) {next_empty_pos.i=-1;next_empty_pos.j=-1;}
		return next_empty_pos;
	}
	private boolean checkPermisionToPlaceMonster(char[]board_tmp,Position p) {
		boolean out=true;
		if(board_tmp[convertTo1DPos(p)]=='N') {
			out=false;
		}
		if((p.j+1)<W-1 && out==true) {//Right Direction
			p.j++;
			if(board_tmp[convertTo1DPos(p)]=='N') {
				out=false;
			}
			p.j--;
		}
		if((p.j-1)>0 && out==true) {//Left Direction
			p.j--;
			if(board_tmp[convertTo1DPos(p)]=='N') {
				out=false;
			}
			p.j++;
		}
		if((p.i-1)>0 && out==true) {//Up Direction
			p.i--;
			if(board_tmp[convertTo1DPos(p)]=='N') {
				out=false;
			}
			p.i++;
		}
		if((p.i+1)<H-1 && out==true) {//Down Direction
			p.i++;
			if(board_tmp[convertTo1DPos(p)]=='N') {
				out=false;
			}
			p.i--;
		}
		return out;
	}
	private char[]markUnavailablePlaces(char []board_tmp,Position p){
			p.i--;
			if((p.i>=0)) {
				board_tmp[convertTo1DPos(p)]='N';
			}
			p.i++;
			p.i++;
			if(p.i<H) {
				board_tmp[convertTo1DPos(p)]='N';
			}
			p.i--;
			p.j--;
			if(p.j>=0) {
				board_tmp[convertTo1DPos(p)]='N';
			}
			p.j++;
			if(p.j<W) {
				board_tmp[convertTo1DPos(p)]='N';
			}
			p.j--;
			return board_tmp;
			
	}
	private void generateMonsters() {
		board_tmp=board.clone();
		board_tmp[convertTo1DPos(pl.getPlayer_pos())]='N';
		Position []monsters_pos=new Position[256];
		Position surveyor=new Position(0,0);
		for(surveyor.i=0;surveyor.i<H;surveyor.i++) {
			for(surveyor.j=0;surveyor.j<W;surveyor.j++) {
				if(board_tmp[convertTo1DPos(surveyor)]==SPACE && checkPermisionToPlaceMonster(board_tmp,surveyor)) {
					Position next_empty_pos=checkPosAround(surveyor.i, surveyor.j);
					if(next_empty_pos.i!=-1) {
						if(checkPermisionToPlaceMonster(board_tmp,next_empty_pos)) {
							markUnavailablePlaces(board_tmp, next_empty_pos);
							monsters_pos[monster_n]=new Position(surveyor.i,surveyor.j);
							monster_n++;
						}
					}
				}
			}
		}
		monsters=new Monster[monster_n];
		for(int i=0;i<monster_n;i++) {
			monsters[i]=new Monster();
			monsters[i].setMonster_pos(monsters_pos[i]);
		}
		monsters_pos=null;
	}
	private void generateGround(int ground_contingency,int space_contingency) {
		FillBoardWith('#');
		int sum=ground_contingency+space_contingency;
		
		int tmp=0;
		Position p=new Position(0,0);
		for(p.i=0;p.i<H;p.i++) {
			for(p.j=0;p.j<W;p.j++) {
				tmp=space_contingency;
				//tmp=(int)(Math.pow((double)(space_n*80/(W*H)),1.3)*3);
				//tmp=space_contingency-(int)(Math.pow(space_n,1.7)*1000/(Math.pow(Math.sqrt( W*H),2.6)));
				tmp=space_contingency-(int)(Math.pow(space_n,1.3)*1600/(Math.pow(Math.sqrt( W*H),2.6)));
				if(tmp<=0)tmp=1;
				int rnd=getRandomNumberInts(0,sum);
				if(getBoardAt(p)!=SPACE) {
					if(rnd<tmp) {
						placeEmptyArea(p);
						space_n++;
						
						
					}
				}
			}
		}
	}
	private void generateCoins() {
		int c=0;
		while(c<coins_n) {
			int rnd=getRandomNumberInts(0,(W*H)-1);
			if(board[rnd]==GROUND) {
				board[rnd]=COIN;
				c++;
			}
		}
	}
	private void generateRocks() {
		int c=0;
		int rocks_n= (int)((Math.sqrt(W*H)*0.60)+0.5);
		rocks_n=getRandomNumberInts(rocks_n-1,rocks_n+1);
		while(c<rocks_n) {
			int rnd=getRandomNumberInts(0,(W*H)-1);
			Position p=convertTo2DPos(rnd);
			if(p.i<H-1)p.i++;
			
			if(board[rnd]==GROUND && getBoardAt(p)==GROUND) {
				
				board[rnd]=ROCK;
				c++;
			}
		}
	}
	private void generateBombs() {
		int c=0;
		bomb_n= (int)((Math.sqrt(W*H)/2.6)+0.5);
		//bomb_n=getRandomNumberInts(bomb_n-1,bomb_n+1);
		while(c<bomb_n) {
			int rnd=getRandomNumberInts(0,(W*H)-1);
			if(board[rnd]==GROUND) {
				
				board[rnd]=BOMB;
				c++;
			}
		}
	}
	private void generatePlayer() {
		boolean done=false;
		while(!done) {
			int rnd=getRandomNumberInts(0,(W*H)-1);
			if(board[rnd]==SPACE) {
				pl.setPlayer_pos(convertTo2DPos(rnd));
				done=true;
			}
		}
	}
	////////////////////////////////////
}
