import greenfoot.Actor;

/***************
 * Font object *
 ***************/
public class Font extends Actor
{
    public Font(char type) {
            setImage("font/dark/" + Util.asciiToInt(type) + ".png");
    }
}