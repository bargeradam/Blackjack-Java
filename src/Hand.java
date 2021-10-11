import java.util.ArrayList;

// This class exists so I can create a player hand and a computer hand, and get the total value of each players cards.
public class Hand extends ArrayList<Card> {
    public Hand(){

    }

    public int getValue() {
        int value =0;
        for(int i = 0; i < this.size(); i++) {
            value+=this.get(i).getRealValue();
        }
        return value;
    }



 }


