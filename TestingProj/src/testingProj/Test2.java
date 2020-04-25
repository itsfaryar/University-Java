package testingProj;
import java.io.Serializable;
public class Test2 implements Serializable{

	private Test1 ts;
	String s=null;
	public int numbers[] ;
	public void doit(String sm){
		this.s=sm;
		numbers=new int[5];
		numbers[2]=563;
	}
	public String getTs1() {
		return ts.getH();
	}
	public void setTs1(Test1 skjs) {
		this.ts=skjs;
	}
	public void start() {
		ts.setH(s);
	}

}
