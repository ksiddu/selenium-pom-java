package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriter {

    protected static PropertyUtil prop   = new PropertyUtil();

    public static void writeAllureEnvironment() {
        try {
            Properties props = new Properties();
            props.setProperty("Browser", "Chrome");
            props.setProperty("Browser.Version", "126.0");
            props.setProperty("Environment", "QA");
            props.setProperty("BaseURL", prop.get("url"));

            File file = new File("allure-results/environment.properties");
            file.getParentFile().mkdirs();
            try (FileOutputStream out = new FileOutputStream(file)) {
                props.store(out, "Allure environment properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
