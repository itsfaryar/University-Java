package main;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.FileNotFoundException;



public class MasterMind {
	static Scanner sysin= new Scanner(System.in);
	//for game status
	public enum situation { START, IN_GAME, PEEKING_ON_ANS,WINNER,LOSER,HISTORY, EXIT }  
	//ANSI codes for changing terminal color
	//text color
	public static final String RST_T    = "\u001B[0m";
	public static final String ORANGE_T = "\u001b[38;5;208m";
	public static final String BLACK_T  = "\u001B[30m";
	public static final String RED_T    = "\u001B[31m";
	public static final String GREEN_T  = "\u001B[32m";
	public static final String YELLOW_T = "\u001B[33m";
	public static final String BLUE_T   = "\u001B[34m";
	public static final String PURPLE_T = "\u001B[35m";
	public static final String CYAN_T 	= "\u001b[36m";
	public static final String WHITE_T  = "\u001B[37m";
	//highlight text
	public static final String RST_H    = "\u001b[0m";
	public static final String ORANGE_H = "\u001b[48;5;208m";
	public static final String BLACK_H  = "\u001b[40m";
	public static final String RED_H    = "\u001b[41m";
	public static final String GREEN_H  = "\u001b[48;5;40m";
	public static final String YELLOW_H = "\u001b[43m";
	public static final String BLUE_H   = "\u001b[44m";
	public static final String PURPLE_H = "\u001b[48;5;165m";
	public static final String WHITE_H  = "\u001b[47m";
	/*////////////////////////////////////////////////////convert Xterm256 code to ANSI
	text color: "\u001b[38;5;" + code + "m "
	Highlight : "\u001b[48;5;" + code + "m "
	/////////////////////////////////////////////////////*/
	public static String makeColorFull(String usr_input) {
		String out=new String();
		for(int i=0;i<5;i++) {
			
			switch (usr_input.charAt(i)) {
			case 'R':
				out+=RED_H+usr_input.charAt(i);
				break;
			case 'B':
				out+=BLUE_H+usr_input.charAt(i);
				break;
			case 'P':
				out+=PURPLE_H+usr_input.charAt(i);
				break;
			case 'Y':
				out+=YELLOW_H+usr_input.charAt(i);
				break;
			case 'O':
				out+=ORANGE_H+usr_input.charAt(i);
				break;
			case 'G':
				out+=GREEN_H+usr_input.charAt(i);
				break;
			case 'W':
				out+=WHITE_H+BLACK_T+usr_input.charAt(i);
				break;
			default:
				out+=RST_H+ usr_input.charAt(i);
				break;
			}
		}
		out=out+=RST_H;
		return out;
	}
	public static void clearScreen() {  
		 try {

		        if (System.getProperty("os.name").contains("Windows")) {//windows

		            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		        }
		        else {//Linux

		        	 new ProcessBuilder("clear").inheritIO().start().waitFor();
		        }

		    } catch (IOException | InterruptedException ex) {}
	}  
	public static String capitalizeLetters(String inp) {
		String out=new String();
		for(int i=0;i<inp.length();i++) {
			if(((int)inp.charAt(i))>90) {
				out+=(char)((int)inp.charAt(i)-32);
			}
			else out+=inp.charAt(i);
		}
		return out;
	}
	public static int showStartPage() {
		clearScreen();
		int tries;
		System.out.println(GREEN_T);
		System.out.println("\t\t\t How many tries do you want in game?");
		tries=sysin.nextInt();
		System.out.println(WHITE_T);
		clearScreen();
		return tries;
	}
	public static void showGameBoard(String board,int counter,int tries) {
		
		clearScreen();
		System.out.print(GREEN_T);
		System.out.print("type and enter:\n0 for restart   |   1 for peeking on answer   |   q for exit");
		System.out.print("\n_________________________________________________________________\n");
		System.out.print(YELLOW_T);
		System.out.print("you have "+ (tries-counter+1)+"/"+tries+" tries left");
		System.out.print(ORANGE_T);
		System.out.println("Alowed Colors Are:");
		System.out.println("R: "+RED_H+BLACK_T+"RED"+RST_H+" | "+"B: "+BLUE_H+BLACK_T+"BLUE"+RST_H+ " | "+"P: "+PURPLE_H+BLACK_T+"PURPLE"+RST_H+" | "+"Y: "+YELLOW_H+BLACK_T+"YELLOW"+RST_H+" | "+"O: "+ORANGE_H+BLACK_T+"ORANGE"+RST_H+" | "+"G: "+GREEN_H+BLACK_T+"GREEN"+RST_H+" | "+"W: "+WHITE_H+BLACK_T+"WHITE");
		System.out.print(RST_H);
		System.out.print(GREEN_T);
		System.out.print("\n=======================\n");
		System.out.print(WHITE_T);
		System.out.print(board);
	
		
	}
	public static boolean peekOnAnswer(String inp,boolean permision){
		clearScreen();
		boolean resualt=false;
		if(permision) {
			System.out.print(GREEN_T);
			System.out.println("You can give one of your chances in order to peek on the answer for 1 second!!!");
			System.out.println("Are you sure?(y/n)");
			char ans=sysin.next().charAt(0);
			if(ans=='y'||ans=='Y') {
				clearScreen();
				System.out.print("\n\n\n");
				System.out.println(makeColorFull(inp)); 
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resualt=true;
			}
		}
		else {
			System.out.print(GREEN_T);
			System.out.println("Sorry!!!You don't have enough chances left");
			System.out.println("Please type and enter:");
			System.out.println("O for okay and getting back to game");
			System.out.println("I for ignoring this warning!!!(you will lose:|)");
			char ans=sysin.next().charAt(0);
			if(ans=='I' || ans=='i')resualt=peekOnAnswer(inp, !permision);
		}
		clearScreen();
		return resualt;
	}
	public static situation winnerPage() {
		clearScreen();
		situation state = situation.HISTORY;
		File text = new File("ansiPic/Winner.ans");
		Scanner n;
		try {
			n = new Scanner(text);
			while (n.hasNext())
			   {
			     System.out.println(n.nextLine());
			   }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.print("\033[H\033[2J");
		System.out.println("You Won! :).now...");
		System.out.println("What's it gonna be?");
		System.out.println("type and enter :");
		System.out.println("r for restart   |   q for exit   |   H to see the game board");
		char ans=sysin.next().charAt(0);
		switch (ans) {
		case 'r':
		case 'R':
			state=situation.START;
			break;
		case 'q':
		case 'Q':
			state=situation.EXIT;
			break;
		case 'h':
		case 'H':
			state=situation.HISTORY;
			break;
		default:
			break;
		}
		return state;
	}
	public static situation loserPage() {
		clearScreen();
		situation state = situation.HISTORY;
		File text = new File("ansiPic/Loser.ans");
		Scanner n;
		try {
			n = new Scanner(text);
			while (n.hasNext())
			   {
			     System.out.println(n.nextLine());
			   }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System. out. print("\033[H\033[2J");
		System.out.println("You have lost.It happens.now...");
		System.out.println("What's it gonna be?");
		System.out.println("type and enter :");
		System.out.println("r for restart   |   q for exit   |   H to see the game board");
		char ans=sysin.next().charAt(0);
		switch (ans) {
		case 'r':
		case 'R':
			state=situation.START;
			break;
		case 'q':
		case 'Q':
			state=situation.EXIT;
			break;
		case 'h':
		case 'H':
			state=situation.HISTORY;
			break;
		default:
			break;
		}
		return state;
	}
	public static situation historyPage(String board){
		situation state=situation.HISTORY;
		clearScreen();
		System.out.print(WHITE_T);
		System.out.print(board);
		System.out.print(GREEN_T);
		System.out.print("\n=======================\n");
		System.out.println("type and enter:\nr for restart   |   q for exit ");
		char ans=sysin.next().charAt(0);
		switch (ans) {
		case 'r':
		case 'R':
			state=situation.START;
			break;
		case 'q':
		case 'Q':
			state=situation.EXIT;
			break;
		default:
			break;
		}
		clearScreen();
		return state;
	}
	public static String checkInputFormat(String usr_input) {
		
		if(usr_input.length()==5) {
			boolean flg=true;
			up:for(int i=0;i<5;i++) {
				switch(usr_input.charAt(i)) {
				case 'R':
				case 'B':
				case 'P':
				case 'Y':
				case 'O':
				case 'G':
				case 'W':
					break;
				default:
					flg=false;
					break up;
				}
			}
			if(!flg) {usr_input="CE";}//error in color combination 
		}
		else {usr_input="LE";}//error in input length
		return usr_input;
	}
	public static void main(String[] args) {
		
		int counter=1;
		String usr_input="N";
		situation state=situation.START;
		
		GameOperator GO=new GameOperator();
		clearScreen();
		
		loop:while(state!=situation.EXIT) {
				switch (state) {
				case START:
					counter=1;
					usr_input="N";
					GO=new GameOperator();
					/////////by adding this instead of next line you can see the answer during the game
					////for debugging only :)
					//GO.setBoard(GO.getBoard()+GO.getRandom_word()+"\n");
					//GO.setBoard(GO.getBoard()+WHITE_T+"Guess 1: ");
					//////////////////////////////////////////////////////
					GO.setBoard(WHITE_T+"Guess 1: ");
					GO.setTries(showStartPage());
					state=situation.IN_GAME;
					continue loop;//for while
				case IN_GAME:
					 if(usr_input=="P") {
						 usr_input="N";
						 GO.setBoard(GO.getBoard()+"You have peeked on answer");
						 if((GO.getTries()-counter+1)<=0) {state=situation.LOSER;continue loop;}
						 GO.setBoard(GO.getBoard()+WHITE_T+"\nGuess "+counter+": ");
					 }
					 else if(usr_input.equals("LE")) {
						 usr_input="N";
					 }
					 else if(usr_input.equals("CE")) {
						 usr_input="N";
						 GO.setBoard(GO.getBoard()+"Wrong Color Combination!!!");
						 GO.setBoard(GO.getBoard()+WHITE_T+"\nGuess "+counter+": ");
					 }
					 else if(usr_input!="N") {
						 counter++;
						 String ans=GO.getGuessAns(usr_input);
						 usr_input=makeColorFull(usr_input);
						 GO.setBoard(GO.getBoard()+usr_input+"\n"+"\u001b[38;5;51m"+"\t "+ans);
						 if(ans.equals("#####")==true) {state=situation.WINNER;continue loop;}
						 usr_input="N";
						 if((GO.getTries()-counter+1)<=0) {state=situation.LOSER;continue loop;}
						 GO.setBoard(GO.getBoard()+WHITE_T+"\nGuess "+counter+": ");
					 }
					showGameBoard(GO.getBoard(),counter,GO.getTries());
					break;
				case PEEKING_ON_ANS:
					
					if((GO.getTries()-counter+1)>1) {
						if(peekOnAnswer(GO.getRandom_word(),true)) counter++;
					}
					else {
						if(peekOnAnswer(GO.getRandom_word(),false))counter++;
					
					}
					state=situation.IN_GAME;
					usr_input="P";
					continue loop;
				case WINNER:
					state=winnerPage();
					continue loop;
				case LOSER:
					state=loserPage();
					continue loop;//for while
				case HISTORY:
					state=historyPage(GO.getBoard());
					continue loop;//for while
				default:
					break;
				}
			System.out.print(WHITE_T);
			usr_input=capitalizeLetters(sysin.next());
			switch(usr_input) {
			case "0"://Restarting game
				usr_input="N";
				GO=new GameOperator();
				state=situation.START;
				break;
			case "1"://peeking answer for 1 second
				state=situation.PEEKING_ON_ANS;
				break;
			case "Q":
				state=situation.EXIT;
				break;
			default:
				usr_input=checkInputFormat(usr_input);
				break;
			}
		}
	}
}
