package testingProj;
import java.io.Serializable;
import java.util.ArrayList;
public class Test1 implements Serializable {
	private String h=new String();
	public Test2 th=null;
	public ArrayList<Test2>arr=new ArrayList<Test2>();
	public Test1(String n) {
		this.setH(n);
		
		
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	
}
