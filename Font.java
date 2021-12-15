import greenfoot.*;

public class Font extends Actor {
    public Font(int type){
       // if(type == ' '){
       //     return;
       // }
       // switch(type){
       //     case '`':
       //         setImage("font/dark/space.png");
       //         break;
       //     case '/':
       //         setImage("font/dark/slash.png");
       //         break;
       //     case '.':
       //         setImage("font/dark/dot.png");
       //         break;
       //     case '<':
       //         setImage("font/dark/lessThan.png");
       //         break;
       //     case '>':
       //         setImage("font/dark/moreThan.png");
       //         break;
       //     case ':':
       //         setImage("font/dark/colon.png");
       //         break;
       //     case '"':
       //         setImage("font/dark/doubleQuote.png");
       //     case '|':
       //         setImage("font/dark/pipe.png");
       //         break;
       //     case '?':
       //         setImage("font/dark/questionMark.png");
       //         break;
       //     case '*':
       //         setImage("font/dark/asterisk.png");
       //         break;
       //     default:
       //         setImage("font/dark/"+type+".png");
       //         break;
       // }
       // System.out.print(type+".png\n");
        setImage("font/dark/"+type+".png");
    }
}