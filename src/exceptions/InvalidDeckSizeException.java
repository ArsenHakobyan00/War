package exceptions;

public class InvalidDeckSizeException extends Exception {
	public InvalidDeckSizeException() {
		System.out.println("Invalid number of unshuffled cards");
	}
}
