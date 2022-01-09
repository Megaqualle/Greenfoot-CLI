import greenfoot.Greenfoot;

/**************************
 * Contains Utilities for *
 * the program            *
 **************************/

public class Util {
    // Get OS to print a warning if it is run on a not supported System
    public enum OS {
        LINUX, GENERIC
    }
    static OS os = null;
    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("linux") ) {
                os = OS.LINUX;
            }
            else {
                os = OS.GENERIC;
            }
        }
        return os;
    }

    // Convert input String to US-ASCII value
    public static int asciiToInt(char in) {return in; }    // Convert input string to ascii/UTF-8 value

    // Because the java stdlib can't do this
    public static boolean isBetween ( int x, int lower, int upper){
        return lower <= x && x <= upper;
    }

    // methods for separating Strings into command and parameter
    public static int spaceBefore(int startPoint, char[] target) {
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