import greenfoot.Greenfoot;

public class Util {
    public enum OS {
        WINDOWS, LINUX, MAC, GENERIC
    }
    private static OS os = null;
    public static void panicHandler(int in) {
        if (in == 255) {
            System.out.print("A program panicked");
            Greenfoot.stop();
        }
    }
    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win") || operSys.contains("dos")) {
                os = OS.WINDOWS;
            }
            else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
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
    public static boolean brainfuckSyntaxCheck(String in) {
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