package comp1110.ass2;

public class PlacedCard {
    private Pathway pathway;

    private char orientation;
    private int row;
    private int col;

    public PlacedCard(String cardData, char orientation, int row, int col) {
        this.pathway = new Pathway(cardData);
        this.orientation = orientation;
        this.row = row;
        this.col = col;
    }

    public char getColorAt(int i, int j) {
        // Adjust the values of i and j according to orientation
        int adjustedI = i;
        int adjustedJ = j;
        switch (orientation) {
            case 'N':
                // no adjustment
                break;
            case 'E':
                adjustedI = 2 - j;
                adjustedJ = i;
                break;
            case 'S':
                adjustedI = 2 - i;
                adjustedJ = 2 - j;
                break;
            case 'W':
                adjustedI = j;
                adjustedJ = 2 - i;
                break;
        }

        // Get the colour of the corresponding position in the Pathway object.
        return pathway.squares[adjustedI][adjustedJ].getColour().toChar();
    }


}

