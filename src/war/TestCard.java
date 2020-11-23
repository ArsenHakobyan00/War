package war;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCard {

	@Test
	void test_toString() {
		Card card1 = new Card("Spades", "10", 9);
		Card card2 = new Card("Hearts", "2", 1);

		// Must return the correct string
		assertEquals("10 of Spades", card1.toString(), "Testing Valid Equivalence class");
		assertNotEquals("3 of Hearts", card2.toString(), "Testing Invalid Equivalence class");
	}

}
