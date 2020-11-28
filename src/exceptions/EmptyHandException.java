package exceptions;

public class EmptyHandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyHandException(String playerName) {
		System.out.println(playerName+" has no more cards left");
	}
}
