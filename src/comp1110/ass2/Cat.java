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

    public void move(Cat cat){}

    public boolean sameLocation(Location catLocation){// cannot be in a same location with other cat
        return true;
    }




}
