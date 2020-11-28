package exceptions;

public class InvalidHandSizeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidHandSizeException(String playerName) {
		System.out.println(playerName+" has an invalid number of cards in their hand");
	}
}
