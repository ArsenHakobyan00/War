package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import war.Card;

class TestCard {

	@Test
	void test_toString() {
		Card card1 = new Card("Spades", "10",0);
		Card card2 = new Card("Hearts", "2",0);

		// Must return the correct string
		assertEquals("10 of Spades", card1.toString(), "Testing Valid Equivalence class");
		assertNotEquals("3 of Hearts", card2.toString(), "Testing Invalid Equivalence class");
	}

}
