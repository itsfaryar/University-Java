package dormManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import dormManagement.Dorm.types;

public class Main {
	private static final String data_path="Data/data.SVD";
	private static Scanner sysin=new Scanner(System.in);
	private enum situation{RUNNING,EXIT}
	private static situation state;
	private static String temp_usr=null;
	private static String temp_pass=null;
	private static Student std_chosen=null;
	public static char uppercase(char inp) {
		if(((int)inp)>90) {
			inp=(char)((int)inp-32);
		}
		return inp;
	}
	public static String upperCase(String inp) {
		String out=new String();
		for(int i=0;i<inp.length();i++) {
			if(((int)inp.charAt(i))>90) {
				out+=(char)((int)inp.charAt(i)-32);
			}
			else out+=inp.charAt(i);
		}
		return out;
	}
	private static boolean saveObject(Object obj) {
		boolean res=false;
		try {
			FileOutputStream file=new FileOutputStream(data_path);
			
			ObjectOutputStream obj_straem=new ObjectOutputStream(file);
			obj_straem.writeObject(obj);
			obj_straem.close();
			res=true;
			
		} catch (FileNotFoundException e) {//FileOutputStream
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		} catch (IOException e) {//ObjectOutputStream
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		return res;
	}
	private static Object loadObject() {
		Object obj = null;
		try {
			FileInputStream file=new FileInputStream(data_path);
			ObjectInputStream obj_straem=new ObjectInputStream(file);
			obj=obj_straem.readObject();
			obj_straem.close();
			
		} catch (FileNotFoundException e) {//FileOutputStream
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		return obj;
	}
	private static void safeSave(Core management) {
		boolean logined=management.is_logined();
		if(logined)management.logOut();
		saveObject(management);
		if(logined)management.logIn(temp_usr, temp_pass);
	}
	private static void unchooseStudent(boolean condition) {
		if(condition)std_chosen=null;
	}
	private static void editStudentInfoPage(Core management) {
		String stdnumber=null;
		boolean temperary_choose=false;
		while(std_chosen==null) {
			temperary_choose=true;
			System.out.println("Please enter student number to edit info or C to cancel");
			stdnumber=sysin.next();
			if(stdnumber.charAt(0)=='C'||stdnumber.charAt(0)=='c')return;
			std_chosen=management.findChosenStd(stdnumber);
		}
		String std_name=std_chosen.getName();
		String std_subject=std_chosen.getStuding_subject();
		String std_entrance=std_chosen.getYear_of_entrance();
		int std_debt=std_chosen.getDebt();
		loop:while(true) {
			System.out.println("\t\t===================");
			System.out.println("\t\t1.Name            :"+std_name);
			System.out.println("\t\t2.Student Subject :"+std_subject);
			System.out.println("\t\t3.year of entrance:"+std_entrance);
			System.out.println("\t\t4.debt            :"+std_debt);
			System.out.println("\t\tS:save and exit\tQ:Exit without saving");
			System.out.print("\t\tchoose: ");
			char ans=uppercase(sysin.next().charAt(0));
			switch (ans) {
			case '1':
				System.out.print("\t\tName: ");
				std_name=sysin.next();
				break;
			case '2':
				System.out.print("\t\tStudent Subject :");
				std_subject=sysin.next();
				break;
			case '3':
				System.out.print("\t\tyear of entrance: ");
				std_entrance=sysin.next();
				break;
			case '4':
				System.out.print("\t\tdebt: ");
				try {
					std_debt=sysin.nextInt();
				} catch (Exception e) {}
				break;
			case 'S':
				management.getLoginedManager().editStudent(std_chosen, std_name, std_subject, std_entrance, std_debt);
				safeSave(management);
				break loop;
			case 'Q':
				break loop;
			default:
				break;
			}
			
		}
		unchooseStudent(temperary_choose);
	}
	private static void chooseRoomPage(Core management) {
		String stdnumber=null;
		boolean temperary_choose=false;
		while(std_chosen==null) {
			temperary_choose=true;
			System.out.println("Please enter student number to edit info or C to cancel");
			stdnumber=sysin.next();
			if(stdnumber.charAt(0)=='C'||stdnumber.charAt(0)=='c')return;
			std_chosen=management.findChosenStd(stdnumber);
		}
		while(true) {
			System.out.println("Free Rooms:");
			System.out.println(management.getLoginedManager().freeRooms_Tostring());
			System.out.print("Enter Block Number: ");
			String block_num=sysin.next();
			System.out.print("Enter Room Number: ");
			String room_num=sysin.next();
			if(management.getLoginedManager().chooseRoom(block_num, room_num, std_chosen)) {
				System.out.println("Room has been succesfully chosen(Enter anything to continue)");
				sysin.next();
				break;
			}
			else {
				System.err.println("Faild!Room cannot be found.(Q:exit / Enter anything to continue");
				char ans=uppercase(sysin.next().charAt(0));
				if(ans=='Q') {
					break;
				}
			}
		}
		unchooseStudent(temperary_choose);
	}
	private static boolean roomBuilder(String room_num,Block block) {
		Room room=block.getRoomAtNumber(room_num);
		if(room==null)return false;
		while(true) {
			System.out.println("Number: "+room.getNumber()+"\tFloor Number: "+room.getFloor_number()+"\tRoom's Block: "+block.getNumber());
			System.out.println("1.Capacity: "+room.getCapacity());
			System.out.println("2.Rent Price: "+room.getRent_price());
			System.out.println("B: exit from this Room Q: save and exit from Dorm Builder");
			char ans=uppercase(sysin.next().charAt(0));
			if(ans=='1') {
				while(true) {
					System.out.print("Capacity(use +/- | Q for exit): "+room.getCapacity()+" ");
					ans=uppercase(sysin.next().charAt(0));
					if(ans=='+')room.increaseCapacity();
					else if(ans=='-')room.decreaseCapacity();
					else if(ans=='Q')break;
				}
			}
			else if(ans=='2') {
				try {
				System.out.println("Enter new rent price");
				room.setRent_price(Integer.parseInt(sysin.next()));
			
				}catch (Exception e) {System.err.println("Wrong Input Format!!!");}
			}
			else if(ans=='B') {
				return false;
			}
			else if(ans=='Q') {
				return true;
			}
		}
		//return false;
	}
	private static boolean blockBuilder(String block_num,Dorm dorm) {
		Block block=dorm.getBlockAtNumber(block_num);
		if(block==null)return false;
		loop:while(true) {
			
			System.out.println("Number: "+block.getNumber()+"\tCount of floors: "+block.getCount_of_floors()+"\tBlock's Dorm: "+block.getDorm().getName());
			System.out.println(block.rooms_toString());
			System.out.println("+ (Add a new room)");
			System.out.println("- (Remove a room)");
			System.out.println("B: exit from this block Q: save and exit from Dorm Builder");
			String ans=sysin.next();
			if(ans.charAt(0)=='+') {
				boolean done=false;
				try {
					System.out.print("New room's number: ");
					String number=sysin.next();
					System.out.print("New room's floor number: ");
					int floor_number=Integer.parseInt(sysin.next());
					System.out.print("New room's capacity: ");
					int capacity=Integer.parseInt(sysin.next());
					System.out.print("New room's rent_price: ");
					int rent_price=Integer.parseInt(sysin.next());
					done=block.addRoom(number, floor_number, capacity, rent_price);
					}catch (Exception e) {
						System.err.println("Wrong Input Format!!!");
						continue loop;
					}
					if(done)System.out.println("room has been added.");
					else System.err.println("Wrong Input Format!!!");
			}
			else if(ans.charAt(0)=='-') {
				System.out.print("room's number: ");
				String number=sysin.next();
				System.out.println("Are you sure?(y to continue/enter anything to cancel)");
				char ch=uppercase(sysin.next().charAt(0));
				if(ch=='Y') {
					block.removeRoom(number);
				}
			}
			else if(uppercase(ans.charAt(0))=='B') {
				return false;
			}
			else if(uppercase(ans.charAt(0))=='Q') {
				return true;
			}
			else {
				if(roomBuilder(ans, block)) {
					return true;
				}
				else {
					continue loop;
				}
			}
		}
	}
	private static void dormBuilder(Core management) {
		Dorm dorm=management.getLoginedManagerDorm();
		loop:while(true) {
			
			System.out.println("Dorm Name: "+dorm.getName()+"\tDorm type: "+dorm.getType()+"\tcount of blocks: "+dorm.getCount_of_blocks());
			System.out.println(dorm.blocks_toString());
			System.out.println("+ (Add a new block)");
			System.out.println("- (remove a block)");
			System.out.println("Q: save and exit from Dorm Builder");
			String ans=sysin.next();
			if(ans.charAt(0)=='+') {
				try {
				System.out.print("New Block's number: ");
				String number=sysin.next();
				System.out.print("New Block's count of floors: ");
				int count_of_floors=Integer.parseInt(sysin.next());
				dorm.addNewBlock(number, count_of_floors);
				}catch (Exception e) {
					System.err.println("Wrong Input Format!!!");
					continue loop;
				}
				System.out.println("Block has been added.");
			}
			else if(ans.charAt(0)=='-') {
				System.out.print("Block's number: ");
				String number=sysin.next();
				System.out.println("Are you sure?(y to continue/enter anything to cancel)");
				char ch=uppercase(sysin.next().charAt(0));
				if(ch=='Y') {
					dorm.removeBlock(number);
				}
			}
			else if(uppercase(ans.charAt(0))=='Q') {
				break;
			}
			else {
				if(blockBuilder(ans, dorm)) {
					break loop;
				}
				else {
					continue loop;
				}
			}
		}
		
	}
	private static void showAllFreeRooms(Core management) {
		System.out.println(management.getLoginedManager().freeRooms_Tostring());
		System.out.println("Enter enything to continue.");
		sysin.next();
	}
	private static void guestPage(Core management) {
		System.out.println("\t\t\t======================");
		System.out.println("\t\t\tManagers' name:");
		System.out.println("\t\t\t"+management.getManagerNames());
		System.out.println("\t\t\t======================");
		String name;
		String usr_name;
		String password;
		System.out.println("\t\t\tPlease enter the number of options:");
		System.out.println("\t\t\t1.Log in  |  2.Sign up");
		char ans=sysin.next().charAt(0);
		if(ans=='1') {
			System.out.print("\t\t\tYour user name: ");
			temp_usr=sysin.next();
			System.out.print("\t\t\tYour password: ");
			temp_pass=sysin.next();
			management.logIn(temp_usr, temp_pass);
		}
		else if(ans=='2'){
			System.out.print("\t\t\tYour name: ");
			name=sysin.next();
			System.out.print("\t\t\tYour user name: ");
			usr_name=sysin.next();
			System.out.print("\t\t\tYour password: ");
			password=sysin.next();
			if(management.addNewManager(name, usr_name, password)) {
				safeSave(management);
				System.out.println();
				System.out.println("\t\t\tyour Acount has been Successfully created.");
				System.out.println("\t\t\tanykey:Back login/signUp  |  2: Continue with this acount");
				sysin.next().charAt(0);
				if(ans=='2') {
					temp_usr=usr_name;
					temp_pass=password;
					management.logIn(temp_usr, temp_pass);
					
				}
			}
			else {
				System.out.println();
				System.err.println("\t\t\tSORRY!!!This username has been used before!!!");
				System.out.println("\t\t\tanykey:Back login/signUp");
				sysin.next();
			}
		}
	}
	private static void loginedPage(Core management) {
		
		
		if(management.getLoginedManagerDorm()==null) {
			String name=null;
			Dorm.types type=null;
			System.out.println("You are not manager of any Dorm");
			System.out.println("Choose one Dorm or add a new one");
			System.out.println("0.add a new one");
			System.out.println(management.getDormNames());
			int ans=sysin.nextInt();
			if(ans==0) {
				System.out.println("please enter a name for new Dorm");
				name=sysin.next();
				System.out.println("please enter the type of this Dorm(1.For Boys 2.For Girls");
				type=null;
				while(type==null) {
					char ch=sysin.next().charAt(0);
					if(ch=='1') {
						type=types.FOR_BOYS;
					}
					else if(ch=='2') {
						type=types.FOR_GIRLS;
					}
				}
				management.addDorm(name, type);
				safeSave(management);
			}
			else {
				String key=null;
				if(management.is_dormLocked(ans-1)) {
					System.out.println("Please enter authorizing key");
					key=sysin.next();
				}
				if(management.setDorm(ans-1, key)) {
					safeSave(management);
					System.out.println("Signed in succesfully.");
				}
				else {
					System.err.println("Wrong Authorizing Key!!!");
				}
			}
		}
		else {
			System.out.println("=================");
			System.out.println("\n\t\t\tYou are Manager of Dorm: "+management.getLoginedManagerDorm());
			System.out.println();
			System.out.println("\t\tG: sign out from this Dorm.");
			System.out.println("\t\tL: Logout from your account.");
			if(std_chosen==null)System.out.println("\t0.choose student\t1.Show all students");
			else {
				System.out.println("\t"+std_chosen.toString());
				System.out.println("\t0.unchoose student \""+std_chosen.getName()+"\"\t1.Show all students");
			}
			System.out.println();
			System.out.println(" 2.add new student          3.Remove student                 4.edit student info");
			System.out.println(" 5.Choose room for student  6.Show student's roommates       7.Show all free rooms");
			System.out.println(" 8.Show room members        9.Remove all members from room  10.remove all members from all rooms");
			System.out.println("11.Dorm builder");
			String ans=upperCase(sysin.next());
			if(ans.equals("G")) {
				
			}
			else {
				switch (ans) {
				case "G":
					boolean done=false;
					while(!done) {
						System.out.println("\t\t\tDo you want to lock the Dorm first?(y/n)");
						char ch=uppercase(sysin.next().charAt(0));
						if(ch=='Y') {
							System.out.println("Copy this code and keep it safa to sign in to this Dorm.");
							System.out.println();
							System.out.println(management.unsetDorm(true));
							System.out.println();
							done=true;
						}
						else if(ch=='N') {
							management.unsetDorm(false);
							System.out.println("Signedout succesfully.");
							done=true;
						}
					}	
					safeSave(management);
					break;
				case "L":
					management.logOut();
					break;
				case "0":
					boolean unchoose_con=true;
					while(std_chosen==null) {
						unchoose_con=false;
						System.out.println("Please enter valid student number to edit info or C to cancel");
						String stdnumber=sysin.next();
						if(stdnumber.charAt(0)=='C'||stdnumber.charAt(0)=='c')return;
						std_chosen=management.findChosenStd(stdnumber);
					}
					unchooseStudent(unchoose_con);
					break;
				case "1":
					System.out.println(management.getLoginedManager().Students_toString());
					System.out.println("enter anything to continue.");
					sysin.next();
					break;
				case "2":
					try {
						System.out.println("-----------");
						System.out.print("Name: ");
						String name=sysin.next();
						System.out.print("student number: ");
						String student_number=sysin.next();
						System.out.print("studing subject: ");
						String studing_subject=sysin.next();
						System.out.print("year of entrance: ");
						String year_of_entrance=sysin.next();
						System.out.print("pre debt: ");
						int debt=sysin.nextInt();
						System.out.println("-----------");
						management.getLoginedManager().addNewStudent(name, student_number, studing_subject, year_of_entrance, debt);
					}catch (Exception e) {}
					safeSave(management);
					break;
				case "3":
					System.out.println("-----------");
					while(std_chosen==null) {
						System.out.println("Please enter valid student number to edit info or C to cancel");
						String stdnumber=sysin.next();
						if(stdnumber.charAt(0)=='C'||stdnumber.charAt(0)=='c')return;
						std_chosen=management.findChosenStd(stdnumber);
					}
					System.out.println("Do you want to remove Student \""+std_chosen.getName()+"\"(y to continue | enter anything to cancel");
					char ch=sysin.next().charAt(0);
					if(ch=='y') {
						if(management.getLoginedManager().eraseStudent(std_chosen)) {
							System.out.println("removed succesfully.");
							System.out.println("enter anything to continue.");
							sysin.next();
						}
						else {
							System.out.println("Student not found!!!");
							System.out.println("enter anything to continue.");
							sysin.next();
						}
					}
					else {
						return;
					}
					unchooseStudent(true);
					safeSave(management);
					break;
				case "4":
					editStudentInfoPage(management);
					break;
				case "5":
					chooseRoomPage(management);
					safeSave(management);
					break;
				case "6":
					break;
				case "7":
					showAllFreeRooms(management);
					break;
				case "8":
					break;
				case "9":
					break;
				case "10":
					break;
				case "11":
					dormBuilder(management);
					safeSave(management);
					break;
				default:
					break;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Core management=null;
		File file=new File(data_path);
		state=situation.RUNNING;
		if(file.exists()) {
			management=(Core) loadObject();
		}
		if(management==null) {
			file.getParentFile().mkdirs();
			management=new Core();
		}
		loop:while(true) {
			switch(state) {
			case RUNNING:
				if(management.is_logined()) {
					loginedPage(management);
				}
				else {
					guestPage(management);
				}
				break;
			case EXIT:
				break loop;
			}
		}
	}
}
