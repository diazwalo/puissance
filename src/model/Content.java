package model;

import java.util.HashMap;
import java.util.Map;

public class Content {
	protected Map<Integer, String> fillAssociateToPow;
	protected boolean imgContent;
	
	public Content() {
		this.fillAssociateToPow = new HashMap<>();
		this.imgContent = false;
	}
	
	public Map<Integer, String> getContent() {
		return this.fillAssociateToPow;
	}
	
	public void setContent(Map<Integer, String> fillAssociateToPow) {
		this.fillAssociateToPow = fillAssociateToPow;
	}
	
	public void setFillForPow(Integer pow, String newFill) {
		this.fillAssociateToPow.put(pow, newFill);
	}
	
	public String getFillForPow(Integer pow) {
		return this.fillAssociateToPow.get(pow);
	}
	
	public boolean isContentFull(int sizeOfPlateau) {
		return Math.pow(sizeOfPlateau, 2) <= this.fillAssociateToPow.size() - 1; 
	}
	
	public boolean isImgContent() {
		return this.imgContent;
	}
	
	public void setImgContent(boolean b) {
		this.imgContent = b;
	}
	
	public String toString() {
		String res = "[";
		for (Integer pow : this.fillAssociateToPow.keySet()) {
			res += pow + " -> " + this.fillAssociateToPow.get(pow) + "\n";
		}
		return res + "]";
	}
}
