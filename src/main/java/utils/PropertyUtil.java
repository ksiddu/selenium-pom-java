package utils;

import constants.AppConstants;
import exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class PropertyUtil {
    Properties properties;
    FileInputStream fis;
    String filePath;
    private static final Logger logger = LogManager.getLogger(PropertyUtil.class);

    public PropertyUtil() {

        // mvn clean install -Denv="qa"
        // mvn clean install

        String envName = System.getProperty("env");
        logger.info("running test suite on env--->" + envName);
        if (envName == null) {
            logger.info("env name is not given, hence running it on QA environment....");
            System.out.println("env name is not given, hence running it on QA environment....");
            try {
                filePath = AppConstants.CONFIG_DEV_FILE_PATH;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            try {
                switch (envName.trim().toLowerCase()) {
                    case "qa":
                        filePath = AppConstants.CONFIG_QA_FILE_PATH;
                        break;
                    case "stage":
                        filePath = AppConstants.CONFIG_STAGE_FILE_PATH;
                        break;
                    case "dev":
                        filePath = AppConstants.CONFIG_DEV_FILE_PATH;
                        break;
                    case "uat":
                        filePath = AppConstants.CONFIG_UAT_FILE_PATH;
                        break;
                    case "prod":
                        filePath = AppConstants.CONFIG_PROD_FILE_PATH;
                        break;

                    default:
                        logger.info("please pass the right env name.." + envName);
                        throw new FrameworkException("===WRONGENVPASSED===", new Exception());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            properties = new Properties();
            fis = new FileInputStream(filePath);
            if (fis != null) {
                properties.load(fis);
            } else {
                System.out.println("property file '" + filePath + "' not found in the classpath");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally

        {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PropertyUtil(String fileName) {
        filePath = fileName;
        try {
            properties = new Properties();
            fis = new FileInputStream(filePath);
            if (fis != null) {
                properties.load(fis);
            } else {
                System.out.println("property file '" + fileName + "' not found in the classpath");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally

        {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    // Write a property and save it
    public void set(String key, String value) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.setProperty(key, value);
            properties.store(fos, "Updated on " + new java.util.Date());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to config.properties: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String propFilePath = "src/test/resources/config.properties";
        //PropertyUtil obj = new PropertyUtil(propFilePath);
        PropertyUtil obj = new PropertyUtil();

        System.out.println("BROWSER: " + obj.get("browser"));
        System.out.println("URL: " + obj.get("url"));
        System.out.println("USERNAME: " + obj.get("username"));
        System.out.println("PASSWORD: " + obj.get("password"));

        // write
        obj.set("browser", "chrome");
        System.out.println("BROWSER AFTER UPDATE: " + obj.get("browser"));

    }

}