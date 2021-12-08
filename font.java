import greenfoot.*;

public class font extends Actor {
    public char type;
    public font(char type){
        switch(type){
            case ' ':
                setImage("font/space.png");
                break;
            case '/':
                setImage("font/slash.png");
                break;
            default:
                setImage("font/"+type+".png");
        }
    }
}