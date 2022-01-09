import greenfoot.Actor;

/***************
 * Font object *
 ***************/
public class Font extends Actor
{
    public Font(char type) {
        if (type == ' ') {
            setImage("font/dark/127.png");
        }
        else {
            setImage("font/dark/" + Util.asciiToInt(type) + ".png");
        }
    }
}