package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class PathwayCard {
    private List<String> cardsInHand = new ArrayList<>();

    public PathwayCard(String cardString) {
        cardsInHand = parseStringAndReturnCardsInHand(cardString);
    }

    private List<String> parseStringAndReturnCardsInHand(String cardString) {

        List<String> cardsInHand = new ArrayList<>();

        char currentDeck = ' ';
        for (int i = 0; i < cardString.length(); i++) {
            char ch = cardString.charAt(i);
            if (Character.isUpperCase(ch)) {
                currentDeck = ch;
            } else {

                String[] selectedDeck = new String[25];

                if(currentDeck == 'A'){
                    selectedDeck = Utility.DECK_A;
                } else if (currentDeck == 'B') {
                    selectedDeck = Utility.DECK_A;
                }else if (currentDeck == 'C') {
                    selectedDeck = Utility.DECK_C;
                }else if (currentDeck == 'D') {
                    selectedDeck = Utility.DECK_D;
                }
                if (selectedDeck != null) {
                    int cardIndex = ch - 'a'; // a being the first card
                    if (cardIndex >= 0 && cardIndex < selectedDeck.length) {
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

