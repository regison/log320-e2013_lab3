package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import constants.LOAConstants;

public class GameVisitedList {

	private int index=0;
	private int size = 0;
	private List<Piece> elements;
	
	public GameVisitedList(int size){
		this.size = size;
		this.elements = new ArrayList<Piece>();
	}
	
	public List<Piece> addPiece(Piece p){
		if (p != null && p.getType() != LOAConstants.PIECE_TYPE_NULL){
			if (elements != null){
				elements.add(p);				
				
				return elements;
				}
			}
		return null;
	}
	
	
	
}
