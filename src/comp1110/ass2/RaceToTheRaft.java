package comp1110.ass2;

//import org.junit.jupiter.api.Assertions;

import java.util.*;

import static comp1110.ass2.Utility.*;

/**
 * This class is for testing purposes only. You should create and use your own objects to solve the tasks below
 * instead of directly using the strings provided. Note that Task 2 is the only task you are expected to use string
 * manipulation to solve.
 */
public class RaceToTheRaft {

    public static HashMap<Integer, String[]> challenges = new HashMap<>();

    /**
     * Determine whether a boardState string is well-formed.
     * 判断boardState字符串是否格式良好
     * To be well-formed the string must satisfy all the following conditions:
     * 要成为格式良好的字符串，必须满足以下所有条件:
     * <p>
     * 1. Each line is terminated by a newline character `\n`
     * 每行以换行符' \n '结束
     * 2. The number of printable (non-newline) characters in each line is equal AND is either 9 or 18.
     * 每行中可打印(非换行)字符的数量相等且为9或18。
     * 3. Each character (except the newline character) is one of the following:
     * 每个字符(换行符除外)是下列字符之一:
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
     * 行数是12、15或18
     * </p>
     *
     * @param boardString A string representing the boardState
     *                    表示boardState的字符串
     * @return True if the boardState is well-formed, otherwise false.
     * 如果boardState格式良好，则为True，否则为false
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
    // FIXME TASK 3




    /**
     * Draws a random fire tile from those remaining in the bag.
     *
     * @param gameState the current state of the game, including the fire tile bag
     * @return a random fire tile from those remaining, in string form. If there are no tiles remaining, return the
     * empty string.
     */
    public static String drawFireTile(String[] gameState) {//A not represented
        if (gameState[4].isEmpty()) {
            return "";
        }
        Random random = new Random();
        int index= random.nextInt(gameState[4].length());
        char fireID = gameState[4].charAt(index);
        FireTile fireTile = new FireTile(fireID);
        String fireToStr = fireTile.toString();
        return fireToStr;
        // FIXME TASK 5
    }

