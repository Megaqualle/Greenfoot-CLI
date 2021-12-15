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
    final int[][] cliMap = new int[3][2];
    int cursorX, cursorY;
    static char keyIn = 'e';
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public cli()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(3, 2, 40);
        setBackground("images/background.jpg");
        addKeyListener(this);
        //Greenfoot.start();
        for(int i = 0; i < cliMap.length; i++) {
            for (int j = 0; j < cliMap[0].length; j++) {
                cliMap[i][j] = 50;
            }
        }
        cursorX = 0;
        cursorY = 0;
        //prepare();
    }
    //Font font = new Font((char)cliMap[x][y]);

    public void act(){
        if(Greenfoot.getKey() != null){
            System.out.print(Greenfoot.getKey());
            char[] keyT = Greenfoot.getKey().toCharArray();
            if(keyT[0] != ' ') {
                //addKeyListener(this);
                for (int i = 0; i < cliMap.length; i++) {
                    for (int j = 0; j < cliMap[0].length; j++) {
                        cliMap[i][j] = asciiToInt(keyT[0]);
                        Font font = new Font(cliMap[i][j]);
                        addObject(font, i, j);
                    }
                }
            }
        }
    }

    public static int asciiToInt(char in){
        //return Integer.parseInt(String.valueOf(in));
        int ascii = in;
        return ascii;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        System.out.print(key+"\n");
        //asciiToInt(key);
        for(int i = 0; i < cliMap.length; i++){
            for (int j = 0; j < cliMap[0].length; j++){
                cliMap[i][j] = asciiToInt(key);
                repaint();
                Font font = new Font(cliMap[i][j]);
                addObject(font, i, j);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}

