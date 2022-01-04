import greenfoot.Greenfoot;

public class Util {
    public enum OS {
        WINDOWS, LINUX, MAC, GENERIC
    }
    private static OS os = null;

    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win") || operSys.contains("dos")) {
                os = OS.WINDOWS;
            }
            else if (operSys.contains("linux") ) {
                os = OS.LINUX;
            }
            else if (operSys.contains("mac") || operSys.contains("darwin")) {
                os = OS.MAC;
            }
            else {
                os = OS.GENERIC;
            }
        }
        return os;
    }

    public static int asciiToInt(char in) {return in; }    // Convert input string to ascii/UTF-8 value

    public static boolean brainfuckSyntaxCheck(String in) {
        boolean loopOpen = false;
        for (int i = 0; i < in.length(); i++) {
            switch (in.charAt(i)) {
                case '[':
                    if (loopOpen) {
                        return true;
                    }
                    else {
                        loopOpen = true;
                    }
                case ']':
                    if (!loopOpen) {
                        return true;
                    }
                    else {
                        loopOpen = false;
                    }
            }
        }
        return true;
    }

    public static boolean isBetween ( int x, int lower, int upper){
        return lower <= x && x <= upper;
    }
    public static int spaceBefore(int startPoint, char[] target) {
        int out = 0;
        int i = startPoint;
        while (target[i] == ' ') {
            out++;
            i++;
            if (i >= lib.buffer.length-1) {
                return out;
            }
        }
        return out;
    }

    public static int commandLength(int startPoint, char[] target) {
        int out = 0;
        int i = startPoint;
        while (target[i] != ' ') {
            out++;
            i++;
            if (i >= target.length-1) {
                return out;
            }
        }
        return out;
    }

    public static int spaceAfter(int startPoint, char[] target) {
        int out = 0;
        int i = startPoint;
        while (target[i] == ' ') {
            out++;
            i++;
            if (i >= target.length-1) {
                return out;
            }
        }
        return out;
    }

    public static int parameterLength(int startPoint, char[] target) {
        int out = 0;
        for (int i = startPoint; i < target.length; i++) {
            out++;
        }
        return out;
    }
}