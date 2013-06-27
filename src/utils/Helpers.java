package utils;

public class Helpers {

	public static int convertColunmCharToInt(char colunm){
		return colunm - 65;
	}
	public static char convertColunmIntToLetter(int num){
		return (char) (num + 65);
	}
	public static int convertRowChartoInt(char row){
		return row - 48;
	}
}
