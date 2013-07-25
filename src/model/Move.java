package model;

/*This class define a move. This class contains getters, setters and information relative to a move like
 * get/set the origin of the move, the destination, the distance and the weight*/
public class Move {
	private String destination;
	private String origin;
	private int distance;
	private int weight;
	
	
	public Move(String origin, String destination, int distance, int weight){
		this.origin = origin;
		this.destination = destination;	
		this.distance = distance;
		this.weight = weight;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
