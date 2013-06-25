package interfaces;

import java.util.ArrayList;

import model.*;

public interface IMoveGenerator {

	/**
	 * 
	 * @param m
	 * @return
	 */
	boolean isValid(Move m);
	/**
	 * 
	 * @param p
	 * @return
	 */
	Move makeMove(Piece p);
	/**
	 * 
	 * @param p
	 * @return
	 */
	Move makeCaptureMove(Piece p);
	/**
	 * 
	 * @param board
	 * @return
	 */
	ArrayList<Move> generatePossibleMoves(int[][]board);
	/**
	 * 
	 * @param board
	 * @return
	 */
	ArrayList<Move> generatePossibleCaptures(int [][]board);
	
}
