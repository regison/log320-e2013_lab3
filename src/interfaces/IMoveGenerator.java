package interfaces;

import java.util.ArrayList;
import model.*;

/**
 * This interface define all the functions for the move generator
 */
public interface IMoveGenerator {

	/**
	 * 
	 * @param m
	 * @return
	 */
	boolean isMoveValid(Move m, int color, Piece[][] board);
	/**
	 * 
	 * @param board
	 * @param move
	 * @param type
	 * @return
	 */
	Piece[][] makeMove( Piece board[][], Move move, int type );
	/**
	 * 
	 * @param board
	 * @param i
	 * @param j
	 * @param direction
	 * @param distance
	 * @param playerColor
	 * @return
	 */
	Move createMoveByDirection(Piece[][] board, int i, int j, int direction, int distance, int playerColor);
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
	int getNumberOfPieceByActionLine(int row, int colunm, int direction, Piece[][] board);
	
}
