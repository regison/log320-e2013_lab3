package utils;

public class ServerResponse {

		public final static int RESPONSE_TYPE_PLAYER_WHITE_BOARD_CONFIGURATION = 1;
		public final static int RESPONSE_TYPE_PLAYER_BLACK_BOARD_CONFIGURATION = 2;
		public final static int RESPONSE_TYPE_MOVE = 3;
		public final static int RESPONSE_TYPE_INVALID_MOVE_SPECIFIED = 4;
		public final static int RESPONSE_TYPE_NULL_RESPONSE = -1;

		private int type;
		private String response;

		public ServerResponse(int responseType, String responseContent) {
			type = responseType;
			response = responseContent;
		}
		
		public int getType(){
			return type;
		}
		
		public String getResponse(){
			return response.toString();
		}

}
