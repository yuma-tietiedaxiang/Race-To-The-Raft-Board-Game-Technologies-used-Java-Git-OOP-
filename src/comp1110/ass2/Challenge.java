package comp1110.ass2;

public class Challenge {

    //get form the 4 island boards
    int maxRow;
    int maxColumn;
    Location catLocation;
    Location raftLocation;

    Square[][] fireArea = new Square[maxRow][maxColumn];// has locations, colour 'f'

    public boolean Lose(){
        return false;
    }// loosing conditions

}
