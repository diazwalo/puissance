package core;

public class Case {
	private CaseContent content;
	
	public Case (CaseContent content) {
		this.content = content;
	}
	
	public Case() {
		this(new CaseContent());
	}
	
	public boolean equals(Case c) {
		return this.content.equals(c.getContent());
	}
	
	public CaseContent getContent() {
		return this.content;
	}
	
	public String toString() {
		return this.content.toString();
	}
}
