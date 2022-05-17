import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class SteamFinder {
    // This exists for Windows platforms, and will read the Registry to find where Skyrim is installed to.

    private static final String REGQUERY_UTIL = "reg query ";
    private static final String REGSTR_TOKEN = "REG_SZ";
    private static String steamFolderCMD = REGQUERY_UTIL +
            "\"HKCU\\Software\\Valve\\Steam\\";

    public static String getSteamInstallPathWindows() {
        try {
            Process process = Runtime.getRuntime().exec(steamFolderCMD);
            StreamReader reader = new StreamReader(process.getInputStream());

            reader.start();
            process.waitFor();
            reader.join();

            String result = reader.getResult();
            int p = result.indexOf(REGSTR_TOKEN);

            String[] steamInfo = result.split("\\r\\n");
            String steamInstallDir = null;

            for (int j = 0; j < steamInfo.length; j++) {
                steamInfo[j] = steamInfo[j].strip();
            }

            for (int i = 0; i < steamInfo.length; i++) {
                if (steamInfo[i].startsWith("SteamPath")) {
                    steamInstallDir = steamInfo[i];
                    i = steamInfo.length; // Break out of the check, we found what we need.
                }
            }

            steamInfo = steamInstallDir.split("    ");
            steamInstallDir = steamInfo[2];
            if (p == -1)
                return null;

            return steamInstallDir;
        } catch (Exception e) {
            return null;
        }
    }

    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw;

        StreamReader(InputStream is) {
            this.is = is;
            sw = new StringWriter();
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            } catch (IOException e) {
                ;
            }
        }

        String getResult() {
            return sw.toString();
        }
    }
}
