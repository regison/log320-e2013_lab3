package test;

import static org.junit.Assert.*;

import model.LineOfActionBoard;
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
		System.out.print(m.getNumberOfPieceByDirection(p, LOAConstants.LEFT, b.getBoard()));
		
	}

}
