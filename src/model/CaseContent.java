package model;

public class CaseContent {
	private int pow;
	
	public CaseContent() {
		this(0);
	}
	
	public CaseContent(int pow) {
		this.pow = pow;
	}
	
	public String toString() {
		int res = (int) (Math.pow(2.0, pow));
		
		if(this.pow == 0) {
			return "    ";
		}else if(res < 10) {
			return " "+ res + "  ";
		}else if(res < 100) {
			return " " + res + " ";
		}else if(res < 1000) {
			return res + " ";
		}else {
			return res + "";
		}
	}
	
	public int getPow() {
		return this.pow;
	}
	
	public void setPow(int pow) {
		this.pow = pow;
	}
	
	public void incPow() {
		++this.pow;
	}
	
	public boolean isNull() {
		return this.pow == 0;
	}
	
	public boolean equals(Object o) {
		return this.pow ==  (((CaseContent) (o)).getPow());
	}
}
