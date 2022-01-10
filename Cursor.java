import greenfoot.Actor;

/*****************
 * Cursor object *
 *****************/
public class Cursor extends Actor
{
    public Cursor(char type) {
            setImage("font/light/" + Util.asciiToInt(type) + ".png");
    }
}