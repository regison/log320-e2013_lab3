package interfaces;

import java.util.ArrayList;
import model.*;

/**
 * 
 * @author Regison
 *
 */
public interface IMoveGenerator {

	/**
	 * 
	 * @param m
	 * @return
	 */
	boolean isValid(Move m);
	/**
	 * 
	 * @param board
	 * @param start
	 * @param end
	 * @param type
	 * @return
	 */
	Piece[][] makeMove( Piece board[][], String start, String end, int type );
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
	ArrayList<Move> generatePossibleMoves(Piece[][]board, int type);
	/**
	 * 
	 * @param board
	 * @return
	 */
	ArrayList<Move> generatePossibleCaptures(Piece [][]board, int type );
	
	int calculateDistance(int origin, int destination, int direction);
	int getNumberOfPieceByDirection(Piece piece, int direction, Piece[][] board);
	
}
