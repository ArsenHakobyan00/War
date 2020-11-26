package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import exceptions.DeckAlreadyShuffledException;
import exceptions.EmptyHandException;
import exceptions.InvalidDeckSizeException;
import exceptions.InvalidHandSizeException;
import war.Card;
import war.Deck;
import war.War;

class TestWar {

	@Test
	void test_start() {
		try {
			War war = new War();
			assertTrue(war.getDeck().size() == 52, "Making sure the deck has the right size");

			war.start();

			assertTrue(war.getPlayer1().cardsLeft() == 26 && war.getPlayer2().cardsLeft() == 26,
					"Checking that each player has 26 cards to start");

			// Testing start for a deck of 26 cards
			Queue<Card> cards = new ArrayDeque<Card>();
			for (int i = 0; i < 26; i++) {
				cards.add(new Card(null, null));
			}

			Deck deck = new Deck(cards);
			War war1 = new War();
			war1.setDeck(deck);

			assertFalse(war1.start(), "Testing start with invalid deck size");

			cards = new ArrayDeque<Card>();
			deck = new Deck(cards);
			for (int i = 0; i < 51; i++) {
				cards.add(new Card(null, null));
			}

			war1.setDeck(deck);
			assertFalse(war1.start(), "Testing start with invalid deck size");

			cards = new ArrayDeque<Card>();
			deck = new Deck(cards);
			for (int i = 0; i < 53; i++) {
				cards.add(new Card(null, null));
			}

			war1.setDeck(deck);
			assertFalse(war1.start(), "Testing start with invalid deck size");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test_isWar() {
		War war = new War();
		Card card1 = new Card("Spades", "Ace");
		Card card2 = new Card("Diamonds", "Ace");
		war.getPlayCards().add(card1);
		war.getPlayCards().add(card2);

		assertTrue(war.isWar(), "Checking if two played cards have the same rank");

		War war1 = new War();
		card1 = new Card("Spades", "2");
		card2 = new Card("Spades", "3");

		war1.getPlayCards().add(card1);
		war1.getPlayCards().add(card2);

		assertFalse(war1.isWar(), "Checking if isWar() returns false when the cards are not of the same rank");

	}

	@Test
	void test_isGameOver() {
		try {
			War war = new War();
			war.start();
			while (war.getPlayer1().cardsLeft() > 0) {
				war.getPlayer1().getFirstCard();
			}

			assertEquals(0, war.getPlayer1().cardsLeft(), 
					"Making sure that player1 has no cards left");
			assertTrue(war.isGameOver(), "Testing a game over valid case");

			War war1 = new War();
			war1.start();
			while (war1.getPlayer1().cardsLeft() > 1) {
				war1.getPlayer1().getFirstCard(); // make it 1 card
			}
			war1.getPlayer2().getFirstCard(); // make it 51 cards

			assertEquals(1, war1.getPlayer1().cardsLeft(), 
					"Making sure that player1 has 1 card left");
			assertFalse(war1.isGameOver(), "Testing a game over valid case");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test_isWin() {
		War war = new War();
		Card card1 = new Card("Spades", "Ace");
		Card card2 = new Card("Diamonds", "King");
		war.getPlayCards().add(card1);
		war.getPlayCards().add(card2);

		assertTrue(war.isWin(), 
				"Checking if isWin() returns true when two played cards have different ranks");

		War war1 = new War();
		card1 = new Card("Spades", "3");
		card2 = new Card("Spades", "2");

		war1.getPlayCards().add(card1);
		war1.getPlayCards().add(card2);

		assertTrue(war1.isWin(), 
				"Checking if isWin() returns true when two played cards have different ranks");

		War war2 = new War();
		card1 = new Card("Spades", "Ace");
		card2 = new Card("Diamonds", "Ace");
		war2.getPlayCards().add(card1);
		war2.getPlayCards().add(card2);

		assertFalse(war2.isWin(), 
				"Checking if isWin() returns false when two played cards have same ranks");
	}
	
	@Test
	void test_playWar() {
		
	}// TODO test_playWar()

}// TODO Code TestWar
