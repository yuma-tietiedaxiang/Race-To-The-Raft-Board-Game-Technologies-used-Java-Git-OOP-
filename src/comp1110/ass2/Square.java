package comp1110.ass2;

public abstract class Square {

    private Colour colour;
    boolean hasCat;
    boolean hasFire;

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
