package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class FireTile {

    //fields
    char fireID;
    Location mainLocation;
    Square[] fireSquares;
    List<Location> coordinates;
    String info;

    public FireTile(){}


    // constructor use fromString to get coordinates
    public FireTile(char fireID) {
        this.fireID = fireID;
//        System.out.println(this.fireID);
        if (fireID<='z' && fireID>='a'){
            info = Utility.FIRE_TILES[fireID-'a'];
        }else info = Utility.FIRE_TILES[fireID-'A'+26];//"w0010111222"

        this.fireSquares = new Square[(info.length()-1)/2];// 这个fireTile一共有多少个square
        for (int i = 1; i < this.fireSquares.length; i+=2) {
            String location = info.substring(i,i+2);
            this.fireSquares[i-1] = new Square(location,Colour.fromChar('f'));
        }
//        System.out.println(this.fireSquares[2].location);
    }



    // print something like "a000110111221"
    public String toString() {
        StringBuilder fireStr = new StringBuilder();

        fireStr.append(this.fireID);
//        System.out.println("toString: "+ this.fireID);
//        System.out.println("toString: "+ this.fireSquares[2].location);
        for (Square fireSquare : this.fireSquares) {
            if (fireSquare != null) { //********** Check if fireSquare is not null
                fireStr.append(fireSquare.location);
//                System.out.println(fireStr);
            }
        }
//        System.out.println(fireStr);
        return fireStr.toString();
    }

    // convert str to fire tile, return a List<FireTile> that as 31 elements.
    // Index start from 0, fireID start from a to E
//    public static List<FireTile> fromString(String[] fireStrArray) {
//        List<FireTile> result = new ArrayList<>();
//        for (String fs : fireStrArray) {
//            char id = fs.charAt(0);
//            List<Location> coordinates = new ArrayList<>();
//            Square[][] fireSquares = new Square[9][9];
//
//            for (int i = 1; i < fs.length(); i += 2) {
//                int row = Character.getNumericValue(fs.charAt(i));
//                int column = Character.getNumericValue(fs.charAt(i + 1));
//                fireSquares[row][column] = new Square();
//                fireSquares[row][column].setColour(Colour.fromChar('f'));
//                Location location = new Location(row, column);
//                coordinates.add(location);
//            }
//
//            FireTile fireTile = new FireTile(id, coordinates);
//            fireTile.fireSquares = fireSquares;
//            result.add(fireTile);
//        }
//        return result;
//    }



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
