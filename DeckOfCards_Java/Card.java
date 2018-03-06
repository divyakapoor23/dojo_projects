import java.util.Random;

public class Card {
    String smoosh = " of ";
    String[] suit = new String[] {"Hearts", "Spades", "Diamonds", "Clubs"};
    String[] cardnum = new String[] {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};

    Random rand = new Random();
    int a = rand.nextInt(suit.length);
    int b = rand.nextInt(cardnum.length);

    String card = cardnum[b] + smoosh + suit[a];
}
