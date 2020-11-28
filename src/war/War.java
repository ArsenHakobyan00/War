package war;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import exceptions.EmptyHandException;
import exceptions.InvalidHandSizeException;

public class War {

	private Deck deck;
	private Deque<Card> kitty;
	private Queue<Card> playCards;
	private Player player1;
	private Player player2;

	public War() {
		deck = new Deck();
		kitty = new ArrayDeque<Card>();
		playCards = new ArrayDeque<Card>();
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
	}

	public War(String player1Name, String player2Name) {
		deck = new Deck();
		kitty = new ArrayDeque<Card>();
		playCards = new ArrayDeque<Card>();
		this.player1 = new Player(player1Name);
		this.player2 = new Player(player2Name);
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Deque<Card> getKitty() {
		return kitty;
	}

	public void setKitty(Deque<Card> kitty) {
		this.kitty = kitty;
	}

	public Queue<Card> getPlayCards() {
		return playCards;
	}

	public void setPlayCards(Queue<Card> playCards) {
		this.playCards = playCards;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	// Deals the 26 cards to each player
	public boolean start() throws Exception {
		if (deck.size() == 52) {
			deck.shuffle();
			Queue<Card> player1Hand = new ArrayDeque<Card>();
			Queue<Card> player2Hand = new ArrayDeque<Card>();
			while (deck.size() != 0) {
				player1Hand.add(deck.deal());
				player2Hand.add(deck.deal());
			}
			
			player1.addCards(player1Hand);
			player2.addCards(player2Hand);
			
			player1Hand.clear();
			player2Hand.clear();
			
			return true;
		}
		return false;
	}

	// Description: plays one round of the WarGame.
	// Possible result values:
	// 1 - Player 1 won the round
	// 2 - Player 2 won the round
	// 0 - It is a Tie/War
	// 11 - Player 1 has no cards left (Player 2 wins)
	// 22 - Player 2 has no cards left (Player 1 wins)
	public int play(Card card1, Card card2) throws EmptyHandException, InvalidHandSizeException {
		int result = 0;

		playCards.add(card1);
		playCards.add(card2);

		if (card1.getValue() > card2.getValue()) {
			player1.addCards(playCards);
			result = 1;
			
			// If there are any cards in the kitty, add them to the player as well
			if (!kitty.isEmpty()) {
				player1.addCards(kitty);
				kitty.clear();
			}
		} else if (card1.getValue() < card2.getValue()) {
			player2.addCards(playCards);
			result = 2;
			
			// If there are any cards in the kitty, add them to the player as well
			if (!kitty.isEmpty()) {
				player2.addCards(kitty);
				kitty.clear();
			} 
		} else {
			// Add the 2 cards to the kitty
			kitty.addAll(playCards);

			// Check for a win
			if (player1.cardsLeft() <= 2) {
				player1.clearPlayerHand();
				result = 11;
			} else if (player2.cardsLeft() <= 2) {
				player2.clearPlayerHand();
				result = 22;
			} else {// <-- It's a tie. The result is already 0 by default
				
				// Add 3 cards to the kitty
				for (int i = 0; i < 3; i++) {
					kitty.add(player1.getFirstCard());
					kitty.add(player2.getFirstCard());
				}
			}
		}
		playCards.clear();
		return result;
	}

}// Code War
