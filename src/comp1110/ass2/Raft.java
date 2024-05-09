package comp1110.ass2;

public class Raft {
    // this class can be deleted depending on the challenge


    public static void addRaft(Square[][] board, String raftSubstring) {
        int raftId = Integer.parseInt(raftSubstring.substring(1, 2));
        int row = Integer.parseInt(raftSubstring.substring(2, 4));
        int column = Integer.parseInt(raftSubstring.substring(4, Math.min(6, raftSubstring.length())));

        if (row >= 0 && row + 2 < board.length && column >= 0 && column + 2 < board[0].length) {
            for (int r = row; r < row + 3; r++) {
                for (int c = column; c < column + 3; c++) {
                    board[r][c].setColour(Colour.WILD);
                }
            }
        } else {
            System.err.println("Raft location out of bounds: " + raftSubstring);
        }
    }
}
