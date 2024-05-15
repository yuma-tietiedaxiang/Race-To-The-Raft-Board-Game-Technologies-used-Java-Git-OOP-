package comp1110.ass2;

//import org.junit.jupiter.api.Assertions;

import java.util.*;

import static comp1110.ass2.Cat.addCats;
import static comp1110.ass2.FireTile.addFire;
import static comp1110.ass2.Raft.addRaft;

//import static comp1110.ass2.TheBoard.formBoard;

/**
 * This class is for testing purposes only. You should create and use your own objects to solve the tasks below
 * instead of directly using the strings provided. Note that Task 2 is the only task you are expected to use string
 * manipulation to solve.
 */
public class RaceToTheRaft {

    public static HashMap<Integer, String[]> challenges = new HashMap<>();
    public static TheBoard theBoard = new TheBoard();



    /**
     * Determine whether a boardState string is well-formed.
     * To be well-formed the string must satisfy all the following conditions:
     * <p>
     * 1. Each line is terminated by a newline character `\n`
     * 2. The number of printable (non-newline) characters in each line is equal AND is either 9 or 18.
     * 3. Each character (except the newline character) is one of the following:
     * - 'b' (blue)
     * - 'B' (blue with blue cat)
     * - 'f' (fire)
     * - 'g' (green)
     * - 'G' (green with green cat)
     * - 'n' (none)
     * - 'o' (objective)
     * - 'p' (purple)
     * - 'P' (purple with purple cat)
     * - 'r' (red)
     * - 'R' (red with red cat)
     * - 'w' (wild)
     * - 'W' (wild with a cat of any colour)
     * - 'y' (yellow)
     * - 'Y' (yellow with yellow cat)
     * 4. The number of lines is either 12, 15 or 18.
     * </p>
     *
     * @param boardString A string representing the boardState
     * @return True if the boardState is well-formed, otherwise false.
     */
    public static boolean isBoardStringWellFormed(String boardString) {

        String[] list = boardString.split("\\r?\\n");

        int length = list[0].length();

        String newline = System.lineSeparator();
        boolean hasNewline = boardString.substring(boardString.length() - 1).contains(newline);

        if ((list.length != 12 && list.length != 15 && list.length != 18) || (length != 9 && length != 18) || !hasNewline) {
            return false;
        }

        for (String string : list) {
            if (string.length() != length) {
                return false;
            }

            for (char ch : string.toCharArray()) {
                if (!isValidChar(ch)) {
                    return false;
                }
            }

        }

        return true; // FIXME TASK 2 - Done!!
    }


    private static boolean isValidChar(char ch) {
        return ch == 'b' || ch == 'B' || ch == 'f' || ch == 'g' || ch == 'G' ||
                ch == 'n' || ch == 'o' || ch == 'p' || ch == 'P' || ch == 'r' ||
                ch == 'R' || ch == 'w' || ch == 'W' || ch == 'y' || ch == 'Y';
    }


    /**
     * Make Constructors for each of your objects.
     */
    // FIXME TASK 3 done!




    /**
     * Draws a random fire tile from those remaining in the bag.
     *
     * @param gameState the current state of the game, including the fire tile bag
     * @return a random fire tile from those remaining, in string form. If there are no tiles remaining, return the
     * empty string.
     */


    /**
     * this method is to draw a fireTile from a fire bag. The fire bag is defined in a String[] gameState
     *
     * @author Yu Ma
     * @param gameState the length is 5, follow the order of [Board, Decks, Hand, Exhausted Cats,Fire tile bag].
     *                  each element is a string representing its current state
     * @return a fire tile string like "a000110111221"
     */
    public static String drawFireTile(String[] gameState) {
        if (gameState[4].isEmpty()) {
            return "";
        }
        Random random = new Random();
        int index= random.nextInt(gameState[4].length());
        char fireID = gameState[4].charAt(index);
        FireTile fireTile = new FireTile(fireID);
        String fireToStr = fireTile.toString();
        return fireToStr;
        // FIXME TASK 5 done!
    }

    /**
     * Chooses a random challenge from those available in the Utility class according to the given difficulty.
     *
     * @param difficulty the desired difficulty of the challenge
     * @return a random challenge of the given difficulty
     */

    // Weiqi Huang
    public static String chooseChallenge(int difficulty) {
        // FIXME TASK 6 - Done!!

        Challenge challenge = new Challenge(difficulty);

        // Returns randomly selected elements
        return challenge.getChallenge();
    }

