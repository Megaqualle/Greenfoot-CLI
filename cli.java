import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

import java.util.List;


/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cli extends World {
    char directorySplitter;
    /**
     * Constructor for objects of class MyWorld.
     */
    public cli() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(64, 36, 25);
        setBackground("images/background.jpg");

        switch (Util.getOS()) {
            case LINUX:
                lib.pwd.add('/');
                lib.prefix.add('$');
                directorySplitter = '/';
                break;
            case MAC:
            case WINDOWS:
            case GENERIC:
                System.out.print("This program can only run on *nix or Windows/DOS Operating Systems");
                Greenfoot.stop();
        }
        lib.cursorX = lib.prefix.size();
        for (int i = 0; i < lib.pwd.size(); i++) {
            lib.pwdA[i] = lib.pwd.get(i);
        }
        redraw();
    }

    public void act() {
        lib.input();
        redraw();
    }

    public void redraw() {   // Redraw the screen
        List<Actor> objects = getObjects(null);
        removeObjects(objects);  // Remove all objects
        for (int i = 0; i < lib.cliMap.length; i++) {   // Write the map onto the screen, (0|0) is top left corner
            int c = lib.cliMap[0].length - 2;
            for (int j = 0; j < lib.cliMap[0].length; j++) {
                Font font = new Font(lib.cliMap[i][j]);
                addObject(font, i, c);
                c--;
            }
        }
        for (int i = 0; i < lib.buffer.length; i++) {   // Write the buffer onto the screen
            Font font = new Font(lib.buffer[i]);
            addObject(font, i, lib.cliMap[0].length - 1);
        }
        for (int i = 0; i < lib.pwdA.length; i++) {
            Font font = new Font(lib.pwdA[i]);
            addObject(font, i, lib.cliMap[0].length - 2);
        }
        Cursor cursor = new Cursor(lib.buffer[lib.cursorX]);
        addObject(cursor, lib.cursorX, lib.cliMap[0].length - 1);

        for (int i = 0; i < lib.prefix.size(); i++) {
            Font font = new Font(lib.prefix.get(i));
            addObject(font, i, lib.cliMap[0].length - 1);
        }
    }
}