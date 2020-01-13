package unittestoutputter;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileUtil {
    public static void savePlainText(String data, String path) {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(path);
            pw.print(data);
            pw.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } finally {
            if (pw != null) {
                pw.close();
                pw = null;
            }

        }
    }
}
