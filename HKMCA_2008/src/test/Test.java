package test;

public class Test extends TestSuper{
	public Test() {
		
	}
	
	public int getCount(){
		return super.count;
	}
	public int hit(){
		return super.count++;
	}
}
