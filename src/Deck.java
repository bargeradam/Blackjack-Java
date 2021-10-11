import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> implements Comparable<Deck>{

    //Constructor creates a deck with 26 cards, I used half the deck because it was very time intensive to google search
    //and resize every single card facing.  I think hearts and diamonds will give functionality to the game, and the deck
    //shuffles every time.
    public Deck() {
        String cardSuit = "";
        for (int i = 0; i < 2; i++){
            switch(i) {
                case 0: cardSuit = "Heart";
                break;
                case 1: cardSuit = "Diamond";
                break;
            }
            for(int j = 0; j <13; j++){
                this.add(new Card(cardSuit,j));
            }
        }
    }

    public Card deal(){
        Collections.shuffle(this);
        Card copyCard = new Card(this.get(0));
        return copyCard;
    }
    @Override
    public int compareTo(Deck o) {
        return 0;
    }
}
