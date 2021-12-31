import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrainfuckInterpreter {
    char[] array = new char[30000];
    List<Character> output = new ArrayList<>(List.of());
    int dataPointer = 0x0;
    int loopOpening;
    public String interpreter(String in) {
        if (syntaxCheck(in)) {
            Arrays.fill(array, (char) 0x0);
            for (int instructionPointer = 0x0; instructionPointer < in.length(); instructionPointer++) {
                switch (in.charAt(instructionPointer)) {
                    case '>':
                        // Increment data pointer by one
                        if (dataPointer < array.length) {
                            dataPointer++;
                        }
                        else {
                            dataPointer = 0x0;
                        }
                        break;
                    case '<':
                        // Decrement data pointer by one
                        if (dataPointer > 0) {
                            dataPointer--;
                        }
                        else {
                            dataPointer = array.length;
                        }
                        break;
                    case '+':
                        // Increment the byte at the data pointer
                        if (dataPointer < +127) {
                            array[dataPointer]++;
                        }
                        else {
                            dataPointer = -128;
                        }
                        break;
                    case '-':
                        // Decrement the byte at the data pointer
                        if (dataPointer > -128) {
                            array[dataPointer]--;
                        }
                        else {
                            dataPointer = +127;
                        }
                        break;
                    case '.':
                        // Output the byte at the data pointer
                        output.add(array[instructionPointer]);
                        break;
                    case ',':
                        // Accept one byte of input
                        Greenfoot.getKey();
                        String byteIn = null;
                        while (byteIn == null) {
                            byteIn = Greenfoot.getKey();
                        }
                        break;
                    case '[':
                        // If the byte at the data pointer is zero, jump the instruction pointer forward to the command after the matching ']' command
                        loopOpening = instructionPointer;
                        break;
                    case ']':
                        // Jump the instruction pointer to the command after the matching '[' command
                        instructionPointer = loopOpening;
                        break;
                    default:
                        // Not an instruction
                        break;
                }
            }
        }
        else {
            for (int i = 0; i < "Syntax Error".length(); i++) {
                output.add("Syntax Error".charAt(i));
            }
        }
        return output.toString();
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

