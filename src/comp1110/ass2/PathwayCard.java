package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

// author: Aditya Arora

public class PathwayCard {
    private List<String> cardsInHand = new ArrayList<>();

    public PathwayCard(String cardString) {
        cardsInHand = parseStringAndReturnCardsInHand(cardString);
    }

    private List<String> parseStringAndReturnCardsInHand(String cardString) {
        List<String> cardsInHand = new ArrayList<>();
        char currentDeck = ' ';

        int cardCounter = 0;

        for (int i = 0; i < cardString.length() && cardCounter < 6; i++) {
            char ch = cardString.charAt(i);

            if (Character.isUpperCase(ch)) {
                if(ch != 'A' && ch != 'B' && ch != 'C' && ch != 'D') {
                    throw new IllegalArgumentException("Deck does not exist");
                }
                currentDeck = ch;
            } else {
                String[] selectedDeck;

                switch (currentDeck) {
                    case 'A':
                        selectedDeck = Utility.DECK_A;
                        break;
                    case 'B':
                        selectedDeck = Utility.DECK_B;
                        break;
                    case 'C':
                        selectedDeck = Utility.DECK_C;
                        break;
                    case 'D':
                        selectedDeck = Utility.DECK_D;
                        break;
                    default:
                        selectedDeck = null;
                }

                if (selectedDeck != null) {
                    int cardIndex = ch - 'a'; // 'a' being the first card
                    if (cardIndex >= 0 && cardIndex < selectedDeck.length) {
                        cardCounter++;
                        cardsInHand.add(selectedDeck[cardIndex]);
                    }
                }
            }
        }

        return cardsInHand;
    }


    public int getNumberOfCardsInHand(){
        return cardsInHand.size();
    }

    public List<String> getCardsInHand() {
        return cardsInHand;
    }
}

