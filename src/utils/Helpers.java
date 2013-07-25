package utils;

import constants.LOAConstants;
import model.*;

/*This class contains useful methods for the program in general*/
public class Helpers {

	public static int convertColunmCharToInt(char colunm){
		return colunm - 65;
	}
	public static char convertColunmIntToLetter(int num){
		return (char) (num + 65);
	}
	public static int convertRowChartoInt(char row){
		return row - 48;
	}
	
	public static Piece getPieceInDestination(Piece[][] board, Move m, int numberOfPieceInLineAction, int direction){
		
		if(numberOfPieceInLineAction != 0){
			if (validatePieceOnDirection(board, m, numberOfPieceInLineAction, direction))
				return new Piece(m.getDestination(),board[m.getOrigin().charAt(1)][m.getOrigin().charAt(0)].getType());
		}
		return null;
	}
	public static int getOtherPlayerType(int type){
		if (type == LOAConstants.PIECE_TYPE_BLACK)
			return LOAConstants.PIECE_TYPE_WHITE;
		else
			return LOAConstants.PIECE_TYPE_BLACK;
	}
	private static boolean validatePieceOnDirection(Piece[][] board, Move m, int numberPieceLOA,int direction){
		
		int indiceParcours = (int)m.getOrigin().charAt(0);
		switch(direction){
		case LOAConstants.RIGHT:
			 while (indiceParcours != numberPieceLOA)
				 if (board[m.getOrigin().charAt(1)][m.getOrigin().charAt(0)].getType() != getOtherPlayerType(board[m.getOrigin().charAt(1)][m.getOrigin().charAt(0)].getType())){
					indiceParcours++; 
				 }
		case LOAConstants.LEFT:
		case LOAConstants.UP:
		case LOAConstants.DOWN:
		case LOAConstants.UPPER_LEFT:
		case LOAConstants.UPPER_RIGHT:
		case LOAConstants.BOTTOM_LEFT:
		case LOAConstants.BOTTOM_RIGHT:
		
	}
			
		return true;
	}
	
	
}
