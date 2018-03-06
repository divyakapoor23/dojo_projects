import java.util.ArrayList;

public class DeckOfCards {
    public static void main(String[] args) {
        Card firstdraw = new Card();
        System.out.println(firstdraw.card);

        Deck newdeck = new Deck();
        newdeck.shuffle(newdeck.cards);
        System.out.println(newdeck.cards.get(46));
    }
}
