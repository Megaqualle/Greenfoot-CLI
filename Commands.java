import greenfoot.Greenfoot;
import java.util.Arrays;

/*****************************
 * Class that contains the   *
 * methods for executing     *
 * commands in the interface *
 *****************************/
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
        // Prepare the buffer and then print the output of the brainfuck interpreter
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
        // Empty the map buffer
        for (int i = 0; i < lib.cliMap.length; i++) {
            for (int j = 0; j < lib.cliMap[0].length; j++) {
                lib.cliMap[i][j] = ' ';
            }
        }
    }
}
