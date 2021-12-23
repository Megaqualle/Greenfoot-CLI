import greenfoot.*;

import java.util.Objects;

public class Cursor extends Actor
{
    public Cursor(char type) {
        if (type == ' ') {
            setImage("font/light/127.png");
        }
        else {
                setImage("font/light/" + cli.asciiToInt(type) + ".png");
        }
    }
}