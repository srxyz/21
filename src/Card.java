public class Card
{
    private int value;
    private String suit, face, image;

    public Card( String str )
    {
        value = 0;
        suit = str;
        face = null;
    }

    public void setFace( String str ) {
        face = str;
        if (str.equals("Ace"))
        {
            value = 11;
        }        else
            value = 10;

        image = Character.toString( face.charAt(0) ) + suit.charAt(0) + ".png";
    }

    public void setFace( int i )
    {
        face = Integer.toString( i );
        value = i;
        image = Character.toString( face.charAt(0) ) + suit.charAt(0) + ".png";
    }

    public int getValue()
    {
        return value;
    }

    public String getImage()
    {
        return image;
    }

    public String getFace()
    {
        return face;
    }

    public String toString()
    {
        return face + " " + suit + " " + image;
    }
}
