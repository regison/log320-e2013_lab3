package utils;

import java.util.ArrayList;

import model.Move;
import model.Piece;
import interfaces.IMoveGenerator;

public class MoveGenerator implements IMoveGenerator {

	@Override
	public boolean isValid(Move m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Move makeMove(Piece p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move makeCaptureMove(Piece p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Move> generatePossibleMoves(int[][] board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Move> generatePossibleCaptures(int[][] board) {
		// TODO Auto-generated method stub
		return null;
	}

}
