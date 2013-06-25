package model;

import constants.LOAConstants;

public class LineOfActionBoard {

	private byte[] byteBoard;
	private Piece[][] loaBoard;
	
	public LineOfActionBoard(){
		loaBoard = new Piece [LOAConstants.BOARD_SIZE_WIDTH][LOAConstants.BOARD_SIZE_HEIGHT];		
		initiateBoard();
	}
	
	private void initiateBoard(){
		int rowNumber = 1; 
		for(int i = LOAConstants.BOARD_SIZE_HEIGHT; i >=1; i--){
			
			for(int j=0; j<LOAConstants.BOARD_SIZE_WIDTH ; j++){	
				char columnLetter = (char)(65 + j);// Start by 'A'				
				String location = String.valueOf(columnLetter) + i;				
				//First and last row
				if ((i == 8 || i == 1) && (j != 0 && j !=7)){					
					loaBoard[rowNumber - 1][j] = new Piece(location,LOAConstants.PIECE_TYPE_BLACK);
				}
				//First and last colunm
				else 
				   if (j==0 || j==7){
					if (rowNumber !=1 && rowNumber !=8)
					loaBoard[rowNumber - 1][j] = new Piece(location,LOAConstants.PIECE_TYPE_WHITE);
					else
					loaBoard[rowNumber - 1][j] = new Piece(location,LOAConstants.PIECE_TYPE_NULL);						
				}
				else{
					loaBoard[rowNumber - 1][j] = new Piece(location,LOAConstants.PIECE_TYPE_NULL);
				}			
			}
			rowNumber++;
		}		
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
