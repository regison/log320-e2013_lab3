package utils;

import constants.LOAConstants;
import model.Piece;
import interfaces.IEvaluator;

/*This class goes with the MiniMaxAlphaBeta class. It evaluates the scores for each move.
 * This class also determine which player win the game (all pieces are connected together)*/
public class BoardEvaluator{

	public int evalutate(Piece [][] board, int pieceColor) {
		
		int boardValue = 0;
		
		double amountCurrentPlayerAtWall = wallConcentration(board, pieceColor);
		double amountOtherPlayerAtWall = wallConcentration(board, Helpers.getOtherPlayerType(pieceColor));
		
		double pieceAmountForCurrentPlayer = concentration(board, pieceColor);
		double pieceAmountForOtherPlayer = concentration(board, Helpers.getOtherPlayerType(pieceColor));
		
		double pieceCurrentPlayerConnectedAmount = getNumberConnectedPiece(board, pieceColor);
		double pieceOtherPlayerConnectedAmount = getNumberConnectedPiece(board, Helpers.getOtherPlayerType(pieceColor));
		
		double ratioCurrentPiece = (pieceCurrentPlayerConnectedAmount / LOAConstants.MAX_WHITE_PIECES);
		double ratioOtherPiece = (pieceOtherPlayerConnectedAmount / LOAConstants.MAX_BLACK_PIECES);
		
		boardValue += (int) (ratioCurrentPiece - ratioOtherPiece);
		boardValue += (int) (pieceAmountForCurrentPlayer - pieceAmountForOtherPlayer);
		boardValue += ((LOAConstants.MAX_WHITE_PIECES - LOAConstants.MAX_BLACK_PIECES) * 500);
		boardValue += (amountCurrentPlayerAtWall - amountOtherPlayerAtWall) * 300;
		
		if (pieceColor == LOAConstants.PIECE_TYPE_BLACK)
			boardValue -= (amountCurrentPlayerAtWall * 800);
		else
			boardValue += (amountCurrentPlayerAtWall * 800);
		
			
		return boardValue;
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
	
	public boolean isPieceAtWall(int i, int j){
		if (i == LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE || i == LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE ||
			j == LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE || j == LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE)
			return true;
		
		return false;			
	}
	public int wallConcentration(Piece[][] board, int pieceColor){
		int counter = 0;
		for(int i=0; i< board.length; i++){
			for(int j=0; j< board.length; j++){
				if (board[i][j].getType() == pieceColor && isPieceAtWall(i, j))
					counter++;
			}
		}
		
		return counter;
	}
	public int getNumberConnectedPiece(Piece[][] board, int pieceColor){
		
		int numberOfConnection = 0;
		
		if (pieceColor == LOAConstants.PIECE_TYPE_WHITE){
			numberOfConnection = Integer.MAX_VALUE;
		
			for(int i=0; i< board.length; i++){
				for(int j=0; j< board.length; j++){
					if (board[i][j].getType() == pieceColor)
						numberOfConnection = Math.max(numberOfConnection, numberOfConnectedPiece(i, j, board, pieceColor));
				}
			}
		}
		else if (pieceColor == LOAConstants.PIECE_TYPE_BLACK){
			numberOfConnection = Integer.MIN_VALUE;
			
			for(int i=0; i< board.length; i++){
				for(int j=0; j< board.length; j++){
					if (board[i][j].getType() == pieceColor)
						numberOfConnection = Math.min(numberOfConnection, numberOfConnectedPiece(i, j, board, pieceColor));
				}
			}
		}
		else throw new IllegalArgumentException("Wrong color");
			
		return numberOfConnection;
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
	//Center of the board
	public int concentration(Piece[][] board, int pieceColor) {
		int counter = 0;		
		 for(int i = 0; i < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE; i++){
		  for(int j = 0; j < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE; j++){
			 if (board[i][j].getType() == pieceColor)
				 counter++;
			}
		 }
		 
		return counter;
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

	public boolean isInCentreOfMass(int row, int colunm){
		if(row >= 2 && row <= 5 &&  colunm >= 2 && colunm <= 5)
			return true;
		
		return false;
	}
	
	public int centreOfMass(Piece[][] board, int pieceColor) {

		
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
