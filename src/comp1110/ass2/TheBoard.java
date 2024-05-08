package comp1110.ass2;

// author: Aditya Arora
public class TheBoard {
    Square[][] squares;
    int rows;//total number of rows
    int columns;//total number of columns
    char[][] squareChar;

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


     String boardToString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares[i].length; j++) {
                sb.append(this.squares[i][j].getColour().toChar());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

