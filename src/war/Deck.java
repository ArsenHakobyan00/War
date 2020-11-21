package war;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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

	public Queue<Card> getUnshuffledDeck() {
		return unshuffledDeck;
	}

	public Queue<Card> getShuffledDeck() {
		return shuffledDeck;
	}

	private void createDeck() {
		for (int i = 0; i < SUITS.length; i++) {
			for (int j = 0; j < RANKS.length; j++) {
				unshuffledDeck.add(new Card(SUITS[i], RANKS[j], j));
			}
		}
	}

	public void shuffle() {
		Card[] cards = new Card[unshuffledDeck.size()];
		for (int i = 0; i < cards.length; i++) {
			Card temp = unshuffledDeck.poll();
			cards[i] = temp;
		}

		for (int i = 0; i < cards.length; i++) {
			// Random for remaining positions.
			Random rand = new Random();
			int r = i + rand.nextInt(cards.length - i);

			// swapping the elements
			Card temp = cards[r];
			cards[r] = cards[i];
			cards[i] = temp;
		}

		shuffledDeck = new ArrayDeque<Card>();

		for (int i = 0; i < cards.length; i++) {
			shuffledDeck.add(cards[i]);
		}

	}

	public Card deal() {
		return shuffledDeck.poll();
	}

	public int size() {
		return shuffledDeck.size();
	}
}
