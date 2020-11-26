package exceptions;

public class EmptyHandException extends Exception {
	public EmptyHandException(String playerName) {
		System.out.println(playerName+" has no more cards left");
	}
}
