package war;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import exceptions.InvalidHandSizeException;

public class War {

	private Deck deck = new Deck();
	private Deque<Card> kitty = new ArrayDeque<Card>();
	private Queue<Card> playCards = new ArrayDeque<Card>();
	private Player player1;
	private Player player2;

	public War() {
		this.player1 = new Player();
		this.player2 = new Player();
	}

	public War(String player1Name, String player2Name) {
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

	public boolean start() throws Exception {
		if (deck.size() == 52) {
			deck.shuffle();
			Queue<Card> player1Hand = new ArrayDeque<Card>();
			Queue<Card> player2Hand = new ArrayDeque<Card>();
			while (deck.size() > 0) {
				player1Hand.add(deck.deal());
				player2Hand.add(deck.deal());
			}
			player1.addCards(player1Hand);
			player2.addCards(player2Hand);
			return true;
		}
		return false;
	}

	public void play() {

	}// TODO play()

	public boolean isWar() {
		
		if (playCards.size() == 2) {
			Card card1 = playCards.poll();
			Card card2 = playCards.poll();
			
			if (card1.getRank().equals(card2.getRank())) {
				kitty.push(card1);
				kitty.push(card2);
				return true;
			}
			playCards.add(card1);
			playCards.add(card2);
			return false;
		}
		return false;
		
	}

	public boolean isGameOver() throws InvalidHandSizeException {
		if (player1.cardsLeft() == 0 || player2.cardsLeft() == 0) {
			return true;
		}
		return false;
	}

	public boolean isWin() {
		if (playCards.size() == 2) {
			Card card1 = playCards.poll();
			Card card2 = playCards.poll();
			
			if (card1.getRank().equals(card2.getRank())) {
				kitty.push(card1);
				kitty.push(card2);
				return false;
			}
			playCards.add(card1);
			playCards.add(card2);
			return true;
		}
		return false;
	}

	public void playWar() {

	}// TODO playWar()
}// Code War
