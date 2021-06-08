package game.card;

import java.util.ArrayList;

public class Hand {

    // Variables
    private Card[] hand;

    // Constants
    private final int HAND_SIZE = 7;


    public Hand(Card[] tableCards, Card[] playerCards) {
        hand = new Card[HAND_SIZE];

        for (int i=0; i<HAND_SIZE; i++) {
            if (i < tableCards.length){
                hand[i] = tableCards[i];
            }
            else{
                hand[i] = playerCards[i - tableCards.length];
            }
        }
    }

    public double getHandValue() {
        double value;

        if ((value = getStraightFlushValue()) != 0){
            return 800 + value;
        }
        if ((value = getFourOfAKindValue()) != 0){
            return 700 + value;
        }
        if ((value = getFullHouseValue()) != 0){
            return 600 + value;
        }
        if ((value = getFlushValue()) != 0){
            return 500 + value;
        }
        if ((value = getStraightValue()) != 0){
            return 400 + value;
        }
        if ((value = getThreeOfAKindValue()) != 0){
            return 300 + value;
        }
        if ((value = getTwoPairsValue()) != 0){
            return 200 + value;
        }
        if ((value = getPairValue()) != 0){
            return 100 + value;
        }
        return getHighestCardValues(5);
    }


    // ------------------------ Hands -----------------------------

    private double getFullHouseValue(){
        
        int threeOfAKindValue = (int) getThreeOfAKindValue();

        if (threeOfAKindValue == 0){
            return 0;
        }

        ArrayList<Integer> tmp = new ArrayList<Integer>();

        for (int i = 0; i < HAND_SIZE; i++) {
            int figure = hand[i].getFigure();

            if (tmp.contains(figure) && figure != threeOfAKindValue) {
                return threeOfAKindValue * 2 + figure / 10;
            }
            tmp.add(figure);
        }
        return 0;
    }

    private int getStraightFlushValue() {

        sort(true);

        for (int i = 0; i < 3; i++) {
            int figure = hand[i].getFigure();
            char suit = hand[i].getSuit();
            boolean found = true;

            for (int j = 1; j < 5; j++) {   // Check if the next 4 cards are increasing by 1 and are the same suit
                if (( hand[i+j].getFigure() != figure - j ) || ( hand[i+j].getSuit() != suit )){
                    found = false;
                    break;
                }
            }

            if (found){
                return figure;
            }
        }
        return 0;
    }

    private int getStraightValue(){

        sort(true);

        for (int i = 0; i < 3; i++) {
            int figure = hand[i].getFigure();
            boolean found = true;

            for (int j = 1; j < 5; j++) {   // Check if the next 4 cards are increasing by 1
                if (hand[i+j].getFigure() != figure - j){
                    found = false;
                    break;
                }
            }

            if (found){
                return figure;
            }
        }
        return 0;
    }

    private double getFlushValue(){
        
        sort(false);

        for (int i = 0; i < 3; i++) {
            char suit = hand[i].getSuit();
            boolean found = true;
            
            double decimal = Math.pow(10, hand[i].getFigure() - 15);  // Used for comparing flush hands together

            for (int j = 1; j < 5; j++) {   // Check if the next 4 cards are the same suit
                if (hand[i+j].getSuit() != suit){
                    found = false;
                    break;
                }

                decimal += Math.pow(10, hand[i+j].getFigure() - 15);
            }

            if (found){
                return decimal;
            }
        }
        return 0;
    }

    private double getFourOfAKindValue(){

        sort(true);

        for (int i = 0; i < 4; i++) {
            int figure = hand[i].getFigure();
            boolean found = true;

            for (int j = 1; j < 4; j++) {   // Check if the next 3 cards are the same
                if (hand[i+j].getFigure() != figure){
                    found = false;
                    break;
                }
            }

            if (found){
                return figure + getHighestCardValues(1, figure);
            }
        }
        return 0;
    }

    private double getThreeOfAKindValue(){

        sort(true);

        for (int i = 0; i < 5; i++) {
            int figure = hand[i].getFigure();
            boolean found = true;

            for (int j = 1; j < 3; j++) {   // Check if the next 2 cards are the same
                if (hand[i+j].getFigure() != figure){
                    found = false;
                    break;
                }
            }

            if (found){
                return figure + getHighestCardValues(2, figure);
            }
        }
        return 0;
    }

    private double getTwoPairsValue() {

        sort(true);

        ArrayList<Integer>tmp = new ArrayList<Integer>();
        int pair = 0;

        for (int i = 0; i < HAND_SIZE; i++) {
            int figure = hand[i].getFigure();

            if (tmp.contains(figure) && figure != pair) {
                if (pair != 0) {
                    if (pair > figure){
                        return pair * 2 + figure / 10d + getHighestCardValues(1, figure, pair);
                    }
                    else{
                        return figure * 2 + pair / 10d + getHighestCardValues(1, figure, pair);
                    }
                }
                pair = figure;
            }
            tmp.add(figure);
        }
        return 0;
    }

    private double getPairValue() {

        sort(true);

        ArrayList<Integer> tmp = new ArrayList<Integer>();

        for (int i = 0; i < HAND_SIZE; i++) {
            int figure = hand[i].getFigure();

            if (tmp.contains(figure)) {
                return figure + getHighestCardValues(3, figure);
            }
            tmp.add(figure);
        }
        return 0;
    }

    private double getHighestCardValues(int num, int... differentCards){

        double value = 0;

        sort(true);

        ArrayList<Integer> tmp = new ArrayList<Integer>();

        for (int card : differentCards){
            tmp.add(card);
        }

        int j = 0;
        
        for (int i=0; i<HAND_SIZE; i++){
            if (!tmp.contains(hand[i].getFigure())){
                value += Math.pow(10, hand[i].getFigure() - 15);
                j++;
                if (j == num){
                    break;
                }
            }
        }

        return value;
    }

    // Selection sort
    private void sort(boolean sortingFigure){

        for (int i=0; i<HAND_SIZE; i++){
            int min = i;

            for (int j=i+1; j<HAND_SIZE; j++){
                
                if (sortingFigure){
                    if (hand[j].getFigure() > hand[min].getFigure()){
                        min = j;
                    }
                }
                else{
                    if (hand[j].getSuit() > hand[min].getSuit()){
                        min = j;
                    }
                }
            }

            Card tmp = hand[i];
            hand[i] = hand[min];
            hand[min] = tmp;
        }
    }
}