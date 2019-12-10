package core;

public enum Movment {
	LEFT(new int[]{-1, 0}), RIGHT(new int[]{1, 0}), UP(new int[]{0, -1}), DOWN(new int[]{0, 1});
	private int[] movment;
	
	private Movment(int[] movment) {
		this.movment = movment;
	}
	
	public int[] getMovment() {
		return this.movment;
	}
}
