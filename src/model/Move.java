package model;

public class Move {
	private char[] destination;
	private char[] origin;
	
	
	public Move(char[] origin, char [] destination){
		this.origin = origin;
		this.destination = destination;		
	}

	public char[] getDestination() {
		return destination;
	}

	public void setDestination(char[] destination) {
		this.destination = destination;
	}

	public char[] getOrigin() {
		return origin;
	}

	public void setOrigin(char[] origin) {
		this.origin = origin;
	}
}
