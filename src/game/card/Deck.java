package game.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Deck{

    // Variable
    static private ArrayList<String> cards;
    static private int numOfCardInDeck = 52;

    public Deck(){
        resetDeck();
    }

    public static Card[] dealCards(int numCards) {
        Random rand = new Random();
        Card[] tmp = new Card[numCards];

        for (int i=0; i<numCards; i++){
            int card = rand.nextInt(numOfCardInDeck);
        
            tmp[i] = new Card(cards.get(card));

            cards.remove(card);
            numOfCardInDeck--;
        }
        
        return tmp;
    }

    public static void resetDeck() {
        cards = new ArrayList<String>( Arrays.asList(
        "AH","2H","3H","4H","5H","6H","7H","8H","9H","TH","JH","QH","KH",
        "AD","2D","3D","4D","5D","6D","7D","8D","9D","TD","JD","QD","KD",
        "AC","2C","3C","4C","5C","6C","7C","8C","9C","TC","JC","QC","KC",
        "AS","2S","3S","4S","5S","6S","7S","8S","9S","TS","JS","QS","KS"));

        numOfCardInDeck = 52;
    }
}