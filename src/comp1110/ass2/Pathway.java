package comp1110.ass2;
import comp1110.ass2.Square;
import comp1110.ass2.Colour;
import comp1110.ass2.Location;
import comp1110.ass2.Card;

// author: Yu Ma
public class Pathway extends Card{


    char pathwayId;
    int pathwayNum;

    Location mainLocation;
    Square[][] squares = new Square[3][3];
    Location[][] allLocation;

    // Yu Ma
    //constructor
    public Pathway(String pathwayStr){
        //pathwayStr looks like "abgbbgybby"
        pathwayId = pathwayStr.charAt(0);
        for (int s = 1; s < 10; s++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    squares[i][j] = new Square();
                    squares[i][j].setColour(Colour.fromChar(pathwayStr.charAt(s)));
                    s++;
                }
            }
        }
    }

    //getter
    public Colour getColour(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return squares[row][col].getColour();
        }
        return null;
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
