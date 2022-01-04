import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Cargo {
    public static boolean cargo(List<String> parameter) {
        System.out.print(parameter);
        boolean success = false;
        lib.print("Cargo " + parameter);
        Runtime r = Runtime.getRuntime();
        String[] commands = { "bash", "-c", "cd", Arrays.toString(lib.pwdA), ";", "cargo", String.valueOf(parameter)};
        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line= b.readLine()) != null) {
                lib.println(line);
            }
            b.close();
            success = true;
        }
        catch (Exception e) {
            lib.println("Failed to execute cargo " + parameter);
            e.printStackTrace();
        }
        return success;
    }
}
