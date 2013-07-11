package model;

import constants.LOAConstants;

public class LineOfActionBoard {

	
	private Piece[][] loaBoard;
	
	public LineOfActionBoard(){
		loaBoard = new Piece [LOAConstants.BOARD_SIZE_WIDTH][LOAConstants.BOARD_SIZE_HEIGHT];		
		initiateBoard();
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
	
	public Piece[][] getBoard(){
		return loaBoard;
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
