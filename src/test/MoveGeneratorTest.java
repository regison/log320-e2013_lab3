package test;

import java.util.ArrayList;

import model.LineOfActionBoard;
import model.Move;
import model.Piece;

import org.junit.Test;

import constants.LOAConstants;

import utils.MoveGenerator;

public class MoveGeneratorTest {

	public MoveGeneratorTest() {
	}

	@Test
	public void test() {
		LineOfActionBoard b = new LineOfActionBoard();
		MoveGenerator m = new MoveGenerator();
		Piece p = b.getPiece("H3");
		
		//System.out.print(m.getNumberOfPieceByDirection(p, LOAConstants.LEFT, b.getBoard()));
		
		ArrayList<Move> moves = m.generatePossibleMoves(b.getBoard(), LOAConstants.PIECE_TYPE_BLACK);
		 moves.addAll(m.generatePossibleMoves(b.getBoard(), LOAConstants.PIECE_TYPE_WHITE));
		System.out.println(moves.size());
		
		
	}

}
