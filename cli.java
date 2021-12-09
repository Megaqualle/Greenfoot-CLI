import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cli extends World
{
    final int[][] cliMap = new int[31][17];
    int x, y, cursorX, cursorY;
    char type = 'a';
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public cli()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(32, 18, 40);
        setBackground("images/background.jpg");
        //Greenfoot.start();
        for(x = 0; x < 31; x++) {
            for (y = 0; y < 17; y++) {
                cliMap[x][y] = 77;
            }
        }
        cursorX = 0;
        cursorY = 0;
        x = 1;
        y = 2;
    }
    Font font = new Font((char)cliMap[x][y]);

    public void act(){
       for(x = 0; x < 31; x++) {
           for (y = 0; y < 17; y++) {
               type = (char)cliMap[x][y];
               addObject(font, x, y);
               //System.out.print(cliMap[x][y]);
               //System.out.print(cursorX);
               //System.out.print(cursorY);
               //System.out.print(x);
               //System.out.print(y);
           }
       }
       x = 0; y = 0;
   }
}

