import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cli extends World implements KeyListener
{
    final int[][] cliMap = new int[32][18];
    int cursorX, cursorY;
    static char keyIn = 'e';
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public cli()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(32, 18, 40);
        setBackground("images/background.jpg");
        addKeyListener(this);
        //Greenfoot.start();
        for(int i = 0; i < cliMap.length; i++) {
            for (int j = 0; j < cliMap[0].length; j++) {
                cliMap[i][j] = 127;
            }
        }
        cursorX = 0;
        cursorY = 0;
    }
    //Font font = new Font((char)cliMap[x][y]);

    public void act(){
       for(int i = 0; i < cliMap.length; i++) {
           for (int j = 0; j < cliMap[0].length; j++) {
               Font font = new Font(cliMap[i][j]);
               //type = cliMap[i][j];
               addObject(font, i, j);
               //System.out.print(cliMap[x][y]);
               //System.out.print(cursorX);
               //System.out.print(cursorY);
               //System.out.print(x);
               //System.out.print(y);
           }
       }
   }
   public static int asciiToInt(char in){
       //return Integer.parseInt(String.valueOf(in));
       int ascii = in;
       System.out.print(ascii+"\n");
       return ascii;
   }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        asciiToInt(key);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

