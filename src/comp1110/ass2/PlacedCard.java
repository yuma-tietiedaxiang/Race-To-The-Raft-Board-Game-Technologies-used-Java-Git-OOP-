package comp1110.ass2;

public class PlacedCard {
    private Pathway pathway;

    private char orientation;
    private int row;
    private int col;

    public PlacedCard(String cardData, char orientation, int row, int col) {
        this.pathway = new Pathway(cardData);
        this.orientation = orientation;
        this.row = row;
        this.col = col;
    }

    public char getColorAt(int i, int j) {
        // 根据 orientation 调整 i 和 j 的值
        int adjustedI = i;
        int adjustedJ = j;
        switch (orientation) {
            case 'N':
                // 不需要调整
                break;
            case 'E':
                adjustedI = 2 - j;
                adjustedJ = i;
                break;
            case 'S':
                adjustedI = 2 - i;
                adjustedJ = 2 - j;
                break;
            case 'W':
                adjustedI = j;
                adjustedJ = 2 - i;
                break;
        }

        // 获取 Pathway 对象中对应位置的颜色
        return pathway.squares[adjustedI][adjustedJ].getColour().toChar();
    }

    // 其他方法...
}

