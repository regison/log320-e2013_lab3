package model;

import constants.LOAConstants;

/**
 * Classe qui repr�sentera une pi�ce sur le jeu.
 * Trois types coexistent = BLACK, WHITE, NULL
 * @author Regison
 * @Start Date : 22 juin 2012
 * @End Date   : 22 juin 2012
 */
public class Piece {

	private String location;
	private int type;
	private int weight;
	
	public Piece(String location , int type){
		if(type != LOAConstants.PIECE_TYPE_BLACK && type != LOAConstants.PIECE_TYPE_WHITE && type != LOAConstants.PIECE_TYPE_NULL)
			throw new IllegalArgumentException("This type doesn't exist");
		
		this.type = type;
		this.location = location;
	
	}
	public Piece(String location, int type, int weight){
		if(type != LOAConstants.PIECE_TYPE_BLACK && type != LOAConstants.PIECE_TYPE_WHITE && type != LOAConstants.PIECE_TYPE_NULL)
			throw new IllegalArgumentException("This type doesn't exist");
		
		this.type = type;
		this.location = location;
		this.weight = weight;
	}
	
	public Piece clone() {
		Piece o = new Piece(this.location, this.type, this.weight);
		return o;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}