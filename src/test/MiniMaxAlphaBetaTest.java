package test;

import org.junit.Test;

import constants.LOAConstants;

import utils.MiniMaxAlphaBeta;
import utils.MoveGenerator;

import model.LineOfActionBoard;
import model.Move;

public class MiniMaxAlphaBetaTest {

	public MiniMaxAlphaBetaTest() {
	}

	@Test
	public void test() {
		LineOfActionBoard b = new LineOfActionBoard();
		MoveGenerator m = new MoveGenerator();
		MiniMaxAlphaBeta ab = new MiniMaxAlphaBeta();
		
		Move move = ab.calculateBestMove(b.getBoard(), LOAConstants.PIECE_TYPE_BLACK, 8);
		System.out.println("-- Best move --");
		System.out.println("Weight: "+move.getWeight());
		System.out.println("Origin: "+move.getOrigin());
		System.out.println("Destination: "+move.getDestination());
		System.out.println("Distance: " + move.getDistance());
		
	}

}
