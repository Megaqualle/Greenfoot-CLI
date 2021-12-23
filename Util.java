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
}