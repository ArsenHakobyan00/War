package war;

import java.util.ArrayDeque;
import java.util.Queue;

import exceptions.EmptyHandException;
import exceptions.InvalidHandSizeException;

public class Player {
	private String playerName;
	private Queue<Card> playerHand;

	// TODO wins and losses at the end if time
	private int wins;
	private int losses;

	public Player() {
		setPlayerName("Anonymous");
		playerHand = new ArrayDeque<Card>();
	}

	public Player(String name) {
		setPlayerName(name);
		playerHand = new ArrayDeque<Card>();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Queue<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Queue<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public int cardsLeft() throws InvalidHandSizeException {
		if (playerHand.size() > 52) {
			throw new InvalidHandSizeException(this.playerName);
		}
		return playerHand.size();
	}

	public Card getFirstCard() throws EmptyHandException {
		if (!playerHand.isEmpty()) {
			return playerHand.poll();
		} else {
			throw new EmptyHandException(this.playerName);
		}
	}

	public boolean addCards(Queue<Card> cards) {
		if (cards.isEmpty() || playerHand.size() + cards.size() > 52) {
			return false;
		}
		playerHand.addAll(cards);
		return true;
	}
}
