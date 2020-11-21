package war;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

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
			assertTrue(deck.getUnshuffledDeck().poll() instanceof Card, "Making sure every card is an instance of Card");
		
		for (int i = 0; i < deck1.getUnshuffledDeck().size(); i++)		
			assertFalse(deck1.getUnshuffledDeck().poll() instanceof Card, "Making sure every card is an instance of Card");
	}
	
	@Test
	void test_shuffle() {
		
	}// TODO Test shuffle()

	@Test
	void test_deal() {
		
	}// TODO Test deal()
	
	@Test
	void test_size() {
		
	}// TODO Test size()
	
}// TODO Finish Deck Test Cases
