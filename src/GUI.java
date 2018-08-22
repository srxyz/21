import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
    private HandPanel players = new HandPanel();
    private static Deck deck = new Deck();
    private JButton hitButton, standButton, playAgainButton;
    private JPanel buttonPanel;

    public GUI()
    {
        deck.shuffle();

        setLayout( new BorderLayout() );
        setSize( 500, 400 );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        players.hit( "Dealer" );
        players.hit( "Dealer" );
        players.hit( "Player1" );
        players.hit( "Player1" );
        players.hit( "Player2" );
        players.hit( "Player2" );
        add( players, BorderLayout.CENTER );

        hitButton = new JButton("Hit" );
        standButton = new JButton( "Stand" );
        playAgainButton = new JButton( "Play Again?" );
        hitButton.addActionListener( this );
        standButton.addActionListener( this );
        playAgainButton.addActionListener( this );
        buttonPanel = new JPanel();
        buttonPanel.add( hitButton );
        buttonPanel.add( standButton );
        add( buttonPanel, BorderLayout.SOUTH );

        setVisible( true );
    }

    public static Deck getDeck()
    {
        return deck;
    }

    public void actionPerformed( ActionEvent e )
    {
        if( e.getSource() == hitButton )
            try
            {
                players.hit("Player1");
            }
            catch( IllegalStateException lost )
            {
                buttonPanel.remove( hitButton );
                buttonPanel.updateUI();
            }
        else if( e.getSource() == standButton )
        {
            buttonPanel.removeAll();
            buttonPanel.updateUI();
            buttonPanel.add( playAgainButton );
            players.takeTurn( "Player2" );
            players.takeTurn( "Dealer" );
            players.determineWinner();
        }
        else
        {
            deck = new Deck();
            setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
            dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );
            new GUI();
        }
    }
}
