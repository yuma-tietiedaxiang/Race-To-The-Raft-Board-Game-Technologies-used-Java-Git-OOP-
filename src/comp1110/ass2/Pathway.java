package comp1110.ass2;
import comp1110.ass2.Square;
import comp1110.ass2.Colour;
import comp1110.ass2.Location;
import comp1110.ass2.Card;

public class Pathway extends Card{

    @Override
    public Location setLocation(int b,int a){
        return null;
    }

    public Square setOneSquare(Colour c){
        squares[0][0].setcolour(c);
    }

}
