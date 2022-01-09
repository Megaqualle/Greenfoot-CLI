/********************************
 * The stack of the program and *
 * its methods                  *
 ********************************/
public class Stack {
    static String[] stack = new String[0xff+1]; // The stack array
    static int stackPointer = 0xff; // The stack pointer

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
        lib.print(stack[stackPointer]); // Prints the output
    }
}
