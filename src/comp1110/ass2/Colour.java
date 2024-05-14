package comp1110.ass2;

// author: Weiqi Huang

public enum Colour {
    RED, BLUE, GREEN, YELLOW, PURPLE, FIRE, NONE, OBJECTIVE, WILD,
    REDCAT,BLUECAT,GREENCAT,YELLOWCAT,PURPLECAT,OBJECTIVECAT,WILDCAT;

    public static Colour fromChar(char colour) {
        return switch (colour) {
            case 'p' -> PURPLE;
            case 'b' -> BLUE;
            case 'g' -> GREEN;
            case 'r' -> RED;
            case 'y' -> YELLOW;
            case 'f' -> FIRE;
            case 'o' -> OBJECTIVE;
            case 'w' -> WILD;

            case 'P' -> PURPLECAT;
            case 'B' -> BLUECAT;
            case 'G' -> GREENCAT;
            case 'R' -> REDCAT;
            case 'Y' -> YELLOWCAT;
            case 'O' -> OBJECTIVECAT;
            case 'W' -> WILDCAT;

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
            case OBJECTIVE ->'o';
            case WILD -> 'w';

            case PURPLECAT -> 'P';
            case BLUECAT -> 'B';
            case GREENCAT -> 'G';
            case REDCAT -> 'R';
            case YELLOWCAT -> 'Y';
            case OBJECTIVECAT -> 'O';
            case WILDCAT -> 'W';

            default -> 'x';
        };
    }

    public static Colour catColourToNormalColour(Colour catColour){
        return switch (catColour) {

            case PURPLECAT -> PURPLE;
            case BLUECAT -> BLUE;
            case GREENCAT -> GREEN;
            case REDCAT -> RED;
            case YELLOWCAT -> YELLOW;

            default -> throw new IllegalStateException("Unexpected value: " + catColour);
        };
    }
}
