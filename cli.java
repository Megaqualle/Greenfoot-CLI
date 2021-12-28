import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cli extends World {
    final char[][] cliMap = new char[64][36];    // Map of displayed characters
    int cursorX;   // Position of cursor
    List<Character> pwd = new ArrayList<>(List.of()); // Position in fs
    List<Character> prefix = new ArrayList<>(List.of());    // Prefix
    char[] pwdA = new char[cliMap.length-1];
    String[] stack = new String[0xff+1];
    int stackPointer = 0xff;
    String keyboardInput;   // keyboard input
    char key;
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
            case MAC:
                pwd.add('/');
                prefix.add('$');
                directorySplitter = '/';
                break;
            case WINDOWS:
                pwd.add('C');
                pwd.add(':');
                pwd.add('\\');
                prefix.add('W');
                prefix.add(':');
                directorySplitter = '\\';
                break;
            case GENERIC:
                System.out.print("This program can only run on *nix or Windows/DOS Operating Systems");
                Greenfoot.stop();
        }
        cursorX = prefix.size();
        for (int i = 0; i < pwd.size(); i++) {
            pwdA[i] = pwd.get(i);
        }
        Arrays.fill(stack, " ");
        redraw();
    }

    char[] buffer = new char[cliMap.length - 1];    // Buffer for input
    //Font font = new Font((char)cliMap[x][y]);

    public void act() {
        input();
    }

    public static int asciiToInt(char in) {    // Convert input string to ascii/UTF-8 value
        return in;
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
        for (int i = 0; i < pwdA.length; i++) {
            Font font = new Font(pwdA[i]);
            addObject(font, i, cliMap[0].length - 2);
        }
        Cursor cursor = new Cursor(buffer[cursorX]);
        addObject(cursor, cursorX, cliMap[0].length - 1);

        for (int i = 0; i < prefix.size(); i++) {
            Font font = new Font(prefix.get(i));
            addObject(font, i, cliMap[0].length - 1);
        }
    }

    public void input() {

        keyboardInput = Greenfoot.getKey();   // Attempt to register a keypress and abort if there were none
        if (keyboardInput != null) {
            switch (keyboardInput) {
                case "up":
                case "down":
                case "control":
                case "tab":
                case "shift":
                case "alt":
                case "alt graph":
                case "windows":
                case "undefined":
                    break;
                case "right":
                    if (cursorX < buffer.length - 1) {
                        cursorX++;
                        redraw();
                    }
                    break;
                case "left":
                    if (cursorX > prefix.size()) {
                        cursorX--;
                        redraw();
                    }
                    break;
                case "enter":   // Move everything in the buffer onto the map, and push everything else up by one
                    newline();
                    commandCheck();
                    Arrays.fill(buffer, ' ');   // Empty the input buffer
                    cursorX = prefix.size();   // Reset cursor position
                    redraw();   // Redraw the screen
                    break;
                case "backspace":
                    if (cursorX > prefix.size()) {
                        cursorX--;
                        buffer[cursorX] = ' ';
                    }
                    redraw();
                    break;
                case "space":
                    buffer[cursorX] = ' ';
                    cursorX++;
                    redraw();
                    break;
                default:
                    key = keyboardInput.charAt(0);
                    if (cursorX < buffer.length - 1) { // Check if the input buffer is full
                        int keyInt = cli.asciiToInt(key);
                        if (key == ' ') {
                            buffer[cursorX] = key;  // Write input into buffer
                            redraw();
                        }
                        else  if (isBetween(keyInt, 97, 122)) {
                             if (Greenfoot.isKeyDown("shift")) {
                                 buffer[cursorX] = (char)(keyInt-32);  // Write input into buffer
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
                            buffer[cursorX] = (char)(keyIntShift);  // Write input into buffer
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

    public void newline() {
        char[][] mapBuffer = new char[cliMap.length][cliMap[0].length + 2];   // Buffer for map, y-axis is increased by one
        for (int i = 0; i < mapBuffer.length; i++) {
            for (int j = 0; j < mapBuffer[0].length; j++) {
                mapBuffer[i][j] = ' ';
            }
        }
        for (int i = 0; i < cliMap.length; i++) {    // Write the map onto the map buffer, moved by two y
            for (int j = 0; j < cliMap[0].length; j++) {
                mapBuffer[i][j + 2] = cliMap[i][j];
            }
        }
            for (int i = prefix.size(); i < buffer.length; i++) {    // Write the input buffer onto the map buffer, third row
                mapBuffer[i - prefix.size()][1] = buffer[i];
            }

            mapBuffer[buffer.length - (prefix.size() + 1)][1] = '\n';  // Place newline character

        /*for (int i = 0; i < buffer.length; i++) {
            mapBuffer[i][0] = ' ';
            if (i < prefix.size()) {
                mapBuffer[i][0] = prefix.get(i);
            }
        }*/

        for (int i = cliMap[0].length; i > cliMap[0].length - prefix.size(); i--) {
            for (int j = prefix.size(); j > buffer.length - prefix.size(); j--) {
                mapBuffer[i][j] = ' ';
            }
        }

        for (int i = 0; i < cliMap.length; i++) {   // Write the map buffer back onto the map
            for (int j = 0; j < cliMap[0].length; j++) {
                cliMap[i][j] = mapBuffer[i][j];
            }
        }
    }

    public void commandCheck() {
        int spaceBefore = spaceBefore();
        int commandLength = commandLength(spaceBefore);
        int spaceAfter = spaceAfter(spaceBefore + commandLength);
        int parameterLength = parameterLength(spaceBefore + commandLength + spaceAfter);
        char[] commandChar = new char[commandLength];
        for (int i = 0; i < commandLength; i++) {
            commandChar[i] = buffer[i + spaceBefore + prefix.size()];
        }
        char[] parameterChar = new char[parameterLength];
        for (int i = 0; i < parameterLength; i++) {
            parameterChar[i] = buffer[i + spaceBefore + commandLength + spaceAfter];
        }
        String command = new String(commandChar);
        String parameter = new String(parameterChar);
        switch (command) {
            case "cd":
            case "chdir":
                Util.panicHandler(this.cd(parameter));
                break;
            case "print":
            case "echo":
                Util.panicHandler(this.print(parameter));
                break;
            case "clear":
                Util.panicHandler(this.clear());
                break;
            case "exit":
                this.exit();
            case "push":
                this.push(parameter);
                break;
            case "pop":
                this.pop();
                break;
            default:
                break;
        }
    }

    public int spaceBefore() {
        int out = 0;
        int i = prefix.size();
        while (buffer[i] == ' ') {
            out++;
            i++;
            if (i >= buffer.length-1) {
                return out;
            }
        }
        return out;
    }

    public int commandLength(int startPoint) {
        int out = 0;
        int i = prefix.size() + startPoint;
        while (buffer[i] != ' ') {
            out++;
            i++;
            if (i >= buffer.length-1) {
                return out;
            }
        }
        return out;
    }

    public int spaceAfter(int startPoint) {
        int out = 0;
        int i = prefix.size() + startPoint;
        while (buffer[i] == ' ') {
            out++;
            i++;
            if (i >= buffer.length-1) {
                return out;
            }
        }
        return out;
    }

    public int parameterLength(int startPoint) {
        int out = 0;
        for (int i = prefix.size() + startPoint; i < buffer.length; i++) {
            out++;
        }
        return out;
    }

    public int cd(String parameter) {
        String in = parameter.replace(" ", "");
        Path targetDir = Paths.get(in);
        boolean exists = Files.isDirectory(targetDir);
        if (exists) {
            pwd.clear();
            for (int i = 1; i < parameter.length(); i++) {
                pwd.add(parameter.charAt(i));
            }
            for (int i = 0; i < pwdA.length; i++) {
                pwdA[i] = ' ';
                if (i < pwd.size()) {
                    pwdA[i] = pwd.get(i);
                }
            }
        }
        else {
            this.print("/" + in + " is not a Directory");
        }
        return 0;
    }

    public int print(String parameter) {
        int i = 0;
        int offset = 0;
        Arrays.fill(buffer, ' ');
        while (i < parameter.length()) {
            buffer[i - offset] = parameter.charAt(i);
            i++;
            if (i == buffer.length-1) {
                newline();
                offset = offset + buffer.length-1;
            }
        }
        newline();
        return 0;
    }

    public int clear() {
        for (int i = 0; i < cliMap.length; i++) {
            for (int j = 0; j < cliMap[0].length; j++) {
                cliMap[i][j] = ' ';
            }
        }
        return 0;
    }

    public void exit() {
        Greenfoot.stop();
    }

    public int push(String parameter) {
        stack[stackPointer] = parameter;
        System.out.print("push: " + stackPointer + "\n");
        if (stackPointer == 0x00) {
            stackPointer = 0xff;
        }
        else {
            stackPointer--;
        }
        return 0;
    }

    public int pop() {
        if (stackPointer == 0xff) {
            stackPointer = 0x00;
        }
        else {
            stackPointer++;
        }
        System.out.print("pop: " + stackPointer + "\n");
        /*for (int i = 0; i < stack[stackPointer].length(); i++) {
            buffer[i+ prefix.size()] = stack[stackPointer].charAt(i);
        }*/
        print(stack[stackPointer]);
        redraw();
        return 0;
    }
}