package comp1110.ass2;

public class PlacedFireTile {
    private FireTile fireTile;
    private int row;
    private int col;
    private boolean flipped;
    private char orientation;

    public PlacedFireTile(char fireID, int row, int col, boolean flipped, char orientation) {
        this.fireTile = new FireTile(fireID);
        this.row = row;
        this.col = col;
        this.flipped = flipped;
        this.orientation = orientation;
    }

    public Square[] getSquares() {
        Square[] originalSquares = fireTile.getSquares();
        Square[] placedSquares = new Square[originalSquares.length];

        for (int i = 0; i < originalSquares.length; i++) {
            if (originalSquares[i] != null) {
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

                placedSquares[i] = new Square(new Location(adjustedRow, adjustedCol), originalSquares[i].getColour());
            }
        }

        return placedSquares;
    }
}