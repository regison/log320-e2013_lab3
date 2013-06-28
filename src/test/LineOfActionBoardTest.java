package test;


import junit.framework.TestCase;
import model.LineOfActionBoard;

import org.junit.Test;

public class LineOfActionBoardTest extends TestCase {

	public LineOfActionBoardTest() {
	}

	@Test
	public void test() {
		
		LineOfActionBoard board = new LineOfActionBoard();
		board.printBoard();
	}

}
