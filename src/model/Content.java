package model;

public class Content {
	protected String[] fillAssociateToPow;
	protected boolean imgContent;
	
	public Content() {
		this.fillAssociateToPow = new String[17];
		this.imgContent = false;
	}
	
	public String[] getContent() {
		return this.fillAssociateToPow;
	}
	
	public void setContent(String[] fillAssociateToPow) {
		this.fillAssociateToPow = fillAssociateToPow;
	}
	
	public void setFillForPow(int pow, String newFill) {
		this.fillAssociateToPow[pow] = newFill;
	}
	
	public String getFillForPow(int pow) {
		return this.fillAssociateToPow[pow];
	}
	
	public boolean isImgContent() {
		return this.imgContent;
	}
	
	public void setImgContent(boolean b) {
		this.imgContent = b;
	}
	
	public String[] getFillAssociateToPow() {
		return this.fillAssociateToPow.clone();
	}
	
	public String toString() {
		String res = "[";
		for (int idx = 0; idx < this.fillAssociateToPow.length; idx ++) {
			res += idx + " -> " + this.fillAssociateToPow[idx] + "\n";
		}
		return res + "]";
	}
}
