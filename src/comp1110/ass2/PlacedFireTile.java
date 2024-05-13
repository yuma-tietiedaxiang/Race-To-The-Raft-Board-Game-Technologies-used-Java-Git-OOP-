package comp1110.ass2;

import java.util.Arrays;

public class PlacedFireTile {
    private FireTile fireTile;
    private int row;
    private int col;
    private boolean flipped;
    private char orientation;
    private int boardRows;
    private int boardCols;

    public PlacedFireTile(char fireID, int row, int col, boolean flipped, char orientation, int boardRows, int boardCols) {
        this.fireTile = new FireTile(fireID);
        this.row = row;
        this.col = col;
        this.flipped = flipped;
        this.orientation = orientation;
        this.boardRows = boardRows;
        this.boardCols = boardCols;
    }

    public Square[] getSquares() {
        Square[] originalSquares = fireTile.getSquares();
        Square[] placedSquares = new Square[originalSquares.length];
        int validSquares = 0;

        for (int i = 0; i < originalSquares.length; i++) {
            int adjustedRow = originalSquares[i].getLocation().getRow();
            int adjustedCol = originalSquares[i].getLocation().getColumn();

            // 应用翻转
            if (flipped) {
                adjustedCol = 2 - adjustedCol;
            }

            // 应用旋转
            switch (orientation) {
                case 'N':
                    // 不需要调整
                    break;
                case 'E':
                    int tempRow = adjustedRow;
                    adjustedRow = 2 - adjustedCol;
                    adjustedCol = tempRow;
                    break;
                case 'S':
                    adjustedRow = 2 - adjustedRow;
                    adjustedCol = 2 - adjustedCol;
                    break;
                case 'W':
                    int tempCol = adjustedCol;
                    adjustedCol = 2 - adjustedRow;
                    adjustedRow = tempCol;
                    break;
            }

            // 应用偏移
            adjustedRow += row;
            adjustedCol += col;



            if (adjustedRow >= 0 && adjustedRow < boardRows && adjustedCol >= 0 && adjustedCol < boardCols) {
                placedSquares[validSquares++] = new Square(new Location(adjustedRow, adjustedCol), Colour.FIRE);
            }
        }

        return Arrays.copyOf(placedSquares, validSquares);
    }
}