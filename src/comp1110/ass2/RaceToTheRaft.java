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
    // FIXME TASK 3




    /**
     * Draws a random fire tile from those remaining in the bag.
     *
     * @param gameState the current state of the game, including the fire tile bag
     * @return a random fire tile from those remaining, in string form. If there are no tiles remaining, return the
     * empty string.
     */

    // Yu Ma
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
