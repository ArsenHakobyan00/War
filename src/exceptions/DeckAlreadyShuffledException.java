package exceptions;

public class DeckAlreadyShuffledException extends Exception {
	public DeckAlreadyShuffledException() {
		System.out.println("The deck is already shuffled");
	}
}
