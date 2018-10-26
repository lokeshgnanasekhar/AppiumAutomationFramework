package modules;

import java.io.*;
import java.util.Properties;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class ProjectConfigReader {

    Properties properties;
    InputStream input;

    public ProjectConfigReader(){
        properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("ProjectConfig.properties").getFile());
            input = new FileInputStream(file);
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getBrowserName(){
        return properties.getProperty("BROWSER_NAME");
    }

    public String getURL(){
        return properties.getProperty("APP_URL");
    }

    public String getPlatformName(){
        return properties.getProperty("PLATFORM_NAME");
    }

    public String getPlatformVersion(){
        return properties.getProperty("PLATFORM_VERSION");
    }

    public String getDeviceName(){
        return properties.getProperty("DEVICE_NAME");
    }

    public String getAutomationName(){
        return properties.getProperty("AUTOMATION_NAME");
    }

    public String getAppPath(){
        return properties.getProperty("APP_PATH");
    }

    public String getRemoteURL(){
        return properties.getProperty("REMOTE_URL");
    }

    public String getAppType(){
        return properties.getProperty("APP_TYPE");
    }




}
