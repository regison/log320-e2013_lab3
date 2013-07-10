package utils;

import constants.LOAConstants;
import model.Piece;
import interfaces.IEvaluator;

public class BoardEvaluator{

	public int evalutate(Piece [][] board) {
		
		
		// TODO Auto-generated method stub
		
		return 0;
	}

	public int getPieceCount(Piece[][] board, int pieceColor) {
		int counter = 0;
		
		for(int i=0; i< board.length; i++)
			for(int j=0; j< board.length; j++){
				if (board[i][j].getType() == pieceColor)
					counter++;
			}
		return counter;
	}
	public int numberOfConnectedPiece(int row, int colunm, Piece[][] board, int pieceColor) {

		int connectedCounter = 0;				
		
		//right		
		if (isConnectedPiece(LOAConstants.RIGHT, row, colunm, board, pieceColor)){
			connectedCounter++;
			connectedCounter += numberOfConnectedPiece(row, colunm + 1, board, pieceColor);
		}		
		//up-right
		
		if (isConnectedPiece(LOAConstants.UPPER_RIGHT, row + 1, colunm + 1, board, pieceColor)){
			connectedCounter++;			
		}		
		//down-right
		if (isConnectedPiece(LOAConstants.BOTTOM_RIGHT, row - 1, colunm + 1, board, pieceColor)){
			connectedCounter++;			
		}		
		//left
		if(isConnectedPiece(LOAConstants.LEFT, row, colunm - 1, board, pieceColor)){
			connectedCounter++;
		}		
		//down-left		
		if (isConnectedPiece(LOAConstants.BOTTOM_LEFT, row - 1, colunm - 1, board, pieceColor)){
			connectedCounter++;
		}
		//up-left
		if (isConnectedPiece(LOAConstants.UPPER_LEFT, row + 1, colunm - 1, board, pieceColor)){
			connectedCounter++;			
		}	  
		//up
		if (isConnectedPiece(LOAConstants.UP, row + 1, colunm, board, pieceColor)){
			connectedCounter++;			
		}
		//down
		if (isConnectedPiece(LOAConstants.UP, row - 1, colunm, board, pieceColor)){
			connectedCounter++;			
		} 
		return connectedCounter;
	}
	public boolean isConnectedPiece(int direction, int row, int colunm, Piece[][]board, int pieceColor){
		
		if(direction == LOAConstants.DOWN && row - 1 >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE){
			if (board[row - 1][colunm].getType() == pieceColor )
				return true;
		}
		if( direction == LOAConstants.BOTTOM_RIGHT && row - 1 >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE 
			&& colunm + 1 < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE){
			if (board[row - 1][colunm + 1].getType() == pieceColor )
				return true;
		}
		if( direction == LOAConstants.BOTTOM_LEFT && row - 1 >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE
		&& colunm - 1 >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE){
			if (board[row - 1][colunm - 1].getType() == pieceColor )
				return true;
		}
		if( direction == LOAConstants.UP && row + 1 < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE){
			if (board[row + 1][colunm].getType() == pieceColor )
				return true;
		}
		if( direction == LOAConstants.UPPER_RIGHT && row + 1 < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE
		&& colunm + 1 < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE){
			if (board[row + 1][colunm + 1].getType() == pieceColor )
				return true;
		}
		if( direction == LOAConstants.UPPER_LEFT && row + 1 < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE
		&& colunm - 1 >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE){
			if (board[row + 1][colunm - 1].getType() == pieceColor )
				return true;
		}
		
		if( direction == LOAConstants.RIGHT && colunm + 1 < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE){
			if (board[row][colunm + 1].getType() == pieceColor )
				return true;
		}
		if( direction == LOAConstants.LEFT && colunm - 1 >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE){
			if (board[row][colunm - 1].getType() == pieceColor )
				return true;
		}		
		return false;
	}

	public int concentration(Piece[][] board, int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* 
	 * (non-Javadoc)
	 * @see interfaces.IEvaluator#centralisation(int, int)
	 */
	public static int centralisation(int row, int colunm) { 
		if (colunm == 0 || colunm == 7 || row == 0 || row == 7  )
			return LOAConstants.WEIGHT_ROW0_AND_7_COLUMNA_AND_H;
		else if (colunm == 1 || colunm == 6 || row == 1 || row == 6  )
			return LOAConstants.WEIGHT_ROW1_AND_6_COLUMNB_AND_G;
		else if (colunm == 2 || colunm == 5 || row == 2 || row == 5  )
			return LOAConstants.WEIGHT_ROW2_AND_5_COLUMNC_AND_F;
		else
			return LOAConstants.WEIGHT_ROW3_AND_4_COLUMND_AND_E;		
	}

	public int centreDeMass(Piece[][] board, int pieceColor) {

		
		return 0;
	}

	public int mobility(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}	

	public int quadsHeuristic(Piece[][] quads, Piece[][] board, int type) {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	public boolean isGameEnded(Piece [][] board, int pieceColor){
		
		int numberOfPiece = getPieceCount(board, pieceColor);
		
		return false;
	}

}
