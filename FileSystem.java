import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileSystem {
    public static void cd(String parameter) {
        String in = parameter.replace(" ", "");
        try {
            Path targetDir = Paths.get(in);
            boolean exists = Files.isDirectory(targetDir);
            if (exists) {
                lib.pwd.clear();
                for (int i = 1; i < parameter.length(); i++) {
                    lib.pwd.add(parameter.charAt(i));
                }
                for (int i = 0; i < lib.pwdA.length; i++) {
                    lib.pwdA[i] = ' ';
                    if (i < lib.pwd.size()) {
                        lib.pwdA[i] = lib.pwd.get(i);
                    }
                }
            }
        }
        catch (InvalidPathException e){
            lib.println(in + " is not a Directory");
            lib.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
