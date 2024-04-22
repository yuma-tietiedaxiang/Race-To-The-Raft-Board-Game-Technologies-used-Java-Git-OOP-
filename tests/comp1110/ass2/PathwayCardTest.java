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
        System.out.println("Namaste Mummy and Papa!! " + cards.get(1));
        System.out.println("Namaste Mummy and Papa!! " + Utility.DECK_B[3]);
        Assertions.assertTrue(cards.contains(Utility.DECK_B[3])); // 'd' from DECK_B
    }

    @Test
    public void testInvalidDeckIdentifier() {
        // This test ensures that an invalid deck identifier results in no cards being added
        PathwayCard card = new PathwayCard("XfYeZg");
        Assertions.assertEquals(0, card.getNumberOfCardsInHand());
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

}
