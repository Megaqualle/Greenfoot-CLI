import greenfoot.Actor;
import greenfoot.World;
import java.util.List;

/*****************************
 * Interface of the program, *
 * Greenfoot act method      *
 * and the redraw method.    *
 *****************************/
public class cli extends World {
    public cli() {
        super(64, 36, 25);  // Create a new world with 64x36 cells with a cell size of 25x25 pixels.
        setBackground("images/background.jpg"); // Set the background to black

        switch (Util.getOS()) { // Check the OS
            case LINUX:
                break;
            case GENERIC:
                lib.println("This program only supports Linux systems.");   // Print Warning
                break;
        }
        lib.cursorX = lib.prefix.size();    // Set the cursor start point
        // Set pwdA to be equal to pwd
        for (int i = 0; i < lib.pwd.size(); i++) {
            lib.pwdA[i] = lib.pwd.get(i);
        }
        // Redraw the interface
        redraw();
    }

    public void act() {
        lib.input();    // Get input
        redraw();   // Redraw the interface
    }

    private void redraw() {   // Redraw the interface
        // Remove all objects
        List<Actor> objects = getObjects(null);
        removeObjects(objects);

        // Write the map onto the interface, (0|0) is top left corner
        for (int i = 0; i < lib.cliMap.length; i++) {
            int c = lib.cliMap[0].length - 2;
            for (int j = 0; j < lib.cliMap[0].length; j++) {
                Font font = new Font(lib.cliMap[i][j]);
                addObject(font, i, c);
                c--;
            }
        }

        // Write the buffer onto the interface
        for (int i = 0; i < lib.buffer.length; i++) {
            Font font = new Font(lib.buffer[i]);
            addObject(font, i, lib.cliMap[0].length - 1);
        }

        // Write the position in the file system onto the interface
        for (int i = 0; i < lib.pwdA.length; i++) {
            Font font = new Font(lib.pwdA[i]);
            addObject(font, i, lib.cliMap[0].length - 2);
        }

        // Write the cursor
        Cursor cursor = new Cursor(lib.buffer[lib.cursorX]);
        addObject(cursor, lib.cursorX, lib.cliMap[0].length - 1);

        // Write the prefix onto the screen
        for (int i = 0; i < lib.prefix.size(); i++) {
            Font font = new Font(lib.prefix.get(i));
            addObject(font, i, lib.cliMap[0].length - 1);
        }
    }
}