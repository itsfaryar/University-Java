package digOut;

public class Player {
	public  enum Status{ALIVE,DEAD,WINNER}
	private Status player_status=Status.ALIVE;
	private String death_reason="";
	private String name;
	private Position player_pos;
	private int coins;
	private int playing_time=0;
	private int board_size;
	private Boolean is_bomb_mode;
	public Player(String name) {
		setName(name);
	}
	public Boolean getIs_bomb_mode() {
		return is_bomb_mode;
	}
	public void setIs_bomb_mode(Boolean is_bomb_mode) {
		this.is_bomb_mode = is_bomb_mode;
	}
	public int getBoard_size() {
		return board_size;
	}
	public void setBoard_size(int board_size) {
		this.board_size = board_size;
	}
	public int getPlaying_time() {
		return playing_time;
	}
	public void setPlaying_time(int playing_time) {
		this.playing_time = playing_time;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int score) {
		this.coins = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Position getPlayer_pos() {
		return player_pos;
	}
	public void setPlayer_pos(Position pos) {
		this.player_pos = pos;
	}
	public String getPlayerInfo() {
		String info=new String();
		info+="Player's name: "+name+"\t"+"Coin: "+coins;
		return info;
	}
	public Status getStatus() {
		return player_status;
	}
	public void setStatus(Status player_status) {
		this.player_status = player_status;
	}
	public String getDeath_reason() {
		return death_reason;
	}
	public void setDeath_reason(String death_reason) {
		this.death_reason = death_reason;
	}
	
}
