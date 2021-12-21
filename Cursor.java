import greenfoot.*;

import java.util.Objects;

public class Cursor extends Actor
{
    public Cursor(String type) {
        switch (type) {
            case "no":
                setImage("font/light/127.png");
                break;
            default:
                if (type != null) {
                    setImage("font/light/" + cli.asciiToInt(type) + ".png");
                }
        }
    }
}
