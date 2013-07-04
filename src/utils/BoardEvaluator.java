package utils;

import constants.LOAConstants;
import model.Piece;
import interfaces.IEvaluator;

public class BoardEvaluator{

	public int evalutate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPieceCount(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numberOfConnectedPiece(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int concentration(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* This function returns the weight of a piece from his location
	 * (non-Javadoc)
	 * @see interfaces.IEvaluator#centralisation(model.Piece[][], java.lang.String, java.lang.String)
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

	public int centreDeMass(int pieceColor) {
		// TODO Auto-generated method stub
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

}
