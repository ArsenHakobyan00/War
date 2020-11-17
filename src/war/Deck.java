package war;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {
	private Queue<Card> unshuffledDeck;
	private Queue<Card> shuffledDeck;

	private static final String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };
	private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
			"Ace" };

	public Deck() {
		unshuffledDeck = new LinkedList<Card>();
		createDeck();
	}

	public Deck(Queue<Card> list) {
		unshuffledDeck = list; 
	}
	
	private void createDeck() {
		for(int i = 0; i < SUITS.length; i++) {
			for (int j = 0; j < RANKS.length; j++) {
				unshuffledDeck.add(new Card(SUITS[i], RANKS[j], j));				
			}
		}
	}
	
	public void shuffle() {
		// TODO Code shuffle()
	}
	
	public Card deal() {
		return shuffledDeck.poll();
	}
	
	public int size() {
		return shuffledDeck.size();
	}
}
