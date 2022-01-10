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
        if (keyboardInput != null) {    // Test if there was an input
                switch (keyboardInput) {
                    // Modifier keys that are not being used
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
                    // Move the cursor to the right
                    case "right":
                        if (cursorX < buffer.length - bufferOverflowProtection) {
                            cursorX++;
                        }
                        break;
                    // Move the cursor to the left
                    case "left":
                        if (cursorX > prefix.size()) {
                            cursorX--;
                        }
                        break;
                    // Move everything in the buffer onto the map, and push everything else up by one
                    case "enter":
                        // buffer[cursorX] = '\n';
                        newline();
                        commandCheck(buffer, prefix.size());
                        Arrays.fill(buffer, (char)0x0);   // Empty the input buffer
                        cursorX = prefix.size();   // Reset cursor position
                        break;
                    // Delete the character under the cursor then move it to the left
                    case "backspace":
                        if (cursorX > prefix.size()) {
                            cursorX--;
                            buffer[cursorX] = 0x0;
                        }
                        break;
                    // Set the character under the cursor to a space then move it to the right
                    case "space":
                        if (cursorX < buffer.length - bufferOverflowProtection) {
                            buffer[cursorX] = ' ';
                            cursorX++;
                        }
                        break;
                    // Printable characters
                    default:
                        char key = keyboardInput.charAt(0); // Write String to char
                        if (cursorX < buffer.length - bufferOverflowProtection) { // Check if the input buffer is full
                            int keyInt = Util.asciiToInt(key);  // Write char to int
                            if (Util.isBetween(keyInt, 97, 122)) {
                                if (Greenfoot.isKeyDown("shift")) {
                                    buffer[cursorX] = (char) (keyInt - 32/* Difference between a and A */);  // Write input into buffer
                                } else {
                                    buffer[cursorX] = key;  // Write input into buffer
                                }
                            } else {
                                boolean shift = Greenfoot.isKeyDown("shift");
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

    // Print command
    private static void _print(String parameter) {
        int i = 0;
        int offset = 0;
        Arrays.fill(buffer, (char) 0x0);   // Empty the input buffer
        try {
            // Write the parameter into the buffer
            while (i < parameter.length()) {
                buffer[i - offset + pwd.size()] = parameter.charAt(i);
                i++;
                if (i == buffer.length - 1) {
                    newline();  // Print the parameter
                    offset = offset + buffer.length - 1;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
    // Public print command with a newline
    public static void println(String in) {
        _print(in);
        newline();
    }

    // Splits the input into command and parameter, then execute Commands.commands with these variables
    public static void commandCheck(char[] in, int startPoint) {
        int spaceBefore = Util.spaceBefore(startPoint, in);
        int commandLength = Util.commandLength(startPoint + spaceBefore, in);
        int spaceAfter = Util.spaceAfter(startPoint + spaceBefore + commandLength, in);
        int parameterLength = Util.parameterLength(
                startPoint + spaceBefore + commandLength + spaceAfter, in);
        char[] command = new char[commandLength];   // char array for the command
        // Write command into the command array
        for (int i = 0; i < commandLength; i++) {
            command[i] = in[i + spaceBefore + startPoint];
        }
        char[] parameter = new char[parameterLength];   // char array for the parameter
        // Write parameter into parameter array
        for (int i = 0; i < parameterLength; i++) {
            parameter[i] = in[i + spaceBefore + commandLength + spaceAfter + 2];
        }
        String commandString = new String(command);
        String parameterString = new String(parameter);
        Commands.commands(commandString, parameterString);
    }

    //
    public static void newline() {
        // Buffer for map, y-axis is increased by two y
        char[][] mapBuffer = new char[cliMap.length][cliMap[0].length + 2];
        for (int i = 0; i < mapBuffer.length; i++) {
            for (int j = 0; j < mapBuffer[0].length; j++) {
                mapBuffer[i][j] = 0x0;
            }
        }
        // Write the map onto the map buffer, moved by two y
        for (int i = 0; i < cliMap.length; i++) {
            for (int j = 0; j < cliMap[0].length; j++) {
                mapBuffer[i][j + 2] = cliMap[i][j];
            }
        }
        // Write the input buffer onto the map buffer, third row
        for (int i = prefix.size(); i < buffer.length; i++) {
            mapBuffer[i - prefix.size()][1] = buffer[i];
        }

        // Write 0x0 into the empty space
        for (int i = cliMap[0].length; i > cliMap[0].length - prefix.size(); i--) {
            for (int j = prefix.size(); j > buffer.length - prefix.size(); j--) {
                mapBuffer[i][j] = 0x0;
            }
        }

        mapBuffer[buffer.length - (prefix.size() + 1)][1] = '\n';  // Place newline character

        // Write the map buffer back onto the map
        for (int i = 0; i < cliMap.length; i++) {
            for (int j = 0; j < cliMap[0].length; j++) {
                cliMap[i][j] = mapBuffer[i][j];
            }
        }
    }
}