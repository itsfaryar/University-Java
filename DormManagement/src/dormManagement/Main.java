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
	private enum situation{MAIN}
	private static situation state;
	private static String temp_usr=null;
	private static String temp_pass=null;
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
	private static void editStudentInfoPage(Core management) {
		System.out.println("Please enter student number to edit info");
		String stdnumber=sysin.next();
		int index=management.getLoginedManager().searchStudentByStdNumber(stdnumber);
		if(index==-1) {
			System.out.println("Student not Found!!! Enter anything to back to menu.");
			return ;
		}
		Student std=management.getLoginedManager().getStudent(index);
		String std_name=std.getName();
		String std_subject=std.getStuding_subject();
		String std_entrance=std.getYear_of_entrance();
		int std_debt=std.getDebt();
		loop:while(true) {
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
				management.getLoginedManager().editStudent(std, std_name, std_subject, std_entrance, std_debt);
				safeSave(management);
				break loop;
			case 'Q':
				break loop;
			default:
				break;
			}
			
		}
		
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
				System.out.println("\t\t\tSORRY!!!This username has been used before!!!");
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
					System.out.println("Wrong Authorizing Key!!!");
				}
			}
		}
		else {
			System.out.println("=================");
			System.out.println("\n\t\t\tYou are Manager of Dorm: "+management.getLoginedManagerDorm());
			System.out.println();
			System.out.println("\t\tG: sign out from this Dorm.");
			System.out.println("\t\tL: Logout from your account.");
			System.out.println();
			System.out.println("1.Show all students\t2.add new student\t3.Remove student");
			System.out.println("4.edit student info\t2.add new student\t3.Remove student");
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
					System.out.print("Student Number: ");
					String stdnumber=sysin.next();
					if(management.getLoginedManager().eraseStudent(stdnumber)) {
						System.out.println("removed succesfully.");
						System.out.println("enter anything to continue.");
						sysin.next();
					}
					else {
						System.out.println("Student not found!!!");
						System.out.println("enter anything to continue.");
						sysin.next();
					}
					break;
				case "4":
					editStudentInfoPage(management);
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
		state=situation.MAIN;
		if(file.exists()) {
			management=(Core) loadObject();
		}
		if(management==null) {
			file.getParentFile().mkdirs();
			management=new Core();
		}
		while(true) {
			switch (state) {
			case MAIN:
				if(management.is_logined()) {
					loginedPage(management);
				}
				else {
					guestPage(management);
				}
				break;

			}
		}
	}
}
