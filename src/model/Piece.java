package model;

import constants.LOAConstants;

/**
 * Classe qui représentera une pièce sur le jeu.
 * Trois types coexistent = BLACK, WHITE, NULL
 * @author Regison
 * @Start Date : 22 juin 2012
 * @End Date   : 22 juin 2012
 */
public class Piece {

	public char[] location;
	public int type;
	
	public Piece(char[] location , int type){
		if(type != LOAConstants.PIECE_TYPE_BLACK && type != LOAConstants.PIECE_TYPE_WHITE)
			throw new IllegalArgumentException("This type doesn't exist");
		
		this.type = type;
		this.location = location;
	}

	public char[] getLocation() {
		return location;
	}

	public void setLocation(char[] location) {
		this.location = location;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}