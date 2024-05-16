package comp1110.ass2;

// author: Weiqi Huang and Aditya Arora

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

    public static String getSquareImagePathByCharacter(char c) {
        Colour colour = Colour.fromChar(c);

        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (colour == Colour.BLUE) {
            return imagePath + "blue.png";
        } else if (colour == Colour.RED) {
            return imagePath + "red.png";
        } else if (colour == Colour.YELLOW) {
            return imagePath + "yellow.png";
        } else if (colour == Colour.PURPLE) {
            return imagePath + "purple.png";
        } else if (colour == Colour.GREEN) {
            return imagePath + "green.png";
        } else if (colour == Colour.OBJECTIVE) {
            return imagePath + "objective.png";
        } else if (colour == Colour.WILD) {
            return imagePath + "objective.png";
        } else {
            return imagePath + "blue.png";
        }
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

    /**
     * This method is to quickly recover the colour after cat moved
     *
     * @author Yu MA
     * @param catColour is the uppercase colour
     * @return a lowercase colour
     */
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

