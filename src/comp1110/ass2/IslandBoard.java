package comp1110.ass2;

// author: Yu Ma
public class IslandBoard {
//    public static void main(String[] args) {
//        IslandBoard i = new IslandBoard("brpygybgygrbgyrpbggyppbyyrpybygbrggrgyrpgbypbpbbgrpryb",1);
//        System.out.println(i.rectangleShape[3][5].getcolour());
//    }

    int islandNum;
    Square[][] squareShape = new Square[9][9];
    Square[][] rectangleShape = new Square[6][9];

    //constructor for 2 shape boards
    public IslandBoard(String islandStr){
//        this.islandNum = islandNum;
        System.out.println(islandStr.length());
        if (islandStr.length() == 81) {//for square shape
            for (int s = 0; s < 81; s++) {//rotate islandStr
                for (int i = 0; i < 9; i++) {//rotate squareShape row
                    for (int j = 0; j < 9; j++) {//rotate squareShape column
                        squareShape[i][j] = new Square();
                        squareShape[i][j].setColour(Colour.fromChar(islandStr.charAt(s)));
                        s++;
                    }
                }
            }

        }else {//for rectangle shape
            for (int s = 0; s < 54; s++) {//rotate islandStr
                for (int i = 0; i < 6; i++) {//rotate rectangleShape row
                    for (int j = 0; j < 9; j++) {//rotate rectangleShape column
                        rectangleShape[i][j] = new Square();
                        rectangleShape[i][j].setColour(Colour.fromChar(islandStr.charAt(s)));
                        s++;
                    }
                }
            }
        }
    }

    public Square[][] getShape(){
        if (this.squareShape != null){
            return this.squareShape;
        }else{
            return this.rectangleShape;
        }
    }

    //TODO
    public String rotateIsland(){
        return "";
    }
}
