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

    public FireTile(){}
//
//    public FireTile(String locations){
//        for (int i = 0; i < locations.length(); i+=2) {
//
//        }
//    }


    // constructor use fromString to get coordinates
    public FireTile(char fireID) {
        this.fireID = fireID;
//        System.out.println(this.fireID);
        if (fireID<='z' && fireID>='a'){
            info = Utility.FIRE_TILES[fireID-'a'];
        }else info = Utility.FIRE_TILES[fireID-'A'+26];//"w0010111222"

        fireSquares = new Square[(info.length()-1)/2];// 这个fireTile一共有多少个square
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




    public String rotate(String drawFireTile){//input str like "a000110111221"

        String fireLocation = drawFireTile.substring(1);
        StringBuilder afterRotateLocation = new StringBuilder();
        for (int i = 0; i < fireLocation.length(); i+=2) {
            int newj = 4 - fireLocation.charAt(i)+48 -1;
            afterRotateLocation.append(fireLocation.charAt(i+1)).append(newj);
        }
//        System.out.println(afterRotateLocation);
        return afterRotateLocation.toString();
    }


    public void flip(){}

    //should take another tile as input to check if it overlaps with it
    public boolean notOverlap(){
        return false;
    }//not on fire and cat

    public boolean isAdjacent(){
        return false;
    }//adjacent to other fire

}
