package test;

import static org.junit.Assert.*;

import model.LineOfActionBoard;

import org.junit.Test;

public class LineOfActionBoardTest {

	public LineOfActionBoardTest() {
	}

	@Test
	public void test() {
		
		LineOfActionBoard board = new LineOfActionBoard();
		board.printBoard();
	}

}
