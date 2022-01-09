import greenfoot.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*******************************
 * Main library of the program *
 *******************************/

public class lib {
    final static char[][] cliMap = new char[64][36];    // Map of displayed characters
    static char[] buffer = new char[cliMap.length - 1];    // Buffer for input
    static int cursorX;   // Position of cursor
    static List<Character> pwd = new ArrayList<>(List.of('~', '/')); // Position in fs
    static List<Character> prefix = new ArrayList<>(List.of('$', ' '));    // Prefix
    static char[] pwdA = new char[cliMap.length-1]; // pwd as array
    // Number by which to reduce array calls to avoid a BufferOverflowException
    static int bufferOverflowProtection = 1;

    public static void input() {
        String keyboardInput = Greenfoot.getKey();   // Attempt to register a keypress and abort if there were none
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
                        if (cursorX < buffer.length - bufferOverflowProtection) {
                            cursorX++;
                        }
                        break;
                    case "left":
                        if (cursorX > prefix.size()) {
                            cursorX--;
                        }
                        break;
                    case "enter":   // Move everything in the buffer onto the map, and push everything else up by one
                        newline();
                        commandCheck();
                        Arrays.fill(buffer, ' ');   // Empty the input buffer
                        cursorX = prefix.size();   // Reset cursor position
                        break;
                    case "backspace":
                        if (cursorX > prefix.size()) {
                            cursorX--;
                            buffer[cursorX] = ' ';
                        }
                        break;
                    case "space":
                        if (cursorX < buffer.length - bufferOverflowProtection) {
                            buffer[cursorX] = ' ';
                            cursorX++;
                        }
                        break;
                    default:
                        char key = keyboardInput.charAt(0);
                        if (cursorX < buffer.length - bufferOverflowProtection) { // Check if the input buffer is full
                            int keyInt = Util.asciiToInt(key);
                            if (key == ' ') {
                                buffer[cursorX] = key;  // Write input into buffer
                            } else if (Util.isBetween(keyInt, 97, 122)) {
                                if (Greenfoot.isKeyDown("shift")) {
                                    buffer[cursorX] = (char) (keyInt - 32);  // Write input into buffer
                                } else {
                                    buffer[cursorX] = key;  // Write input into buffer
                                }
                            } else {
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
                                buffer[cursorX] = (char) (keyIntShift);  // Write input into buffer
                            }
                        }
                        if (cursorX < buffer.length - bufferOverflowProtection) {
                            cursorX++;  // Move cursor to the right by one
                        }
                }
        }
    }

    private static void _print(String parameter) {
        //System.out.println("(" + parameter + ")");
        int i = 0;
        int offset = 0;
        Arrays.fill(buffer, ' ');
        try {
            while (i < parameter.length()) {
                buffer[i - offset + pwd.size()] = parameter.charAt(i);
                i++;
                if (i == buffer.length - 1) {
                    newline();
                    offset = offset + buffer.length - 1;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
    public static void print(String in) {
        _print(in);
    }
    public static void println(String in) {
        _print(in);
        newline();
    }

    public static void commandCheck() {
        int spaceBefore = Util.spaceBefore(prefix.size(), buffer);
        int commandLength = Util.commandLength(prefix.size() + spaceBefore, buffer);
        int spaceAfter = Util.spaceAfter(prefix.size() + spaceBefore + commandLength, buffer);
        int parameterLength = Util.parameterLength(
                prefix.size() + spaceBefore + commandLength + spaceAfter, buffer);
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
        Commands.commands(command, parameter);
    }
    public static void newline() {
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
}
