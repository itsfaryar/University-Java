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
	private static void mainPage(Core management) {
		System.out.println("Managers");
		System.out.println(management.getManagersName());
		System.out.println("======================");
		String name;
		String usr_name;
		String password;
		System.out.println("\t\t\tPlease enter the number of options:");
		System.out.println("\t\t\t1.Log in  |  2.Sign up");
		char ans=sysin.next().charAt(0);
		if(ans==1) {
			
		}
		else {
			System.out.print("Your name: ");
			name=sysin.next();
			System.out.print("Your user name: ");
			usr_name=sysin.next();
			System.out.print("Your password: ");
			password=sysin.next();
			management.addNewManager(name, usr_name, password);
		}
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
				mainPage(management);
				break;

			}
		}
	}
}