    /**
     * Draw random cards from the specified decks.
     * 从指定的牌组中随机抽取牌。
     * <p>
     * The decks string denotes what decks to draw from and how many cards to draw from that deck.
     * deck字符串表示要从哪些牌组中抽牌以及要从该牌组中抽多少张牌。
     *
     * <p>
     * For example the drawRequest string "4A1B1D" tells us that we should draw 4 cards from deck A, 1 card from deck B
     * and
     * 1 card from deck D.
     * <p>
     * 例如，drawRequest字符串“4A1B1D”告诉我们应该从牌组A中抽取4张牌，从牌组B中抽取1张牌，从牌组D中抽取1张牌。
     * <p>
     * If I draw cards a, b, d, from deck A, card a from deck B and card s from deck D, I would return the string: "AabdBaDs".
     * 如果我从牌组a中抽牌a, b, d，从牌组b中抽牌a，从牌组d中抽牌s，我将返回字符串"AabdBaDs"。
     * <p>
     * Decks should be listed in alphabetical order, with cards drawn from that deck also listed in alphabetical order.
     * 牌组应按字母顺序排列，从牌组中抽取的牌也应按字母顺序排列。
     *
     * </p>
     * Recall the mapping between deck and char:
     * 回想一下deck和char之间的映射:
     * A -> CIRCLE;
     * B -> CROSS;
     * C -> SQUARE;
     * D -> TRIANGLE;
     *
     * @param gameState   the current state of the game, including the current state of the decks
     *                    游戏的当前状态，包括牌组的当前状态
     * @param drawRequest A string representing the decks to draw from and the number of cards to draw from each respective deck.
     *                    表示要抽的牌组和要抽的牌组数量的字符串。 4A1B1D
     * @return The updated gameState array after the cards have been drawn. (Remove all cards drawn from decks and add them to the Hand string). If it is not possible to draw all the specified cards, you should return the original gameState.
     * 在抽完牌后更新的gameState数组。(从牌组中取出所有牌并将其添加到手牌串中)。如果不能抽到所有指定的牌，你应该返回原始的gameState。
     */

    // Weiqi Huang
    public static String[] drawHand(String[] gameState, String drawRequest) {
        // FIXME TASK 7 Done!
        // Get the current state of the decks 获取牌组的当前状态
        Map<Character, Deck> deckMap = new HashMap<>();
        String deckState = gameState[1];
        for (char c = 'A'; c <= 'D'; c++) {
            String tempDeckInfo;
            if (c == 'D') {
                tempDeckInfo = deckState.substring(deckState.indexOf(c)+1);
            } else {
                tempDeckInfo = deckState.substring(deckState.indexOf(c)+1, deckState.indexOf(c + 1));
            }
            deckMap.put(c, new Deck(c,tempDeckInfo));
        }

        // The final drawn hand 最终抽取的手牌
        StringBuilder handDeck = new StringBuilder();

        // Iterate over the drawRequest string and record each deck request 遍历 drawRequest 字符串，依次记录每个牌组请求
        HashMap<Character, Integer> drawRequestMap = new HashMap();
        drawRequestMap.put('A', 0);
        drawRequestMap.put('B', 0);
        drawRequestMap.put('C', 0);
        drawRequestMap.put('D', 0);
        for (int i = 0; i < drawRequest.length(); i += 2) {
            // Get the current deck and the number of cards to draw 获取当前牌组和需要抽取的牌数
            char deckChar = drawRequest.charAt(i);
            int count = Character.getNumericValue(drawRequest.charAt(i + 1));
            //            System.out.println("从牌组" + deckChar + "中抽取" + count + "张牌：");
            drawRequestMap.put(deckChar, count);
        }


        // Randomly draw cards from the corresponding decks 从对应的牌组中随机抽取牌
        for (char deckChar = 'A'; deckChar <= 'D'; deckChar++) {

            StringBuilder tempHand = new StringBuilder();
            // Number of times to draw from this deck 从该牌组获取的次数
            int count = drawRequestMap.get(deckChar);
            for (int j = 0; j < count; j++) {
                // The state of this deck 该牌组的状态
                String deckInfo = deckMap.get(deckChar).getDeckInfo();

                Deck deck = deckMap.get(deckChar);
                // If there are no cards to draw from this deck 如果该牌组没有牌可以抽取
                if (deck.hasNoCard()) {
                    return gameState;
                }
                // Randomly draw a card 随机取牌
                /*Random = new Random();
                int index = random.nextInt(deckInfo.toCharArray().length);
                char randomChar = deckInfo.charAt(index);*/
                //Add it to the hand 加入到手牌中
                tempHand.append(deck.drawCards());
                //After drawing the card, remove it from the deck 取牌之后, 将该牌组中已经取走的牌删掉,
                deckMap.put(deckChar, deck);
            }
            //Sort the cards 排序
            String str = tempHand.toString();
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            //the result: AabdBaDs
            handDeck.append(deckChar).append(sortedString);
        }
        //Update the latest deck state to gameState 将最新的牌组状态更新到 gameState
        StringBuilder newDeckState = new StringBuilder();
        for (Character deckChar : deckMap.keySet()) {
            String deckInfo = deckMap.get(deckChar).getDeckInfo();
            newDeckState.append(deckChar).append(deckInfo);
        }
        gameState[1] = newDeckState.toString();

        //Assign the hand to gameState 将手牌赋值给 gameState
        gameState[2] = handDeck.toString();

        return gameState;
    }

