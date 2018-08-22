public class Hand
{
    private Node head;

    private class Node
    {
        private Node next;
        private Card card;

        public Node( Node n, Card c )
        {
            next = n;
            card = c;
        }

        public Node()
        {
            next = null;
            card = null;
        }
    }

    public void addCard( Card c )
    {
        head = new Node( head, c );
    }

    public int getValue()
    {
        if( handValue( head ) > 21 && hasAce( head ) )
        {
            return handValue( head ) - 10;
        }
        else return handValue( head );
    }

    public int handValue( Node n )
    {
        if ( n.next == null) return n.card.getValue();
        else return n.card.getValue() + handValue( n.next );
    }

    public boolean hasAce( Node n )
    {
        if( n.next == null && !n.card.getFace().equals("Ace") ) return false;
        else if( n.card.getFace().equals( "Ace" ) ) return true;
        else return hasAce( n.next );
    }
}