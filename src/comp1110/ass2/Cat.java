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
    int catId;
    Colour Color;
    boolean isExhausted;

    Location catLocation;

    public Cat(int catId, Colour Color,boolean isExhausted){
    }

    public Location initialLocation(Location l){
        return null;
    }//set initial location

    public void move(Cat cat){}

    public boolean overLap(BigBoard b){}//not on fire or cat

}
