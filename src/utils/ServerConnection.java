package utils;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import constants.LOAConstants;

import model.LineOfActionBoard;
import model.Move;
import model.Piece;


class ServerConnection {
	public static void main(String[] args) {
         
	Socket MyClient;
	BufferedInputStream input;
	BufferedOutputStream output;
    int[][] board = new int[8][8];
	try {
		LineOfActionBoard LOA_Board = new LineOfActionBoard();
		
		MyClient = new Socket("localhost", 8888);
	   	input    = new BufferedInputStream(MyClient.getInputStream());
		output   = new BufferedOutputStream(MyClient.getOutputStream());
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); 
	   	
		while(1 == 1)
		{	
			char cmd = 0;
		   	int profondeur = 4;
			int color = 0; int oppositeColor = 0;
            cmd = (char)input.read();
            System.out.println("Ligne de commande:" + cmd);
            
            // Début de la partie en joueur blanc
            if(cmd == '1')
            {
                byte[] aBuffer = new byte[1024];		
				int size = input.available();
				input.read(aBuffer,0,size);
                String s = new String(aBuffer).trim();
                
                String moveStr = null;
                
                color = LOAConstants.PIECE_TYPE_WHITE;
                oppositeColor = LOAConstants.PIECE_TYPE_BLACK;
                
                LOA_Board.initiateWithCommandLine(s);
                /*System.out.println("-- Current Board --");
                LOA_Board.printBoard();
                System.out.println("-------------------");*/
                MoveGenerator mg = new MoveGenerator();
                ArrayList<Move> moves = mg.generatePossibleMoves(LOA_Board.getBoard(), LOAConstants.PIECE_TYPE_WHITE);
                
               
                /*for(int i = 0; i < moves.size(); i++)
                {
                	System.out.println("Moves: "+moves.get(i).getOrigin() + moves.get(i).getDestination());
                }
                */
                MiniMaxAlphaBeta mmab = new MiniMaxAlphaBeta();
                Piece[][] currentBoard = LOA_Board.getBoard();
                Move newMove = mmab.calculateBestMove(currentBoard, color, profondeur);
                //LOA_Board.printBoard();
                
                moveStr = newMove.getOrigin() + newMove.getDestination();
                
                Piece[][] newBoard = mg.makeMove(LOA_Board.getBoard(), newMove, color);
                LOA_Board.setBoard(newBoard);
                //LOA_Board.printBoard();
                System.out.println("New move: " + moveStr);
                
				output.write(moveStr.getBytes(),0,moveStr.length());
				output.flush();
            }
            
            // Début de la partie en joueur Noir
            if(cmd == '2')
            {
                System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs.");
                byte[] aBuffer = new byte[1024];			
				int size = input.available();
				input.read(aBuffer,0,size);
                String s = new String(aBuffer).trim();
                
                color = LOAConstants.PIECE_TYPE_BLACK;
                oppositeColor = LOAConstants.PIECE_TYPE_WHITE;
                
                LOA_Board.initiateWithCommandLine(s);
                LOA_Board.printBoard();  
            }

			// Le serveur demande le prochain coup
			// Le message contient aussi le dernier coup joué.
			if(cmd == '3')
			{
				byte[] aBuffer = new byte[16];		
				int size = input.available();
				input.read(aBuffer,0,size);
				String s = new String(aBuffer);
				
				String[] moveTab = s.split(" - ");
				System.out.println("Dernier coup : "+ s);
				
				MoveGenerator mg = new MoveGenerator();
				//System.out.println(moveTab[0] + moveTab[1]);
                Move lastMove = new Move(moveTab[0].trim(), moveTab[1].trim(), 0, 0);
                LOA_Board.printBoard();
                Piece[][] newBoard = mg.makeMove(LOA_Board.getBoard(), lastMove, oppositeColor);
                LOA_Board.setBoard(newBoard);
                
                String moveStr = null;
                MiniMaxAlphaBeta mmab = new MiniMaxAlphaBeta();
                Move newMove = mmab.calculateBestMove(LOA_Board.getBoard(), color, profondeur);
                moveStr = newMove.getOrigin() + newMove.getDestination();
                System.out.println("New move: " + moveStr);
                
				output.write(moveStr.getBytes(),0,moveStr.length());
				output.flush();
				
			}
			
			// Le dernier coup est invalide
			if(cmd == '4')
			{
				System.out.println("Coup invalide, entrez un nouveau coup : ");
		       	String move = null;
				move = console.readLine();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
			}
        }
	}
	catch (IOException e) {
   		System.out.println(e);
	}
	
    }
}
