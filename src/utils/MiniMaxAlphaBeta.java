package utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import constants.LOAConstants;
import model.Move;
import model.Piece;

/* This class is used to find the best move possible for a piece while
 * leaving the opponent with smaller chance to make a good move*/
public class MiniMaxAlphaBeta {

	private int playerColor;
	private int oppositePlayerColor;
	private int profondeurMax;
	MoveGenerator m = new MoveGenerator();
	BoardEvaluator boardEvaluator = new BoardEvaluator();
	
	// objects for the timer
	private int interval;
	private Timer timer;
	private boolean timerDone;
	
	// Constructor
	public MiniMaxAlphaBeta(){
		// create the item
	}
	
	// calculate the best move for the current board and current player
	public Move calculateBestMove(Piece[][] originalBoard, int playerColor, int profondeurMax)
	{
		Piece[][] currentBoard = originalBoard;
		
		this.playerColor = playerColor;
		this.profondeurMax = profondeurMax;
		
		// put opposite color
		if(playerColor == LOAConstants.PIECE_TYPE_BLACK)
			this.oppositePlayerColor = LOAConstants.PIECE_TYPE_WHITE;
		else
			this.oppositePlayerColor = LOAConstants.PIECE_TYPE_BLACK;
		
		// timer
		this.timerDone = false;
		int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    this.interval = 5;
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            setInterval();
	        }
	    }, delay, period);
	    
	    // start search
	    int bestMoveWeight = 0;
	    Move bestMove = null;
	    ArrayList<Move> moves = m.generatePossibleMoves(currentBoard, playerColor);
	    Piece[][] modifiedBoard;
	    Piece[][] newCurrentBoard;
	    double modifiedAlpha;
		for(int i = 0; i < moves.size(); i++)
		{
			if(!timerDone)
			{
				System.out.println("************* move: " + moves.get(i).getOrigin() + moves.get(i).getDestination());
			
				newCurrentBoard = new Piece[LOAConstants.BOARD_SIZE_HEIGHT][LOAConstants.BOARD_SIZE_WIDTH];
				for(int k = 0; k < currentBoard.length; k++) {
					  for(int j = 0; j < currentBoard[k].length; j++) {
						  newCurrentBoard[k][j] = currentBoard[k][j].clone();
					  }
				}
			
				modifiedBoard = new Piece[LOAConstants.BOARD_SIZE_HEIGHT][LOAConstants.BOARD_SIZE_WIDTH];
				modifiedBoard = m.makeMove(newCurrentBoard, moves.get(i), playerColor);
				
				modifiedAlpha = alphaValue(modifiedBoard, null, 
						Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
				
				if(modifiedAlpha > bestMoveWeight)
				{
					bestMove = moves.get(i);
					bestMoveWeight = (int)modifiedAlpha;
				}
			}
		}
		
		return bestMove;
	}
	
	private int setInterval() {
	    if (interval == 1)
	    {
	        timer.cancel();
	        this.timerDone = true;
	    }
	    return --interval;
	}
	
	private double alphaValue(Piece[][] board, Move currentMove, double alpha, double beta, int profondeur)
	{
		ArrayList<Move> moves = m.generatePossibleMoves(board, playerColor);
		
		// if game state is a leaf node
		if((moves.size() == 0 && moves != null) || profondeur == profondeurMax || timerDone)
			return boardEvaluator.evalutate(board, playerColor);
		
		double v = Double.NEGATIVE_INFINITY;
		Piece[][] childBoard;
		for(int i = 0; i < moves.size(); i++)
		{
			childBoard = new Piece[LOAConstants.BOARD_SIZE_HEIGHT][LOAConstants.BOARD_SIZE_WIDTH];
			childBoard = m.makeMove(board, moves.get(i), playerColor);
			
			v = Math.max(v, betaValue(childBoard, moves.get(i), alpha, beta, (profondeur+1)));
			if(v >= beta)
				return v;
			alpha = Math.max(v, alpha);
		}
		
		return v;
	}
	
	private double betaValue(Piece[][] board, Move currentMove, double alpha, double beta, int profondeur)
	{
		ArrayList<Move> moves = m.generatePossibleMoves(board, oppositePlayerColor);
		
		// if game state is a leaf node
		if((moves.size() == 0 && moves != null) || profondeur == profondeurMax || timerDone)
			return boardEvaluator.evalutate(board, playerColor);
		
		double v = Double.POSITIVE_INFINITY;
		Piece[][] childBoard;
		for(int i = 0; i < moves.size(); i++)
		{
			childBoard = new Piece[LOAConstants.BOARD_SIZE_HEIGHT][LOAConstants.BOARD_SIZE_WIDTH];
			childBoard = m.makeMove(board, moves.get(i), oppositePlayerColor);
			
			v = Math.min(v, alphaValue(childBoard, moves.get(i), alpha, beta, (profondeur+1)));
			if(alpha >= v)
				return v;
			beta = Math.min(v, beta);
		}
		
		return v;
	}
}
