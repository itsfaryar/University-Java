package main;

import java.util.Random;

public class GameOperator {
	private int tries;
	private String board=new String();//game history
	private String random_word  = new String();
	private static char reference_table[] = {'R','B','P','Y','O','G','W'};
	
	public GameOperator(){
		//Random rand=new Random();
		for(int i=0;i<5;i++) {
			//this.random_word+= reference_table[rand.nextInt(7)];
			this.random_word+= reference_table[getRandomNumberInts(0,6)];
		}
		
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getRandom_word() {
		return random_word;
	}
	public int getTries() {
		return tries;
	}
	public void setTries(int tries) {
		this.tries = tries;
	}
	private static int getRandomNumberInts(int min, int max){
	    Random random = new Random();
	    return random.ints(min,(max+1)).findFirst().getAsInt();
	}
	public String getGuessAns(String input_guess) {
		String ans=new String();
		for(int i=0;i<5;i++) {
			if(this.random_word.charAt(i)==input_guess.charAt(i)) {
				ans+="#";
			}
			else if(this.random_word.indexOf(input_guess.charAt(i))!=-1) {
				ans+="+";
			}
			else {
				ans+="-";
			}
		}
		return ans;
	}


	


}
