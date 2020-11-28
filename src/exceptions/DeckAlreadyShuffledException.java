package exceptions;

public class DeckAlreadyShuffledException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeckAlreadyShuffledException() {
		System.out.println("The deck is already shuffled");
	}
}
