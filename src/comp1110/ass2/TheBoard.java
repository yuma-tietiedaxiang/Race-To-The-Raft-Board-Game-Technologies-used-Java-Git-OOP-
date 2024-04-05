package comp1110.ass2;

import comp1110.ass2.Colour;

public class TheBoard {
    private char[][] squares;
    int rows;
    int columns;

    // Constructor
    public TheBoard(String boardString) {
        String[] list = boardString.split("\\r?\\n");

        rows = list.length;
        columns = list[0].length();
        squares = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            String rowString = list[i];
            for (int j = 0; j < columns; j++) {
                squares[i][j] = rowString.charAt(j);
            }
        }
    }

    public char[][] getSquares() {
        return squares;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Colour getColor(int row, int column) {
        return Colour.fromChar(Character.toLowerCase(squares[row][column]));
//        return squares[row][column];
    }

    // Check if a square has a cat on it
    public boolean hasCat(int row, int column) {
        char square = squares[row][column];
        return Character.isUpperCase(square);
    }


}

