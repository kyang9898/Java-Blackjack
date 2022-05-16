import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // generate cards
        for (Suits cardSuit : Suits.values()) {
            for (Values cardValue : Values.values()) {
                // Add a new card
                this.deck.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public String toString() {
        String cardListOutput = "";
        for (Card card : this.deck) {
            cardListOutput += "\n-" + card.toString();
        }
        return cardListOutput;
    }

    public Card getCard(int i) {
        return this.deck.get(i);
    }

    public void removeCard(int i) {
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        this.deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.deck.size();


        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }

    public int cardsValue() {
        int totalCardValue = 0;
        int ace = 0;

        for (Card card : this.deck) {
            switch (card.getValue()) {
                case TWO:
                    totalCardValue += 2;
                    break;
                case THREE:
                    totalCardValue += 3;
                    break;
                case FOUR:
                    totalCardValue += 4;
                    break;
                case FIVE:
                    totalCardValue += 5;
                    break;
                case SIX:
                    totalCardValue += 6;
                    break;
                case SEVEN:
                    totalCardValue += 7;
                    break;
                case EIGHT:
                    totalCardValue += 8;
                    break;
                case NINE:
                    totalCardValue += 9;
                    break;
                case TEN, JACK, QUEEN, KING:
                    totalCardValue += 10;
                    break;
                case ACE:
                    ace += 1;
                    break;

            }
        }

        for (int i = 0; i < ace; i++) {
            if (totalCardValue > 10) {
                totalCardValue += 1;
            } else {
                totalCardValue += 11;
            }
        }

        return totalCardValue;
        
    }
}