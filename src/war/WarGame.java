package war;

public class WarGame {

	public static void main(String[] args) {
		Deck deck = new Deck();
		try {
			deck.shuffle();
		} catch (InvalidDeckSizeException e) {
			e.printStackTrace();
		}
	}

}
