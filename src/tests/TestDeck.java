package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import exceptions.DeckAlreadyShuffledException;
import exceptions.InvalidDeckSizeException;
import war.Card;
import war.Deck;

class TestDeck {

	@Test
	void test_createDeck() {
		Deck deck = new Deck();

		Queue<Card> testDeck = new LinkedList<Card>();
		testDeck.add(null);
		Deck deck1 = new Deck(testDeck);

		assertEquals(52, deck.getUnshuffledDeck().size(), "Test size");
		assertNotEquals(51, deck.getUnshuffledDeck().size(), "Test size");

		for (int i = 0; i < deck.getUnshuffledDeck().size(); i++)
			assertTrue(deck.getUnshuffledDeck().poll() instanceof Card,
					"Making sure every card is an instance of Card");

		for (int i = 0; i < deck1.getUnshuffledDeck().size(); i++)
			assertFalse(deck1.getUnshuffledDeck().poll() instanceof Card,
					"Making sure every card is an instance of Card");
	}

	@Test
	void test_shuffle() {
		assertThrows(InvalidDeckSizeException.class, () -> {

			// Testing unshuffled must be empty at the end
			Deck deck = new Deck();

			deck.shuffle();
			Queue<Card> unshuffled = deck.getUnshuffledDeck();
			assertTrue(unshuffled.isEmpty(), "unshuffledDeck is empty");

			Deck deck1 = new Deck();
			Queue<Card> unshuffled1 = deck1.getUnshuffledDeck();
			assertFalse(unshuffled1.isEmpty(), "unshuffledDeck is not empty");

			// unshuffled deck with a size of 52
			int unshuffledSize;
			Deck deck2 = new Deck();
			unshuffledSize = deck2.getUnshuffledDeck().size();
			assertEquals(52, unshuffledSize, "unshuffled deck must have a size of 52");

			// unshuffled deck with a size of 53
			Deck deck3 = new Deck();
			Queue<Card> testUnshuffled = deck3.getUnshuffledDeck();
			testUnshuffled.add(new Card(null, null));
			deck3.setUnshuffledDeck(testUnshuffled);
			unshuffledSize = deck3.getUnshuffledDeck().size();
			deck3.shuffle();

			// unshuffled deck with a size of 51
			Deck deck4 = new Deck();
			testUnshuffled = deck4.getUnshuffledDeck();
			testUnshuffled.poll();
			deck4.setUnshuffledDeck(testUnshuffled);
			unshuffledSize = deck4.getUnshuffledDeck().size();
			deck4.shuffle();

		});

		assertThrows(DeckAlreadyShuffledException.class, () -> {
			// unshuffled deck with a size of 52
			// test shuffle more than once
			Deck deck5 = new Deck();
			deck5.shuffle();
			deck5.shuffle();
		});

	}

	@Test
	void test_deal() {
		Deck deck = new Deck();
		Queue<Card> newShuffledDeck = new ArrayDeque<Card>();
		newShuffledDeck.add(new Card("Diamonds", "Ace"));
		newShuffledDeck.add(new Card("Clubs", "4"));
		newShuffledDeck.add(new Card("Hearts", "3"));

		deck.setShuffledDeck(newShuffledDeck);

		assertEquals(newShuffledDeck.peek(), deck.deal());
	}

	@Test
	void test_size() {
		assertThrows(InvalidDeckSizeException.class, () -> {

			Deck deck1 = new Deck();
			deck1.shuffle();
			assertTrue(deck1.size() == 52, "shuffled deck with a size of 52");

			deck1.getShuffledDeck().add(new Card(null, null));
			deck1.size(); // Throws InvalidDeckSizeException, because size is 53

			Deck deck2 = new Deck();
			Queue<Card> newShuffledDeck = new ArrayDeque<Card>();
			deck2.setShuffledDeck(newShuffledDeck);
			assertTrue(deck2.size() == 0, "shuffled deck with a size of 0");

			Deck deck3 = new Deck();
			deck3.shuffle();
			deck3.deal();
			assertTrue(deck3.size() == 51, "shuffled deck with a size of 51");

			Deck deck4 = new Deck();
			newShuffledDeck = new ArrayDeque<Card>();
			newShuffledDeck.add(new Card(null, null));
			deck4.setShuffledDeck(newShuffledDeck);
			assertTrue(deck4.size() == 1, "shuffled deck with a size of 1");
		});
	}

}
