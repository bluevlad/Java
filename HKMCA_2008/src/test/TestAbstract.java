package test;

public class TestAbstract extends TestSuperAbstract {
	public TestAbstract() {
	}

	public int getCount() {
		return super.count;
	}

	public int hit() {
		return super.count++;
	}
}
