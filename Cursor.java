import greenfoot.Actor;

/*****************
 * Cursor object *
 *****************/
public class Cursor extends Actor
{
    public Cursor(char type) {
        if (type == ' ') {
            setImage("font/light/127.png");
        }
        else {
            setImage("font/light/" + Util.asciiToInt(type) + ".png");
        }
    }
}