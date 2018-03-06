import java.util.Random;
import java.util.ArrayList;

public class Deck{
    public ArrayList<String> cards;
    public Deck() {
        String smoosh = " of ";
        String[] suit = new String[] {"Hearts", "Spades", "Diamonds", "Clubs"};
        String[] cardnum = new String[] {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};

        ArrayList<String> decko = new ArrayList<>();
        for(int x=0; x<suit.length; x++){
            for(int y=0; y<cardnum.length; y++){
                decko.add(cardnum[y] + smoosh + suit[x]);
            }
        }
        cards = decko;
    }

    public void shuffle(ArrayList<String> deck){
        Random rand = new Random();
        for(int x=0; x<deck.size(); x++) {
            int y = rand.nextInt(deck.size());
            String temp = deck.get(x);
            deck.set(x,deck.get(y));
            deck.set(y,temp);
        }
        // System.out.println(deck);
    }

}