    /**
     * Place the given card or fire tile as described by the placement string and return the updated gameState array.
     * See the README for details on these two strings.
     * You may assume that the placements given are valid.
     * <p>
     * When placing a card, you should update both the Board string and remove the corresponding card from the Hand
     * string in the gameState array.
     *
     * @param gameState       An array representing the game state.
     * @param placementString A string representing a Fire Tile Placement or a Card Placement.
     * @return the updated gameState array after this placement has been made
     */
    public static String[] applyPlacement(String[] gameState, String placementString) {
        if (Character.isLetter(placementString.charAt(1)) && Character.isLetter(placementString.charAt(6))) {
            // 放置卡片
            // 字符串是否既不是火牌也不是路径卡
            char deckType = placementString.charAt(0);
            char cardID = placementString.charAt(1);
            int row = Integer.parseInt(placementString.substring(2, 4));
            int col = Integer.parseInt(placementString.substring(4, 6));
            char orientation = placementString.charAt(6);

            // 根据卡片的类型和ID获取卡片数据
            String[] cardData;
            DeckType deckType1 = DeckType.fromChar(placementString.charAt(0));
            switch (deckType1) {
                case CIRCLE:
                case CROSS:
                case SQUARE:
                case TRIANGLE:
                    String[][] deckData = new String[][]{Utility.DECK_A, Utility.DECK_B, Utility.DECK_C, Utility.DECK_D};
                    cardData = new String[]{deckData[deckType1.ordinal()][cardID - 'a']};
                    break;
                default:
                    throw new IllegalArgumentException("Invalid deck type: " + deckType);
            }

            // 创建 PlacedCard 对象
            PlacedCard card = new PlacedCard(cardData[0], orientation, row, col);

            // 更新 Board 字符串
            StringBuilder boardBuilder = new StringBuilder(gameState[0]);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int boardRow = row + i;
                    int boardCol = col + j;
                    if (boardRow >= 0 && boardRow < boardLength(boardBuilder) && boardCol >= 0 && boardCol < boardLength(boardBuilder)) {
                        char squareColor = card.getColorAt(i, j);
                        int index = boardRow * (boardLength(boardBuilder) + 1) + boardCol;
                        boardBuilder.setCharAt(index, squareColor);
                    }
                }
            }
            gameState[0] = boardBuilder.toString();

            // 从 Hand 字符串中移除卡片
            String handString = gameState[2];

            // 找到 handString 中表示卡组的大写字母的位置
            int deckIndex = handString.indexOf(deckType);

