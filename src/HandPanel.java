import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HandPanel extends JPanel
{
    private JPanel
            dealerHandPanel = new JPanel(),
            player1HandPanel = new JPanel(),
            player2HandPanel = new JPanel(),

            dealerPanel = new JPanel(),
            player1Panel = new JPanel(),
            player2Panel = new JPanel();

    private JLabel
            dealerValue = new JLabel(),
            player1Value = new JLabel(),
            player2Value = new JLabel();

    private Hand
            dealerHand = new Hand(),
            player1Hand = new Hand(),
            player2Hand = new Hand();


    public HandPanel()
    {
        JPanel dealerValuePanel = new JPanel();
        JPanel dealerInnerPanel = new JPanel();
        dealerPanel.setBorder( BorderFactory.createTitledBorder( "Dealer" ) );
        dealerInnerPanel.setLayout( new GridLayout( 2, 1 ) );
        dealerHandPanel.setLayout( new GridLayout() );
        dealerValuePanel.add( dealerValue );
        dealerInnerPanel.add( dealerHandPanel );
        dealerInnerPanel.add( dealerValuePanel );
        dealerPanel.add( dealerInnerPanel );

        JPanel player1InnerPanel = new JPanel();
        JPanel player1ValuePanel = new JPanel();
        player1Panel.setBorder( BorderFactory.createTitledBorder( "Player 1" ) );
        player1InnerPanel.setLayout( new GridLayout( 2, 1 ) );
        player1HandPanel.setLayout( new GridLayout() );
        player1ValuePanel.add( player1Value );
        player1InnerPanel.add( player1HandPanel );
        player1InnerPanel.add( player1ValuePanel );
        player1Panel.add( player1InnerPanel );

        JPanel player2InnerPanel = new JPanel();
        JPanel player2ValuePanel = new JPanel();
        player2Panel.setBorder( BorderFactory.createTitledBorder( "Player 2" ) );
        player2InnerPanel.setLayout( new GridLayout( 2, 1 ) );
        player2HandPanel.setLayout( new GridLayout() );
        player2ValuePanel.add( player2Value );
        player2InnerPanel.add( player2HandPanel );
        player2InnerPanel.add( player2ValuePanel );
        player2Panel.add( player2InnerPanel );

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout( new GridLayout( 1, 2 ) );
        playerPanel.add( player1Panel );
        playerPanel.add( player2Panel );


        setLayout( new GridLayout( 2, 1 ) );
        add( dealerPanel );
        add( playerPanel );
    }

    public void takeTurn( String s )
    {
        if( s.equals( "Player2" ) )
            while( player2Hand.getValue() < 20 ) hit( "Player2" );
        else
            while( dealerHand.getValue() < 17 ) hit( "Dealer" );
    }

    public void hit( String s )
    {
        Card nextCard = GUI.getDeck().getNext();
        ImageIcon cardImage;

        try
        {
            BufferedImage img = ImageIO.read( new File( "PNG/" + nextCard.getImage() ) );
            cardImage = new ImageIcon( img.getScaledInstance( 69, 106, Image.SCALE_SMOOTH) );
        }
        catch( IOException e )
        {
            cardImage = null;
            e.printStackTrace();
            System.exit(0);
        }

        switch( s )
        {
            case "Dealer":
                dealerHandPanel.add( new JLabel( cardImage ) );
                dealerHand.addCard( nextCard );
                dealerValue.setText( "Value: " + dealerHand.getValue() );
                if( dealerHand.getValue() > 21 )
                {
                    dealerPanel.setBorder( BorderFactory.createTitledBorder( "Dealer Busted" ) );
                    dealerValue.setForeground( Color.RED );
                }
                break;
            case "Player1":
                player1HandPanel.add( new JLabel( cardImage ) );
                player1Hand.addCard( nextCard );
                player1Value.setText( "Value: " + player1Hand.getValue() );
                if( player1Hand.getValue() > 21 )
                {
                    player1Panel.setBorder( BorderFactory.createTitledBorder( "You Busted" ) );
                    player1Value.setForeground( Color.RED );
                    throw( new IllegalStateException() );
                }
                break;
            case "Player2":
                player2HandPanel.add( new JLabel( cardImage ) );
                player2Hand.addCard( nextCard );
                player2Value.setText( "Value: " + player2Hand.getValue() );
                if( player2Hand.getValue() > 21 )
                {
                    player2Panel.setBorder( BorderFactory.createTitledBorder( "Player 2 Busted" ) );
                    player2Value.setForeground( Color.RED );
                }
                break;
        }
    }

    public void determineWinner()
    {
        if( dealerHand.getValue() > 21 )
        if( player1Hand.getValue() < 22 && player1Hand.getValue() >= player2Hand.getValue() )
        {
            player1Panel.setBorder( BorderFactory.createTitledBorder( "Player 1 Wins!" ) );
        }
    }
}
