package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// author: Yu Ma
public class Card {
    /*
    every card(islands, pathway, fire, raft) is a card
     */
    Location mainLocation;//say the upper left point is the mainLocation
    Square[][] squares;//2D

    private char deckId;

    //id like a, b, c .... , y
    private char cardId;

    char[][] cardColours = new char[3][3];

    //easier approach for colors
    private String colors;

    public Card(String cardString) {
        this.cardId = cardString.charAt(0);
        this.colors = cardString.substring(1);
    }

    public String getColors() {
        return colors;
    }

    public Card(){}
    public Card(char deckId, char cardId, String pattern) {
        this.deckId = deckId;
        this.cardId = cardId;
        setCardColours(pattern);
    }

    public static List<Card> convertStringArrayOfCardsToListOldApproach(String[] deck) {
        List<Card> cardList = new ArrayList<>();
        for (String cardString : deck) {
            cardList.add(new Card(cardString));
        }
        return cardList;
    }

    public static List<Card> convertStringArrayOfCardsToList(String[] deck) {
        return Arrays.stream(deck)
                .map(Card::new) // Convert each string to a Card object
                .collect(Collectors.toList()); // Collect results to a list
    }

    private void setCardColours(String pattern) {
        String[] rows = pattern.split("(?<=\\G.{9})"); // Split string every 9 characters
        for (int i = 0; i < 9; i++) {
            cardColours[i] = rows[i].toCharArray();
        }
    }

    public char getDeckId() {
        return deckId;
    }

    public char getCardId() {
        return cardId;
    }

    public char[][] getCardColours() {
        return cardColours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck ").append(deckId).append(", Card ").append(cardId).append(":\n");
        for (int i = 0; i < 9; i++) {
            sb.append(new String(cardColours[i])).append("\n");
        }
        return sb.toString();
    }

    public void setMainLocation(int row, int column){
    }

//    public boolean overLap(BigBoard b){
//        return false;
//    }// all card cannot overlap fire or cat
}