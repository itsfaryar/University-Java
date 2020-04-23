package testingProj;

public class Test1 {
	private String h;
	public Test2 th;
	public Test1(String n) {
		this.setH(n);
		this.th=new Test2();
		th.ts=this;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	
}
