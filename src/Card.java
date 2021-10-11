import javax.swing.*;

// This creates all the cards that go into our deck class.  It uses the jpeg/png files to create image
// icons for JLabels that make up the little cards.

public class Card extends JLabel {

    private int cardValue;
    private String cardSuit;


    public Card(String cardSuit, int cardValue) {

        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
        switch(cardSuit){
            case("Heart"):
                switch(cardValue) {
                    case 0: this.setIcon(new ImageIcon("twoofhearts.png"));
                    break;
                    case 1: this.setIcon(new ImageIcon("threeofhearts.png"));
                    break;
                    case 2: this.setIcon(new ImageIcon("fourofhearts.png"));
                    break;
                    case 3: this.setIcon(new ImageIcon("fiveofhearts.png"));
                    break;
                    case 4: this.setIcon(new ImageIcon("sixofhearts.png"));
                    break;
                    case 5: this.setIcon(new ImageIcon("sevenofhearts.png"));
                    break;
                    case 6: this.setIcon(new ImageIcon("eightofhearts.png"));
                    break;
                    case 7: this.setIcon(new ImageIcon("nineofhearts.png"));
                    break;
                    case 8: this.setIcon(new ImageIcon("tenofhearts.png"));
                    break;
                    case 9: this.setIcon(new ImageIcon("jackofhearts.png"));
                    break;
                    case 10: this.setIcon(new ImageIcon("queenofhearts1.png"));
                    break;
                    case 11: this.setIcon(new ImageIcon("kingofhearts.jpg"));
                    break;
                    case 12: this.setIcon(new ImageIcon("aceofhearts.png"));
            }
            break;
            case("Diamond"):
                switch(cardValue) {
                    case 0: this.setIcon(new ImageIcon("twoofdiamonds.png"));
                        break;
                    case 1: this.setIcon(new ImageIcon("threeofdiamonds.png"));
                        break;
                    case 2: this.setIcon(new ImageIcon("fourofdiamonds.png"));
                        break;
                    case 3: this.setIcon(new ImageIcon("fiveofdiamonds.png"));
                        break;
                    case 4: this.setIcon(new ImageIcon("sixofdiamonds.png"));
                        break;
                    case 5: this.setIcon(new ImageIcon("sevenofdiamonds.png"));
                        break;
                    case 6: this.setIcon(new ImageIcon("eightofdiamonds.png"));
                        break;
                    case 7: this.setIcon(new ImageIcon("nineofdiamonds.png"));
                        break;
                    case 8: this.setIcon(new ImageIcon("tenofdiamonds.png"));
                        break;
                    case 9: this.setIcon(new ImageIcon("jackofdiamonds.png"));
                        break;
                    case 10: this.setIcon(new ImageIcon("queenofdiamonds.png"));
                        break;
                    case 11: this.setIcon(new ImageIcon("kingofdiamonds.png"));
                        break;
                    case 12: this.setIcon(new ImageIcon("aceofhearts.png"));
                }
                break;
                    }


    }
    public Card(Card other) {
        this.cardValue = other.cardValue;
        this.cardSuit = other.cardSuit;
        switch(cardSuit){
            case("Heart"):
                switch (cardValue) {
                    case 0 -> this.setIcon(new ImageIcon("twoofhearts.png"));
                    case 1 -> this.setIcon(new ImageIcon("threeofhearts.jpg"));
                    case 2 -> this.setIcon(new ImageIcon("fourofhearts.png"));
                    case 3 -> this.setIcon(new ImageIcon("fiveofhearts.png"));
                    case 4 -> this.setIcon(new ImageIcon("sixofhearts.png"));
                    case 5 -> this.setIcon(new ImageIcon("sevenofhearts.png"));
                    case 6 -> this.setIcon(new ImageIcon("eightofhearts.png"));
                    case 7 -> this.setIcon(new ImageIcon("nineofhearts.png"));
                    case 8 -> this.setIcon(new ImageIcon("tenofhearts.png"));
                    case 9 -> this.setIcon(new ImageIcon("jackofhearts.png"));
                    case 10 -> this.setIcon(new ImageIcon("queenofhearts1.png"));
                    case 11 -> this.setIcon(new ImageIcon("kingofhearts.jpg"));
                    case 12 -> this.setIcon(new ImageIcon("aceofhearts.png"));
                }
                break;
            case("Diamond"):
                switch(cardValue) {
                    case 0: this.setIcon(new ImageIcon("twoofdiamonds.png"));
                        break;
                    case 1: this.setIcon(new ImageIcon("threeofdiamonds.png"));
                        break;
                    case 2: this.setIcon(new ImageIcon("fourofdiamonds.png"));
                        break;
                    case 3: this.setIcon(new ImageIcon("fiveofdiamonds.png"));
                        break;
                    case 4: this.setIcon(new ImageIcon("sixofdiamonds.png"));
                        break;
                    case 5: this.setIcon(new ImageIcon("sevenofdiamonds.png"));
                        break;
                    case 6: this.setIcon(new ImageIcon("eightofdiamonds.png"));
                        break;
                    case 7: this.setIcon(new ImageIcon("nineofdiamonds.png"));
                        break;
                    case 8: this.setIcon(new ImageIcon("tenofdiamonds.png"));
                        break;
                    case 9: this.setIcon(new ImageIcon("jackofdiamonds.png"));
                        break;
                    case 10: this.setIcon(new ImageIcon("queenofdiamonds.png"));
                        break;
                    case 11: this.setIcon(new ImageIcon("kingofdiamonds.png"));
                        break;
                    case 12: this.setIcon(new ImageIcon("aceofhearts.png"));
                }
                break;
        }
    }

    public int getCardValue() {
        return this.cardValue;
    }
    public String getCardSuit() {
        return this.cardSuit;
    }

    //Getting the value of the card
    public int getRealValue() {
        if (this.cardValue < 8) {
            return this.cardValue+2;
        } else if (this.cardValue == 12){
            return 11;
        } else {
            return 10;
        }
    }
}
