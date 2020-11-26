package exceptions;

public class InvalidHandSizeException extends Exception {
	public InvalidHandSizeException(String playerName) {
		System.out.println(playerName+" has an invalid number of cards in their hand");
	}
}
