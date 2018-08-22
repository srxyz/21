import java.util.Random;

public class Deck
{
    private Random rand = new Random();
    private Card deck[] = new Card[52];
    private int top;

    public Deck()
    {
        top = 0;

        for( int i = 0; i < 52 ; i++ )
        {
            switch(i/13)
            {
                case 0: deck[i] = new Card("Clubs"); break;
                case 1: deck[i] = new Card( "Diamonds"); break;
                case 2: deck[i] = new Card("Hearts"); break;
                case 3: deck[i] = new Card("Spades"); break;
            }

            if( i%13 + 1 == 1 ) deck[i].setFace("Ace");
            else if( i%13 + 1 < 11 ) deck[i].setFace( i%13 + 1 );
            else
            {
                switch( i%13 + 1 )
                {
                    case 11: deck[i].setFace("Jack"); break;
                    case 12: deck[i].setFace("King"); break;
                    case 13: deck[i].setFace("Queen"); break;
                }
            }
        }
    }

    //---------------------------------
    // Fisher-Yates shuffle algorithm
    //---------------------------------
    public void shuffle()
    {
        for( int i = 51 ; i > 0 ; i-- )
        {
            int j = rand.nextInt(i);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    public Card getNext()
    {
        if( top == 52 )
        {
            shuffle();
            top = 0;
        }
        Card c = deck[top];
        top++;
        return c;
    }
}
