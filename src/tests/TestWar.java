package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.jupiter.api.Test;
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
				cards.add(new Card(null, null, 0));
			}

			Deck deck = new Deck(cards);
			War war1 = new War();
			war1.setDeck(deck);

			assertFalse(war1.start(), "Testing start with invalid deck size");

			cards = new ArrayDeque<Card>();
			deck = new Deck(cards);
			for (int i = 0; i < 51; i++) {
				cards.add(new Card(null, null, 0));
			}

			war1.setDeck(deck);
			assertFalse(war1.start(), "Testing start with invalid deck size");

			cards = new ArrayDeque<Card>();
			deck = new Deck(cards);
			for (int i = 0; i < 53; i++) {
				cards.add(new Card(null, null, 0));
			}

			war1.setDeck(deck);
			assertFalse(war1.start(), "Testing start with invalid deck size");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test_play() {
		War war = new War();
		try {
			war.start();

			// Test 1 - Player 1 wins

			Card card1 = new Card("Spades", "3", 1);
			Card card2 = new Card("Spades", "2", 0);

			// Make space for the new cards
			war.getPlayer1().getFirstCard();
			war.getPlayer2().getFirstCard();

			// After removing the first card from each player,
			// Player 1 has 25 cards and Player 2 has 25 (2 cards removed)
			assertTrue(war.getPlayer1().cardsLeft() == 25 && war.getPlayer2().cardsLeft() == 25);

			assertEquals(25, war.getPlayer1().cardsLeft(), "Checking cards left");
			assertEquals(1, war.play(card1, card2), "Player 1 wins the round");
			assertEquals(27, war.getPlayer1().cardsLeft(), "Making sure that the number has changed");

			// Test 2 - Player 2 wins

			// New Cards
			card1 = new Card("Hearts", "King", 12);
			card2 = new Card("Diamonds", "Ace", 13);

			// Make space for the new cards
			war.getPlayer1().getFirstCard();
			war.getPlayer2().getFirstCard();

			// After removing the first card from each player,
			// Player 1 has 26 cards and Player 2 has 24 (2 cards removed)
			assertTrue(war.getPlayer1().cardsLeft() == 26 && war.getPlayer2().cardsLeft() == 24);

			assertEquals(24, war.getPlayer2().cardsLeft(), "Checking cards left");
			assertEquals(2, war.play(card1, card2), "Player 2 wins the round");
			assertEquals(26, war.getPlayer2().cardsLeft(), "Making sure that the number has changed");

			// Test 3 - There's a tie (test war)

			// New Cards
			card1 = new Card("Clubs", "Jack", 10);
			card2 = new Card("Diamonds", "Jack", 10);

			// Make space for the new cards
			war.getPlayer1().getFirstCard();
			war.getPlayer2().getFirstCard();

			// After removing the first card from each player,
			// Both players have 25 cards (2 cards removed)
			assertTrue(war.getPlayer1().cardsLeft() == 25 && war.getPlayer2().cardsLeft() == 25);

			assertEquals(25, war.getPlayer2().cardsLeft(), "Checking cards left");
			assertEquals(0, war.play(card1, card2), "It is a tie");
			assertEquals(22, war.getPlayer2().cardsLeft(), "Making sure that the number has changed");
			assertEquals(8, war.getKitty().size(), "Kitty size must be 8");

			// Testing kitty adding after a tie

			// Make space for the new cards
			war.getPlayer1().getFirstCard();
			war.getPlayer2().getFirstCard();

			// After removing the first card from each player,
			// Both players have 21 cards (2 cards removed and 8 are in the kitty)
			assertTrue(war.getPlayer1().cardsLeft() == 21 && war.getPlayer2().cardsLeft() == 21);
			
			Card card3 = new Card("Hearts", "5", 3);
			Card card4 = new Card("Hearts", "10", 8);
			
			assertEquals(21, war.getPlayer2().cardsLeft(), "Checking cards left");
			assertEquals(2, war.play(card3, card4), "It is a tie");
			assertEquals(31, war.getPlayer2().cardsLeft(), "Making sure that the number has changed");
			assertEquals(0, war.getKitty().size(), "Kitty size must be 8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
