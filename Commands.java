import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands {
    public static void commands(String name, String parameter) {
        switch (name.toLowerCase()) {
            case "brainfuck":
                brainfuck(parameter);
                break;
            case "push":
                push(parameter);
                break;
            case "pop":
                pop();
                break;
            case "echo":
            case "print":
            case "println":
                println(parameter);
                break;
            case "exit":
            case "stop":
                exit();
                break;
            case "clear":
                clear();
                break;
            default:
                break;
        }
    }

    private static void brainfuck(String in) {
        Arrays.fill(lib.buffer, ' ');
        lib.println(BrainfuckInterpreter.brainfuckInterpreter(in));
    }

    private static void push(String in) {
        Stack.push(in);
    }

    private static void pop() {
        Stack.pop();
    }

    private static void println(String in) {
        lib.println(in);
    }

    private static void exit() {
        Greenfoot.stop();
    }
    private static void clear() {
        for (int i = 0; i < lib.cliMap.length; i++) {
            for (int j = 0; j < lib.cliMap[0].length; j++) {
                lib.cliMap[i][j] = ' ';
            }
        }
    }
}
