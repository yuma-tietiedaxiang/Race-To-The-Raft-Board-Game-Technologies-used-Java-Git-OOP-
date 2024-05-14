package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Yu Ma
class FireRotateTest {

    @Test
    void rotate_d() {
        var fireTile = new FireTile();
        String afterRotateLocation = fireTile.rotate("d000102121314");
        assertEquals("041424233343",afterRotateLocation);
    }
}