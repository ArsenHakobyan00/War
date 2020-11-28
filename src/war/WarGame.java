package war;

import java.util.Scanner;

import exceptions.InvalidHandSizeException;

public class WarGame {
	static Scanner in = new Scanner(System.in);
	static War war = null;

	public static void main(String[] args) throws Exception {
		greetPlayers();

		boolean playersHaveNames = askForNames();

		if (!playersHaveNames) {
			war = new War();
			System.out.println("\nWelcome! Player 1 and Player 2.\n");
		} else {
			setNames();
		}
		war.start();
		handsDealt();

		while (war.getPlayer1().cardsLeft() != 0 && war.getPlayer2().cardsLeft() != 0) {
			Card card1 = war.getPlayer1().getFirstCard();
			Card card2 = war.getPlayer2().getFirstCard();
			displayPlayedCards(card1, card2);
			int result = war.play(card1, card2);

			switch (result) {
			case 1:
				System.out.println(war.getPlayer1().getPlayerName() + " has won this hand.");
				displayCardsLeft();
				continueOrQuit();
				break;
			case 2:
				System.out.println(war.getPlayer2().getPlayerName() + " has won this hand.");
				displayCardsLeft();
				continueOrQuit();
				break;
			case 0:
				displayWarMessage();
				displayKittySize();
				displayCardsLeft();
				continueOrQuit();
				break;
			case 11:
				System.out.println(war.getPlayer1().getPlayerName() + " has no cards left.");
				break;
			case 22:
				System.out.println(war.getPlayer2().getPlayerName() + " has no cards left.");
				break;
			}
		}
		if (war.getPlayer1().cardsLeft() == 0)
			System.out.println(war.getPlayer2().getPlayerName() + " has won the game.");
		else if (war.getPlayer2().cardsLeft() == 0)
			System.out.println(war.getPlayer1().getPlayerName() + " has won the game.");
		System.out.println("Thank you for playing!");
	}

	public static void continueOrQuit() throws InvalidHandSizeException {
		if (war.getPlayer1().cardsLeft() != 0 && war.getPlayer2().cardsLeft() != 0) {
			System.out.println("Hit any key to continue or Q to quit.");
			Scanner input = new Scanner(System.in);
			char status = input.next().toUpperCase().charAt(0);
			if (status == 'Q') {
				System.out.println("Thank you for playing!");
				input.close();
				System.exit(0);
			}
		}
	}

	private static void setNames() {
		String player1, player2;
		do {
			System.out.print("What is the name of Player 1? : ");
			player1 = in.nextLine().trim();
		} while (player1.trim().isEmpty());

		do {
			System.out.print("What is the name of Player 2? : ");
			player2 = in.nextLine().trim();
			if (player1.equals(player2)) {
				System.out.println("Two Players cannot have the same names");
			}
		} while (player1.equals(player2) || player2.trim().isEmpty());

		war = new War(player1.trim(), player2.trim());
		System.out.println("Welcome! " + player1 + " and " + player2 + ".\n");
	}

	private static boolean askForNames() {
		System.out.print(
				"If you'd like to name the players type \"yes\". If you want to use the default names just hit any key: ");

		String answer = in.nextLine().trim();
		if (answer.equalsIgnoreCase("yes")) {
			return true;
		}
		return false;
	}

	private static void handsDealt() throws InvalidHandSizeException {
		System.out.println("Both hands are dealt.\n" + war.getPlayer1().getPlayerName() + " has "
				+ war.getPlayer1().cardsLeft() + " cards to start.\n" + war.getPlayer2().getPlayerName() + " has "
				+ war.getPlayer2().cardsLeft() + " cards to start.\n");
	}

	private static void displayCardsLeft() throws InvalidHandSizeException {
		System.out.println(war.getPlayer1().getPlayerName() + " has " + war.getPlayer1().cardsLeft() + " cards left.\n"
				+ war.getPlayer2().getPlayerName() + " has " + war.getPlayer2().cardsLeft() + " cards left");
	}

	private static void displayPlayedCards(Card card1, Card card2) {
		System.out.println(war.getPlayer1().getPlayerName() + " plays " + card1.toString() + ". "
				+ war.getPlayer2().getPlayerName() + " plays " + card2.toString() + ".");
	}

	private static void displayWarMessage() {
		System.out.println("It's a tie! Each player lays 3 cards face down.");
	}

	private static void displayKittySize() {
		System.out.println("Kitty has " + war.getKitty().size() + " cards.");
	}

	private static void greetPlayers() {
		System.out.println("Welcome to the game of war. \n"
				+ "The object of the game is to force the other player to run out of cards.\n"
				+ "All the cards are dealt at the beginning of the game.\n"
				+ "Each play both players lay the top card of their pile face up. The player with the highest rank card, puts both cards on the bottom of his pile. \n"
				+ "If both cards have the same rank, each player plays three cards face down and plays another round.\n"
				+ "The winner of the tie-breaking round gets all the played cards (the cards in the tie, the six face down and the two in the tie-breaking play.)\n\n");
	}
}
