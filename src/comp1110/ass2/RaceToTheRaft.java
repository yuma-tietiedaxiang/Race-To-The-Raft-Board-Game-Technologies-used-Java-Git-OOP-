package comp1110.ass2;

//import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.function.BiFunction;

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
    //author: Aditya Arora
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


    //author: Aditya Arora
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
     * @param gameState the length is 5, follow the order of [Board, Decks, Hand, Exhausted Cats,Fire tile bag].
     *                  each element is a string representing its current state
     * @return a fire tile string like "a000110111221"
     * @author Yu Ma
     */
    public static String drawFireTile(String[] gameState) {
        if (gameState[4].isEmpty()) {
            return "";
        }
        Random random = new Random();
        int index = random.nextInt(gameState[4].length());
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
     * <p>
     * The decks string denotes what decks to draw from and how many cards to draw from that deck.
     *
     * <p>
     * For example the drawRequest string "4A1B1D" tells us that we should draw 4 cards from deck A, 1 card from deck B
     * and
     * 1 card from deck D.
     * <p>
     * <p>
     * If I draw cards a, b, d, from deck A, card a from deck B and card s from deck D, I would return the string: "AabdBaDs".
     * <p>
     * Decks should be listed in alphabetical order, with cards drawn from that deck also listed in alphabetical order.
     *
     * </p>
     * Recall the mapping between deck and char:
     * A -> CIRCLE;
     * B -> CROSS;
     * C -> SQUARE;
     * D -> TRIANGLE;
     *
     * @param gameState   the current state of the game, including the current state of the decks
     * @param drawRequest A string representing the decks to draw from and the number of cards to draw from each respective deck.
     * @return The updated gameState array after the cards have been drawn. (Remove all cards drawn from decks and add them to the Hand string). If it is not possible to draw all the specified cards, you should return the original gameState.
     */

    // Weiqi Huang
    public static String[] drawHand(String[] gameState, String drawRequest) {
        // FIXME TASK 7 Done!
        // Get the current state of the decks
        Map<Character, Deck> deckMap = new HashMap<>();
        String deckState = gameState[1];
        for (char c = 'A'; c <= 'D'; c++) {
            String tempDeckInfo;
            if (c == 'D') {
                tempDeckInfo = deckState.substring(deckState.indexOf(c) + 1);
            } else {
                tempDeckInfo = deckState.substring(deckState.indexOf(c) + 1, deckState.indexOf(c + 1));
            }
            deckMap.put(c, new Deck(c, tempDeckInfo));
        }

        // The final drawn hand
        StringBuilder handDeck = new StringBuilder();

        // Iterate over the drawRequest string and record each deck request
        HashMap<Character, Integer> drawRequestMap = new HashMap();
        drawRequestMap.put('A', 0);
        drawRequestMap.put('B', 0);
        drawRequestMap.put('C', 0);
        drawRequestMap.put('D', 0);
        for (int i = 0; i < drawRequest.length(); i += 2) {
            // Get the current deck and the number of cards to draw
            char deckChar = drawRequest.charAt(i);
            int count = Character.getNumericValue(drawRequest.charAt(i + 1));
            drawRequestMap.put(deckChar, count);
        }


        // Randomly draw cards from the corresponding decks
        for (char deckChar = 'A'; deckChar <= 'D'; deckChar++) {

            StringBuilder tempHand = new StringBuilder();
            // Number of times to draw from this deck
            int count = drawRequestMap.get(deckChar);
            for (int j = 0; j < count; j++) {
                // The state of this deck
                String deckInfo = deckMap.get(deckChar).getDeckInfo();

                Deck deck = deckMap.get(deckChar);
                // If there are no cards to draw from this deck
                if (deck.hasNoCard()) {
                    return gameState;
                }
                // Randomly draw a card
                /*Random = new Random();
                int index = random.nextInt(deckInfo.toCharArray().length);
                char randomChar = deckInfo.charAt(index);*/
                //Add it to the hand
                tempHand.append(deck.drawCards());
                //After drawing the card, remove it from the deck
                deckMap.put(deckChar, deck);
            }
            //Sort the cards
            String str = tempHand.toString();
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            //the result: AabdBaDs
            handDeck.append(deckChar).append(sortedString);
        }
        //Update the latest deck state to gameState
        StringBuilder newDeckState = new StringBuilder();
        for (Character deckChar : deckMap.keySet()) {
            String deckInfo = deckMap.get(deckChar).getDeckInfo();
            newDeckState.append(deckChar).append(deckInfo);
        }
        gameState[1] = newDeckState.toString();

        //Assign the hand to gameState
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
     * @author Yu Ma, Weiqi Huang
     */
    public static String[] applyPlacement(String[] gameState, String placementString) {
        if (Character.isLetter(placementString.charAt(1)) && Character.isLetter(placementString.charAt(6))) {
            // place cards
            // Whether the string is neither a fire card nor a path card
            char deckType = placementString.charAt(0);
            char cardID = placementString.charAt(1);
            int row = Integer.parseInt(placementString.substring(2, 4));
            int col = Integer.parseInt(placementString.substring(4, 6));
            char orientation = placementString.charAt(6);

            // Get card data based on the type and ID of the card
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

            // Creating PlacedCard Objects
            PlacedCard card = new PlacedCard(cardData[0], orientation, row, col);

            // Update Board String
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

            // Removing cards from the Hand string
            String handString = gameState[2];

            // Find the location of the uppercase letter in the handString that represents the deck.
            int deckIndex = handString.indexOf(deckType);

            // If the deck logo is found
            if (deckIndex != -1) {
                // Search for a lowercase letter that matches the cardID after the deck ID.
                int cardIndex = handString.indexOf(cardID, deckIndex + 1);

                // If a matching deck is found
                if (cardIndex != -1) {
                    // Remove matching cards from handString
                    gameState[2] = handString.substring(0, cardIndex) + handString.substring(cardIndex + 1);
                }
            }
        } else {
            // Placement of Flame Blocks

            //evaluate placement string
            char fireID = placementString.charAt(0);
            String fileBag = "abcdefghigklmnopqrstuvwxyzABCDE";
            String fireTileString = Utility.FIRE_TILES[fileBag.indexOf(fireID)];
            int fireIDIndex = fileBag.indexOf(fireID);

            int placementRow = Integer.parseInt(placementString.substring(1, 3));//placement placementRow
            int placementCol = Integer.parseInt(placementString.substring(3, 5));//placement placementCol
            boolean flipped = placementString.charAt(5) == 'T';
            char orientation = placementString.charAt(6);


            //Calculate the maximum rows and columns of the board
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


    private static int boardLength(StringBuilder boardBuilder) {
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
     * @param gameState      An array representing the game state.
     * @param movementString A string representing the movement of a cat and the cards discarded to allow this move.
     *                       "B01100710Bm"
     * @return the updated gameState array after this movement has been made.
     * @author Yu Ma, Aditya Arora
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
        int indexForWithoutSpace = 0;

        for (int i = 0; i < boardMaxRow; i++) {
            for (int j = 0; j < boardMaxColumn; j++) {
                boardSquares[i][j] = new Square(Colour.fromChar(boardWithoutSpace.charAt(indexForWithoutSpace++)));
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
        String catPosition = "" + cat;
        if (toRow < 10 && toCol < 10) {
            catPosition += "" + 0 + toRow + 0 + toCol;
        } else if (toRow > 9 && toCol < 10) {
            catPosition += "" + toRow + 0 + toCol;
        } else if (toRow < 10 && toCol > 9) {
            catPosition += "" + 0 + toRow + toCol;
        } else if (toRow > 9 && toCol > 9) {
            catPosition += "" + toRow + toCol;
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
     * @param challengeString A string representing the challenge to initialise
     * @return A board string for this challenge.
     * @author Yu Ma
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
        // Parses the placement string to determine if it is a card placement or a flame placement.
        if (Character.isLetter(placementString.charAt(1)) && Character.isLetter(placementString.charAt(6))) {
            // Card Placement
            int row = Integer.parseInt(placementString.substring(2, 4));
            int col = Integer.parseInt(placementString.substring(4, 6));
            char orientation = placementString.charAt(6);

            String[] cardData = new String[]{Utility.DECK_A[placementString.charAt(1) - 'a']};
            PlacedCard card = new PlacedCard(cardData[0], orientation, row, col);

            // Check that the cards are not beyond the board boundaries
            if (row < 0 || row + 2 >= boardLength(gameState[0]) || col < 0 || col + 2 >= boardLength(gameState[0])) {
                return false;
            }

            // Check that the cards don't overlap with flame, cat or raft cards
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
            // fire Placement
            int row = Integer.parseInt(placementString.substring(1, 3));
            int col = Integer.parseInt(placementString.substring(3, 5));
            boolean flipped = placementString.charAt(5) == 'T';
            char orientation = placementString.charAt(6);

            // evaluate placement string
            char fireID = placementString.charAt(0);
            String fileBag = "abcdefghijklmnopqrstuvwxyzABCDE";
            int fireIDIndex = fileBag.indexOf(fireID);

            if (fireIDIndex < 0 || fireIDIndex >= Utility.FIRE_TILES.length) {
                throw new IllegalArgumentException("Invalid fireID: " + fireID);
            }

            String fireTileString = Utility.FIRE_TILES[fileBag.indexOf(fireID)];

//            System.out.println("fireID: " + fireID + ", fireIDIndex: " + fireIDIndex);
            int placementRow = Integer.parseInt(placementString.substring(1, 3)); // placement placementRow
            int placementCol = Integer.parseInt(placementString.substring(3, 5));
            // Calculate the maximum rows and columns of the board
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

            PlacedFireTile fireTile = new PlacedFireTile(fireTileString, placementRow, placementCol, flipped, orientation, boardMaxRow, boardMaxColumn);
            for (Square square : fireTile.getSquares()) {
                int boardRow = square.getLocation().getRow() + placementRow;
                int boardCol = square.getLocation().getColumn() + placementCol;
                if (boardRow < 0 || boardRow >= boardMaxRow || boardCol < 0 || boardCol >= boardMaxColumn) {
                    return false;
                }
            }


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

            // Check that the flame block is orthogonally adjacent to the existing flame
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
        // Parsing cat movement strings
        char catColor = catMovementString.charAt(0);
        int startRow = Integer.parseInt(catMovementString.substring(1, 3));
        int startCol = Integer.parseInt(catMovementString.substring(3, 5));
        int endRow = Integer.parseInt(catMovementString.substring(5, 7));
        int endCol = Integer.parseInt(catMovementString.substring(7, 9));
        theBoard = new TheBoard(gameState[0]);
        String[] move = catMovementString.split("(?=\\p{Upper})");

        if (endRow < 0 || endRow >= theBoard.getSquares().length || endCol < 0 || endCol >= theBoard.getSquares()[0].length) {
            return false;
        }//cross-border

        if (gameState[3].contains(catMovementString.substring(0, 5)) && move.length < 3) {
            return false;
        }
        String[] hands = gameState[2].split("(?=\\p{Upper})");
        for (int i = 1; i < move.length; i++) {
            int index = move[i].charAt(0) - 'A';
            for (int j = 1; j < move[i].length(); j++) {
                if (hands[index].indexOf(move[i].charAt(j)) == -1) return false;
            }
        }

        // Check that the end position matches the colour of the cat
        if (theBoard.squares[endRow][endCol].colour.toChar() != Character.toLowerCase(catColor)) {
            return false;
        }

        // Use breadth-first search (BFS) to check if there is a path made up of squares of the same colour, excluding diagonal shifts
        int rows = boardLength(gameState[0]);
        int cols = rows;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});

        for (int i = 0; i < theBoard.rows; i++) {
            for (int j = 0; j < theBoard.columns; j++) {
                if (Character.toLowerCase(theBoard.squares[i][j].colour.toChar()) != Character.toLowerCase(catColor)) {
                    theBoard.visited[i][j] = true;
                } else {
                    theBoard.visited[i][j] = false;
                }
            }
        }


        return theBoard.dfs(startRow, startCol, endRow, endCol);


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


        if (!isRaftInCorrectPosition(board1)) {
            System.out.println("Raft is not in the correct position.");
        }

        String actionType = determineActionType(action);
        BiFunction<String[], String, Boolean> handler = actionHandlers.get(actionType);
        if (handler != null) {
            boolean gameOver = handler.apply(gameState, action);
            if (!gameOver) {
                return false;
            }
        }

        for (Map.Entry<String, BiFunction<String[], String, Boolean>> entry : actionHandlers.entrySet()) {
            String type = entry.getKey();
            BiFunction<String[], String, Boolean> handlerFunc = entry.getValue();
            if (handlerFunc != handler && type.equals(actionType)) {
                boolean gameOver = handlerFunc.apply(gameState, action);
                if (!gameOver) {
                    return false;
                }
            }
        }

        System.out.println("Game is over.");
        return true;
    }


    private static final Map<String, BiFunction<String[], String, Boolean>> actionHandlers = new HashMap<>();

    static {
        actionHandlers.put("fireTilePlacement", RaceToTheRaft::handleFireTilePlacement);
        actionHandlers.put("catMove", RaceToTheRaft::handleCatMove);
        actionHandlers.put("cardPlacement", RaceToTheRaft::handleCardPlacement);
    }

    private static String determineActionType(String action) {
        if (isFireTilePlacement(action)) {
            return "fireTilePlacement";
        } else if (isCatMove(action)) {
            return "catMove";
        } else if (isCardPlacement(action)) {
            return "cardPlacement";
        }
        return "unknown";
    }

    private static boolean isFireTilePlacement(String action) {
        return action.length() == 7 && Character.isLetter(action.charAt(0)) && Character.isLetter(action.charAt(6));
    }

    private static boolean isCatMove(String action) {
        return action.length() >= 10;
    }

    private static boolean isCardPlacement(String action) {
        return action.length() == 7;
    }

    private static boolean handleFireTilePlacement(String[] gameState, String action) {
        System.out.println("Action type: Fire tile placement");
        if (!isPlacementValid(gameState, action)) {
            System.out.println("Placement invalid: " + action);
            if (!hasValidFireTilePlacement(gameState, new TheBoard(gameState[0]), action.charAt(0))) {
                System.out.println("No valid placements left, game over.");
                return true;
            }
        }

        String[] updatedGameState = applyPlacement(gameState, action);
        TheBoard updatedBoard = new TheBoard(updatedGameState[0]);

        System.out.println("Updated gameState after placement: " + Arrays.toString(updatedGameState));

        if (!isValidGameState(updatedGameState)) {
            System.out.println("Updated game state is empty or invalid.");
            return false;
        }

//        if (isCatSurroundedByFire(updatedBoard)) {
//
//            return true;
//        }
//        System.out.println("A cat is not surrounded by fire within a 9x9 area and can reach the raft.");


        if (!areAllCatsAbleToReachRaft(updatedBoard)) {
            System.out.println("A cat cannot reach the raft.");
            return true;
        }


        return false;
    }

//    private static boolean isCatSurroundedByFire(TheBoard board) {
//        for (int row = 0; row < board.getRows(); row++) {
//            for (int col = 0; col < board.getColumns(); col++) {
//                if (Character.isUpperCase(board.getColour(row, col).toChar())) {
//                    System.out.println("Checking cat at position: (" + row + ", " + col + ")");
//                    if (isFireWithin9x9Area(board, row, col) && !hasPathToObjective(board, row, col)) {
//                        System.out.println("Cat at (" + row + ", " + col + ") is surrounded by fire and has no path to objective.");
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    private static boolean hasPathToObjective(TheBoard board, int catRow, int catCol) {
//        int rowStart = Math.max(0, catRow - 4);
//        int rowEnd = Math.min(board.getRows() - 1, catRow + 4);
//        int colStart = Math.max(0, catCol - 4);
//        int colEnd = Math.min(board.getColumns() - 1, catCol + 4);
//
//        char catColor = Character.toLowerCase(board.getColour(catRow, catCol).toChar());
//        System.out.println("Checking path for cat at (" + catRow + ", " + catCol + ") with color " + catColor);
//
//        for (int row = rowStart; row <= rowEnd; row++) {
//            for (int col = colStart; col <= colEnd; col++) {
//                char currentColor = board.getColour(row, col).toChar();
//                if (currentColor != 'f' && currentColor != 'o') {
//                    if (currentColor == 'w' || currentColor == catColor) {
//                        System.out.println("Trying path from (" + row + ", " + col + ")");
//                        if (board.raftDfs(row, col, catColor)) {
//                            System.out.println("Found path to objective for cat at (" + catRow + ", " + catCol + ")");
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private static boolean isFireWithin9x9Area(TheBoard board, int catRow, int catCol) {
//        int rowStart = Math.max(0, catRow - 4);
//        int rowEnd = Math.min(board.getRows() - 1, catRow + 4);
//        int colStart = Math.max(0, catCol - 4);
//        int colEnd = Math.min(board.getColumns() - 1, catCol + 4);
//
//        // Check top and bottom rows
//        for (int col = colStart; col <= colEnd; col++) {
//            if (board.getColour(rowStart, col).toChar() != 'f' || board.getColour(rowEnd, col).toChar() != 'f') {
//                System.out.println("Fire not within 9x9 area at row: (" + rowStart + " or " + rowEnd + "), col: " + col);
//                return false;
//            }
//        }
//
//        // Check left and right columns
//        for (int row = rowStart + 1; row < rowEnd; row++) {
//            if (board.getColour(row, colStart).toChar() != 'f' || board.getColour(row, colEnd).toChar() != 'f') {
//                System.out.println("Fire not within 9x9 area at row: " + row + ", col: (" + colStart + " or " + colEnd + ")");
//                return false;
//            }
//        }
//
//        System.out.println("Fire is within 9x9 area for cat at (" + catRow + ", " + catCol + ")");
//        return true;
//    }


    private static boolean handleCatMove(String[] gameState, String action) {
        System.out.println("Action type: Cat move");

        String[] updatedGameState = moveCat(gameState, action);
        TheBoard updatedBoard = new TheBoard(updatedGameState[0]);

        System.out.println("Updated gameState after moving cat: " + Arrays.toString(updatedGameState));


        if (areAllCatsOnRaft(updatedBoard)) {
            System.out.println("All cats are on the raft.");
            return true;
        }

        return false;
    }

    private static boolean handleCardPlacement(String[] gameState, String action) {
        System.out.println("Action type: Card placement");

        String[] updatedGameState = applyPlacement(gameState, action);

        System.out.println("Updated gameState after placing card: " + Arrays.toString(updatedGameState));

        if (updatedGameState[4].isEmpty()) {
            System.out.println("Fire tile backpack is empty.");
            return true;
        }

//        TheBoard updatedBoard = new TheBoard(updatedGameState[0]);
//        if (!areAllCatsAbleToReachRaft(updatedBoard)) {
//            System.out.println("A cat cannot reach the raft.");
//            return true;
//        }

        return false;
    }

    private static boolean isValidGameState(String[] gameState) {
        return gameState != null && gameState.length > 0 && gameState[0] != null && !gameState[0].isEmpty();
    }

    private static boolean hasValidFireTilePlacement(String[] gameState, TheBoard board1, char ID) {
        if (findValidPlacement(gameState, board1, ID)) {
            return true;
        }
//        for (char fireID : gameState[4].toCharArray()) {
//            if (findValidPlacement(gameState, board1, fireID)) {
//                return true;
//            }
//        }
        return false;
    }

    private static boolean findValidPlacement(String[] gameState, TheBoard board, char fireID) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (checkPlacementOptions(gameState, board, fireID, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkPlacementOptions(String[] gameState, TheBoard board, char fireID, int row, int col) {
        for (boolean flipped : new boolean[]{false, true}) {
            for (char orientation : new char[]{'N', 'E', 'S', 'W'}) {
                String placementString = String.format("%c%02d%02d%c%c", fireID, row, col, flipped ? 'T' : 'F', orientation);
                if (isPlacementValid(gameState, placementString)) {
                    System.out.println("Found valid placement: " + placementString);
                    return true;
                }
            }
        }
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

        return checkRaftOuterRing(board, startRow, startCol);
    }

    private static boolean checkRaftOuterRing(TheBoard board, int startRow, int startCol) {
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (!isValidRaftCell(board, row, col, startRow, startCol)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidRaftCell(TheBoard board, int row, int col, int startRow, int startCol) {
        Colour colour = board.getColour(row, col);
        if (colour != Colour.OBJECTIVE && colour != Colour.WILD) {
            if (row == startRow || row == startRow + 2 || col == startCol || col == startCol + 2) {
                return colour == Colour.WILD || Character.isLowerCase(colour.toChar());
            }
        }
        return true;
    }

    private static boolean areAllCatsAbleToReachRaft(TheBoard board) {
        int rows = board.getRows();
        int cols = board.getColumns();

        for (int catRow = 0; catRow < rows; catRow++) {
            for (int catCol = 0; catCol < cols; catCol++) {
                if (Character.isUpperCase(board.getColour(catRow, catCol).toChar()) && !isCatUnableToReachRaft(board, catRow, catCol)) {
                    System.out.println("Checking if cat at (" + catRow + ", " + catCol + ") can reach the raft.");
                    System.out.println("Cat at (" + catRow + ", " + catCol + ") cannot reach the raft.");
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isCatUnableToReachRaft(TheBoard board, int catRow, int catCol) {
        char catSquare = board.getColour(catRow, catCol).toChar();
        if (Character.isUpperCase(catSquare)) {
            char catColor = Character.toLowerCase(catSquare);
            System.out.println("Checking if cat at (" + catRow + ", " + catCol + ") with color " + catColor + " can reach any raft.");
            boolean can = canCatReachAnyRaft(board, catRow, catCol, catColor);
            return can;
        }
        return false;
    }

    private static boolean canCatReachAnyRaft(TheBoard board, int catRow, int catCol, char catColor) {
        int rows = board.getRows();
        int cols = board.getColumns();
        for (int i = 0; i < board.rows; i++) {
            for (int j = 0; j < board.columns; j++) {
                if (Character.toLowerCase(board.squares[i][j].colour.toChar()) == 'f') {
                    //&& board.squares[i][j].colour.toChar() != 'w'
                    board.visited[i][j] = true;
                } else {
                    board.visited[i][j] = false;
                }
            }
        }
        System.out.println("Starting DFS for cat at (" + catRow + ", " + catCol + ") with color " + catColor);
        return board.raftDfs(catRow, catCol, catColor);

        /*for (int raftRow = 0; raftRow < rows; raftRow++) {
            for (int raftCol = 0; raftCol < cols; raftCol++) {
                if (board.getColour(raftRow, raftCol).toChar()=='o') {
                    if (board.dfs(catRow, catCol, raftRow, raftCol, catColor)) {
                        return true;
                    }
                }
            }
        }
        return false;*/
    }

    private static boolean areAllCatsOnRaft(TheBoard board) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (isCatNotOnRaft(board, row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isCatNotOnRaft(TheBoard board, int row, int col) {
        if (Character.isUpperCase(board.getColour(row, col).toChar())) {
            char catColor = Character.toLowerCase(board.getColour(row, col).toChar());
            return !isCatOnAnyRaft(board, row, col, catColor);
        }
        return false;
    }

    private static boolean isCatOnAnyRaft(TheBoard board, int row, int col, char catColor) {
        for (int raftRow = row - 1; raftRow <= row + 1; raftRow++) {
            for (int raftCol = col - 1; raftCol <= col + 1; raftCol++) {
                if (raftRow >= 0 && raftRow < board.getRows() && raftCol >= 0 && raftCol < board.getColumns()) {
                    if (board.getColour(raftRow, raftCol).toChar() == 'o') {
                        return true;
                    }
                }
            }
        }
        return false;
        /*for (int raftRow = 0; raftRow <= board.getRows() - 3; raftRow++) {
            for (int raftCol = 0; raftCol <= board.getColumns() - 3; raftCol++) {
                if (isRaftAtPosition(board, raftRow, raftCol)) {
                    if (isCatOnRaft(board, row, col, catColor, raftRow, raftCol)) {
                        return true;
                    }
                }
            }
        }
        return false;*/
    }

    private static boolean isCatOnRaft(TheBoard board, int row, int col, char catColor, int raftRow, int raftCol) {
        for (int rRow = raftRow; rRow < raftRow + 3; rRow++) {
            for (int rCol = raftCol; rCol < raftCol + 3; rCol++) {
                Colour raftColour = board.getColour(rRow, rCol);
                if (isCatOnRaftCell(raftColour, catColor, row, col, rRow, rCol)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCatOnRaftCell(Colour raftColour, char catColor, int row, int col, int rRow, int rCol) {
        return (raftColour.toChar() == catColor || raftColour == Colour.WILD || raftColour == Colour.OBJECTIVE) && (row == rRow && col == rCol);
    }
}


// FIXME TASK 15


//    public static void main(String[] args) {
//        String[] strings = RaceToTheRaft.drawHand(new String[]{}, "A1C2D3");
//        System.out.println(Arrays.toString(strings));
//    }
//}

