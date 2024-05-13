package comp1110.ass2;

import javax.lang.model.type.NullType;

/**
 * This enumeration type represents the 3 animal types.
 * <p>
 * Notice that this is an enumeration type, so none of the fields change once the type is created (they are all declared final).
 * </p>
 */

// author: Weiqi Huang
public enum DeckType {
    CIRCLE, CROSS, SQUARE, TRIANGLE,NONE;

    /**
     * Given an upper-case character, return the Type associated with this character.
     * If the character doesn't have an associated orientation, return `NONE`
     *
     * @param type the given char
     * @return the Type associated with this char.
     */
    public static DeckType fromChar(char type) {
        return switch (type) {
            case 'A' -> CIRCLE;
            case 'B' -> CROSS;
            case 'C' -> SQUARE;
            case 'D' -> TRIANGLE;
            default -> NONE;
        };
    }

    /**
     * @return the representative char for this Type.
     */
    public char toChar() {
        return switch (this) {
            case CIRCLE -> 'A';
            case CROSS -> 'B';
            case SQUARE -> 'C';
            case TRIANGLE -> 'D';
            case NONE -> 'N';
        };
    }
}
