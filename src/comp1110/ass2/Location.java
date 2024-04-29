package comp1110.ass2;

// Yu Ma
public class Location {
    /*
    this is a type called Location
     */
    int row;
    int column;

    //Constructor
    public Location(int row,int column){
        this.row = row;
        this.column = column;
    }

    public void setRow(int row){
        this.row = row;
    }
    public void setColumn(int column){
        this.column = column;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

    @Override
    public String toString() {
        return "" + row + column;
    }


}
