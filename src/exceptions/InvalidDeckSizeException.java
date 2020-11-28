package exceptions;

public class InvalidDeckSizeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDeckSizeException() {
		System.out.println("Invalid number of unshuffled cards");
	}
}
