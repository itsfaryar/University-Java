package dormManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
	private static final String data_path="Data/data.SVD";
	private static Scanner sysin=new Scanner(System.in);
	private enum situation{MAIN}
	private static situation state;
	private static void saveObject(Object obj) {
		
		try {
			FileOutputStream file=new FileOutputStream(data_path);
			
			ObjectOutputStream obj_straem=new ObjectOutputStream(file);
			obj_straem.writeObject(obj);
			obj_straem.close();
			
		} catch (FileNotFoundException e) {//FileOutputStream
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {//ObjectOutputStream
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
			e.printStackTrace();
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return obj;
	}
	private static void guestPage(Core management) {
		System.out.println("\t\t\t======================");
		System.out.println("\t\t\tManagers' name:");
		System.out.println("\t\t\t"+management.getManagersName());
		System.out.println("\t\t\t======================");
		String name;
		String usr_name;
		String password;
		System.out.println("\t\t\tPlease enter the number of options:");
		System.out.println("\t\t\t1.Log in  |  2.Sign up");
		char ans=sysin.next().charAt(0);
		if(ans=='1') {
			System.out.print("\t\t\tYour user name: ");
			usr_name=sysin.next();
			System.out.print("\t\t\tYour password: ");
			password=sysin.next();
			management.logIn(usr_name, password);
		}
		else if(ans=='2'){
			System.out.print("\t\t\tYour name: ");
			name=sysin.next();
			System.out.print("\t\t\tYour user name: ");
			usr_name=sysin.next();
			System.out.print("\t\t\tYour password: ");
			password=sysin.next();
			if(management.addNewManager(name, usr_name, password)) {
				saveObject(management);
				System.out.println();
				System.out.println("\t\t\tyour Acount has been Successfully created.");
				System.out.println("\t\t\tanykey:Back login/signUp  |  2: Continue with this acount");
				sysin.next().charAt(0);
				if(ans=='2') {
					management.logIn(usr_name, password);
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
		
	}
	public static void main(String[] args) {
		Core management;
		File file=new File(data_path);
		state=situation.MAIN;
		if(file.exists()) {
			management=(Core) loadObject();
		}
		else {
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
