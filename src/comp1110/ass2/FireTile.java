package comp1110.ass2;

import java.util.List;

// author: Yu Ma
public class FireTile {

    //fields
    char fireID;
    Location mainLocation;
    Square[] fireSquares;
    List<Location> coordinates;
    String info;

    public FireTile() {}

    // constructor use fromString to get coordinates
    public FireTile(char fireID) {
        this.fireID = fireID;
        String info;
        if (fireID <= 'z' && fireID >= 'a') {
            info = Utility.FIRE_TILES[fireID - 'a'];
        } else {
            info = Utility.FIRE_TILES[fireID - 'A' + 26];
        }

        fireSquares = new Square[(info.length() - 1) / 2];
        for (int i = 0; i < fireSquares.length; i++) {
            String locationString = info.substring(2 * i + 1, 2 * i + 3);
            int row = Integer.parseInt(locationString.substring(0, 1));
            int col = Integer.parseInt(locationString.substring(1, 2));
            Location location = new Location(row, col);
            fireSquares[i] = new Square(location, Colour.FIRE);
        }
    }


    // print something like "a000110111221" same as utility fires
    public String toString() {
        StringBuilder fireStr = new StringBuilder();

        fireStr.append(this.fireID);
        for (Square fireSquare : this.fireSquares) {
            if (fireSquare != null) { // Check if fireSquare is not null
                fireStr.append(fireSquare.location);
            }
        }
        return fireStr.toString();
    }


    /**
     * this method makes the fireTile rotate clockwise of 90 degree
     * @author Yu MA
     * @param drawFireTile is one of the fire strings in utility. eg."a000110111221"
     * @return a string only consists of locations after rotate
     */
    public String rotate(String drawFireTile) {

        String fireLocation = drawFireTile.substring(1);
        StringBuilder afterRotateLocation = new StringBuilder();
        for (int i = 0; i < fireLocation.length(); i += 2) {
            int newj = 5 - fireLocation.charAt(i) + 48 - 1;
            afterRotateLocation.append(fireLocation.charAt(i + 1)).append(newj);
        }
//        System.out.println(afterRotateLocation);
        return afterRotateLocation.toString();
    }

    /**
     * This method is to add fireTiles to the board according to challenge string.
     * Though the board may have inherited fire squares, we need to add other fires.
     * <p>
     * In this part of initializing the game, all added fire tiles are 3*3 shape. And the coordiante
     * refers to the top left location.
     * <p>
     * Return nothing, just update the Square[][] board.
     *
     * @author Yu MA
     * @param board         the Square[][] representing the play board. This is generated by putting 4 islands together
     * @param fireSubstring Substring from challenge. eg. "000300060012001503030903"
     *                      every 2 digit represent a row index or a column index on the play board.
     */
    public static void addFire(Square[][] board, String fireSubstring) {
        while (!fireSubstring.isEmpty()) {
            int row = Integer.parseInt(fireSubstring.substring(0, 2));
            int column = Integer.parseInt(fireSubstring.substring(2, 4));

            if (row >= 0 && row + 2 < board.length && column >= 0 && column + 2 < board[0].length) {

                //rotate through a 3*3 tile
                for (int r = row; r < row + 3; r++) {
                    for (int c = column; c < column + 3; c++) {
                        board[r][c].setColour(Colour.FIRE);
                    }
                }
            } else {
                System.err.println("Fire location out of bounds: " + fireSubstring);
            }

            //update the fireSubstring,delete used ones
            fireSubstring = fireSubstring.substring(4);
        }
    }


    public void flip() {
    }

    //should take another tile as input to check if it overlaps with it
    public boolean notOverlap() {
        return false;
    }//not on fire and cat

    public boolean isAdjacent() {
        return false;
    }//adjacent to other fire


    public Square[] getSquares() {
        return fireSquares;
    }

}

