package utils;
import java.util.ArrayList;

import constants.LOAConstants;
import model.Move;
import model.Piece;

public class MiniMaxAlphaBeta {

	private Piece[][] returnedValue;
	private int playerColor;
	private int oppositePlayerColor;
	private int profondeurMax;
	MoveGenerator m = new MoveGenerator();
	
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
		
		double alpha = alphaValue(currentBoard, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		
		ArrayList<Move> moves = m.generatePossibleMoves(currentBoard, playerColor);
		for(int i = 0; i < moves.size(); i++)
		{
			if(moves.get(i).getWeight() == (int)alpha)
			{
				return moves.get(i);
			}
		}
		
		return null;
	}
	
	public Piece[][] getNewBoard()
	{
		return this.returnedValue;
	}
	
	private double alphaValue(Piece[][] board, double alpha, double beta, int profondeur)
	{
		ArrayList<Move> moves = m.generatePossibleMoves(board, playerColor);
		
		// if game state is a leaf node
		if(moves.size() == 0 || profondeur == profondeurMax)
			return alpha;
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
		for(int i = 0; i < moves.size(); i++)
		{
			System.out.println("destination:"+moves.get(i).getDestination());
			System.out.println("distance:"+moves.get(i).getDistance());
			System.out.println("origin:"+moves.get(i).getOrigin());
			System.out.println("weight:"+moves.get(i).getWeight());
			Piece[][] childBoard = m.makeMove(board, moves.get(i), playerColor);
			v = Math.max(v, betaValue(childBoard, alpha, beta, profondeur++));
			if(v >= beta)
				return v;
			alpha = Math.max(v, alpha);
		}
		
		return v;
	}
	
	private double betaValue(Piece[][] board, double alpha, double beta, int profondeur)
	{
		ArrayList<Move> moves = m.generatePossibleMoves(board, oppositePlayerColor);
		
		// if game state is a leaf node
		if(moves.size() == 0 || profondeur == profondeurMax)
			return beta;
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
		for(int i = 0; i < moves.size(); i++)
		{
			Piece[][] childBoard = m.makeMove(board, moves.get(i), oppositePlayerColor);
			v = Math.min(v, alphaValue(childBoard, alpha, beta, profondeur++));
			if(v <= alpha)
				return v;
			beta = Math.min(v, beta);
		}
		
		return v;
	}
}
