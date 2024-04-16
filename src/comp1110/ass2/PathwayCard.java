package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class PathwayCard {
    private List<String> cardsInHand = new ArrayList<>();

    public PathwayCard(String cardString) {
        parseCardString(cardString);
    }

    private void parseCardString(String cardString) {
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
    }

    public int getNumberOfCardsInHand(){
        return cardsInHand.size();
    }

    public List<String> getCardsInHand() {
        return cardsInHand;
    }
}

