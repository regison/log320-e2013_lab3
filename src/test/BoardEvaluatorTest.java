package test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import model.LineOfActionBoard;

import org.junit.Test;

import utils.BoardEvaluator;

public class BoardEvaluatorTest extends TestCase{

	public BoardEvaluatorTest() {
		
	}

	@Test
	public void test() {
		LineOfActionBoard board = new LineOfActionBoard();
		BoardEvaluator eval = new BoardEvaluator();
		System.out.println(eval.evalutate(board.getBoard()));
	}

}
