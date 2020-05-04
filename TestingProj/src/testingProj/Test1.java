package testingProj;
import java.io.Serializable;
public class Test1 implements Serializable {
	private String h=new String();
	public Test2 th;
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
