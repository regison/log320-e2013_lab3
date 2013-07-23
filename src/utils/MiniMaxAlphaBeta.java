package utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import constants.LOAConstants;
import model.Move;
import model.Piece;

public class MiniMaxAlphaBeta {

	private int playerColor;
	private int oppositePlayerColor;
	private int profondeurMax;
	MoveGenerator m = new MoveGenerator();
	
	// objects for the timer
	private int interval;
	private Timer timer;
	private boolean timerDone;
	
	public MiniMaxAlphaBeta(){
		// create the item
	}
	
	// calculate the best move for the current board and current player
	public Move calculateBestMove(Piece[][] originalBoard, int playerColor, int profondeurMax)
	{
		Piece[][] currentBoard = originalBoard;
		//Piece[][] currentBoard = new Piece[LOAConstants.BOARD_SIZE_WIDTH][LOAConstants.BOARD_SIZE_HEIGHT];
		
		this.playerColor = playerColor;
		this.profondeurMax = profondeurMax;
		
		// put opposite color
		if(playerColor == LOAConstants.PIECE_TYPE_BLACK)
			this.oppositePlayerColor = LOAConstants.PIECE_TYPE_WHITE;
		else
			this.oppositePlayerColor = LOAConstants.PIECE_TYPE_BLACK;
		
		//double alpha = alphaValue(currentBoard, null, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		
		// timer
		this.timerDone = false;
		int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    this.interval = 5;
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	        	//System.out.println(interval);
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
				/*if(moves.get(i).getWeight() == (int)alpha)
				{
					return moves.get(i);
				}*/
			
				newCurrentBoard = new Piece[LOAConstants.BOARD_SIZE_HEIGHT][LOAConstants.BOARD_SIZE_WIDTH];
				for(int k = 0; k < currentBoard.length; k++) {
					  for(int j = 0; j < currentBoard[k].length; j++) {
						  newCurrentBoard[k][j] = currentBoard[k][j].clone();
					  }
				}
			
				modifiedBoard = new Piece[LOAConstants.BOARD_SIZE_HEIGHT][LOAConstants.BOARD_SIZE_WIDTH];
				modifiedBoard = m.makeMove(newCurrentBoard, moves.get(i), playerColor);
				
				/*for(int j = 0; j < LOAConstants.BOARD_SIZE_HEIGHT; j++)
				{
					for(int k = 0; k < LOAConstants.BOARD_SIZE_WIDTH; k++)
					{
						System.out.print(newCurrentBoard[j][k].getType());
					}
					System.out.println("");
				}
				System.out.println("******");*/
				
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
			return currentMove.getWeight();
			//return m.generatePossibleMoves(board, playerColor).get(0).getWeight();
		////
		
		/*double v = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(board[i][j].getType() == playerColor)
				{
					MoveGenerator m = new MoveGenerator();
					ArrayList<Move> moves = m.generatePossibleMoves(board, playerColor);
					
					for(int k = 0; k < moves.size(); k++)
					{
						Move currentMove = moves.get(k);
						if(currentMove.getOrigin() == board[i][j].getLocation())
						{
							if(currentMove.getWeight() > this.bestMove.getWeight())
								this.bestMove = currentMove;
							
							board = m.makeMove(board, currentMove, playerColor);
							v = Math.max(v, betaValue(board, alpha, beta));
							if(v >= beta)
								return v;
							alpha = Math.max(v, alpha);
						}
					}
				}
			}
		}*/
		
		double v = Double.NEGATIVE_INFINITY;
		Piece[][] childBoard;
		for(int i = 0; i < moves.size(); i++)
		{
			/*System.out.println("destination:"+moves.get(i).getDestination());
			System.out.println("distance:"+moves.get(i).getDistance());
			System.out.println("origin:"+moves.get(i).getOrigin());*/
			//System.out.println("alpha weight:"+moves.get(i).getWeight() + " " + (profondeur+1) + " " + moves.get(i).getOrigin() + moves.get(i).getDestination());
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
			return currentMove.getWeight();
		////
		
		/*double v = Double.POSITIVE_INFINITY;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(board[i][j].getType() == playerColor)
				{
					MoveGenerator m = new MoveGenerator();
					ArrayList<Move> moves = m.generatePossibleMoves(board, playerColor);
					
					for(int k = 0; k < moves.size(); k++)
					{
						Move currentMove = moves.get(k);
						if(currentMove.getOrigin() == board[i][j].getLocation())
						{
							if(currentMove.getWeight() > this.bestMove.getWeight())
								this.bestMove = currentMove;
							
							board = m.makeMove(board, currentMove, playerColor);
							v = Math.min(v, alphaValue(board, alpha, beta));
							if(v <= alpha)
								return v;
							beta = Math.min(v, beta);
						}
					}
				}
			}
		}*/
		
		double v = Double.POSITIVE_INFINITY;
		Piece[][] childBoard;
		for(int i = 0; i < moves.size(); i++)
		{
			//System.out.println("beta weight:"+moves.get(i).getWeight() + " " + (profondeur+1) + " " + moves.get(i).getOrigin() + moves.get(i).getDestination());
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
