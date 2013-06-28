package utils;

import java.util.ArrayList;

import constants.LOAConstants;

import model.Move;
import model.Piece;
import interfaces.IMoveGenerator;

public class MoveGenerator implements IMoveGenerator {

	public ArrayList<Integer> horizontalMoves = new ArrayList<Integer>();
	public ArrayList<Integer> verticalMoves = new ArrayList<Integer>();
	public ArrayList<Integer> diagonal135Moves = new ArrayList<Integer>();
	public ArrayList<Integer> diagonal45Moves = new ArrayList<Integer>();
	
	@Override
	public boolean isValid(Move m) {
		if (m.getDestination()[0] > LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE || 
				m.getDestination()[0] < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE)
			return true;
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see interfaces.IMoveGenerator#makeMove(model.Piece[][], java.lang.String, java.lang.String, int)
	 */
	public Piece[][] makeMove(Piece board[][], String start, String end, int type) {
		
		int rowStart = Helpers.convertRowChartoInt(start.charAt(1));
		int colunmStart = Helpers.convertRowChartoInt(start.charAt(0));
		
		int rowEnd = Helpers.convertRowChartoInt(end.charAt(1));
		int colunmEnd = Helpers.convertColunmCharToInt(end.charAt(0));
		
		board[rowEnd][colunmEnd].setType( type );
		
		board[rowStart][colunmStart].setType(  LOAConstants.PIECE_TYPE_NULL );
		
		return board;
	}

	@Override
	public Move makeCaptureMove(Piece p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Move> generatePossibleMoves(Piece[][] board, int type) {
		// TODO Auto-generated method stub
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i< board.length; i++)
			for(int j=0; j<board[i].length; j++){	
				if(board[i][j].getType() == type){
					//
				}
					
			}
		return moves;
	}

	@Override
	public ArrayList<Move> generatePossibleCaptures(Piece[][] board, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateDistance(int origin, int destination, int direction) {
		// TODO Auto-generated method stub
		if (direction == LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE || direction == LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE)		
			return (int) Math.sqrt( ( Math.pow( origin, 2 ) + Math.pow( destination, 2 ) ) );		
		
		return (destination - origin);		
	}


	/**
	 * This function will get the number of pieces in the specific
	 * direction gave by the the current piece
	 */
	public int getNumberOfPieceByDirection(Piece piece, int direction, Piece[][]board) {
	
		int counter=0;	
		
		//Get the position of the current piece
		int currentColunm = (int)piece.getLocation().charAt(0) - 65;
		int currentRow = (int)piece.getLocation().charAt(1) - 48;
		
		//Variable for the diagonal
		int startX = 0, startY = 0;
		
		if (board[currentRow][currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL){
		
			switch( direction ){
			
			case LOAConstants.RIGHT: // means that we decrease the colunm and check if there any piece
				while (currentColunm < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE - 1 ){
					if(board[currentRow][++currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL)					
						counter++;				
				}			
				break;
				
			case LOAConstants.LEFT: // means that we increase the colunm and check if there is any piece
				while (currentColunm > LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE - 1){
					if(board[currentRow][--currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL)
						counter++;
				}
				break;
				
			case LOAConstants.DOWN:// means that we decrease the row and check if there is any piece
				while (currentRow < LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE ){
					if(board[currentRow--][currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL)
						counter++;
				}
				break;
				
			case LOAConstants.UP:// means that we increase the row and check if there is any piece
				while (currentRow < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE ){
					if(board[currentRow++][currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL)
						counter++;
				}
				break;
				
			case LOAConstants.UPPER_RIGHT:
				startX = currentColunm;
				startY = currentRow;
				while(startX < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE &&
					  startY < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE){
					if(board[startX++][startY++].getType() != LOAConstants.PIECE_TYPE_NULL)
					counter++;
				}
				break;
				
			case LOAConstants.UPPER_LEFT:
				startX = currentColunm;
				startY = currentRow;
				while(startX > LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE &&
					  startY < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE){
					if(board[startX--][startY++].getType() != LOAConstants.PIECE_TYPE_NULL)
					counter++;
				}			
				break;
				
			case LOAConstants.BOTTOM_LEFT:
				startX = currentColunm;
				startY = currentRow;
				while(startX > LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE &&
					  startY > LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE){
					if(board[startX--][startY--].getType() != LOAConstants.PIECE_TYPE_NULL)
					counter++;
				}
				break;
				
			case LOAConstants.BOTTOM_RIGHT:
				startX = currentColunm;
				startY = currentRow;
				while(startX < LOAConstants.BOARD_RIGHT_AND_UPPER_SIDE &&
					  startY > LOAConstants.BOARD_LEFT_AND_BOTTOM_SIDE){
					if(board[startX++][startY--].getType() != LOAConstants.PIECE_TYPE_NULL)
					counter++;
				}
				break;				
			}			
		}
		return counter;
	}
}
