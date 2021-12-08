import greenfoot.*;

public class cursor extends Actor {
    public cursor(char type){

        switch(type){
            case ' ':
                setImage("font/light/space.png");
                break;
            case '/':
                setImage("font/light/slash.png");
                break;
            case '.':
                setImage("font/light/dot.png");
                break;
            case '<':
                setImage("font/light/lessThan.png");
                break;
            case '>':
                setImage("font/light/moreThan.png");
                break;
            case ':':
                setImage("font/light/colon.png");
                break;
            case '"':
                setImage("font/light/doubleQuote.png");
            case '|':
                setImage("font/light/pipe.png");
                break;
            case '?':
                setImage("font/light/questionMark.png");
                break;
            case '*':
                setImage("font/light/asterisk.png");
                break;
            default:
                setImage("font/light/"+type+".png");
        }
    }
}
