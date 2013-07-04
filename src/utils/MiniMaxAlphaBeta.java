package utils;
import java.util.ArrayList;

import constants.LOAConstants;
import model.Move;
import model.Piece;

public class MiniMaxAlphaBeta {

	private Piece[][] returnedValue;
	private Piece[][] originalBoard;
	private int playerColor;
	private Move bestMove;
	MoveGenerator m = new MoveGenerator();
	
	public MiniMaxAlphaBeta(Piece[][] board, int playerColor)
	{
		this.originalBoard = board;
		this.playerColor = playerColor;
		alphaBeta();
		System.out.println("Player color: " + playerColor);
	}
	
	private void alphaBeta()
	{
		Piece[][] currentBoard = originalBoard;
		double alpha = alphaValue(currentBoard, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		ArrayList<Move> moves = m.generatePossibleMoves(currentBoard, playerColor);
		Move currentMove = moves.get((int)alpha);
		returnedValue = m.makeMove(originalBoard, currentMove, playerColor);
	}
	
	public Piece[][] getNewBoard()
	{
		return this.returnedValue;
	}
	
	private double alphaValue(Piece[][] board, double alpha, double beta)
	{
		// if game state is a leaf node

		////
		
		double v = Double.NEGATIVE_INFINITY;
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
		}
		return v;
	}
	
	private double betaValue(Piece[][] board, double alpha, double beta)
	{
		// if game state is a leaf node
		
		////
		
		double v = Double.POSITIVE_INFINITY;
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
		}
		return v;
	}
}
