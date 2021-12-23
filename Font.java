import greenfoot.*;

import java.util.Objects;

public class Font extends Actor
{
    public Font(char type) {
        if (type == ' ') {
            setImage("font/dark/127.png");
        }
        else {
            setImage("font/dark/" + cli.asciiToInt(type) + ".png");
        }
    }
}