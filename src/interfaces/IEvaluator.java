package interfaces;

import model.Piece;

public interface IEvaluator {
	
	int evalutate();
	int quadsHeuristic(Piece[][] quads,Piece[][]board, int type);
	int getPieceCount (Piece[][] board, int pieceColor);
	int numberOfConnectedPiece(int row, int colunm, Piece[][] board, int pieceColor);
	boolean isConnectedPiece(int direction, int row, int colunm, Piece[][]board, int pieceColor);
	
	//The concentration of the pieces is calculated by a center-of-mass approach
	/**
	 *  First, the center of mass of the pieces on the board is 
	 *  computed for each side. Second, we compute for each piece its distance to the center of mass. 
	 *  The distance is measured as the minimal number of squares from the piece to the centre of mass. 
	 *  These distances are summed together, called the sum-of-distances. Third, the sum-of-minimal-distances 
	 *  is looked up in a table.It is defined as the sum of the minimal distances of the pieces from the center of mass. This number is necessary 
	 *  since otherwise boards with a few pieces would be preferred.
	 *  Fourth, we calculate the concentration,defined as the inverse of the average surplus-of-distances.
	 * 	@param pieceColor
	 * 	@return
	 */
	int concentration(Piece[][] board, int pieceColor);
	
	int centralisation(int row, int colunm);
	int centreDeMass(Piece [][] board, int pieceColor);
	
	int mobility(int pieceColor);
	boolean isGameEnded(Piece [][] board, int pieceColor);
	
}
