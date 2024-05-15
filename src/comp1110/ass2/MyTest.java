package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class MyTest {}
// else {
//        // 火焰块放置
//        int row = Integer.parseInt(placementString.substring(1, 3));
//        int col = Integer.parseInt(placementString.substring(3, 5));
//        boolean flipped = placementString.charAt(5) == 'T';
//        char orientation = placementString.charAt(6);
//
//        // 解析 placement string
//        System.out.println("放置命令 " + placementString);
//        char fireID = placementString.charAt(0);
//        String fireTileString = Utility.FIRE_TILES[fireID - 'a'];
//        int placementRow = Integer.parseInt(placementString.substring(1, 3)); // placement placementRow
//        int placementCol = Integer.parseInt(placementString.substring(3, 5)); // placement placementCol
//
//        // 计算板子最大行列
//        StringBuilder boardBuilder = new StringBuilder(gameState[0]);
//        String[] boardRows = boardBuilder.toString().split("\\r?\\n");
//        int boardMaxRow = boardRows.length;
//        int boardMaxColumn = boardRows[0].length();
//
//        TheBoard currentBoardState = new TheBoard();
//        Square[][] boardSquares = new Square[boardMaxRow][boardMaxColumn];
//        String boardWithSpace = gameState[0];
//        String boardWithoutSpace = boardWithSpace.replaceAll("\\r\\n|\\r|\\n", "");
//        int indexForWithoutSpace = 0;
//
//        for (int i = 0; i < boardMaxRow; i++) {
//        for (int j = 0; j < boardMaxColumn; j++) {
//        boardSquares[i][j] = new Square(Colour.fromChar(boardWithoutSpace.charAt(indexForWithoutSpace)));
//        indexForWithoutSpace++;
//        }
//        }
//
//        currentBoardState.setSquares(boardSquares);
//
//        // 创建 PlacedFireTile 对象
//        PlacedFireTile fireTile = new PlacedFireTile(fireTileString, placementRow, placementCol, flipped, orientation, boardMaxRow, boardMaxColumn);
//
//                // 检查火焰块是否超出棋盘边界
//                for (Square square : fireTile.getSquares()) {
//                        int boardRow = square.getLocation().getRow();
//                        int boardCol = square.getLocation().getColumn();
//                        if (boardRow < 0 || boardRow >= boardRows || boardCol < 0 || boardCol >= boardCols) {
//                                return false;
//                        }
//                }
//
//                // 检查火焰块是否与火焰、猫或筏子卡片重叠
//                for (Square square : fireTile.getSquares()) {
//                        int boardRow = square.getLocation().getRow();
//                        int boardCol = square.getLocation().getColumn();
//                        char squareColor = currentBoardState.getSquares()[boardRow][boardCol].getColour().toChar();
//                        if (squareColor == 'f' || Character.isUpperCase(squareColor) || squareColor == 'o') {
//                                return false;
//                        }
//                }
//
//                // 检查火焰块是否与现有火焰正交相邻
//                boolean isAdjacent = false;
//                for (Square square : fireTile.getSquares()) {
//                        int boardRow = square.getLocation().getRow();
//                        int boardCol = square.getLocation().getColumn();
//                        if ((boardRow > 0 && currentBoardState.getSquares()[boardRow - 1][boardCol].getColour() == Colour.FIRE) ||
//                                (boardRow < boardRows - 1 && currentBoardState.getSquares()[boardRow + 1][boardCol].getColour() == Colour.FIRE) ||
//                                (boardCol > 0 && currentBoardState.getSquares()[boardRow][boardCol - 1].getColour() == Colour.FIRE) ||
//                                (boardCol < boardCols - 1 && currentBoardState.getSquares()[boardRow][boardCol + 1].getColour() == Colour.FIRE)) {
//                                isAdjacent = true;
//                                break;
//                        }
//                }
//                if (!isAdjacent) {
//                        return false;
//                }
//
//                return true;
//        }
//}
