package model;

public class Move {
	private char[] destination;
	private char[] origin;
	private int distance;
	
	
	public Move(char[] origin, char [] destination, int distance){
		this.origin = origin;
		this.destination = destination;	
		this.distance = distance;
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
