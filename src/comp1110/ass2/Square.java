package comp1110.ass2;

// author: Yu Ma and Aditya Arora
public class Square {

    private Colour colour;
    boolean hasCat;
    boolean hasFire;
    Location location;

    public Square(){}

    // Yu Ma
    public Square(String location, Colour colour){
        int row = Character.getNumericValue(location.charAt(0));
        int column = Character.getNumericValue(location.charAt(1));
        this.location = new Location(row,column);
//        this.location.setRow(row);
//        this.location.setColumn(column);

        this.colour = colour;
    }


    public void setColour(Colour colour){
        this.colour = colour;
    }
    public Colour getcolour(){
        return colour;
    }
    public void setHasCat(boolean hasCat){
        this.hasCat = hasCat;
    }
    public boolean getHasCat(){
        return hasCat;
    }
    public void setHasFire(boolean hasFire){
        this.hasFire = hasFire;
    }
    public boolean getHasFire(){
        return hasFire;
    }

}