            // 如果找到了卡组标识
            if (deckIndex != -1) {
                // 在卡组标识后搜索与 cardID 匹配的小写字母
                int cardIndex = handString.indexOf(cardID, deckIndex + 1);

                // 如果找到了匹配的卡牌
                if (cardIndex != -1) {
                    // 从 handString 中移除匹配的卡牌
                    gameState[2] = handString.substring(0, cardIndex) + handString.substring(cardIndex + 1);
                }
            }
        } else {
            // 放置火焰块

            //解析placement string
//            System.out.println("放置命令 "+placementString);
            char fireID = placementString.charAt(0);
            String fileBag="abcdefghigklmnopqrstuvwxyzABCDE";
            String fireTileString = Utility.FIRE_TILES[fileBag.indexOf(fireID)];
            int fireIDIndex = fileBag.indexOf(fireID);

            int placementRow = Integer.parseInt(placementString.substring(1, 3));//placement placementRow
            int placementCol = Integer.parseInt(placementString.substring(3, 5));//placement placementCol
            boolean flipped = placementString.charAt(5) == 'T';
            char orientation = placementString.charAt(6);


            //计算板子最大行列
            StringBuilder boardBuilder = new StringBuilder(gameState[0]);
            String[] boardRows = boardBuilder.toString().split("\\r?\\n");
            int boardMaxRow = boardRows.length;
            int boardMaxColumn = boardRows[0].length();

            TheBoard currentBoardState = new TheBoard();
            Square[][] boardSquares = new Square[boardMaxRow][boardMaxColumn];
            String boardWithSpace = gameState[0];
            String boardWithoutSpace = boardWithSpace.replaceAll("\\r\\n|\\r|\\n", "");
            int indexForWitoutSpace = 0;

            for (int i = 0; i < boardMaxRow; i++) {
                for (int j = 0; j < boardMaxColumn; j++) {
                    boardSquares[i][j] = new Square(Colour.fromChar(boardWithoutSpace.charAt(indexForWitoutSpace)));
                    indexForWitoutSpace++;
                }
            }

//            System.out.println(currentBoardState.boardToString());

            //
//            System.out.println();
            PlacedFireTile placedFireTile = new PlacedFireTile(fireTileString, placementRow, placementCol, flipped, orientation, boardMaxRow, boardMaxColumn);


            // 更新 Board 字符串
            Square[] readyOnBoard = placedFireTile.getSquares();
            for (Square square : readyOnBoard) {
                int finalRow = square.getLocation().getRow() + placementRow;
                int finalCol = square.getLocation().getColumn() + placementCol;
                if (finalRow >= 0 && finalRow < boardMaxRow && finalCol >= 0 && finalCol < boardMaxColumn) {
                    boardSquares[finalRow][finalCol].setColour(Colour.FIRE);
                }
            }

            currentBoardState.setSquares(boardSquares);
            gameState[0] = currentBoardState.boardToString();
        }
        return gameState;
    }


        private static int boardLength (StringBuilder boardBuilder){
            return boardBuilder.toString().split("\\r?\\n")[0].length();
        }
    // FIXME TASK 8 done!




    /**
     * Move the given cat as described by the cat movement string and return the updated gameState array. You may
     * assume that the cat movement is valid.
     * <p>
     * You should both move the cat (updating the Board string) and also add the cat to the Exhausted Cats string, or
     * update that cat's reference in the Exhausted Cats string if it was already exhausted.
     *
     * @author Yu Ma
     * @param gameState      An array representing the game state.
     * @param movementString A string representing the movement of a cat and the cards discarded to allow this move.
     *                       "B01100710Bm"
     * @return the updated gameState array after this movement has been made.
     */
    public static String[] moveCat(String[] gameState, String movementString) {

        //gameState[0] is a string representing a current board state.
        //So create a TheBoard to make it actually a game board.
        TheBoard currentBoardState = new TheBoard();

        StringBuilder boardBuilder = new StringBuilder(gameState[0]);
        String[] boardRows = boardBuilder.toString().split("\\r?\\n");
        int boardMaxRow = boardRows.length;
        int boardMaxColumn = boardRows[0].length();

        Square[][] boardSquares = new Square[boardMaxRow][boardMaxColumn];
        String boardWithSpace = gameState[0];
        String boardWithoutSpace = boardWithSpace.replaceAll("\\r\\n|\\r|\\n", "");
        int indexForWitoutSpace = 0;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 18; j++) {
                boardSquares[i][j] = new Square(Colour.fromChar(boardWithoutSpace.charAt(indexForWitoutSpace)));
                indexForWitoutSpace++;
            }
        }
        currentBoardState.setSquares(boardSquares);

        // Example movementString: "Y01100610Cv"
        // Cat 'Y' moves from position (01, 10) to (06, 10) using card 'Cv'
        char cat = movementString.charAt(0);
        int fromRow = Integer.parseInt(movementString.substring(1, 3));
        int fromCol = Integer.parseInt(movementString.substring(3, 5));
        int toRow = Integer.parseInt(movementString.substring(5, 7));
        int toCol = Integer.parseInt(movementString.substring(7, 9));

        //update board state after cat moved
        Colour normalColour = boardSquares[fromRow][fromCol].getColour();
        boardSquares[fromRow][fromCol].setColour(Colour.catColourToNormalColour(normalColour));
        boardSquares[toRow][toCol].setColour(Colour.fromChar(cat));

        //update catPosition string of moved cat
        String catPosition = ""+cat;
        if(toRow<10 && toCol<10){
            catPosition += "" + 0 + toRow + 0 + toCol;
        }else if(toRow>9 && toCol<10){
            catPosition += ""+toRow+0+toCol;
        }else if(toRow<10 && toCol>9){
            catPosition += ""+0+toRow+toCol;
        }else if(toRow>9 && toCol>9){
            catPosition += ""+toRow+toCol;
        }

        //update exhaustedCats
        String exhaustedCats = gameState[3];//gameState[3] like "P0709"
        if (exhaustedCats.contains(String.valueOf(cat))) {
            // If cat is already in the list, update its position
            exhaustedCats = exhaustedCats.replaceAll(cat + "\\d{4}", catPosition);
        } else {
            // Find the right position to insert the new catPosition based on the order of cats
            int index = 0;
            for (; index < exhaustedCats.length(); index += 5) {
                if (exhaustedCats.charAt(index) > cat) {
                    break;
                }
            }
            exhaustedCats = exhaustedCats.substring(0, index) + catPosition + exhaustedCats.substring(index);
        }

        gameState[0] = currentBoardState.boardToString();
        gameState[3] = exhaustedCats;

        return gameState;

        // FIXME TASK 9 done!
    }




    /**
     * Given a challengeString, construct a board string that satisfies the challenge requirements.
     * <p>
     * Each board in the `squareBoard` or `rectangleBoard` arrays may only be used once. For example: if the
     * challenge requires 4 Large (square) boards, you must use all 4 square boards. You may not use the same board
     * twice, even in different orientations.
     * The cat, fire card and raft card placements should all match the challenge string.
     *
     * @author Yu Ma
     * @param challengeString A string representing the challenge to initialise
     * @return A board string for this challenge.
     */
    public static String initialiseChallenge(String challengeString) {// FIXME 10 done!
        //example challengeString = "LNSNLASA F000300060012001503030903 C112033060340009 R01215";

        // find substrings for different parts
        String islandSubstring = challengeString.substring(0, challengeString.indexOf('F'));
        String fireSubstring = challengeString.substring(challengeString.indexOf('F') + 1, challengeString.indexOf('C'));
        String catSubstring = challengeString.substring(challengeString.indexOf('C') + 1, challengeString.indexOf('R'));
        String raftSubstring = challengeString.substring(challengeString.indexOf('R') + 1);

        // initialize the game board with island substring
        Square[][] board = theBoard.formBoard(islandSubstring);
        theBoard.setSquares(board);

        // add fires to the game board according to fire substring
        addFire(board, fireSubstring);

        // add cats area to the game board according to cat substring
        addCats(board, catSubstring);

        // add raft area to the game board according to raft substring
        addRaft(board, raftSubstring);

        return theBoard.boardToString();
    }


    /**
     * Given a card placement string or a fire tile placement string, check if that placement is valid.
     * <p>
     * A card placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the card is off-board
     * 2. No part of the card is overlapping fire.
     * 3. No part of the card is overlapping a cat.
     * 4. No part of the card is overlapping part of a Raft card (any of the 8 squares surrounding the `o`
     * state)
     * </p>
     * A fire tile placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the fire tile is off-board
     * 没有火超出边界
     * 2. No part of the fire tile overlaps fire
     * 没有火覆盖火
     * 3. No part of the fire tile overlaps a cat
     * 没有火覆盖猫
     * 4. No part of the fire tile overlaps part of a Raft card (any of the 8 squares surrounding the `o` state)
     * 没有火覆盖木筏
     * 5. The Fire tile is orthogonally adjacent to another fire square.
     * 火和火临近
     * </p>
     *
     * @param gameState       An array representing the gameState
     * @param placementString A string representing a card placement or a fire tile placement
     * @return True if the placement is valid, otherwise false.
     */
    public static boolean isPlacementValid(String[] gameState, String placementString) {
        // 解析放置字符串,确定是卡片放置还是火焰块放置
        if (Character.isLetter(placementString.charAt(1)) && Character.isLetter(placementString.charAt(6))) {
            // 卡片放置
            int row = Integer.parseInt(placementString.substring(2, 4));
            int col = Integer.parseInt(placementString.substring(4, 6));
            char orientation = placementString.charAt(6);

            // 创建 PlacedCard 对象
            String[] cardData = new String[]{Utility.DECK_A[placementString.charAt(1) - 'a']};
            PlacedCard card = new PlacedCard(cardData[0], orientation, row, col);

            // 检查卡片是否超出棋盘边界
            if (row < 0 || row + 2 >= boardLength(gameState[0]) || col < 0 || col + 2 >= boardLength(gameState[0])) {
                return false;
            }

            // 检查卡片是否与火焰、猫或筏子卡片重叠
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int boardRow = row + i;
                    int boardCol = col + j;
                    char squareColor = gameState[0].charAt(boardRow * (boardLength(gameState[0]) + 1) + boardCol);
                    if (squareColor == 'f' || Character.isUpperCase(squareColor) || squareColor == 'o') {
                        return false;
                    }
                }
            }

            return true;

        } else {
            // 火焰块放置
            int row = Integer.parseInt(placementString.substring(1, 3));
            int col = Integer.parseInt(placementString.substring(3, 5));
            boolean flipped = placementString.charAt(5) == 'T';
            char orientation = placementString.charAt(6);

            // 解析 placement string
//            System.out.println("放置命令 " + placementString);
            char fireID = placementString.charAt(0);
            String fileBag="abcdefghijklmnopqrstuvwxyzABCDE";
            int fireIDIndex = fileBag.indexOf(fireID);

            if (fireIDIndex < 0 || fireIDIndex >= Utility.FIRE_TILES.length) {
                throw new IllegalArgumentException("Invalid fireID: " + fireID);
            }

            String fireTileString = Utility.FIRE_TILES[fileBag.indexOf(fireID)];

//            System.out.println("fireID: " + fireID + ", fireIDIndex: " + fireIDIndex);
            int placementRow = Integer.parseInt(placementString.substring(1, 3)); // placement placementRow
            int placementCol = Integer.parseInt(placementString.substring(3, 5));
//            System.out.println("fireTileString: " + fireTileString);// placement placementCol
//            System.out.println("placementRow: " + placementRow + ", placementCol: " + placementCol + ", flipped: " + flipped + ", orientation: " + orientation);
            // 计算板子最大行列
            StringBuilder boardBuilder = new StringBuilder(gameState[0]);
            String[] boardRows = boardBuilder.toString().split("\\r?\\n");
            int boardMaxRow = boardRows.length;
            int boardMaxColumn = boardRows[0].length();

            TheBoard currentBoardState = new TheBoard();
            Square[][] boardSquares = new Square[boardMaxRow][boardMaxColumn];
            String boardWithSpace = gameState[0];
            String boardWithoutSpace = boardWithSpace.replaceAll("\\r\\n|\\r|\\n", "");
            int indexForWithoutSpace = 0;

            for (int i = 0; i < boardMaxRow; i++) {
                for (int j = 0; j < boardMaxColumn; j++) {
                    boardSquares[i][j] = new Square(Colour.fromChar(boardWithoutSpace.charAt(indexForWithoutSpace)));
                    indexForWithoutSpace++;
                }
            }

            currentBoardState.setSquares(boardSquares);

            // 创建 PlacedFireTile 对象
            PlacedFireTile fireTile = new PlacedFireTile(fireTileString, placementRow, placementCol, flipped, orientation, boardMaxRow, boardMaxColumn);
            for (Square square : fireTile.getSquares()) {
            int boardRow = square.getLocation().getRow() + placementRow;
            int boardCol = square.getLocation().getColumn() + placementCol;
            if (boardRow < 0 || boardRow >= boardMaxRow || boardCol < 0 || boardCol >= boardMaxColumn) {
                return false;
            }}


            // 检查火焰块的任何部分是否越界或重叠到无效方格
            for (Square square : fireTile.getSquares()) {
                int boardRow = square.getLocation().getRow() + placementRow;
                int boardCol = square.getLocation().getColumn() + placementCol;
                if (boardRow < 0 || boardRow >= boardMaxRow || boardCol < 0 || boardCol >= boardMaxColumn) {
                    return false;
                }
                char squareColor = currentBoardState.getColour(boardRow, boardCol).toChar();
                if (squareColor == 'f' || Character.isUpperCase(squareColor) || squareColor == 'o' || squareColor == 'w') {
                    return false;
                }
            }

            // 检查火焰块是否与现有火方格正交相邻
            boolean isAdjacent = false;
            for (Square square : fireTile.getSquares()) {
                int boardRow = square.getLocation().getRow() + placementRow;
                int boardCol = square.getLocation().getColumn() + placementCol;
                if ((boardRow > 0 && currentBoardState.getColour(boardRow - 1, boardCol) == Colour.FIRE) ||
                        (boardRow < boardMaxRow - 1 && currentBoardState.getColour(boardRow + 1, boardCol) == Colour.FIRE) ||
                        (boardCol > 0 && currentBoardState.getColour(boardRow, boardCol - 1) == Colour.FIRE) ||
                        (boardCol < boardMaxColumn - 1 && currentBoardState.getColour(boardRow, boardCol + 1) == Colour.FIRE)) {
                    isAdjacent = true;
                    break;
                }
            }
            if (!isAdjacent) {
                return false;
            }

            return true;
        }
    }


    private static int boardLength(String boardString) {
        return boardString.split("\\r?\\n")[0].length();
    }// FIXME TASK 12


    /**
     * Given a cat movement string, check if the cat movement is valid.
     * <p>
     * A cat movement is valid if:
     * 1. The end location is the same colour as the cat.
     * 2. There is a path from start location to the end location, consisting only of squares the same colour as the
     * cat.
     * 3. The path does not include diagonal movements.
     *
     * @param gameState         An array representing the gameState
     * @param catMovementString A string representing a cat movement.
     * @return True if the cat movement is valid, otherwise false
     */
    public static boolean isCatMovementValid(String[] gameState, String catMovementString) {
        // 解析猫移动字符串
        char catColor = catMovementString.charAt(0);
        int startRow = Integer.parseInt(catMovementString.substring(1, 3));
        int startCol = Integer.parseInt(catMovementString.substring(3, 5));
        int endRow = Integer.parseInt(catMovementString.substring(5, 7));
        int endCol = Integer.parseInt(catMovementString.substring(7, 9));
        theBoard=new TheBoard(gameState[0]);
        String[] move=catMovementString.split("(?=\\p{Upper})");

        if (endRow < 0 || endRow >= theBoard.getSquares().length ||  endCol < 0 || endCol >= theBoard.getSquares()[0].length ) {
            return false;
        }//越界

        if (gameState[3].contains(catMovementString.substring(0,5)) && move.length<3) {return false;}
        String[] hands=gameState[2].split("(?=\\p{Upper})");
        for (int i=1;i<move.length;i++){
            int index=move[i].charAt(0)-'A';
            for (int j=1;j<move[i].length();j++){
                if (hands[index].indexOf(move[i].charAt(j))==-1) return false;
            }
        }

        // 检查终点位置是否与猫的颜色匹配
        if (theBoard.squares[endRow][endCol].colour.toChar()!= Character.toLowerCase(catColor)) {
            return false;
        }

        // 使用广度优先搜索(BFS)检查是否存在一条由相同颜色的方块组成的路径,不包括对角线移动
        int rows = boardLength(gameState[0]);
        int cols = rows;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});

        for (int i=0;i<theBoard.rows;i++){
            for (int j=0;j<theBoard.columns;j++){
                if (Character.toLowerCase(theBoard.squares[i][j].colour.toChar()) != Character.toLowerCase(catColor)){
                    theBoard.visited[i][j]=true;
                }else {
                    theBoard.visited[i][j]=false;
                }
            }
        }



        return theBoard.dfs(startRow,startCol,endRow,endCol);


    }

    // FIXME TASK 14




    /**
     * Determine whether the game is over. The game ends if any of the following conditions are met:
     * <p>
     * Fire tile placement:
     * 1. If this placement action is not valid AND there is no other position this tile could be placed validly
     * (the game is lost).
     * 2. If placing this fire tile makes it impossible for any one cat to reach the raft (the game is lost).
     * <p>
     * Cat movement:
     * 1. If after moving this cat, all cats have safely reached the raft (the game is won).
     * <p>
     * Card placement:
     * 1. If after placing this card, there are no more fire tiles left in the bag (the game is lost).
     * </p>
     * * 火砖放置:
     * * 1. 如果这个放置动作无效,而且没有其他位置可以有效地放置这个火砖,那么游戏就输了。
     * *
     * * 2. 如果放置这个火砖会导致任何一只猫无法到达筏子,那么游戏就输了。
     * * <p>
     * * 猫的移动:
     * * 1. 如果在移动这只猫之后,所有的猫都安全地到达了筏子,那么游戏就赢了。
     * * <p>
     * * 卡片放置:
     * * 1. 如果在放置这张卡片之后,袋子里没有剩余的火砖,那么游戏就输了。
     * * </p>
     *
     * @param gameState An array of strings representing the game state
     * @param action    A string representing a fire tile placement, cat movement or card placement action.
     * @return True if the game is over (regardless of whether it is won or lost), otherwise False.
     */
    public static boolean isGameOver(String[] gameState, String action) {
        System.out.println("Initial gameState: " + Arrays.toString(gameState));
        System.out.println("Initial action: " + action);

        if (gameState == null || gameState.length == 0 || gameState[0] == null || gameState[0].isEmpty()) {
            System.out.println("Game state is invalid or empty.");
            return false;
        }

        TheBoard board1 = new TheBoard(gameState[0]);
        System.out.println("Initial board created: " + board1);

        // Check if the raft is in the correct position
        if (!isRaftInCorrectPosition(board1)) {
            System.out.println("Raft is not in the correct position.");
            return false;
        }

        if (action.length() == 7 && Character.isLetter(action.charAt(0)) && Character.isLetter(action.charAt(6))) {
            System.out.println("Action type: Fire tile placement");
            if (!isPlacementValid(gameState, action)) {
                System.out.println("Placement invalid: " + action);
                boolean hasValidPlacement = false;
                for (char fireID : gameState[4].toCharArray()) {
                    for (int row = 0; row < board1.getRows(); row++) {
                        for (int col = 0; col < board1.getColumns(); col++) {
                            for (boolean flipped : new boolean[]{false, true}) {
                                for (char orientation : new char[]{'N', 'E', 'S', 'W'}) {
                                    String placementString = String.format("%c%02d%02d%c%c", fireID, row, col, flipped ? 'T' : 'F', orientation);
                                    if (isPlacementValid(gameState, placementString)) {
                                        hasValidPlacement = true;
                                        System.out.println("Found valid placement: " + placementString);
                                        break;
                                    }
                                }
                                if (hasValidPlacement) break;
                            }
                            if (hasValidPlacement) break;
                        }
                        if (hasValidPlacement) break;
                    }
                }
                if (!hasValidPlacement) {
                    System.out.println("No valid placements left, game over.");
                    return true;
                }
            }

            String[] updatedGameState = applyPlacement(gameState, action);
            TheBoard updatedBoard = new TheBoard(updatedGameState[0]);

            System.out.println("Updated gameState after placement: " + Arrays.toString(updatedGameState));

            if (updatedGameState == null || updatedGameState.length == 0 || updatedGameState[0] == null || updatedGameState[0].isEmpty()) {
                System.out.println("Updated game state is empty or invalid.");
                return false;
            }

            if (!areAllCatsAbleToReachRaft(updatedBoard)) {
                System.out.println("A cat cannot reach the raft.");
                return true;
            }
        } else if (action.length() >= 10) {
            System.out.println("Action type: Cat move");

            String[] updatedGameState = moveCat(gameState, action);
            TheBoard updatedBoard = new TheBoard(updatedGameState[0]);

            System.out.println("Updated gameState after moving cat: " + Arrays.toString(updatedGameState));

            if (areAllCatsOnRaft(updatedBoard)) {
                System.out.println("All cats are on the raft.");
                return true;
            }
        } else if (action.length() == 7) {
            System.out.println("Action type: Card placement");

            String[] updatedGameState = applyPlacement(gameState, action);

            System.out.println("Updated gameState after placing card: " + Arrays.toString(updatedGameState));

            if (updatedGameState[4].isEmpty()) {
                System.out.println("Fire tile backpack is empty.");
                return true;
            }

            TheBoard updatedBoard = new TheBoard(updatedGameState[0]);
            if (!areAllCatsAbleToReachRaft(updatedBoard)) {
                System.out.println("A cat cannot reach the raft.");
                return true;
            }
        }
        System.out.println("Game is not over.");
        return false;
    }

    private static boolean isRaftInCorrectPosition(TheBoard board) {
        int rows = board.getRows();
        int cols = board.getColumns();

        for (int row = 0; row <= rows - 3; row++) {
            for (int col = 0; col <= cols - 3; col++) {
                if (isRaftAtPosition(board, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isRaftAtPosition(TheBoard board, int startRow, int startCol) {
        Colour centerColour = board.getColour(startRow + 1, startCol + 1);
        if (centerColour != Colour.OBJECTIVE) {
            return false;
        }

        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                Colour colour = board.getColour(row, col);
                if (colour != Colour.OBJECTIVE && colour != Colour.WILD) {
                    if (row == startRow || row == startRow + 2 || col == startCol || col == startCol + 2) {
                        // 如果是外围一圈，则必须是 WILD 或猫的颜色
                        if (colour != Colour.WILD && !Character.isLowerCase(colour.toChar())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean areAllCatsAbleToReachRaft(TheBoard board) {
        int rows = board.getRows();
        int cols = board.getColumns();

        for (int catRow = 0; catRow < rows; catRow++) {
            for (int catCol = 0; catCol < cols; catCol++) {
                char catSquare = board.getColour(catRow, catCol).toChar();
                if (Character.isUpperCase(catSquare)) {
                    boolean canReachRaft = false;
                    char catColor = Character.toLowerCase(catSquare);

                    for (int raftRow = 0; raftRow < rows; raftRow++) {
                        for (int raftCol = 0; raftCol < cols; raftCol++) {
                            if (board.getColour(raftRow, raftCol) == Colour.OBJECTIVE) {
                                canReachRaft = board.dfs(catRow, catCol, raftRow, raftCol, catColor);
                                if (canReachRaft) break;
                            }
                        }
                        if (canReachRaft) break;
                    }

                    if (!canReachRaft) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean areAllCatsOnRaft(TheBoard board) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (Character.isUpperCase(board.getColour(row, col).toChar())) {
                    boolean catOnRaft = false;
                    char catColor = Character.toLowerCase(board.getColour(row, col).toChar());

                    for (int raftRow = 0; raftRow <= board.getRows() - 3; raftRow++) {
                        for (int raftCol = 0; raftCol <= board.getColumns() - 3; raftCol++) {
                            if (isRaftAtPosition(board, raftRow, raftCol)) {
                                for (int rRow = raftRow; rRow < raftRow + 3; rRow++) {
                                    for (int rCol = raftCol; rCol < raftCol + 3; rCol++) {
                                        Colour raftColour = board.getColour(rRow, rCol);
                                        if (raftColour.toChar() == catColor || raftColour == Colour.WILD || raftColour == Colour.OBJECTIVE) {
                                            if (row == rRow && col == rCol) {
                                                catOnRaft = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (catOnRaft) break;
                                }
                            }
                            if (catOnRaft) break;
                        }
                        if (catOnRaft) break;
                    }

                    if (!catOnRaft) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}




// FIXME TASK 15



//    public static void main(String[] args) {
//        String[] strings = RaceToTheRaft.drawHand(new String[]{}, "A1C2D3");
//        System.out.println(Arrays.toString(strings));
//    }
//}

