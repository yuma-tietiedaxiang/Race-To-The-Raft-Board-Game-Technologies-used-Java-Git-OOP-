package comp1110.ass2;

public class Card {
    /*
    every card(islands, pathway, fire, raft) is a card
     */
    Location mainLocation;//say the upper left point is the mainLocation
    Square[][] squares;//2D


    public Card(){}//constructor

    public Location setLocation(int row, int column){}

    public Location[] getAllLocations(Location mianLocation){}//get all locations on this tile
}
