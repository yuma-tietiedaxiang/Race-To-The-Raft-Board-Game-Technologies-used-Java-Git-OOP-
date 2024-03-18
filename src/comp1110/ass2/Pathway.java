package comp1110.ass2;
import comp1110.ass2.Square;
import comp1110.ass2.Colour;
import comp1110.ass2.Location;
import comp1110.ass2.Card;

public class Pathway extends Card{

    int pthId;//should no more than 6

    Location mainLocation;
    Square[][] squares = new Square[3][3];

    Location[][] allLocation;


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

    public boolean isOverlap(){//pathway cannot overlap fire and cat
        return true;
    }

}
