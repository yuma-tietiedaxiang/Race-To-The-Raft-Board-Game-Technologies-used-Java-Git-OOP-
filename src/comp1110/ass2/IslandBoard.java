package comp1110.ass2;


// author: Yu Ma
public class IslandBoard {

    int islandNum;
    Square[][] squareShape = new Square[9][9];
    Square[][] rectangleShape = new Square[6][9];


    public IslandBoard(){}

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
            for (int s = 0; s < 54; s++) {//rotate islandStr
                for (int i = 0; i < 6; i++) {//rotate rectangleShape row
                    for (int j = 0; j < 9; j++) {//rotate rectangleShape column
                        rectangleShape[i][j] = new Square();
                        rectangleShape[i][j].setColour(Colour.fromChar(islandStrNoSpace.charAt(s)));
                        s++;
                    }
                }
            }
        }
    }

    /**
     * this method is used to initialize the game board.
     * this method rotate the islandBoard clockwise of 90 degree
     *
     * @author Yu MA
     * @param islandSquares 2D of Square representing the islandBoard
     * @return a 2D Square representing the islandBoard after rotation
     */
    public Square[][] rotateIslandBoard(Square[][] islandSquares) {// can get Square[][] with island.getIslandSquares
        int rows = islandSquares.length;
        int cols = islandSquares[0].length;

        // Create a new two-dimensional array to store the results of the rotation.
        Square[][] rotatedIslandSquares = new Square[cols][rows];//The number of rows is the original number of columns

        // Perform matrix transposition
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedIslandSquares[j][i] = islandSquares[i][j];
                rotatedIslandSquares[j][i].setColour(islandSquares[i][j].getColour());

            }
        }

        // flip horizontally
        if(rows == cols) {//square shape
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols / 2; j++) {
                    Square temp = rotatedIslandSquares[i][j];//temp stores the Square on the left, with the corresponding colours
                    rotatedIslandSquares[i][j] = rotatedIslandSquares[i][cols - 1 - j];//Horizontal flip right to left
                    rotatedIslandSquares[i][cols - 1 - j] = temp;//Horizontal flip left to right
                }
            }
        }else{//rectangle shape..
            //switch the value of rectangle rows and cols. Because of transposition.
            int rectangleRow = cols;
            int rectangleCol = rows;

            for (int i = 0; i < rectangleRow; i++) {
                for (int j = 0; j < rectangleCol / 2; j++) {
                    Square temp = rotatedIslandSquares[i][j];// temp stores the Square on the left, with the corresponding colours
                    rotatedIslandSquares[i][j] = rotatedIslandSquares[i][rectangleCol - 1 - j];// Horizontal flip right to left
                    rotatedIslandSquares[i][rectangleCol - 1 - j] = temp;// Assign the temp value (left) to the right (rectangleCol - 1 - j)

                }
            }
        }
        return rotatedIslandSquares;
    }

    /**
     * this method is to rotate island Board Square[][] many times
     * @author Yu Ma
     * @param islandSquares 2D of Square representing the islandBoard
     * @param rotations an int representing the number of times to call the rotateIslandBoard method
     * @return a 2D Square representing the islandBoard after rotation
     */
    public Square[][] rotateIslandTimes(Square[][] islandSquares, int rotations) {
        for (int i = 0; i < rotations; i++) {
            islandSquares = rotateIslandBoard(islandSquares);
        }
        return islandSquares;
    }



    /**
     * Rotate each island according to its size and orientation.
     * Delete each island string after it is selected.
     *
     * @author Yu Ma
     * @param size a char got from a challenge string. It should be L or S
     * @param orientation a char got from a challenge string. It should be N,S,E,W or A
     * @param copiedSquareBoard Square board strings in Utility is final. So use a copied one that can be altered.
     * @param copiedRectangleBoard Rectangle board strings in Utility is final. So use a copied one that can be altered.
     * @return 2D of Square representing the islandBoard after rotation
     */
    public Square[][] generateIslandLayout(char size, char orientation, String[][] copiedSquareBoard, String[][] copiedRectangleBoard) {

//        System.out.println("get into generateIslandLayout!");
        IslandBoard chooseIsland;
        Square[][] chooseIslandSquares = new Square[0][];
        int indexRow;


        if (size == 'S' && orientation != 'A') {
            //Use one island Board at a time, cannot be used twice
            for(indexRow = 0; indexRow < 4; indexRow++){
                if(copiedRectangleBoard[indexRow][0] != null){
                    break;
                }
            }
            //select one from the rectangular board by order, and replace it with null
            String randomChooseIsland = copiedRectangleBoard[indexRow][0];
            chooseIsland = new IslandBoard(randomChooseIsland);
            copiedRectangleBoard[indexRow][0] = null;
            chooseIslandSquares = chooseIsland.getIslandSquares();

            // Rotate according to the orientation
            switch (orientation) {
                case 'N':
                    return chooseIslandSquares;
                case 'S':
                    return chooseIsland.rotateIslandTimes(chooseIslandSquares, 2);
                case 'E':
                    return chooseIsland.rotateIslandTimes(chooseIslandSquares, 1);
                case 'W':
                    return chooseIsland.rotateIslandTimes(chooseIslandSquares, 3);
            }

        } else if(size == 'L' && orientation != 'A'){
            //Use one island Board at a time, cannot be used twice
            for(indexRow = 0; indexRow < 4; indexRow++){
                if(copiedSquareBoard[indexRow][0] != null){
                    break;
                }
            }
            //select one from the Square board by order, and replace it with null
            String randomChooseIsland = copiedSquareBoard[indexRow][0];
            chooseIsland = new IslandBoard(randomChooseIsland);
            copiedSquareBoard[indexRow][0] = null;
            chooseIslandSquares = chooseIsland.getIslandSquares();
            // Rotate according to the orientation
            switch (orientation) {
                case 'N':
                    return chooseIslandSquares;
                case 'S':
                    return chooseIsland.rotateIslandTimes(chooseIslandSquares, 2);
                case 'E':
                    return chooseIsland.rotateIslandTimes(chooseIslandSquares, 1);
                case 'W':
                    return chooseIsland.rotateIslandTimes(chooseIslandSquares, 3);
            }

        //use other side of the island board
        }else if(size == 'S' && orientation == 'A'){
            //Use one island Board at a time, cannot be used twice
            for(indexRow = 0; indexRow < 4; indexRow++){
                if(copiedRectangleBoard[indexRow][1] != null){
                    break;
                }
            }
            //select one from the rectangular board by order, and replace it with null
            String randomChooseIsland = copiedRectangleBoard[indexRow][1];
            chooseIsland = new IslandBoard(randomChooseIsland);
            copiedRectangleBoard[indexRow][1] = null;
            chooseIslandSquares = chooseIsland.getIslandSquares();


        //use other side of the island board
        }else if(size == 'L' && orientation == 'A'){
            //Use one island Board at a time, cannot be used twice
            for(indexRow = 0; indexRow < 4; indexRow++){
                if(copiedSquareBoard[indexRow][1] != null){
                    break;
                }
            }
            //select one from the Square board by order, and replace it with null
            String randomChooseIsland = copiedSquareBoard[indexRow][1];
            chooseIsland = new IslandBoard(randomChooseIsland);
            copiedSquareBoard[indexRow][1] = null;
            chooseIslandSquares = chooseIsland.getIslandSquares();
        }
        
        return chooseIslandSquares;
    }


    //getter
    public Square[][] getIslandSquares(){
        if (this.squareShape[0][0] != null){
            return this.squareShape;
        }else{
            return this.rectangleShape;
        }
    }

}
