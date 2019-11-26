package core;

public class CaseContent {
	private int pow;
	
	public CaseContent() {
		this(1);
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
		this.pow ++;
	}
	
	public boolean equals(CaseContent c) {
		return this.pow == c.getPow();
	}
}
