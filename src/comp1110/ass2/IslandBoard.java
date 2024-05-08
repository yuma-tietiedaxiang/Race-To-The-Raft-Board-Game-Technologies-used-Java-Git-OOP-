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
        //remove '\n' .etc
        String islandStrNoSpace = islandStr.replaceAll("\\r\\n|\\r|\\n", "");

        if (islandStrNoSpace.length() == 81) {//for square shape
            for (int s = 0; s < 81; s++) {//rotate islandStr
                for (int i = 0; i < 9; i++) {//rotate squareShape row
                    for (int j = 0; j < 9; j++) {//rotate squareShape column
                        squareShape[i][j] = new Square();
                        squareShape[i][j].setColour(Colour.fromChar(islandStrNoSpace.charAt(s)));
                        s++;
                    }
                }
            }

        }else {//for rectangle shape
//            System.out.println("在矩形分支");
            for (int s = 0; s < 54; s++) {//rotate islandStr
                for (int i = 0; i < 6; i++) {//rotate rectangleShape row
                    for (int j = 0; j < 9; j++) {//rotate rectangleShape column
                        rectangleShape[i][j] = new Square();
                        rectangleShape[i][j].setColour(Colour.fromChar(islandStrNoSpace.charAt(s)));
//                        System.out.println("i,j "+i+j);
//                        System.out.println(rectangleShape[i][j].getColour());
                        s++;
                    }
                }
            }
        }
    }

    // 顺时针旋转 Square[][]，并输出旋转后的结果
    public Square[][] rotateIslandBoard(Square[][] islandSquares) {//可以用island.getIslandSquaresd得到Square[][]
        int rows = islandSquares.length;
        int cols = islandSquares[0].length;

        // 创建一个新的二维数组用于存储旋转后的结果
        Square[][] rotatedIslandSquares = new Square[cols][rows];//行数是原来的列数

        // 进行矩阵转置
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedIslandSquares[j][i] = islandSquares[i][j];
                rotatedIslandSquares[j][i].setColour(islandSquares[i][j].getColour());//颜色也传过去

            }
        }

        // 水平翻转每一行
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                Square temp = rotatedIslandSquares[i][j];//temp储存左边的Square，有对应的颜色
                rotatedIslandSquares[i][j] = rotatedIslandSquares[i][cols - 1 - j];//水平翻转右边传到左边
                rotatedIslandSquares[i][cols - 1 - j] = temp;//水平翻转左边传到右边
            }
        }
        return rotatedIslandSquares;//输出Square[][]
    }

    //rotate island Board Square[][] many times
    public Square[][] rotateIslandTimes(Square[][] islandSquares, int rotations) {
        for (int i = 0; i < rotations; i++) {
            islandSquares = rotateIslandBoard(islandSquares);
        }
        return islandSquares;
    }


    public Square[][] getIslandSquares(){
        if (this.squareShape[0][0] != null){
            return this.squareShape;
        }else{
            return this.rectangleShape;
        }
    }

}
