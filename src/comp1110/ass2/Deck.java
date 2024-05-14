package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final DeckType deckType;
    //private ArrayList<Card> cards;
    private String deckInfo;



    public Deck(char type, String deckInfo){
        this.deckType = DeckType.fromChar(type);
        this.deckInfo=deckInfo;
    }
    public String getDeckInfo(){
        return deckInfo;
    }
    public boolean hasNoCard(){// see if this deck is empty
        if (deckInfo.isEmpty()) {
            return true;}
        return false;
    }

    public char drawCards(){
        Random random = new Random();
        int index = random.nextInt(deckInfo.toCharArray().length);
        char randomChar = deckInfo.charAt(index);
        deckInfo = deckInfo.replace(String.valueOf(randomChar), "");
        return randomChar;
    }

}
