package utils;

import constants.LOAConstants;
import model.Piece;
import interfaces.IEvaluator;

public class BoardEvaluator implements IEvaluator {

	@Override
	public int evalutate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPieceCount(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numberOfConnectedPiece(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int concentration(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* This function returns the weight of a piece from his location
	 * (non-Javadoc)
	 * @see interfaces.IEvaluator#centralisation(model.Piece[][], java.lang.String, java.lang.String)
	 */
	public int centralisation(String row, String colunm) { 
		if (colunm.equals("A") || colunm.equals("H") || row.equals("0") || row.equals("7")  )
			return LOAConstants.WEIGHT_ROW0_AND_7_COLUMNA_AND_H;
		else if (colunm.equals("B") || colunm.equals("G") || row.equals("1") || row.equals("6")  )
			return LOAConstants.WEIGHT_ROW1_AND_6_COLUMNB_AND_G;
		else if (colunm.equals("C") || colunm.equals("F") || row.equals("2") || row.equals("5")  )
			return LOAConstants.WEIGHT_ROW2_AND_5_COLUMNC_AND_F;
		else
			return LOAConstants.WEIGHT_ROW3_AND_4_COLUMND_AND_E;		
	}

	@Override
	public int centreDeMass(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int quads() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int mobility(int pieceColor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int quadHeuristic() {
		// TODO Auto-generated method stub
		return 0;
	}

}
