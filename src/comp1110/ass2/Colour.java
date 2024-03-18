package comp1110.ass2;

public enum Colour {
    RED, BLUE, GREEN, YELLOW, PURPLE, FIRE, NONE;

    public static Colour fromChar(char colour) {
        return switch (colour) {
            case 'p' -> PURPLE;
            case 'b' -> BLUE;
            case 'g' -> GREEN;
            case 'r' -> RED;
            case 'y' -> YELLOW;
            case 'f' -> FIRE;
            default -> NONE;
        };
    }

    public char toChar() {
        return switch (this) {
            case PURPLE -> 'p';
            case BLUE -> 'b';
            case GREEN -> 'g';
            case RED -> 'r';
            case YELLOW -> 'y';
            case FIRE -> 'f';
            default -> 'x';
        };
    }
}
