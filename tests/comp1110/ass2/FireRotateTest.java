package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireRotateTest {

    @Test
    void rotateA() {
        var fireTile = new FireTile();
        String afterRotateLocation = fireTile.rotate("a000110111221");
//        System.out.println(fireTile.toString());
        assertEquals("031302122211",afterRotateLocation);
    }

    @Test
    void rotateM() {
        var fireTile = new FireTile();
        String afterRotateLocation = fireTile.rotate("m00011112");
        assertEquals("03131222",afterRotateLocation);
    }

    @Test
    void rotateUpperE() {
        var fireTile = new FireTile();
        String afterRotateLocation = fireTile.rotate("E000102121323");
        assertEquals("031323223231",afterRotateLocation);
    }
}