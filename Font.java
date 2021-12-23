import greenfoot.*;

import java.util.Objects;

public class Font extends Actor
{
    public Font(String type) {
        if ("no".equals(type)) {
            setImage("font/dark/127.png");
        }
        else if (type != null) {
            setImage("font/dark/" + cli.asciiToInt(type) + ".png");
        }
        else {
            setImage("font/dark/127.png");
        }
    }
}