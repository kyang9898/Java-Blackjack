import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods
        // that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();
        
        int playerMoneyAmount = 2000;

        Scanner scanner = new Scanner(System.in);

        while (playerMoneyAmount > 0) {
            System.out.print("Your amount of money is $" + playerMoneyAmount
                    + ", what is the amount that you would like to wager? ");
            int playerWagerAmount = Integer.valueOf(scanner.nextInt());
            if (playerWagerAmount % 5 != 0) {
                System.out.println("You can only bet in increments of $5");
                continue;
            }

            if (playerWagerAmount > playerMoneyAmount) {
                System.out.println("You cannot wager more than your total amount of money");
                System.out.println("Wager an amount that is less than or equal to your total amount of money");
                break;
            }

            boolean endOfRound = false;

            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            // if the player gets a pair of cards with the same values, they can choose to
            // split the cards and double down
            if (playerHand.getCard(0).getValue() == playerHand.getCard(1).getValue()) {
                System.out.println("Would you like to split your pair and double down?");
                Deck splitPair = new Deck();
                splitPair.addCard(new Card());
                splitPair.addCard(new Card());

            }

            while (true) {
                System.out.println("Your hand is: ");
                System.out.println(playerHand.toString());
                System.out.println("Your hand total is: " + playerHand.cardsValue());
                System.out.println("Dealer Hand is: " + dealerHand.getCard(0).toString() + " and [Unknown Card]");
                System.out.println(
                        "Do you want to Double Down on your wager? If so your wager will be doubled and you will be dealt another card.");
                String userInput = scanner.next();
                boolean doubleDownWager = false;
                String hitOrStay = "";

                do {

                    if (!userInput.equals("yes")) {
                        System.out.print("Would you like to hit or stay? ");
                        System.out.println("Type the letter h to hit or type the letter s to stay");
                        hitOrStay = scanner.next();
                        if (hitOrStay.charAt(0) == 'h' || hitOrStay.charAt(0) == 'H') {
                            playerHand.draw(playingDeck);
                            System.out
                                    .println("You draw a: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                            System.out.println("Your hand total is now: " + playerHand.cardsValue());
                            if (playerHand.cardsValue() > 21) {
                                System.out.println(
                                        "Bust. Over 21! You Lose! Current total is: " + playerHand.cardsValue());
                                playerMoneyAmount -= playerWagerAmount;
                                endOfRound = true;
                                break;

                            }
                        }

                        if (hitOrStay.charAt(0) == 's' || hitOrStay.charAt(0) == 'S') {
                            break;

                        }
                    }

                    if (userInput.equals("yes")) {
                        if (playerWagerAmount * 2 > playerMoneyAmount) {
                            System.out
                                    .println("You do not have enough money to double down. Continue playing your hand");
                            System.out.print("Would you like to hit or stay? ");
                            System.out.println("Type the letter h to hit or type the letter s to stay");
                            hitOrStay = scanner.next();
                            if (hitOrStay.charAt(0) == 'h' || hitOrStay.charAt(0) == 'H') {
                                playerHand.draw(playingDeck);
                                System.out.println(
                                        "You drew a: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                                System.out.println("Your hand total is now: " + playerHand.cardsValue());
                                if (playerHand.cardsValue() > 21) {
                                    System.out.println(
                                            "Bust. Over 21! You Lose! Current total is: " + playerHand.cardsValue());
                                    playerMoneyAmount -= playerWagerAmount;
                                    endOfRound = true;
                                    break;

                                }
                            }

                            if (hitOrStay.charAt(0) == 's' || hitOrStay.charAt(0) == 'S') {
                                break;

                            }

                        } else {

                            playerWagerAmount = playerWagerAmount * 2;
                            doubleDownWager = true;
                            System.out.println("Your wager is now $" + playerWagerAmount);
                            playerHand.draw(playingDeck);
                            System.out
                                    .println("You drew a: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                            System.out.println("Your hand total is now: " + playerHand.cardsValue());
                            if (playerHand.cardsValue() > 21) {
                                System.out.println(
                                        "Bust. Over 21! You Lose! Current total is: " + playerHand.cardsValue());
                                playerMoneyAmount -= playerWagerAmount;
                                endOfRound = true;
                                break;

                            }
                        }
                    }

                } while (!doubleDownWager);

                break;

            }

            System.out.println("Dealer Cards: " + dealerHand.toString());
            if ((dealerHand.cardsValue() >= 17) && (dealerHand.cardsValue() > playerHand.cardsValue()) && !endOfRound) {
                System.out.println("Dealer wins");
                playerMoneyAmount -= playerWagerAmount;
                endOfRound = true;

            }

            while ((dealerHand.cardsValue() < 17) && !endOfRound) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());

            }

            System.out.println("Dealer's Hand total is: " + dealerHand.cardsValue() + "\nPlayer's Hand total is: "
                    + playerHand.cardsValue());
            if ((dealerHand.cardsValue() > 21) && !endOfRound) {
                System.out.println("Dealer busts! Over 21! You win.");
                playerMoneyAmount += playerWagerAmount;
                endOfRound = true;

            }

            if ((playerHand.cardsValue() == dealerHand.cardsValue()) && !endOfRound) {
                System.out.println("Push");
                endOfRound = true;

            }

            if ((playerHand.cardsValue() > dealerHand.cardsValue()) && !endOfRound) {
                System.out.println("You win the hand!");
                playerMoneyAmount += playerWagerAmount;
            } else if (!endOfRound) {
                System.out.println("You lose the hand!");
                playerMoneyAmount -= playerWagerAmount;
            }

            playerHand.moveAllToDeck(playingDeck);
            dealerHand.moveAllToDeck(playingDeck);
            System.out.println("End of hand");

        }

        System.out.println("Game Over! Leave the table! You do not have anymore money!");

    }
}