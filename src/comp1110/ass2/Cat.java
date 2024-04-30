package comp1110.ass2;

// author: Weiqi Huang

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

//    public Location initialLocation(Location l){
//        return null;
//    }//set initial location
//
//    public void move(Cat cat){}
//
//    public boolean overLap(BigBoard b){
//        return false;
//    }//not on fire or cat
//
//}

    public Colour getColour() {
        return colour;
    }

    public boolean isExhausted() {
        return isExhausted;
    }

    public void setExhausted(boolean exhausted) {
        isExhausted = exhausted;
    }

    public Location getCatLocation() {
        return catLocation;
    }

    public void setCatLocation(Location catLocation) {
        this.catLocation = catLocation;
    }

    public Location initialLocation(Location l) {
        // set initial location
        this.catLocation = l;
        return this.catLocation;
    }

    public void move(Location newLocation) {
        // move the cat to a new location
        this.catLocation = newLocation;
        this.isExhausted = true;
    }

//    public boolean overLap(BigBoard b) {
//        // check if the cat's location overlaps with fire or another cat on the board
//        return b.isOverlap(this.catLocation);
//    }

    @Override
    public String toString() {
        return String.format("%c%02d%02d", colour.toChar(), catLocation.getRow(), catLocation.getColumn());
    }
}


