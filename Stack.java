public class Stack {
    static String[] stack = new String[0xff+1];
    static int stackPointer = 0xff;

    public static void push(String parameter) {
        stack[stackPointer] = parameter;
        System.out.print("push: " + stackPointer + "\n");
        if (stackPointer == 0x00) {
            stackPointer = 0xff;
        }
        else {
            stackPointer--;
        }
    }

    public static void pop() {
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
        lib.print(stack[stackPointer]);
    }
}
