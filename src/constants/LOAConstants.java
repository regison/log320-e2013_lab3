package constants;

/*This class contains all the constant used in the program*/
public class LOAConstants {
	
	public static final int PIECE_TYPE_WHITE = 4;
	public static final int PIECE_TYPE_BLACK = 2;
	public static final int PIECE_TYPE_NULL = 0;
	
	public static final int BOARD_UPPER_AND_BOTTOM_SIDE = 0;
	public static final int BOARD_RIGHT_AND_LEFT_SIDE = 8;
	
	public static final int BOARD_SIZE_WIDTH = 8;
	public static final int BOARD_SIZE_HEIGHT = 8;
	
	public static final int MAX_WHITE_PIECES = 12;
	public static final int MAX_BLACK_PIECES = 12;
	public static final int MAX_TOTAL_PIECES = 24;
	public static final int MIN_PLAYER = Integer.MAX_VALUE;
	public static final int MAX_PLAYER = Integer.MIN_VALUE;		

	//8 POSSIBLES DIRECTIONS
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	public static final int UP = 4;
	public static final int UPPER_RIGHT = 5;
	public static final int UPPER_LEFT = 8;
	public static final int BOTTOM_RIGHT = 7;
	public static final int BOTTOM_LEFT = 6;

	public static final int WEIGHT_ROW0_AND_7_COLUMNA_AND_H = 0;
	public static final int WEIGHT_ROW1_AND_6_COLUMNB_AND_G = 1;
	public static final int WEIGHT_ROW2_AND_5_COLUMNC_AND_F = 2;
	public static final int WEIGHT_ROW3_AND_4_COLUMND_AND_E = 3;
	
	public static final byte BIT_BOARD_SIZE = 64;
	public static final int BIT_BOARD_45 = 45;
	public static final int BIT_BOARD_135 = 135;
	
	public final int TIME_ALLOWED = 4980;
}
