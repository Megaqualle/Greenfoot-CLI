import greenfoot.*;

import java.util.Objects;

public class Font extends Actor
{
    public Font(String type) {
        switch (type) {
            case "no":
                setImage("font/dark/127.png");
                break;
            default:
                setImage("font/dark/" + cli.asciiToInt(type) + ".png");
        }
    }
}