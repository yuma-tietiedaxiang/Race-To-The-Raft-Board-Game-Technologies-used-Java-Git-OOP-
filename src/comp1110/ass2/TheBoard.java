package comp1110.ass2;


//import static comp1110.ass2.IslandBoard.generateIslandLayout;


// author: Aditya Arora and Yu Ma
public class TheBoard {
    Square[][] squares;
    public int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上、下、左、右

    int rows;//total number of rows
    int columns;//total number of columns
    char[][] squareChar;

    IslandBoard islandBoard = new IslandBoard();
    public boolean[][] visited;
    //four islandBoards store here. The 3rd and 4th may be null according to challenge string.
    public static Square[][] islandLayout01;
    public static Square[][] islandLayout02;
    public static Square[][] islandLayout03;
    public static Square[][] islandLayout04;


    public TheBoard() {
    }

    // Constructor
    public TheBoard(String boardString) {
        String[] list = boardString.split("\\r?\\n");

        rows = list.length;
        columns = list[0].length();
        System.out.println(rows + " " + columns);
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
        visited=new boolean[rows][columns];
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
        //wht happened
        return squareChar;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
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

    public Colour getColour(int row, int col) {
        return squares[row][col].getColour();
    }

    public char getColourChar(int row, int col) {
        return squares[row][col].getColour().toChar();
    }


    /**
     * this method is to form a board with 4 island boards
     *
     * @param islandSubstring A string from challenge string that represents islands eg."LASNLESA"
     * @return The Square[][] represents all the squares on the play board
     * @author Yu Ma
     */
    public Square[][] formBoard(String islandSubstring) {
        //sorry for this duplication of utility strings. But this method is called in the third layer
        // by another method, and Utility seems to refuse access. I have to copy them here.
        String[][] copiedSquareBoard = {
                // Board 1
                {""" 
            fffgygbyr
            fffgygpby
            fffrrbrgp
            fffbgypbr
            fffpbrpgy
            fffyrygyp
            fffgbbrpb
            fffpggbyg
            fffpypgrr
            """,
                        """
            gbygygbyr
            brpgygpby
            ygbrrbrgp
            bypbgypbr
            gprpbrpgy
            rbgyrygyp
            ybygbbrpb
            ygrpggbyg
            bbypypgrr
            """
                },
                // Board 2
                {""" 
            fffyggybp
            fffpyyrgy
            fffrgbpgb
            fffbrgyrp
            fffppbgrb
            fffbyrpby
            fffbygbrp
            fffprgryg
            fffybyppy
            """,
                        """
            gbbyggybp
            rbgpyyrgy
            ygyrgbpgb
            bprbrgyrp
            gbrppbgrb
            pygbyrpby
            gbybygbrp
            pygprgryg
            gbrybyppy
            """},
                // Board 3
                {""" 
            fffpybygr
            fffrpgbyb
            fffbrbgyr
            fffgpypbg
            fffrbgpry
            fffpyrygb
            fffrggbpg
            fffpbyryb
            fffgybrgy
            """,
                        """
            grgpybygr
            gyprpgbyb
            brbbrbgyr
            gypgpypbg
            pbrrbgpry
            ygppyrygb
            bpyrggbpg
            brypbyryb
            gybgybrgy
            """},
                // Board 4
                {"""
            fffpgyyrb
            fffpbbryp
            fffbgypgb
            fffyybyrg
            fffbgrggy
            fffgpgbgp
            fffbgybpb
            fffpyrgyg
            fffgppbrr
            """,
                        """
            rrbpgyyrb
            ygypbbryp
            bprbgypgb
            rgbyybyrg
            pypbgrggy
            ygygpgbgp
            bprbgybpb
            rybpyrgyg
            bgygppbrr
            """}
        };

        /**
         * Element [x][0] is the side of the board with fire, in the north orientation
         * Element [x][1] is the side of the board without fire, in the north orientation
         */
        String[][] copiedRectangleBoard = {
                // Board 1
                {"""
            fffbrgprg
            fffrbpyby
            fffpgybrr
            fffbybgyp
            fffbrgygg
            fffybpbry
            """,
                        """
            yypbrgprg
            bpgrbpyby
            gbypgybrr
            grgbybgyp
            rypbrgygg
            bpgybpbry
            """},
                // Board 2
                {"""
            fffgrybbr
            fffybgryp
            fffbpbygg
            fffyprypy
            fffrygpbb
            fffbpygyr
            """,
                        """
            bbggrybbr
            grpybgryp
            pgrbpbygg
            gpbyprypy
            bygrygpbb
            grybpygyr
            """},
                // Board 3
                {"""
            fffygybgy
            fffgyrpbg
            fffpbyyrp
            fffgbrggr
            fffpgbypb
            fffgrpryb
            """,
                        """
            brpygybgy
            grbgyrpbg
            gyppbyyrp
            ybygbrggr
            gyrpgbypb
            pbbgrpryb
            """},
                // Board 4
                {"""
            fffbgpgpb
            fffyrgbyr
            fffgybpgy
            fffbrrbgb
            fffpygybp
            fffgpbyry
            """,
                        """
            gyybgpgpb
            prbyrgbyr
            yprgybpgy
            ggybrrbgb
            brgpygybp
            bprgpbyry
            """}
        };

        // Parses island layout strings and generates corresponding character arrays
        int islandCount = islandSubstring.length() / 2; // Calculation of the number of islands

        int boardRow = 0;
        int boardColumn = 0;

        Square[][] board;


        for (int i = 0; i < islandCount; i++) {//iterate all islands one by one

            char size = islandSubstring.charAt(i * 2);//should be L or S
            char orientation = islandSubstring.charAt(i * 2 + 1);//the orientation of each island

            //get all four islands and calculate how big should the board be
            if (i == 0) {
                islandLayout01 = islandBoard.generateIslandLayout(size, orientation, copiedSquareBoard, copiedRectangleBoard);
                boardRow += islandLayout01.length;
                boardColumn += islandLayout01[0].length;
            } else if (i == 1) {
                islandLayout02 = islandBoard.generateIslandLayout(size, orientation, copiedSquareBoard, copiedRectangleBoard);
                boardRow += islandLayout02.length;
            } else if (i == 2) {
                islandLayout03 = islandBoard.generateIslandLayout(size, orientation, copiedSquareBoard, copiedRectangleBoard);
                boardColumn += islandLayout03[0].length;
            } else if (i == 3) {
                islandLayout04 = islandBoard.generateIslandLayout(size, orientation, copiedSquareBoard, copiedRectangleBoard);
            }
        }

        //initialize board
        board = new Square[boardRow][boardColumn];


        //set colours to board squares, according to the order of 4 island boards
        for (int row = 0; row < boardRow; row++) {
            for (int column = 0; column < boardColumn; column++) {

                if (row < islandLayout01.length && column < islandLayout01[0].length) {
                    //1st island
                    board[row][column] = new Square(islandLayout01[row][column].getColour());

                } else if (row >= islandLayout01.length && column < islandLayout01[0].length) {
                    //2nd island
                    board[row][column] = new Square(islandLayout02[row - islandLayout01.length][column].getColour());

                } else if (row < islandLayout01.length && column >= islandLayout01[0].length) {
                    //3rd island
                    board[row][column] = new Square(islandLayout03[row][column - islandLayout01[0].length].getColour());

                } else if (row >= islandLayout01.length && column >= islandLayout01[0].length) {
                    //4th island
                    board[row][column] = new Square(islandLayout04[row - islandLayout01.length][column - islandLayout01[0].length].getColour());
                }
            }
        }

        return board;
    }


    String boardToString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares[0].length; j++) {
                sb.append(this.squares[i][j].getColour().toChar());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public boolean dfs(int startrow, int startcolumn, int endRow, int endCol) {
        if (startrow == endRow && startcolumn == endCol) {
            return true;

        }
        for (int[] direction : directions) {
            int newRow = startrow + direction[0];
            int newCol = startcolumn + direction[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns && !visited[newRow][newCol]) {

                if (!visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    if (dfs(newRow, newCol, endRow, endCol)) return true;
                    visited[newRow][newCol] = false;
                }
            }
        }
        return false;
    }
}


//    public Colour getColour(int row, int col) {
//        if (row < 0 || row >= rows || col < 0 || col >= columns) {
//            // 如果传入的行列索引超出了棋盘边界,则抛出异常或返回默认值
//            return Colour.NONE;
//        }
//        return squares[row][col].getColour();
//    }



