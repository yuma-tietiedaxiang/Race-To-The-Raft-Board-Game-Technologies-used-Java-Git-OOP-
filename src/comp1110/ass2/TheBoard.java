package comp1110.ass2;

import java.util.Arrays;

import static comp1110.ass2.IslandBoard.generateIslandLayout;

// author: Aditya Arora
public class TheBoard {
    Square[][] squares;
    int rows;//total number of rows
    int columns;//total number of columns
    char[][] squareChar;
    //four islandBoards store here. The 3rd and 4th may be null according to challenge string.
    public static Square[][] islandLayout01;
    public static Square[][] islandLayout02;
    public static Square[][] islandLayout03;
    public static Square[][] islandLayout04;


    public TheBoard(){}

    // Constructor
    public TheBoard(String boardString) {
        String[] list = boardString.split("\\r?\\n");

        rows = list.length;
        columns = list[0].length();
        System.out.println(rows+" "+ columns);
        this.squares = new Square[rows][columns];
        System.out.println(this.squares[0].length);

        for (int i = 0; i < rows; i++) {
            String rowString = list[i];
            for (int j = 0; j < columns; j++) {
                this.squares[i][j] = new Square();
                this.squares[i][j].setColour(Colour.fromChar(rowString.charAt(j)));
            }
        }
        this.squareChar = getSquares();
    }

    //here converts Square[][] into char[][]
    public char[][] getSquares() {
        int row = this.squares.length;
        int column = this.squares[0].length;
        char[][] squareChar = new char[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                squareChar[i][j] = squares[i][j].getColour().toChar();
            }
        }
        return squareChar;
    }
    public void setSquares(Square[][] squares){
        this.squares = squares;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Colour getColor(int row, int column) {
        return Colour.fromChar(Character.toLowerCase(squareChar[row][column]));
//        return squares[row][column];
    }

    // Check if a square has a cat on it
    public boolean hasCat(int row, int column) {
        char square = squareChar[row][column];
        return Character.isUpperCase(square);
    }


    /**
     * this method is to form a board with 4 island boards
     * @param islandSubstring A string from challenge string that represents islands eg."LASNLESA"
     * @return The Square[][] represents all the squares on the play board
     */
    public static Square[][] formBoard(String islandSubstring) {
        // 解析岛屿布局字符串并生成对应的字符数组
        int islandCount = islandSubstring.length() / 2; // 计算岛屿数量
        System.out.println("island count: " + islandCount);

        int boardRow = 0;
        int boardColumn = 0;

        Square[][] board;


        for (int i = 0; i < islandCount; i++) {//rotate all islands one by one
            // 解析岛屿子字符串
            char size = islandSubstring.charAt(i * 2);//should be L or S
            char orientation = islandSubstring.charAt(i * 2 + 1);//the orientation of each island
//            System.out.println("现在i=" + i);

            //get all four islands and calculate how big should the board be
            if (i == 0) {
                islandLayout01 = generateIslandLayout(size, orientation, islandSubstring);
//                System.out.println("landLayout01有没有颜色？" + islandLayout01[0][0].getColour());
                boardRow += islandLayout01.length;
                boardColumn += islandLayout01[0].length;
            } else if (i == 1) {
                islandLayout02 = generateIslandLayout(size, orientation, islandSubstring);
//                System.out.println("landLayout02有没有颜色？" + islandLayout02[0][0].getColour());
                boardRow += islandLayout02.length;
            } else if (i == 2) {
                islandLayout03 = generateIslandLayout(size, orientation, islandSubstring);
//                System.out.println("landLayout03有没有颜色？" + islandLayout03[0][0].getColour());
                boardColumn += islandLayout03[0].length;
            } else if (i == 3) {
                islandLayout04 = generateIslandLayout(size, orientation, islandSubstring);
//                System.out.println("landLayout04有没有颜色？" + islandLayout04[0][0].getColour());
            }
        }

        //initialize board
        board = new Square[boardRow][boardColumn];
//        System.out.println("1&4 行和列 "+ islandLayout01.length +'\t'+ islandLayout04.length +'\t'
//                + islandLayout01[0].length +'\t'+ islandLayout04[0].length +'\t');
//        System.out.println("board row column "+boardRow+" "+boardColumn);


        //set colours to board squares, according to the order of 4 island boards
        for (int row = 0; row < boardRow; row++) {
            for (int column = 0; column < boardColumn; column++) {

                if (row < islandLayout01.length && column < islandLayout01[0].length){
                    //1st island
//                    System.out.println("1st位置: "+row+" "+column);
                    board[row][column] = new Square(islandLayout01[row][column].getColour());

                }else if(row >= islandLayout01.length && column < islandLayout01[0].length){
                    //2nd island
//                    System.out.println("2nd位置: "+(row-islandLayout01.length)+" "+column);
                    board[row][column] = new Square(islandLayout02[row-islandLayout01.length][column].getColour());

                }else if(row < islandLayout01.length && column >= islandLayout01[0].length){
                    //3rd island
//                    System.out.println("3rd位置: "+row+" "+(column-islandLayout01[0].length));
                    board[row][column] = new Square(islandLayout03[row][column-islandLayout01[0].length].getColour());

                }else if(row >= islandLayout01.length && column >= islandLayout01[0].length){
                    //4th island
//                    System.out.println();
//                    System.out.println("板上位置："+row +" "+ column);
//                    System.out.println("4st位置: "+(row-islandLayout01.length)+" "+(column-islandLayout01[0].length));
                    board[row][column] = new Square(islandLayout04[row-islandLayout01.length][column-islandLayout01[0].length].getColour());
                }
            }
        }

        System.out.println("formBoard结束，板子生成");
        return board;
    }


     String boardToString() {
        StringBuilder sb = new StringBuilder();
         System.out.println(this.squares.length);
         System.out.println(this.squares[0].length);

        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares[0].length; j++) {
                sb.append(this.squares[i][j].getColour().toChar());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

