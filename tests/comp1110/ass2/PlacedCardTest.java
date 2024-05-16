package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlacedCardTest {

    @Test
    public void testGetColorAt() {
        PlacedCard cardN = new PlacedCard("abgbbgybby", 'N', 0, 0);
        assertEquals('b', cardN.getColorAt(0, 0));
        assertEquals('g', cardN.getColorAt(0, 1));
        assertEquals('b', cardN.getColorAt(0, 2));
        assertEquals('b', cardN.getColorAt(1, 0));
        assertEquals('y', cardN.getColorAt(2, 2));

        PlacedCard cardE = new PlacedCard("abgbbgybby", 'E', 0, 0);
        assertEquals('b', cardE.getColorAt(0, 0));
        assertEquals('b', cardE.getColorAt(0, 1));
        assertEquals('b', cardE.getColorAt(0, 2));
        assertEquals('b', cardE.getColorAt(1, 0));
        assertEquals('b', cardE.getColorAt(2, 2));

        PlacedCard cardS = new PlacedCard("abgbbgybby", 'S', 0, 0);
        assertEquals('y', cardS.getColorAt(0, 0));
        assertEquals('b', cardS.getColorAt(0, 1));
        assertEquals('b', cardS.getColorAt(0, 2));
        assertEquals('y', cardS.getColorAt(1, 0));
        assertEquals('b', cardS.getColorAt(2, 2));

        PlacedCard cardW = new PlacedCard("abgbbgybby", 'W', 0, 0);
        assertEquals('b', cardW.getColorAt(0, 0));
        assertEquals('y', cardW.getColorAt(0, 1));
        assertEquals('y', cardW.getColorAt(0, 2));
        assertEquals('g', cardW.getColorAt(1, 0));
        assertEquals('b', cardW.getColorAt(2, 2));
    }
}
