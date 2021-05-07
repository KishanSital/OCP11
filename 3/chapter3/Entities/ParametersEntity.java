package chapter3.Entities;

public class ParametersEntity {

	private int parameter1;
	private double parameter2;
	private long parameter3;

	public ParametersEntity (int parameter1, double parameter2, long parameter3){
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.parameter3 = parameter3;
	}

	public ParametersEntity(){
	}

	public int getParameter1() {
		return parameter1;
	}

	public void setParameter1(int parameter1) {

		this.parameter1 = parameter1;
	}


	public double getParameter2() {
		return parameter2;
	}

	public void setParameter2(double parameter2) {
		this.parameter2 = parameter2;
	}

	public long getParameter3() {
		return parameter3;
	}

	public void setParameter3(long parameter3) {
		this.parameter3 = parameter3;
	}
}
