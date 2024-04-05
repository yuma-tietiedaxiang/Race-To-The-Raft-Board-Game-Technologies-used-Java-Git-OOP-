package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class FireTile {

    //fields
    char fireID;
    Location mainLocation;
    Square[][] fireSquares;
    List<Location> coordinates;

    public FireTile(){}

    // constructor use fromString to get coordinates
    public FireTile(char id, List<Location> coordinates) {
        this.fireID = id;
        this.coordinates = coordinates;
        this.fireSquares = new Square[9][9];
    }

    //constructor to initialise
    public FireTile(String[] fireStrArray) {
        //fromString uses the first constructor "public FireTile(char id, List<Location> coordinates)"
        List<FireTile> fireTileList = fromString(fireStrArray);
//        for (FireTile fireTile : fireTileList) {
//            System.out.println(fireTile);
//        }
//        System.out.println("==============");
//        System.out.println(fireTileList.get(4));
//        System.out.println(fireTileList.get(4).fireSquares[1][1].getcolour());
//        return fireTileList;
    }

    // print something like "a000110111221"
    public String toString() {
        StringBuilder fireStr = new StringBuilder();
        fireStr.append(fireID);
        for (Location coordinate : coordinates) {
            fireStr.append(coordinate);
        }
        return fireStr.toString();
    }

    // convert str to fire tile, return a List<FireTile> that as 31 elements.
    // Index start from 0, fireID start from a to E
    public static List<FireTile> fromString(String[] fireStrArray) {
        List<FireTile> result = new ArrayList<>();
        for (String fs : fireStrArray) {
            char id = fs.charAt(0);
            List<Location> coordinates = new ArrayList<>();
            Square[][] fireSquares = new Square[9][9];

            for (int i = 1; i < fs.length(); i += 2) {
                int row = Character.getNumericValue(fs.charAt(i));
                int column = Character.getNumericValue(fs.charAt(i + 1));
                fireSquares[row][column] = new Square();
                fireSquares[row][column].setColour(Colour.fromChar('f'));
                Location location = new Location(row, column);
                coordinates.add(location);
            }

            FireTile fireTile = new FireTile(id, coordinates);
            fireTile.fireSquares = fireSquares;
            result.add(fireTile);
        }
        return result;
    }



    public void rotate(){}

    public void flip(){}

    //should take another tile as input to check if it overlaps with it
    public boolean notOverlap(){
        return false;
    }//not on fire and cat

    public boolean isAdjacent(){
        return false;
    }//adjacent to other fire

}
