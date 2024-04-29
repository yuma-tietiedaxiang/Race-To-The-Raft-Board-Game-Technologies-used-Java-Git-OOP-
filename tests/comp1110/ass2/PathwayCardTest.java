package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class PathwayCardTest {


    @Test
    public void testCorrectCardParsing() {

        PathwayCard card = new PathwayCard("AfBd");
        List<String> cards = card.getCardsInHand();
        Assertions.assertEquals(2, cards.size());
        Assertions.assertTrue(cards.contains(Utility.DECK_A[5])); // 'f' from DECK_A
        Assertions.assertTrue(cards.contains(Utility.DECK_B[3])); // 'd' from DECK_B
    }

    @Test
    public void testInvalidDeckIdentifier() {
        // This test ensures that an invalid deck identifier results in no cards being added
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PathwayCard card = new PathwayCard("XfYeZg");
        });
    }

    @Test
    public void testMaxCardsInHand() {
        // This test ensures that the class correctly handles holding a maximum of 6 cards
        PathwayCard card = new PathwayCard("AfhkBCDahw");
        List<String> cards = card.getCardsInHand();

        Assertions.assertEquals(6, cards.size()); // Check if the number of cards in hand is 6
    }

    @Test
    public void testEmptyInput() {
        // Check how the class handles empty input
        PathwayCard card = new PathwayCard("");
        Assertions.assertEquals(0, card.getNumberOfCardsInHand());
    }

    @Test
    public void testInvalidCardIndex() {
        // Test that providing an invalid card index results in the corresponding card not being added to the list
        PathwayCard card = new PathwayCard("Aabcz");
        List<String> cards = card.getCardsInHand();
        Assertions.assertEquals(3, cards.size());
    }

    @Test
    public void testMaxNumberOfCards() {
        // Test that providing a string with more than 6 valid cards only adds the first 6 cards to the list
        PathwayCard card = new PathwayCard("AfgsBrteCghDiu");
        List<String> cards = card.getCardsInHand();
        Assertions.assertEquals(6, cards.size()); // Only the first 6 valid cards should be added
    }

}