    /**
     * Chooses a random challenge from those available in the Utility class according to the given difficulty.
     *
     * @param difficulty the desired difficulty of the challenge
     * @return a random challenge of the given difficulty
     */
    public static String chooseChallenge(int difficulty) {
        // FIXME TASK 6 - Done!!
        // init challenges
        // difficulty0
//        String[] difficulty0 = new String[4];
//        System.arraycopy(CHALLENGES, 0, difficulty0, 0, 4);
//        challenges.put(0, difficulty0);
//        // difficulty1
//        String[] difficulty1 = new String[4];
//        System.arraycopy(CHALLENGES, 4, difficulty1, 0, 4);
//        challenges.put(1, difficulty1);
//        // difficulty2
//        String[] difficulty2 = new String[8];
//        System.arraycopy(CHALLENGES, 8, difficulty2, 0, 8);
//        challenges.put(2, difficulty2);
//        // difficulty3
//        String[] difficulty3 = new String[8];
//        System.arraycopy(CHALLENGES, 16, difficulty3, 0, 8);
//        challenges.put(3, difficulty3);
//        // difficulty4
//        String[] difficulty4 = new String[8];
//        System.arraycopy(CHALLENGES, 24, difficulty4, 0, 8);
//        challenges.put(4, difficulty4);
//        // difficulty5
//        String[] difficulty5 = new String[7];
//        System.arraycopy(CHALLENGES, 32, difficulty5, 0, 7);
//        challenges.put(5, difficulty5);
//
//        // Create a random number generator
//        Random random = new Random();
//        //        random.setSeed(22);
//
//        //根据难度来选择关卡
//        String[] difficulties = challenges.get(difficulty);
//
//        // Generate random index
//        int randomIndex = random.nextInt(difficulties.length);
//
//        // Gets the element at the random index
//        String randomElement = difficulties[randomIndex];
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
    public static String[] drawHand(String[] gameState, String drawRequest) {
        // FIXME TASK 7 Done!
        // 获取牌组的当前状态
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

        // 最终抽取的手牌
        StringBuilder handDeck = new StringBuilder();

        // 遍历 drawRequest 字符串，依次记录每个牌组请求
        HashMap<Character, Integer> drawRequestMap = new HashMap();
        drawRequestMap.put('A', 0);
        drawRequestMap.put('B', 0);
        drawRequestMap.put('C', 0);
        drawRequestMap.put('D', 0);
        for (int i = 0; i < drawRequest.length(); i += 2) {
            // 获取当前牌组和需要抽取的牌数
            char deckChar = drawRequest.charAt(i);
            int count = Character.getNumericValue(drawRequest.charAt(i + 1));
            //            System.out.println("从牌组" + deckChar + "中抽取" + count + "张牌：");
            drawRequestMap.put(deckChar, count);
        }


        // 从对应的牌组中随机抽取牌
        for (char deckChar = 'A'; deckChar <= 'D'; deckChar++) {

            StringBuilder tempHand = new StringBuilder();
            // 从该牌组获取的次数
            int count = drawRequestMap.get(deckChar);
            for (int j = 0; j < count; j++) {
                // 该牌组的状态
                String deckInfo = deckMap.get(deckChar).getDeckInfo();

                Deck deck = deckMap.get(deckChar);
                // 如果该牌组没有牌可以抽取
                if (deck.hasNoCard()) {
                    return gameState;
                }
                // 随机取牌
                /*Random random = new Random();
                int index = random.nextInt(deckInfo.toCharArray().length);
                char randomChar = deckInfo.charAt(index);*/
                //加入到手牌中
                tempHand.append(deck.drawCards());
                //取牌之后, 将该牌组中已经取走的牌删掉,
                deckMap.put(deckChar, deck);
            }
            //排序
            String str = tempHand.toString();
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            //拼接结果: AabdBaDs
            handDeck.append(deckChar).append(sortedString);
        }
        //将最新的牌组状态更新到 gameState
        StringBuilder newDeckState = new StringBuilder();
        for (Character deckChar : deckMap.keySet()) {
            String deckInfo = deckMap.get(deckChar).getDeckInfo();
            newDeckState.append(deckChar).append(deckInfo);
        }
        gameState[1] = newDeckState.toString();

        //将手牌赋值给 gameState
        gameState[2] = handDeck.toString();

        return gameState;


//                //修改 gameState中的牌组内容
//                String deckInfo = gameState[1];
//                StringBuilder newDeckInfo = new StringBuilder();
//                for (char c = 'A'; c <= 'D'; c++) {
//                    String tempDeckInfo = "";
//                    if (c == 'D') {
//                        tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c));
//                    } else {
//                        tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c), deckInfo.indexOf(c + 1));
//                    }
//                    //
//                    if (c == 'A') {
//                        tempDeckInfo = tempDeckInfo.replace(select, "");
//                    }
//                    newDeckInfo.append(tempDeckInfo);
//                }
//                gameState[1] = newDeckInfo.toString();
//            }
//
//
//
//            StringBuilder builder = new StringBuilder();
//            switch (deckChar) {
//                case 'A':
//                    //取牌操作
//                    for (int j = 0; j < count; j++) {
//                        int index = random.nextInt(DECK_A.length);
//                        String select = String.valueOf(DECK_A[index].toCharArray()[0]);
//                        builder.append(select);
//                        //取牌之后, 将DECK中已经取走的牌删掉,
//                        //创建一个新的数组，大小比原数组小1
//                        String[] newDeck = new String[DECK_A.length - 1];
//                        // 将原数组中除要删除的元素外的所有元素复制到新数组中
//                        for (int m = 0, n = 0; m < DECK_A.length; m++) {
//                            if (m != index) {
//                                newDeck[n++] = DECK_A[m];
//                            }
//                        }
//                        //修改 gameState中的牌组内容
//                        String deckInfo = gameState[1];
//                        StringBuilder newDeckInfo = new StringBuilder();
//                        for (char c = 'A'; c <= 'D'; c++) {
//                            String tempDeckInfo = "";
//                            if (c == 'D') {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c));
//                            } else {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c), deckInfo.indexOf(c + 1));
//                            }
//                            //
//                            if (c == 'A') {
//                                tempDeckInfo = tempDeckInfo.replace(select, "");
//                            }
//                            newDeckInfo.append(tempDeckInfo);
//                        }
//                        gameState[1] = newDeckInfo.toString();
//                    }
//                    break;
//                case 'B':
//                    //取牌操作
//                    for (int j = 0; j < count; j++) {
//                        int index = random.nextInt(DECK_B.length);
//                        String select = String.valueOf(DECK_B[index].toCharArray()[0]);
//                        builder.append(select);
//                        //取牌之后, 将DECK中已经取走的牌删掉,
//                        //创建一个新的数组，大小比原数组小1
//                        String[] newDeck = new String[DECK_B.length - 1];
//                        // 将原数组中除要删除的元素外的所有元素复制到新数组中
//                        for (int m = 0, n = 0; m < DECK_B.length; m++) {
//                            if (m != index) {
//                                newDeck[n++] = DECK_B[m];
//                            }
//                        }
//                        //修改 gameState中的牌组内容
//                        String deckInfo = gameState[1];
//                        StringBuilder newDeckInfo = new StringBuilder();
//                        for (char c = 'A'; c <= 'D'; c++) {
//                            String tempDeckInfo = "";
//                            if (c == 'D') {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c));
//                            } else {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c), deckInfo.indexOf(c + 1));
//                            }
//                            //
//                            if (c == 'B') {
//                                tempDeckInfo = tempDeckInfo.replace(select, "");
//                            }
//                            newDeckInfo.append(tempDeckInfo);
//                        }
//                        gameState[1] = newDeckInfo.toString();
//                    }
//                    break;
//                case 'C':
//                    //取牌操作
//                    for (int j = 0; j < count; j++) {
//                        int index = random.nextInt(DECK_C.length);
//                        String select = String.valueOf(DECK_C[index].toCharArray()[0]);
//                        builder.append(select);
//                        //取牌之后, 将DECK中已经取走的牌删掉,
//                        //创建一个新的数组，大小比原数组小1
//                        String[] newDeck = new String[DECK_C.length - 1];
//                        // 将原数组中除要删除的元素外的所有元素复制到新数组中
//                        for (int m = 0, n = 0; m < DECK_C.length; m++) {
//                            if (m != index) {
//                                newDeck[n++] = DECK_C[m];
//                            }
//                        }
//                        //修改 gameState中的牌组内容
//                        String deckInfo = gameState[1];
//                        StringBuilder newDeckInfo = new StringBuilder();
//                        for (char c = 'A'; c <= 'D'; c++) {
//                            String tempDeckInfo = "";
//                            if (c == 'D') {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c));
//                            } else {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c), deckInfo.indexOf(c + 1));
//                            }
//                            //
//                            if (c == 'C') {
//                                tempDeckInfo = tempDeckInfo.replace(select, "");
//                            }
//                            newDeckInfo.append(tempDeckInfo);
//                        }
//                        gameState[1] = newDeckInfo.toString();
//                    }
//                    break;
//                case 'D':
//                    //取牌操作
//                    for (int j = 0; j < count; j++) {
//                        int index = random.nextInt(DECK_D.length);
//                        String select = String.valueOf(DECK_D[index].toCharArray()[0]);
//                        builder.append(select);
//                        //取牌之后, 将DECK中已经取走的牌删掉,
//                        //创建一个新的数组，大小比原数组小1
//                        String[] newDeck = new String[DECK_D.length - 1];
//                        // 将原数组中除要删除的元素外的所有元素复制到新数组中
//                        for (int m = 0, n = 0; m < DECK_D.length; m++) {
//                            if (m != index) {
//                                newDeck[n++] = DECK_D[m];
//                            }
//                        }
//                        //修改 gameState中的牌组内容
//                        String deckInfo = gameState[1];
//                        StringBuilder newDeckInfo = new StringBuilder();
//                        for (char c = 'A'; c <= 'D'; c++) {
//                            String tempDeckInfo = "";
//                            if (c == 'D') {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c));
//                            } else {
//                                tempDeckInfo = deckInfo.substring(deckInfo.indexOf(c), deckInfo.indexOf(c + 1));
//                            }
//                            //
//                            if (c == 'D') {
//                                tempDeckInfo = tempDeckInfo.replace(select, "");
//                            }
//                            newDeckInfo.append(tempDeckInfo);
//                        }
//                        gameState[1] = newDeckInfo.toString();
//                    }
//                    break;
//                default:
//                    System.out.println("无效的牌组：" + deckChar);
//                    return gameState;
//            }
            //排序
//            String str = builder.toString();
//            char[] chars = str.toCharArray();
//            Arrays.sort(chars);
//            String sortedString = new String(chars);
            //拼接结果: AabdBaDs
//            handDeck.append(deckChar + sortedString);

        // 在抽完牌后更新的gameState数组
//        // 创建一个新的数组，大小比原数组大1
//        String[] newGameState = new String[gameState.length + 1];
//        // 将原数组的元素复制到新数组中
//        System.arraycopy(gameState, 0, newGameState, 0, gameState.length);
//        // 在新数组的末尾添加新元素
//        newGameState[newGameState.length - 1] = handDeck.toString();
//        gameState[2] = handDeck.toString();
//        return gameState;
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
        return new String[0]; // FIXME TASK 8
    }

    /**
     * Move the given cat as described by the cat movement string and return the updated gameState array. You may
     * assume that the cat movement is valid.
     * <p>
     * You should both move the cat (updating the Board string) and also add the cat to the Exhausted Cats string, or
     * update that cat's reference in the Exhausted Cats string if it was already exhausted.
     *
     * @param gameState      An array representing the game state.
     * @param movementString A string representing the movement of a cat and the cards discarded to allow this move.
     * @return the updated gameState array after this movement has been made.
     */
    public static String[] moveCat(String[] gameState, String movementString) {
        return new String[0]; // FIXME TASK 9
    }

    /**
     * Given a challengeString, construct a board string that satisfies the challenge requirements.
     * <p>
     * Each board in the `squareBoard` or `rectangleBoard` arrays may only be used once. For example: if the
     * challenge requires 4 Large (square) boards, you must use all 4 square boards. You may not use the same board
     * twice, even in different orientations.
     * The cat, fire card and raft card placements should all match the challenge string.
     *
     * @param challengeString A string representing the challenge to initialise
     * @return A board string for this challenge.
     */
    public static String initialiseChallenge(String challengeString) {
//        String challengeString = "LNSNLASA F000300060012001503030903 C112033060340009R01215";

        return "";  // FIXME 10
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
     * 2. No part of the fire tile overlaps fire
     * 3. No part of the fire tile overlaps a cat
     * 4. No part of the fire tile overlaps part of a Raft card (any of the 8 squares surrounding the `o` state)
     * 5. The Fire tile is orthogonally adjacent to another fire square.
     * </p>
     *
     * @param gameState       An array representing the gameState
     * @param placementString A string representing a card placement or a fire tile placement
     * @return True if the placement is valid, otherwise false.
     */
    public static boolean isPlacementValid(String[] gameState, String placementString) {
        return false; // FIXME TASK 12
    }

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
        return false; // FIXME TASK 14
    }


    /**
     * Determine whether the game is over. The game ends if any of the following conditions are met:
     * <p>
     * Fire tile placement:
     * 1. If this placement action is not valid AND there is no other position this tile could be placed validly
     * (the game is lost).
     * 2. If placing this fire tile makes it impossible for any one cat to reach the raft (the game is lost).
     * <p>
     * Cat movement:
     * 1. If after moving this cat, there are greater than or equal to 4 cards in the disaster pile and there are
     * no more file tiles left in the bag (the game is lost).
     * 2. If after moving this cat, all cats have safely reached the raft (the game is won).
     * <p>
     * Card placement:
     * 1. If after placing this card, there are no more fire tiles left in the bag (the game is lost).
     * </p>
     *
     * @param gameState An array of strings representing the game state
     * @param action    A string representing a fire tile placement, cat movement or card placement action.
     * @return True if the game is over (regardless of whether it is won or lost), otherwise False.
     */
    public static boolean isGameOver(String[] gameState, String action) {
        return false;     // FIXME TASK 15
    }


    public static void main(String[] args) {
        String[] strings = RaceToTheRaft.drawHand(new String[]{}, "A1C2D3");
        System.out.println(Arrays.toString(strings));
    }
}
