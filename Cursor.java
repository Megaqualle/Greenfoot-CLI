import greenfoot.*;

import java.util.Objects;

public class Cursor extends Actor
{
    public Cursor(String type) {
        if ("no".equals(type)) {
            setImage("font/light/127.png");
        }
        else if (type != null) {
                setImage("font/light/" + cli.asciiToInt(type) + ".png");
        }
        else {
            setImage("font/light/127.png");
        }
    }
}