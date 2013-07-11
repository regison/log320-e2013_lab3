package utils;

import java.util.ArrayList;
import java.util.HashMap;

import constants.LOAConstants;

import model.*;

public class LineOfActionsEngine {


	private static LineOfActionsEngine engine;
	private static LineOfActionNetworkManager networkManager;
	private static MoveGenerator moveGenerator;
	private LineOfActionBoard board;

	private LineOfActionsEngine() {
		networkManager = LineOfActionNetworkManager.getInstance();
	}

	public static LineOfActionsEngine getInstance() {

		if (engine == null)
			engine = new LineOfActionsEngine();

		return engine;
	}

	public void startGame(String breakthroughServerAddress,
			int breakthroughServerPort) {

		ServerResponse serverResponseObject;

		networkManager.connect(breakthroughServerAddress,
				breakthroughServerPort);
		moveGenerator = new MoveGenerator();

		while (networkManager.isConnected()) {
			serverResponseObject = networkManager.readServerResponse();
			System.out.print(serverResponseObject.getResponse());

			if (serverResponseObject instanceof ServerResponse) {
				switch (serverResponseObject.getType()) {
				case ServerResponse.RESPONSE_TYPE_PLAYER_WHITE_BOARD_CONFIGURATION:
					board = new LineOfActionBoard(
							serverResponseObject.getResponse());
					System.out.print("The Board ( Player White ): \n"
							+ board.toString());
					playGame(LOAConstants.PIECE_TYPE_WHITE);
					break;
				case ServerResponse.RESPONSE_TYPE_PLAYER_BLACK_BOARD_CONFIGURATION:
					board = new LineOfActionBoard(
							serverResponseObject.getResponse());
					System.out.print("The Board ( Player Black ): \n"
							+ board.toString());
					playGame(LOAConstants.PIECE_TYPE_BLACK);
					break;
				case ServerResponse.RESPONSE_TYPE_MOVE:
					
					System.out.println("move: "+ board.toString()
							);
					break;
				default:
					break;
				}
			}
			break;
		}
	}

	public void playGame(int playerType) {
		MoveGenerator gen = new MoveGenerator();
		if (!endGame(board.getBoard())) {
			makeMove(playerType);
			
			
		}
	}

	public void playerSet(int indice) {

	}

	public void scanMove(String move) {

	}

	public void makeMove(int playerType) {

		ArrayList<Move> moveList = moveGenerator.generatePossibleMoves(	board.getBoard(), playerType);
		if (!moveList.isEmpty()) {			
//				int bestMove = PositionEvaluator.MinMaxAlphaBeta(
//						board.getBoard(), m, 3, -Integer.MAX_VALUE,
//						Integer.MIN_VALUE, playerType);
			Move move = moveList.get(0);
			
			networkManager.sendCommand(move.getOrigin() + move.getDestination());
//				board.setBoard(MoveGenerator.movePieceOnBoard(
//						board.getBoard(),moveList.get(0)));
//			
		}

	}

	public boolean endGame(Piece[][] board) {
		return false;
	}

	public void resetGame() {

	}

}

