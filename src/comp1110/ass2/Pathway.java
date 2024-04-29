package comp1110.ass2;
import comp1110.ass2.Square;
import comp1110.ass2.Colour;
import comp1110.ass2.Location;
import comp1110.ass2.Card;

// Yu Ma
public class Pathway extends Card{
//    public static void main(String[] args) {
//        Pathway p = new Pathway("abgbbgybby",1);
//        System.out.println(p.pathwayId);
//        System.out.println(p.squares[2][2].getcolour());
//
//    }

    char pathwayId;
    int pathwayNum;

    Location mainLocation;
    Square[][] squares = new Square[3][3];
    Location[][] allLocation;

    // Yu Ma
    //constructor
    public Pathway(String pathwayStr){
        //"abgbbgybby"
        pathwayId = pathwayStr.charAt(0);
//        this.pathwayNum = pathwayNum;
        for (int s = 1; s < 10; s++) {//rotate pathwayStr
            for (int i = 0; i < 3; i++) {//rotate squares row
                for (int j = 0; j < 3; j++) {//rotate squares column
                    squares[i][j] = new Square();
                    squares[i][j].setColour(Colour.fromChar(pathwayStr.charAt(s)));
                    s++;
                }
            }
        }
    }

    @Override
    public void setMainLocation(int b,int a){}//set main location

    public Location[][] getAllLocations(Location mainLocation){
        return null;
    }// calculate all locations of this pathway

    public Pathway fromDeck(char c){
        return null;
    }//select pathways from 4 deck, should be if-else

    public void rotate(){}//rotate this pathway card

    public void discard(){}//discard this pathway



}
