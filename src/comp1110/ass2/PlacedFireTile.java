package comp1110.ass2;

import java.util.Arrays;

public class PlacedFireTile {
    private String fireTileString;
    private int row;
    private int col;
    private boolean flipped;
    private char orientation;
    private int boardRows;
    private int boardCols;

    public PlacedFireTile(String fireTileString, int row, int col, boolean flipped, char orientation, int boardRows, int boardCols) {
        this.fireTileString = fireTileString;
        this.row = row;
        this.col = col;
        this.flipped = flipped;
        this.orientation = orientation;
        this.boardRows = boardRows;
        this.boardCols = boardCols;
    }

    public Square[] getSquares() {
        char[][] fireTile = readFireTile(fireTileString);
        Square[] originalSquares = new Square[countSquares(fireTile)];
        int squareIndex = 0;

        for (int i = 0; i < fireTile.length; i++) {
            for (int j = 0; j < fireTile[0].length; j++) {
                if (fireTile[i][j] == '#') {
                    originalSquares[squareIndex++] = new Square(new Location(i, j), Colour.FIRE);
                }
            }
        }

        Square[] placedSquares = new Square[originalSquares.length];
        int validSquares = 0;

        int maxRow = 0;
        int maxCol = 0;

        for (Square square : originalSquares) {
            int row = square.getLocation().getRow();
            int col = square.getLocation().getColumn();
            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }

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

            if (adjustedRow >= 0 && adjustedRow < boardRows && adjustedCol >= 0 && adjustedCol < boardCols) {
                placedSquares[validSquares++] = new Square(new Location(adjustedRow, adjustedCol), Colour.FIRE);
            }
        }

        return Arrays.copyOf(placedSquares, validSquares);
    }

    private char[][] readFireTile(String fireTileString) {
        char id = fireTileString.charAt(0);
        int[] coords = new int[fireTileString.length() - 1];

        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < coords.length; i++) {
            if (i % 2 == 0) { // 偶数索引表示行数
                maxRow = Math.max(maxRow, coords[i]);
            } else { // 奇数索引表示列数
                maxCol = Math.max(maxCol, coords[i]);
            }
        }

        char[][] fireTile = new char[maxRow + 1][maxCol + 1];

        for (int i = 0; i < coords.length; i++) {
            int row = coords[i] < 10 ? coords[i] : coords[i] / 10;
            int col = coords[i] < 10 ? 0 : coords[i] % 10;
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