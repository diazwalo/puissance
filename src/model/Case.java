package model;

public class Case {
	private CaseContent content;
	
	public Case (CaseContent content) {
		this.content = content;
	}
	
	public Case() {
		this(new CaseContent());
	}
	
	public boolean equals(Object o) {
		return this.content.equals(((Case)(o)).getContent());
	}
	
	public CaseContent getContent() {
		return this.content;
	}
	
	public String toString() {
		return this.content.toString();
	}
}
