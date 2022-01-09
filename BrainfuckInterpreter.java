import greenfoot.Greenfoot;
import java.util.Arrays;

/****************************
 * A brainfuck interpreter  *
 * that returns the output  *
 * as a String.             *
 *                          *
 * Uses a 7 bit long array  *
 * and no Wraparound.       *
 ****************************/

public class BrainfuckInterpreter {
    public static String brainfuckInterpreter(String in) {
        char[] array = new char[0b1111111]; // The Brainfuck array
        StringBuilder out = new StringBuilder();    // Output of the program
        int dataPointer = 0x0;  // The data pointer
        int loopOpening = 0;    // Instruction pointer value of the loop opening
        int loopClosing = 0;    // Instruction pointer value of the loop ending
        try {
            Arrays.fill(array, (char) 0x0); // Initialize the array filled with 0x0
            int instructionPointer = 0; // Instruction pointer
            while (instructionPointer < in.length()) {
                switch (in.charAt(instructionPointer)) {
                    case '>':
                        dataPointer++;  // Increment data pointer by one
                        break;
                    case '<':
                        dataPointer--;  // Decrement data pointer by one
                        break;
                    case '+':
                        array[dataPointer]++;   // Increment the byte at the data pointer
                        break;
                    case '-':
                        array[dataPointer]--;   // Decrement the byte at the data pointer
                        break;
                    case '.':
                        out.append(array[dataPointer]); // Output the byte at the data pointer
                        break;
                    case ',':
                        // Accept one byte of input
                        Greenfoot.getKey(); // Empty previous input
                        String byteIn = null;   // Set input to null
                        // Read input and continue if it is not null
                        while (byteIn == null) {
                            byteIn = Greenfoot.getKey();
                        }
                        array[dataPointer] = byteIn.charAt(0);  // Write the input
                        break;
                    case '[':
                        // If the byte at the data pointer is zero, jump the instruction pointer forward to the command after the matching ']' command
                        if (array[dataPointer] == 0) {
                            instructionPointer = loopClosing;   // Jump to the end of the loop
                        }
                        else {
                            loopOpening = instructionPointer - 1;   // Set the loop opening
                        }
                        break;
                    case ']':
                        // Jump the instruction pointer to the command after the matching '[' command
                        loopClosing = instructionPointer;   // Set the loop closing
                        instructionPointer = loopOpening;   // Jump to the loop opening
                        break;
                    default:
                        // Not an instruction
                        break;
                }
                instructionPointer++;   // Advances the instruction pointer
            }
        }
        catch (Exception e){
            out = new StringBuilder((CharSequence) e);   // Print error
        }
        return out.toString();  // return the output
    }
}

