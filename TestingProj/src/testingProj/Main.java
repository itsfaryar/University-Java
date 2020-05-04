package testingProj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner sysin=new Scanner(System.in);
	public static void main(String[] args) {
		Test1 ts=new Test1("hello");
		String st=ts.getH();
		st=sysin.next();
		System.out.println(ts.getH());
		
	}
	private static void saveObject(String object_name,Object obj) {
	
		try {
			FileOutputStream file=new FileOutputStream(("Data_"+object_name+".txt"));
			
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
	private static Object loadObject(String object_name) {
		Object obj = null;
		try {
			FileInputStream file=new FileInputStream(("Data_"+object_name+".txt"));
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
}
