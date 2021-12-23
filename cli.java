import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cli extends World {
    final String[][] cliMap = new String[64][36];    // Map of displayed characters
    int cursorX;   // Position of cursor
    List<String> pwd = new ArrayList<>(Arrays.asList()); // position in fs
    String key;   // keyboard input
    boolean init = false;   // Redraw once upon starting the program

    /**
     * Constructor for objects of class MyWorld.
     */
    public cli() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(64, 36, 25);
        setBackground("images/background.jpg");
        //Greenfoot.start();
        for (int i = 0; i < cliMap.length; i++) {
            for (int j = 0; j < cliMap[0].length; j++) {
                cliMap[i][j] = "no";    // Make sure no null-pointer are in the array.
            }
        }
        Arrays.fill(buffer, "no");  // Make sure no null-pointers are in the buffer

        switch (Util.getOS()) {
            case LINUX:
            case MAC:
                pwd.add("/");
                pwd.add("$");
                pwd.add(" ");
                break;
            case WINDOWS:
                pwd.add("C");
                pwd.add(":");
                pwd.add("\\");
                pwd.add(" ");
                break;
            case GENERIC:
                System.out.print("This program can only run on *nix or Windows/DOS Operating Systems");
                Greenfoot.stop();
        }
        cursorX = pwd.size();
        redraw();
    }

    String[] buffer = new String[cliMap.length - 1];    // Buffer for input
    //Font font = new Font((char)cliMap[x][y]);

    public void main() {
    }

    public void act() {
        if (!init) {
        }
        input();
    }

    public static int asciiToInt(String in) {    // Convert input string to ascii/UTF-8 value
        return in.charAt(0);
    }

    public void redraw() {   // Redraw the screen
        List<Actor> objects = getObjects(null);
        removeObjects(objects);  // Remove all objects
        for (int i = 0; i < cliMap.length; i++) {   // Write the map onto the screen, (0|0) is top left corner
            int c = cliMap[0].length - 2;
            for (int j = 0; j < cliMap[0].length; j++) {
                Font font = new Font(cliMap[i][j]);
                addObject(font, i, c);
                c--;
            }
        }
        for (int i = 0; i < buffer.length; i++) {   // Write the buffer onto the screen
            Font font = new Font(buffer[i]);
            addObject(font, i, cliMap[0].length - 1);
        }
        Cursor cursor = new Cursor(buffer[cursorX]);
        addObject(cursor, cursorX, cliMap[0].length - 1);

        for (int i = 0; i < pwd.size(); i++) {
            Font font = new Font(pwd.get(i));
            addObject(font, i, cliMap[0].length - 1);
        }
    }

    public void input() {

        key = Greenfoot.getKey();   // Attempt to register a keypress and abort if there were none
        if (key != null) {
            switch (key) {
                case "up":                  // Arrow keys to move cursor
                    break;
                case "down":
                    break;
                case "right":
                    if (cursorX < buffer.length - 1) {
                        cursorX++;
                        redraw();
                    }
                    break;
                case "left":
                    if (cursorX > pwd.size()) {
                        cursorX--;
                        redraw();
                    }
                    break;
                case "enter":   // Move everything in the buffer onto the map, and push everything else up by one
                    String[][] mapBuffer = new String[cliMap.length][cliMap[0].length + 1];   // Buffer for map, y-axis is increased by one
                    for (int i = 0; i < cliMap.length; i++) {    // Write the map onto the map buffer, moved by one y
                        for (int j = 0; j < cliMap[0].length; j++) {
                            mapBuffer[i][j + 1] = cliMap[i][j];
                        }
                    }

                    for (int i = pwd.size(); i < buffer.length; i++) {    // Write the input buffer onto the map buffer, first row
                        mapBuffer[i - pwd.size()][0] = buffer[i];
                    }

                    mapBuffer[buffer.length-(pwd.size()+1)][0] = "\n";  // Place newline character

                    for (int i = cliMap[0].length; i > cliMap[0].length - pwd.size(); i--) {
                        for (int j = pwd.size(); j > buffer.length - pwd.size(); j--) {
                            mapBuffer[i][j] = "no";
                        }
                    }

                    Arrays.fill(buffer, "no");  // Empty input buffer

                    for (int i = 0; i < cliMap.length; i++) {   // Write the map buffer back onto the map
                        for (int j = 0; j < cliMap[0].length; j++) {
                            cliMap[i][j] = mapBuffer[i][j];
                        }
                    }
                    cursorX = pwd.size();
                    redraw();   // Redraw the screen
                    break;
                case "control":
                    break;
                case "tab":
                    break;
                case "shift":
                    break;
                case "alt":
                case "alt graph":
                    break;
                case "backspace":
                    if (cursorX > pwd.size()) {
                        cursorX--;
                        buffer[cursorX] = "no";
                    }
                    redraw();
                    break;
                case "windows":
                    break;
                case "space":
                    buffer[cursorX] = "no";
                    cursorX++;
                    redraw();
                    break;
                default:
                    if (cursorX < buffer.length - 1) { // Check if the input buffer is full
                        int keyInt = cli.asciiToInt(key);
                        if ("no".equals(key)) {
                            buffer[cursorX] = key;  // Write input into buffer
                            redraw();
                        }
                        else  if (isBetween(keyInt, 97, 122)) {
                             if (Greenfoot.isKeyDown("shift")) {
                                 buffer[cursorX] = Character.toString(keyInt-32);  // Write input into buffer
                             } else {
                                 buffer[cursorX] = key;  // Write input into buffer
                             }
                        }
                        else {
                            boolean shift = Greenfoot.isKeyDown("shift");   // Print correct characters while shift-key is pressed, US-ANSI layout version
                            int keyIntShift = keyInt;
                            switch (keyInt) {
                                case 59:
                                    if (shift) {
                                        keyIntShift = keyInt - 1;
                                    }
                                    break;
                                case 39:
                                    if (shift) {
                                        keyIntShift = keyInt - 5;
                                    }
                                    break;
                                case 48:
                                    if (shift) {
                                        keyIntShift = keyInt - 7;
                                    }
                                    break;
                                case 50:
                                    if (shift) {
                                        keyIntShift = keyInt + 14;
                                    }
                                    break;
                                case 56:
                                    if (shift) {
                                        keyIntShift = keyInt - 14;
                                    }
                                    break;
                                case 49:
                                case 51:
                                case 52:
                                case 53:
                                    if (shift) {
                                        keyIntShift = keyInt - 16;
                                    }
                                    break;
                                case 44:
                                case 46:
                                case 47:
                                    if (shift) {
                                        keyIntShift = keyInt + 16;
                                    }
                                    break;
                                case 55:
                                case 57:
                                    if (shift) {
                                        keyIntShift = keyInt - 17;
                                    }
                                    break;
                                case 61:
                                    if (shift) {
                                        keyIntShift = keyInt - 18;
                                    }
                                    break;
                                case 96:
                                    if (shift) {
                                        keyIntShift = keyInt + 30;
                                    }
                                    break;
                                case 92:
                                case 91:
                                case 93:
                                    if (shift) {
                                        keyIntShift = keyInt + 32;
                                    }
                                    break;
                                case 54:
                                    if (shift) {
                                        keyIntShift = keyInt + 40;
                                    }
                                    break;
                                case 45:
                                    if (shift) {
                                        keyIntShift = keyInt + 50;
                                    }
                                    break;
                            }
                            buffer[cursorX] = Character.toString(keyIntShift);  // Write input into buffer
                        }
                      }
                    cursorX++;  // Move cursor to the right by one
                    redraw();
            }
        }
    }
    static boolean isBetween ( int x, int lower, int upper){
        return lower <= x && x <= upper;
    }
}