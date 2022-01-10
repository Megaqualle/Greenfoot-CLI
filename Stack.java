import java.util.Arrays;

/********************************
 * The stack of the program and *
 * its methods                  *
 ********************************/
public class Stack {
    static String[] stack = new String[0xff+1]; // The stack array
    static int stackPointer = 0xff; // The stack pointer

    public static void init() {
        Arrays.fill(stack, Character.toString(0x0));
    }
    // Push command
    public static void push(String parameter) {
        stack[stackPointer] = parameter;    // Writes the input into the stack
        // Stack wraparound
        if (stackPointer == 0x00) {
            stackPointer = 0xff;
        }
        else {
            stackPointer--; // Decrements the stack pointer
        }
    }

    // Pop command
    public static void pop() {
        // Stack wraparound
        if (stackPointer == 0xff) {
            stackPointer = 0x00;
        }
        else {
            stackPointer++;  // Increments the stack pointer
        }
        System.out.print("pop\n" + "Pointer: " + stackPointer + "\nCell: " + stack[stackPointer]);
        try {
            lib.println(stack[stackPointer]); // Prints the output
        }
        catch (Exception e) {
            lib.println(String.valueOf(e));
        }
    }
}