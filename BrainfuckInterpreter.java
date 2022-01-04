import greenfoot.Greenfoot;

import java.util.Arrays;

public class BrainfuckInterpreter {
    public static String brainfuckInterpreter(String in) {
        char[] array = new char[30000];
        StringBuilder out = new StringBuilder();
        int dataPointer = 0x0;
        int loopOpening = 0;
        int loopClosing = 0;
        if (Util.brainfuckSyntaxCheck(in)) {
            Arrays.fill(array, (char) 0x0);
            int instructionPointer = 0;
            while (instructionPointer < in.length()) {
                switch (in.charAt(instructionPointer)) {
                    case '>':
                        // Increment data pointer by one
                        //if (dataPointer == array.length - 1) {
                        //    dataPointer = 0x0;
                        //}
                        //else {
                        dataPointer++;
                        //}
                        break;
                    case '<':
                        // Decrement data pointer by one
                        //if (dataPointer == 0) {
                        //    dataPointer = array.length - 1;
                        //}
                        //else {
                        dataPointer--;
                        //}
                        break;
                    case '+':
                        // Increment the byte at the data pointer
                        //if (array[dataPointer] < (byte) +127) {
                        array[dataPointer]++;
                        //}
                        //else {
                        //    array[dataPointer] = (byte) -128;
                        //}
                        break;
                    case '-':
                        // Decrement the byte at the data pointer
                        //if (array[dataPointer] > (byte) -128) {
                        array[dataPointer]--;
                        //}
                        //else {
                        //    array[dataPointer] = (byte) +127;
                        //}
                        break;
                    case '.':
                        // Output the byte at the data pointer
                        //output.add((char)array[instructionPointer]);
                        ////System.out.print(array[dataPointer]);
                        //print(Character.toString(array[dataPointer]));
                        out.append(array[dataPointer]);
                        break;
                    case ',':
                        // Accept one byte of input
                        Greenfoot.getKey();
                        String byteIn = null;
                        while (byteIn == null) {
                            byteIn = Greenfoot.getKey();
                        }
                        array[dataPointer] = byteIn.charAt(0);
                        break;
                    case '[':
                        // If the byte at the data pointer is zero, jump the instruction pointer forward to the command after the matching ']' command
                        if (array[dataPointer] == 0) {
                            instructionPointer = loopClosing;
                        }
                        else {
                            loopOpening = instructionPointer - 1;
                        }
                        break;
                    case ']':
                        // Jump the instruction pointer to the command after the matching '[' command
                        loopClosing = instructionPointer;
                        instructionPointer = loopOpening;
                        break;
                    default:
                        // Not an instruction
                        break;
                }
                instructionPointer++;
            }
        }
        else {
            out = new StringBuilder("Error");
        }
        ////System.out.print(output);
        //return output.toString();
        //System.out.print("\n");
        return out.toString();
    }

    private boolean syntaxCheck(String in) {
        boolean loopOpen = false;
        for (int i = 0; i < in.length(); i++) {
            switch (in.charAt(i)) {
                case '[':
                    if (loopOpen) {
                        return false;
                    }
                    else {
                        loopOpen = true;
                    }
                case ']':
                    if (!loopOpen) {
                        return false;
                    }
                    else {
                        loopOpen = false;
                    }
            }
        }
        return true;
    }
}

