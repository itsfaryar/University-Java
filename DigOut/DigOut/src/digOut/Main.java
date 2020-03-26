package digOut;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import digOut.Control.capital_Sensitivity;
import digOut.GameBoard.Display_Mode;



public class Main {
	private static final String coffin=" _____.______.______._____\n" + " \\`\\                   /'/\n" + "  \\ |                 | /\n" + "   >|___,____,____,___|<\n" + "  /          "+"\u001b[38;5;33m"+"!"+"\u001b[0m"+"          \\\n" + " /                       \\\n" + "<=====w======w======w=====>\n" + " \\ \\____> \\_____/ <____/ /\n" + "  \\_____________________/ ";
	private static final String closed_coffin="    _________________________\n" + "      |\\ _____________________ /|\n" + "      | |_____________________| |\n" + "      |/_______________________\\|\n" + "      /=========================\\\n" + "     '==========================='\n" + "      |  ~~       _|_        ~~ |\n" + "      |            |            |\n" + "      |_________________________|";
	private static final String empty_coffin=" _____.______.______._____\n" + " \\`\\                   /'/\n" + "  \\ |                 | /\n" + "   >|___,____,____,___|<\n" + "  /                     \\\n" + " /                       \\\n" + "<=====w======w======w=====>\n" + " \\ \\____> \\_____/ <____/ /\n" + "  \\_____________________/ ";
	private static Scanner sysin=new Scanner(System.in);
	private enum situation{MENU,KBD_SETT,RECORDS,START,IN_GAME,PEEK_ON_BOMBS,WON,SIMITARY,REST,RISE,ADMIN,EXIT};  
	private static situation state=situation.MENU;
	private static Display_Mode display=Display_Mode.ASCII;
	private static String pl_name;
	private static int W,H;
	private static int coins_n;
	private static int min_w=4;
	private static int min_h=4;
	private static int max_w=10;
	private static int max_h=10;
	private static int start_time=0;
	private static boolean bomb_mode;
	////////////////////////////////////
	public static void clearScreen() {  
		 try {
			 if (System.getProperty("os.name").contains("Windows")) {//windows
				 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			 }
			 else {//Linux
				 new ProcessBuilder("clear").inheritIO().start().waitFor();
			 }

		 }
		 catch (IOException | InterruptedException ex) {}
	}  
	public static char capital(char inp,capital_Sensitivity control_mode) {
		if(control_mode==capital_Sensitivity.OFF) {
			if(((int)inp)>90) {
				inp=(char)((int)inp-32);
			}
		}
		return inp;
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
	public static void menuPage(boolean resume_flg) {
		clearScreen();
		if(resume_flg) {
			System.out.println("\t\t\t+=======================+");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|1.Resume               |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|2.New Game             |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|3.Keyboard Setting     |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|4.Records              |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|5.EXIT                 |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t+=======================+");
			System.out.print  ("\t\t\tChoose number:");
			try {
				int ans=sysin.nextInt();
				switch (ans) {
				case 1:
					state=situation.IN_GAME;
					break;
				case 2:
					state=situation.START;
					break;
				case 3:
					state=situation.KBD_SETT;
					break;
				case 4:
					state=situation.RECORDS;
					break;
				case 5:
					state=situation.EXIT;
					break;
				default:
					break;
				}
			} catch (Exception e) {
				sysin=new Scanner(System.in);
			}
		}
		else {
			System.out.println("\t\t\t+=======================+");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|1.Start                |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|2.Keyboard Setting     |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|3.Records              |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t|4.EXIT                 |");
			System.out.println("\t\t\t|                       |");
			System.out.println("\t\t\t+=======================+");
			System.out.print  ("\t\t\tChoose number:");
			try {
				int ans=sysin.nextInt();
				switch (ans) {
				case 1:
					state=situation.START;
					break;
				case 2:
					state=situation.KBD_SETT;
					break;
				case 3:
					state=situation.RECORDS;
					break;
				case 4:
					state=situation.EXIT;
					break;
				default:
					break;
				}
			} catch (Exception e) {
				sysin=new Scanner(System.in);
			}
		}
			
		
	}
	public static void kbdSetting(Control key_bind) {
		clearScreen();
		System.out.println("\t\t\t+========================+");
		System.out.println("\t\t\t|                        |");
		System.out.println("\t\t\t|1. Move Up         : "+key_bind.MOVE_UP+"  |");
		System.out.println("\t\t\t|2. Move Down       : "+key_bind.MOVE_DOWN+"  |");
		System.out.println("\t\t\t|3. Move Right      : "+key_bind.MOVE_RIGHT+"  |");
		System.out.println("\t\t\t|4. Move Left       : "+key_bind.MOVE_LEFT+"  |");
		System.out.println("\t\t\t|5. Dig Up          : "+key_bind.DIG_UP+"  |");
		System.out.println("\t\t\t|6. Dig Down        : "+key_bind.DIG_DOWN+"  |");
		System.out.println("\t\t\t|7. Dig Right       : "+key_bind.DIG_RIGHT+"  |");
		System.out.println("\t\t\t|8. Dig Left        : "+key_bind.DIG_LEFT+"  |");
		System.out.println("\t\t\t|9. Caps sencetivity: "+key_bind.control_mode+"|");
		System.out.println("\t\t\t|10.Save & Exit          |");
		System.out.println("\t\t\t|                        |");
		System.out.println("\t\t\t+========================+");
		System.out.print  ("\t\t\tChoose number:");
		try {
			int ans=sysin.nextInt();
			
			switch (ans) {
			case 1:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tMove Up: ");
				key_bind.MOVE_UP=sysin.next().charAt(0);
				break;
			case 2:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tMove Down: ");
				key_bind.MOVE_DOWN=sysin.next().charAt(0);
				break;
			case 3:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tMove Right: ");
				key_bind.MOVE_RIGHT=sysin.next().charAt(0);
				break;
			case 4:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tMove Left: ");
				key_bind.MOVE_LEFT=sysin.next().charAt(0);
				break;
			case 5:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tDig Up: ");
				key_bind.DIG_UP=sysin.next().charAt(0);
				break;
			case 6:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tDig Down: ");
				key_bind.DIG_DOWN=sysin.next().charAt(0);
				break;
			case 7:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tDig Right: ");
				key_bind.DIG_RIGHT=sysin.next().charAt(0);
				break;
			case 8:
				System.out.println("\t\t\tChoose a key");
				System.out.print("\t\t\tDig Left: ");
				key_bind.DIG_LEFT=sysin.next().charAt(0);
				break;
			case 9:
				System.out.println("\t\t\tUse:\n\t\t\t1 for ON | 0 for OFF");
				System.out.print("\t\t\tCaps sencetivity: ");
				int tmp=sysin.nextInt();
				if(tmp==1)key_bind.control_mode=capital_Sensitivity._ON;
				else if(tmp==0)key_bind.control_mode=capital_Sensitivity.OFF;
				break;
			case 10:
				state=situation.MENU;
				break;
			default:
				break;
			}
		} catch (Exception e) {
			sysin=new Scanner(System.in);
		}
		key_bind.saveSetting();
	}
	public static void showRecords(Records RC) {
		clearScreen();
		System.out.println(RC.getPrintableRecords());
		System.out.println();
		System.out.println("1:Menu");
		System.out.println("2:Exit");
		char ans=sysin.next().charAt(0);
		switch (ans) {
		case '1':
			state=situation.MENU;
			break;
		case '2':
			state=situation.EXIT;
			break;
		}
	}
	public static boolean startPage() {
		boolean res=false;
		clearScreen();
		try {
			System.out.print("\t\t\tPlease enter your name:  ");
			pl_name=sysin.next();
			System.out.print("\t\t\tplease enter width and height:(between 4-10)  ");
			W=sysin.nextInt();
			H=sysin.nextInt();
			System.out.print("\t\t\tPlease enter the count of coins:  ");
			coins_n=sysin.nextInt();
			System.out.println("\t\t\tPlease choose display mode");
			System.out.print("\t\t\t1 for Ascii mode | 2 for Ansi colored mode :");
			int tmp=sysin.nextInt();
			if(tmp==1)display=Display_Mode.ASCII;
			else if(tmp==2)display=Display_Mode.ANSI_COLORFULL;
			if(W>=min_w && W<=max_w && H>=min_h && H<=max_h)res=true;
			System.out.println("\t\t\tDo you want bombs in board?(y/n)");
			char ch=capital(sysin.next().charAt(0),capital_Sensitivity.OFF);
			if(ch=='Y')bomb_mode=true;
			else if(ch=='N')bomb_mode=false;
			else res=false;
			
		}
		catch (Exception e) {
			sysin=new Scanner(System.in);
			state=situation.START;
		}
		return res;
	}
	public static void inGamePage(GameBoard GB,Player pl,Control key_bind,Records RC) {
		clearScreen();
		GB.getPlayer().setPlaying_time((LocalTime.now().toSecondOfDay()-start_time));
		Player previousRecord=RC.getPreviousRecord(pl);
		if(previousRecord!=null) {
			System.out.println("previous record --> "+previousRecord.getName()+" : "+LocalTime.ofSecondOfDay(previousRecord.getPlaying_time()));
		}
		System.out.println("Playing time: "+LocalTime.ofSecondOfDay((LocalTime.now().toSecondOfDay()-start_time)).toString());
		System.out.println(key_bind.getControlInfo());
		System.out.println("Menu:1\tExit:2\tChange Display mode:3\tAdminview:4");
		System.out.println("==========================================");
		System.out.println(pl.getPlayerInfo());
		if(GB.is_bomb()) {
			System.out.print("Count of bombs: "+GB.getbomb_n());
			if(GB.isFirst_peek()) {
				System.out.print("\t5:See the bombs for 2 seconds once\n");
			}
			else {
				System.out.println();
			}
		}
		System.out.println(GB.getPrintableBoard());
		/*for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				System.out.print(GB.board_tmp[GB.convertTo1DPos(new Position(i, j))]);
			}
			System.out.println();
		}*/
		if(GB.isBombVisibility()) {
			GB.setBombVisibility(false);
			try {
				TimeUnit.SECONDS.sleep(2);
				GB.setFirst_peek(false);
			} catch (InterruptedException e) {}
		}
		else {
			char key=capital(sysin.next().charAt(0),key_bind.control_mode);
			if(key=='1') {
				state=situation.MENU;
			}
			else if(key=='2') {
				state=situation.EXIT;
			}
			else if(key=='3') {
				GB.changeDisplayMode();
			}
			else if(key=='4') {
				state=situation.ADMIN;
			}
			else if(key=='5'&&GB.isFirst_peek()) {
				GB.setBombVisibility(true);
			}
			else if(key==key_bind.MOVE_UP) {
				GB.movePlayer(GameBoard.Directions.UP);
				GB.moveMonster();
			}
			else if(key==key_bind.MOVE_DOWN) {
				GB.movePlayer(GameBoard.Directions.DOWN);
				GB.moveMonster();
			}
			else if(key==key_bind.MOVE_RIGHT) {
				GB.movePlayer(GameBoard.Directions.RIGHT);
				GB.moveMonster();
			}
			else if(key==key_bind.MOVE_LEFT) {
				GB.movePlayer(GameBoard.Directions.LEFT);
				GB.moveMonster();
			}
			else if(key==key_bind.DIG_UP) {
				GB.dig(GameBoard.Directions.UP);
				GB.moveMonster();
			}
			else if(key==key_bind.DIG_DOWN) {
				GB.dig(GameBoard.Directions.DOWN);
				GB.moveMonster();
			}
			else if(key==key_bind.DIG_RIGHT) {
				GB.dig(GameBoard.Directions.RIGHT);
				GB.moveMonster();
			}
			else if(key==key_bind.DIG_LEFT) {
				GB.dig(GameBoard.Directions.LEFT);
				GB.moveMonster();
			}
		}
		GB.getPlayer().setPlaying_time((LocalTime.now().toSecondOfDay()-start_time));
		GB.checkForWinner();
	}
	
	public static void winnerPage(Player pl,Records RC) {
		clearScreen();
		if(RC.saveDatas(pl)==true) {
			System.out.println("You have made a new record in this board config!!!");
		}
		System.out.println(pl.getName()+" You have Won!!!");
		System.out.println("Playing time: "+LocalTime.ofSecondOfDay(pl.getPlaying_time()).toString());
		System.out.println();
		System.out.println("1:Enter to a new game\n2:Go to menu\n3:Exit");
		char ans;
		ans=sysin.next().charAt(0);
		if(ans=='1') {
			state=situation.START;
		}
		else if(ans=='2') {
			state=situation.MENU;
		}
		else if(ans=='3') {
			state=situation.EXIT;
		}
	}
	public static void inSimitary(Player pl) {
		clearScreen();
		System.out.println(coffin);
		System.out.println("   "+pl.getDeath_reason());
		System.out.println();
		System.out.println("Your dead!!!\nHow do you want to spend your after life?\n1:Rest in peace\n2:Rise from the dead");
		char ans;
		ans=sysin.next().charAt(0);
		if(ans=='1') {
			state=situation.REST;
		}
		if(ans=='2') {
			state=situation.RISE;
		}
	}
	public static void inRestInPeace() {
		clearScreen();
		System.out.println(closed_coffin);
		System.out.println("... ... ... .... ...");
		System.out.println("1:Get out of the coffin!!!\n2:Exit");
		char ans;
		ans=sysin.next().charAt(0);
		if(ans=='1') {
			state=situation.RISE;
		}
		else if(ans=='2') {
			state=situation.EXIT;
		}
	}
	public static void riseFromTheCoffin() {
		clearScreen();
		System.out.println(empty_coffin);
		System.out.println("You are going to have a new life.Use it WISELY");
		System.out.println("1:Enter to a new game\n2:Go to menu");
		char ans;
		ans=sysin.next().charAt(0);
		if(ans=='1') {
			state=situation.START;
		}
		else if(ans=='2') {
			state=situation.MENU;
		}
	}
	public static void main(String[] args) {
		Player pl=null;
		GameBoard GB=new GameBoard(null,0,0,0,true,display);
		Control key_bind=new Control();
		Records recs=new Records();
		/*pl=GB.getPlayer();
		System.out.println(pl.getPlayer_pos().i+":"+pl.getPlayer_pos().j);
		System.out.println(GB.getPrintableBoard()+"\n");*/
		while(state!=situation.EXIT) {
			switch (state) {
			case MENU:
				if(GB.getPlayer()==null)menuPage(false);
				else menuPage(true);
				break;
			case KBD_SETT:
				kbdSetting(key_bind);
				break;
			case RECORDS:
				showRecords(recs);
				break;
			case START:
				if(startPage()) {
					state=situation.IN_GAME;
					pl=new Player(pl_name);
					GB=new GameBoard(pl,W,H,coins_n,bomb_mode,display);
					recs=new Records();
					start_time=LocalTime.now().toSecondOfDay();
				}
				break;
			case IN_GAME:
				pl=GB.getPlayer();
				if(GB.getPlayer().getStatus()==Player.Status.WINNER)state=situation.WON;
				else if(GB.getPlayer().getStatus()==Player.Status.DEAD)state=situation.SIMITARY;
				else inGamePage(GB,pl,key_bind,recs);
				
				break;
			case WON:
				winnerPage(pl,recs);
				GB=new GameBoard(null,0,0,0,true,display);
				break;
			case SIMITARY:
				GB=new GameBoard(null,0,0,0,true,display);
				inSimitary(pl);
				break;
			case RISE:
				riseFromTheCoffin();
				break;
			case REST:
				inRestInPeace();
				break;
			case ADMIN:
				break;
			default:
				break;
			}
		}
	}
}
