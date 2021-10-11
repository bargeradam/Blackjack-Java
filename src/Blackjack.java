import javax.swing.*;
import java.awt.*;

public class Blackjack extends JFrame {

private int wallet;
private int betSize;
private Deck cardDeck = new Deck();
private JPanel playerCardArea;
private JPanel computerCardArea;
private Card hiddenCard;
private JPanel root;
private JPanel bot;
private JPanel top;
private Hand playerHand;
private Hand computerHand;
private JLabel handValue;
private JLabel wagerAmount;
private JLabel walletLabel;



    public Blackjack() {

        // Setting variables
        Color backgroundColor = new Color(41,144,41);
        wallet = 100;
        betSize = 0;
        wagerAmount = new JLabel("Wager: $" + betSize);
        walletLabel = new JLabel("Cash: $" + wallet);

        // Creating the card back to hide the computers seconds card.
        hiddenCard = new Card("null", 0);
        hiddenCard.setIcon(new ImageIcon("CardBack.jpg"));

        // Creating each players hand.
        playerHand = new Hand();
        computerHand = new Hand();

        root = new JPanel();
        root.setLayout(new BorderLayout());
        top = new JPanel(new BorderLayout());
        root.add(top, BorderLayout.NORTH);

        bot = new JPanel(new BorderLayout());
        root.add(bot, BorderLayout.SOUTH);

        playerCardArea = new JPanel();
        handValue = new JLabel("Hand Value: ");

        JPanel walletPanel = new JPanel();
        walletPanel.add(walletLabel);


        JPanel wagerAmountPanel = new JPanel();
        wagerAmountPanel.setLayout(new BoxLayout(wagerAmountPanel, BoxLayout.Y_AXIS));

        // Adding a hand value JLabel and a wager amount JLabel to the top right of the screen.
        wagerAmountPanel.add(wagerAmount);
        wagerAmountPanel.add(handValue);


        top.add(walletPanel, BorderLayout.WEST);
        computerCardArea = new JPanel();
        top.add(computerCardArea, BorderLayout.CENTER);
        top.add(wagerAmountPanel, BorderLayout.EAST);

        JPanel leftActionPanel = new JPanel();
        leftActionPanel.setLayout(new BoxLayout(leftActionPanel, BoxLayout.Y_AXIS));

        // Creating increase bet button.  It increments in terms of $10 with each click and subtracts this from your
        // wallet and adds it to the bet size.  If you click it and your cash balance is 0, it shows a message saying
        // not enough cash
        JButton increaseWager = new JButton("Increase Bet");
        increaseWager.addActionListener(e -> {
            if (wallet >= 10) {
                wallet -= 10;
                betSize += 10;
                refresh();
            } else {
                JOptionPane.showMessageDialog(root,"Not enough cash!");
            }

        });

        //Simple button that exits the program.
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        //Creating an "all-in" button, to deposit all of your cash into the wager.
        JButton allIn = new JButton("All In!");
        allIn.addActionListener(e -> {
            betSize += wallet;
            wallet = 0;
            refresh();
        });

        leftActionPanel.add(allIn);
        leftActionPanel.add(increaseWager);
        leftActionPanel.add(quitButton);

        // Creating the hit action button.  You have to have some money in the wager pot to hit or it will shows an error
        // message.  If your hand goes over 21, it will show a message saying busted! and you will lose the amount wagered
        // If you hit and go over 21 AND you run out of money, it shows a message saying you lost and exits.
        JButton hitButton = new JButton("Hit");
        hitButton.addActionListener(e -> {
            if(betSize == 0 ) {
                JOptionPane.showMessageDialog(root, "Must add wager to play!");
            } else {
                playerHand.add(cardDeck.deal());
                playerCardArea.add(playerHand.get(playerHand.size() - 1));
                handValue.setText("" + playerHand.getValue());
                if (playerHand.getValue() > 21) {
                    JOptionPane.showMessageDialog(root, "Busted!");
                    betSize = 0;
                    refresh();
                    dealNewCards();
                    if(wallet<=0) {
                        JOptionPane.showMessageDialog(root, "You broke the bank! Try again next time!");
                        System.exit(0);
                    }
                }
                refresh();

            }

        });

        JPanel rightActionPanel = new JPanel();
        rightActionPanel.setLayout(new BoxLayout(rightActionPanel, BoxLayout.Y_AXIS));


        // Creating the Stand button.  The button should end the user's turn and initiate the computer's turn. If your
        // hand has a higher value it should return a dialog window saying you won, if your hand value is lower
        // it should return a window saying you lose, and if you lose and run out of money it exits the program. If you
        // lose you lose the amount wagered, and if you win you win double the amount.
        JButton stand = new JButton("Stand");
        stand.addActionListener(e -> {
            int playerTotal = playerHand.getValue();
            computerTurn();
            int computerTotal = computerHand.getValue();

            if(playerTotal > computerTotal || computerTotal > 21) {

                wallet += betSize*2;
                betSize=0;
                refresh();
                JOptionPane.showMessageDialog(null, "You win!");
            }  else {
                betSize=0;
                refresh();
                JOptionPane.showMessageDialog(null, "You lose!");
                if(wallet<=0) {
                    JOptionPane.showMessageDialog(root, "You broke the bank! Try again next time!");
                    System.exit(0);
                }
            }
            dealNewCards();
            refresh();
        });

        // Creating a button to fold your hand if you don't want to place a bet.
        JButton fold = new JButton("Fold");
        fold.addActionListener(e -> {
            wallet -= betSize;
            betSize = 0;
            refresh();
            dealNewCards();
        });
        rightActionPanel.add(hitButton);
        rightActionPanel.add(stand);
        rightActionPanel.add(fold);

        bot.add(leftActionPanel, BorderLayout.WEST);
        bot.add(playerCardArea, BorderLayout.CENTER);
        bot.add(rightActionPanel, BorderLayout.EAST);





        dealNewCards();
        root.setBackground(backgroundColor);
        leftActionPanel.setBackground(backgroundColor);
        rightActionPanel.setBackground(backgroundColor);
        playerCardArea.setBackground(backgroundColor);
        computerCardArea.setBackground(backgroundColor);
        wagerAmountPanel.setBackground(backgroundColor);
        walletPanel.setBackground(backgroundColor);
        this.setVisible(true);
        this.setSize(800,400);
        this.setTitle("Blackjack");
        this.setContentPane(root);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    // Method refresh to just refresh all the objects in the panel.  I was having a lot of trouble having things update
    // consistently so I just made this method to have a shortcut to just refresh everything on the screen.
    private void refresh(){
        walletLabel.setText("Cash: $" + wallet);
        wagerAmount.setText("Wager: $"+ betSize);
        handValue.setText("Your Hand Value: "+playerHand.getValue());
        this.repaint();
        top.repaint();
        bot.repaint();
        root.repaint();

    }

    // The extremely simple computer AI.  It removes the card back from it's hand and adds an actual card in.
    // Then the computer keeps drawing cards until it busts, gets 21 or just beats the players hand.  I think that is how
    // the dealer works in blackjack.
    private void computerTurn() {
        computerCardArea.remove(computerHand.get(computerHand.size()-1));
        computerHand.remove(1);
        computerHand.add(cardDeck.deal());
        computerCardArea.add(computerHand.get(1));

        while(computerHand.getValue() < 21 && computerHand.getValue() < playerHand.getValue()) {
            computerHand.add(cardDeck.deal());
            computerCardArea.add(computerHand.get(computerHand.size()-1));
            refresh();
        }
        refresh();
    }

    // Clearing all the cards and issuing new ones. Basically resetting the turn.
    public void dealNewCards(){
        playerCardArea.removeAll();
        computerCardArea.removeAll();
        playerHand.clear();
        computerHand.clear();
        playerHand.add(cardDeck.deal());
        playerHand.add(cardDeck.deal());
        computerHand.add(cardDeck.deal());
        computerHand.add(hiddenCard);
        playerCardArea.add(playerHand.get(0));
        playerCardArea.add(playerHand.get(1));
        computerCardArea.add(computerHand.get(0));
        computerCardArea.add(computerHand.get(1));
        refresh();


    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Blackjack());
    }

}
