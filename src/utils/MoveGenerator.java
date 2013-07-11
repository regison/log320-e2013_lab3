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

	private ArrayList<Integer> possibleDirections =  new ArrayList<Integer>();
	private ArrayList<Move> possibleCaptures = new ArrayList<Move>();

	public MoveGenerator(){
		possibleDirections.add(LOAConstants.RIGHT);
		possibleDirections.add(LOAConstants.LEFT);		
		possibleDirections.add(LOAConstants.DOWN);
		possibleDirections.add(LOAConstants.UP);		
		possibleDirections.add(LOAConstants.UPPER_RIGHT);
		possibleDirections.add(LOAConstants.BOTTOM_LEFT);	
		possibleDirections.add(LOAConstants.BOTTOM_RIGHT);
		possibleDirections.add(LOAConstants.UPPER_LEFT);				
	}

	/*
	 * (non-Javadoc)
	 * @see interfaces.IMoveGenerator#isValid(model.Move)
	 */
	public boolean isMoveValid(Move m, int color, Piece[][] board) {		
		if (m != null){
			
			int i = Helpers.convertRowChartoInt(m.getDestination().charAt(1));
			int j = Helpers.convertColunmCharToInt(m.getDestination().charAt(0));
			
			if (( i > LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE || i <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE) || 
					( j > LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE || j <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE  ) &&
					board[i][j].getType() != color && m.getOrigin() != (m.getDestination())){
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see interfaces.IMoveGenerator#makeMove(model.Piece[][], java.lang.String, java.lang.String, int)
	 */
	public Piece[][] makeMove(Piece board[][], Move move, int type) {

		int rowStart = Helpers.convertRowChartoInt(move.getOrigin().charAt(1));
		int colunmStart = Helpers.convertRowChartoInt(move.getOrigin().charAt(0));

		int rowEnd = Helpers.convertRowChartoInt(move.getDestination().charAt(1));
		int colunmEnd = Helpers.convertColunmCharToInt(move.getDestination().charAt(0));

		board[rowEnd][colunmEnd].setType( type );

		board[rowStart][colunmStart].setType(  LOAConstants.PIECE_TYPE_NULL );

		return board;
	}

	@Override
	public ArrayList<Move> generatePossibleMoves(Piece[][] board, int type) {
		// TODO Auto-generated method stub
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int i = 0; i<= board.length -1 ; i++)
			for(int j=0; j<= board.length -1 ; j++){	
				if(board[i][j].getType() == type){
					for( int direction : possibleDirections ){
						Move move = createMoveByDirection( board, i, j, direction, getNumberOfPieceByActionLine( i, j, direction, board ), type );
						if ( isMoveValid( move, type, board ) )
							moves.add( move );
					}
				}					
			}
		return moves;
	}

	@Override
	public ArrayList<Move> generatePossibleCaptures(Piece[][] board, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	public Move createMoveByDirection( Piece [][] board, int i, int j, int direction, int distance, int playerColor ){

		int currentColunm = j;
		int currentRow =  i ;
		switch(direction){

		case LOAConstants.RIGHT: // means that we decrease the colunm and check if there any piece		
			j += distance ;						
			break;									

		case LOAConstants.LEFT: // means that we increase the colunm and check if there is any piece
			j -= distance;			
			break;						

		case LOAConstants.DOWN:// means that we decrease the row and check if there is any piece			
			i -= distance;
			break;

		case LOAConstants.UP:// means that we increase the row and check if there is any piece			
			i += distance;						
			break;
		case LOAConstants.UPPER_RIGHT:
			j += distance;
			i += distance;
			break;

		case LOAConstants.UPPER_LEFT:
			j -= distance;
			i += distance;
			break;

		case LOAConstants.BOTTOM_LEFT:
			j -= distance;
			i -= distance;
			break;

		case LOAConstants.BOTTOM_RIGHT:
			j += distance;
			i -= distance;
			break;
		}

		if (  (i >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE && i <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1)  &&
		   (  j >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE && j <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1 )  ){			
			
			int weight = BoardEvaluator.centralisation(i, j);

			return new Move ( Helpers.convertColunmIntToLetter( currentColunm ) + String.valueOf( 8 - currentRow ), 
					Helpers.convertColunmIntToLetter( j ) + String.valueOf( 8 - i ), 
					distance, weight );
		}
		else if ( ( i > LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE || i < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1 ) ||
				 ( j > LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE || j < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1 ) &&
				board[i][j].getType() == Helpers.getOtherPlayerType(playerColor))
			return null;

		return null;
	}
	

	/**
	 * This function will get the number of pieces in the specific
	 * direction gave by the the current piece
	 */
	public int getNumberOfPieceByActionLine(int row, int colunm, int direction, Piece[][]board) {

		int counter=0;	

		//Get the position of the current piece
		int currentColunm = colunm;
		int currentRow = row ;
		
		//Variable for the diagonal
		int startX = 0, startY = 0;

		if (board[currentRow][currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL){

			switch( direction ){

			case LOAConstants.RIGHT: // means that we decrease the colunm and check if there any piece
			case LOAConstants.LEFT: // means that we increase the colunm and check if there is any piece
				while (startY < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1 ){
					if( board[currentRow][startY++].getType() != LOAConstants.PIECE_TYPE_NULL )
						counter++;				
				}			
				break;			

			case LOAConstants.DOWN:// means that we decrease the row and check if there is any piece
			case LOAConstants.UP:// means that we increase the row and check if there is any piece
				while (startX <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1){
					if( board[startX++][currentColunm].getType() != LOAConstants.PIECE_TYPE_NULL ) 
						counter++;
				}
				break;			

			case LOAConstants.UPPER_RIGHT:
			case LOAConstants.BOTTOM_LEFT:
				startY = currentColunm - (Math.min(currentColunm, currentRow));
				startX = currentRow - (Math.min(currentColunm, currentRow));
				
				while( startY < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE   &&
						startX < LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE   &&
						startY >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE &&
					   startX >= LOAConstants.BOARD_UPPER_AND_BOTTOM_SIDE){
					if(board[startX++][startY++].getType() != LOAConstants.PIECE_TYPE_NULL )
						counter++;
				
				}
				break;

			case LOAConstants.BOTTOM_RIGHT:
			case LOAConstants.UPPER_LEFT:				
				startY = currentColunm - (Math.min(currentColunm, currentRow));
				startX = currentRow - (Math.min(currentColunm, currentRow));				
				
				while( startY <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1 && 
					   startX <= LOAConstants.BOARD_RIGHT_AND_LEFT_SIDE - 1){

					if(board[startX++][startY++].getType() != LOAConstants.PIECE_TYPE_NULL )							
						counter++;
				 }				
				break;
				
			}			
		}
		return counter;
	}
}
