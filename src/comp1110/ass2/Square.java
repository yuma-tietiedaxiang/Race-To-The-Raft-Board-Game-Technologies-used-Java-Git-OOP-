package comp1110.ass2;

// author: Yu Ma and Aditya Arora
public class Square {

    Colour colour;
    boolean hasCat;
    boolean hasFire;
    Location location;

    public Square(){
        this.location = new Location(0, 0);
    }

    public Square(Colour colour){
        this.colour = colour;
        this.location = new Location(0, 0);
    }

    // Yu Ma
    public Square(String location, Colour colour){
        if (location.length() != 2 || !Character.isDigit(location.charAt(0)) || !Character.isDigit(location.charAt(1))) {
            throw new IllegalArgumentException("Invalid location string: " + location);
        }
        int row = Character.getNumericValue(location.charAt(0));
        int column = Character.getNumericValue(location.charAt(1));
        this.location = new Location(row,column);
        this.colour = colour;
    }

    public Square(Location location, Colour colour) {
        this.location = location;
        this.colour = colour;
    }

    public Colour setColour(Colour colour){
        this.colour = colour;
        return colour;
    }

    public Colour getColour(){
        return this.colour;
    }


    public Location setLocation(Colour colour){
        this.location = location;
        return location;
    }

    public Location getLocation(){
        return this.location;
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

