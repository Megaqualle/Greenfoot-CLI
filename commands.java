public class commands {
    public static boolean commands(String command) {
        switch (command) {
            case "print":
            case "cd":
                return true;
            default:
                return false;
        }
    }
}
