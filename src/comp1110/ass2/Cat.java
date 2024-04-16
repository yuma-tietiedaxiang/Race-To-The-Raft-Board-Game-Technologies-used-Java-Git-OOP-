package comp1110.ass2;

public class Cat {
    /*
    fields:
color
ID: 1-5
location
boolean isExhausted
methods:
move
     */
    private int catId;
    private Colour colour;
    private boolean isExhausted;

    private Location catLocation;

    public Cat(int catId, Colour colour, boolean isExhausted, Location location){
        this.isExhausted = isExhausted;
        this.catId = catId;
        this.catLocation = location ;
        this.colour = colour;
    }

    public Location initialLocation(Location l){
        return null;
    }//set initial location

    public void move(Cat cat){}

    public boolean overLap(BigBoard b){
        return false;
    }//not on fire or cat

}
