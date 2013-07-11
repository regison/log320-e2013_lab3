package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;

public class LineOfActionNetworkManager {

	public final static String LOCALHOST_ADDRESS = "127.0.0.1";
	public final static int DEFAULT_PORT = 8888;
	public final static int NEGATIVE_SIZE = -1;
	public final static int SERVER_RESPONSE_BUFFER_SIZE = 1024;
	
	private static LineOfActionNetworkManager manager;
	private static Socket clientConnectionSocket;
	private static BufferedInputStream clientInputStream;
	private static BufferedOutputStream clientOutputStream;

	private LineOfActionNetworkManager() {
	}

	public static LineOfActionNetworkManager getInstance() {
		if (manager == null) {
			manager = new LineOfActionNetworkManager();
		}

		return manager;
	}

	public void connect(String breakthroughServerAddress,
			int breakthroughServerPort) {
		try {
			clientConnectionSocket = new Socket(breakthroughServerAddress,
					breakthroughServerPort);

			if (clientConnectionSocket.isConnected()) {
				clientInputStream = new BufferedInputStream(
						clientConnectionSocket.getInputStream());
				clientOutputStream = new BufferedOutputStream(
						clientConnectionSocket.getOutputStream());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (isConnected()) {
				clientInputStream.close();
				clientOutputStream.flush();
				clientOutputStream.close();
				clientConnectionSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isConnected() {
		return clientConnectionSocket.isConnected();
	}

	public void sendCommand(String command) {
		try {
			clientOutputStream.write(command.getBytes(), 0, command.length());
			clientOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServerResponse readServerResponse() {

		int availableInputBytes = NEGATIVE_SIZE;
		byte[] serverResponseBuffer = new byte[SERVER_RESPONSE_BUFFER_SIZE];
		ServerResponse response = null;

		try {
			int responseType = Character
					.getNumericValue((char) clientInputStream.read());
			availableInputBytes = clientInputStream.available();
			clientInputStream.read(serverResponseBuffer, 0, availableInputBytes);
			
			switch (responseType) {
			case ServerResponse.RESPONSE_TYPE_PLAYER_WHITE_BOARD_CONFIGURATION:
				if (availableInputBytes > 0) {

					response = new ServerResponse(
							ServerResponse.RESPONSE_TYPE_PLAYER_WHITE_BOARD_CONFIGURATION,
							new String(serverResponseBuffer).trim().replaceAll(" ",	""));
				}

				break;
			case ServerResponse.RESPONSE_TYPE_PLAYER_BLACK_BOARD_CONFIGURATION:
				if (availableInputBytes > 0) {
					
					response = new ServerResponse(
							ServerResponse.RESPONSE_TYPE_PLAYER_BLACK_BOARD_CONFIGURATION,
							new String(serverResponseBuffer).trim().replaceAll(" ",	""));
				}

				break;
			case ServerResponse.RESPONSE_TYPE_MOVE:
				if (availableInputBytes > 0) {
					
					response = new ServerResponse(
							ServerResponse.RESPONSE_TYPE_MOVE,
							new String(serverResponseBuffer).trim());
				}
				break;
			case ServerResponse.RESPONSE_TYPE_INVALID_MOVE_SPECIFIED:
				break;

			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}
}
