package comp1110.ass2;

import java.util.Arrays;

/*
这个类是用来
 */

public class PlacedFireTile {
    private String fireTileString;
    private int row;
    private int col;
    private boolean flipped;
    private char orientation;
    private int boardMaxRows;
    private int boardMaxCols;

    public PlacedFireTile(String fireTileString, int row, int col, boolean flipped, char orientation, int boardMaxRows, int boardMaxCols) {
        this.fireTileString = fireTileString;
        this.row = row;
        this.col = col;
        this.flipped = flipped;
        this.orientation = orientation;
        this.boardMaxRows = boardMaxRows;
        this.boardMaxCols = boardMaxCols;
    }


    //Get the Square[] after being flipped and rotated.
    public Square[] getSquares() {
        char[][] fireTile = readFireTile(fireTileString);
        Square[] originalSquares = new Square[countSquares(fireTile)];//Square[6]
        int squareIndex = 0;

        //fire squares before flip and rotate,has its own location
        for (int i = 0; i < fireTile.length; i++) {
            for (int j = 0; j < fireTile[0].length; j++) {
                if (fireTile[i][j] == '#') {
                    originalSquares[squareIndex] = new Square(new Location(i, j), Colour.FIRE);
                    squareIndex++;
                }
            }
        }


        Square[] placedSquares = new Square[originalSquares.length];//record squares after flip and rotate
        int validSquares = 0;

        int maxRow = 0;
        int maxCol = 0;

        //Get the maximum rows and columns of the final fire square
        for (Square square : originalSquares) {
            int row = square.getLocation().getRow();
            int col = square.getLocation().getColumn();
            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }

        //flip and rotate each square according to orientation
        for (Square square : originalSquares) {
            int adjustedRow = square.getLocation().getRow();
            int adjustedCol = square.getLocation().getColumn();

            if (flipped) {
                adjustedCol = maxCol - adjustedCol;
            }

            switch (orientation) {
                case 'N':
                    break;
                case 'E':
                    int tempRow = adjustedRow;
                    adjustedRow = adjustedCol;
                    adjustedCol = maxRow - tempRow;
                    break;
                case 'S':
                    adjustedRow = maxRow - adjustedRow;
                    adjustedCol = maxCol - adjustedCol;
                    break;
                case 'W':
                    int tempCol = adjustedCol;
                    adjustedCol = adjustedRow;
                    adjustedRow = maxCol - tempCol;
                    break;
            }


            placedSquares[validSquares] = new Square(new Location(adjustedRow, adjustedCol), Colour.FIRE);
            validSquares++;

        }

        return Arrays.copyOf(placedSquares, validSquares);
    }

    private char[][] readFireTile(String fireTileString) {
        char id = fireTileString.charAt(0);
        String fireLocations = fireTileString.substring(1);
        int[] coords = new int[fireLocations.length()];


        for (int i = 0; i < fireLocations.length(); i++) {
            coords[i] = fireLocations.charAt(i)-'0';
        }

        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < coords.length; i++) {
            if (i % 2 == 0) { // Even indexes indicate the number of rows
                maxRow = Math.max(maxRow, coords[i]);
            } else { // Odd indexes indicate the number of columns
                maxCol = Math.max(maxCol, coords[i]);
            }
        }

        char[][] fireTile = new char[maxRow+1][maxCol+1];

        for (int i = 0; i+1 < coords.length; i += 2) {

            int row = coords[i];
            int col = coords[i + 1];
            fireTile[row][col] = '#';

        }

        return fireTile;
    }

    private int countSquares(char[][] fireTile) {
        int count = 0;
        for (char[] row : fireTile) {
            for (char c : row) {
                if (c == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}