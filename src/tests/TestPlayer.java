package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import exceptions.EmptyHandException;
import exceptions.InvalidHandSizeException;
import war.Card;
import war.Deck;
import war.Player;

class TestPlayer {

	@Test
	void test_cardsLeft() {
		assertThrows(InvalidHandSizeException.class, () -> {

			Player player1 = new Player();
			Queue<Card> playerHand = new ArrayDeque<Card>();
			playerHand.add(new Card("Clubs", "Ace"));
			playerHand.add(new Card("Diamonds", "2"));
			playerHand.add(new Card("Spades", "3"));
			player1.setPlayerHand(playerHand);

			assertEquals(playerHand.size(), player1.cardsLeft(), "Making sure cardsLeft() returns the right size");

			Deck deck = new Deck();
			// playerHand with a size of 52
			Queue<Card> playerHand1 = deck.getUnshuffledDeck();
			player1.setPlayerHand(playerHand1);
			player1.cardsLeft();

			// playerHand with a size of 53
			Queue<Card> playerHand2 = deck.getUnshuffledDeck();
			playerHand2.add(new Card(null, null));
			player1.setPlayerHand(playerHand2);
			player1.cardsLeft();
		});
	}

	@Test
	void test_getFirstCard() {
		assertThrows(EmptyHandException.class, () -> {

			Player player1 = new Player();
			Queue<Card> playerHand = new ArrayDeque<Card>();
			playerHand.add(new Card("Clubs", "2"));
			playerHand.add(new Card("Hearts", "Jack"));
			playerHand.add(new Card("Diamonds", "5"));
			playerHand.add(new Card("Clubs", "7"));
			playerHand.add(new Card("Spades", "10"));

			player1.setPlayerHand(playerHand);
			player1.getFirstCard();

			Queue<Card> playerHand1 = new ArrayDeque<Card>();
			player1.setPlayerHand(playerHand1);
			player1.getFirstCard();

		});
	}

	@Test
	void test_addCards() {
		Player player1 = new Player();
		Queue<Card> playerHand = new ArrayDeque<Card>();
		playerHand.add(new Card("Clubs", "2"));
		playerHand.add(new Card("Hearts", "Jack"));
		playerHand.add(new Card("Diamonds", "5"));
		playerHand.add(new Card("Clubs", "7"));
		playerHand.add(new Card("Spades", "10"));

		assertTrue(player1.addCards(playerHand), "Adding some cards to check if the method is working properly");

		assertTrue(player1.getPlayerHand().containsAll(playerHand), "Making sure the cards are added");

		Queue<Card> playerHand1 = new ArrayDeque<Card>();
		assertFalse(player1.addCards(playerHand1), "Adding an empty queue of cards");
		
		player1.addCards(playerHand);
		Deque<Card> playerHand2 = (Deque<Card>) player1.getPlayerHand();
		assertEquals("10 of Spades", playerHand2.getLast().toString(), "Making sure that the cards are being added at the end");
		

		
		Player player2 = new Player();
		// 2 cards to add, 50 cards are in the player's hand
		Deck deck = new Deck();
		Queue<Card> cardsToAdd = new ArrayDeque<Card>();
		cardsToAdd.add(new Card(null, null));
		cardsToAdd.add(new Card(null, null));
		
		Queue<Card> playerHand3 = deck.getUnshuffledDeck();
		playerHand3.poll();
		playerHand3.poll();
		
		player2.setPlayerHand(playerHand3);

		assertTrue(player2.addCards(cardsToAdd), "2 cards to add, 50 cards are in the player's hand");

		// 2 cards to add, 51 cards are in the player's hand
		Deck deck1 = new Deck();
		Queue<Card> playerHand4 = deck1.getUnshuffledDeck();
		playerHand4.poll();
		player2.setPlayerHand(playerHand4);
		
		assertFalse(player2.addCards(cardsToAdd), "2 cards to add, 51 cards are in the player's hand");

	}

}
