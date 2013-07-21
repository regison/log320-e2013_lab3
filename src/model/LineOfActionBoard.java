package model;

import constants.LOAConstants;

public class LineOfActionBoard {
	
	private Piece[][] loaBoard;
	
	public LineOfActionBoard(){
		loaBoard = new Piece [LOAConstants.BOARD_SIZE_WIDTH][LOAConstants.BOARD_SIZE_HEIGHT];		
		initiateBoard();
	}
	
	public LineOfActionBoard(String commandLine){
		loaBoard = new Piece [LOAConstants.BOARD_SIZE_WIDTH][LOAConstants.BOARD_SIZE_HEIGHT];		
		initiateWithCommandLine(commandLine);
	}
	
	public Piece getPiece(String location) {
	
		return loaBoard[((int)location.charAt(1)-48) ][(int)location.charAt(0)-65];
	}
	
	private void initiateBoard(){
	 
		for(int i = 0; i <LOAConstants.BOARD_SIZE_HEIGHT; i++){
			
			for(int j=0; j<LOAConstants.BOARD_SIZE_WIDTH ; j++){	
				char columnLetter = (char)(65 + j);// Start by 'A'				
				String location = String.valueOf(columnLetter) + (8 - i);				
				//First and last row
				if ((i == 0 || i == 7) && (j != 0 && j !=7)){					
					loaBoard[i][j] = new Piece(location,LOAConstants.PIECE_TYPE_BLACK, LOAConstants.WEIGHT_ROW0_AND_7_COLUMNA_AND_H);
				}
				//First and last colunm
				else 
				   if (j==0 || j==7){
					if (i !=0 && i !=7)
					loaBoard[i][j] = new Piece(location,LOAConstants.PIECE_TYPE_WHITE, LOAConstants.WEIGHT_ROW0_AND_7_COLUMNA_AND_H);
					else
					loaBoard[i][j] = new Piece(location,LOAConstants.PIECE_TYPE_NULL);						
				}
				else{
					loaBoard[i][j] = new Piece(location,LOAConstants.PIECE_TYPE_NULL);
				}			
			}
			
		}		
	}
	
	public void initiateWithCommandLine(String line){
		 
		int j = 0;
		int row = 0;
		String trimmedLine = line.replaceAll(" ", "");
		for(int i = 0; i < trimmedLine.length(); i++)
		{
			if(i % LOAConstants.BOARD_SIZE_WIDTH == 0 && i != 0)
			{
				j = 0;
				row++;
			}
			
			char columnLetter = (char)(65 + j); // Start by 'A'				
			String location = String.valueOf(columnLetter) + String.valueOf(8 - row);	
			int value = Integer.parseInt(trimmedLine.substring(i, i+1));
			
			// Black piece
			if(value == 2)
			{					
				loaBoard[row][j] = new Piece(location,LOAConstants.PIECE_TYPE_BLACK, LOAConstants.WEIGHT_ROW0_AND_7_COLUMNA_AND_H);
			}
			// White piece
			else 
			if(value == 4){
				loaBoard[row][j] = new Piece(location,LOAConstants.PIECE_TYPE_WHITE, LOAConstants.WEIGHT_ROW0_AND_7_COLUMNA_AND_H);				
			}
			// Empty space
			else
			if(value == 0)
			{
				loaBoard[row][j] = new Piece(location,LOAConstants.PIECE_TYPE_NULL);
			}	
			
			j++;
		}		
	}
	
	public Piece[][] getBoard(){
		return loaBoard;
	}
	
	public void setBoard(Piece[][] board){
		this.loaBoard = board;
	}
	public void printBoard(){
		for(int i=0; i< LOAConstants.BOARD_SIZE_HEIGHT; i++){
			for(int j=0; j<LOAConstants.BOARD_SIZE_WIDTH; j++){		
				System.out.print(loaBoard[i][j].getType());
			}
			System.out.println();
		}
	}
	
	
	
}
