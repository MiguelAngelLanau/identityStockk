package stockkms.identity_stockk.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configs {

    private static final String CONFIG_FILE = "identity_stockk/config.properties";
    
    public static final String SERVICES_DIRECTORY_BASE_URI = "SERVICES_DIRECTORY_BASE_URI";
    public static final String IDENTITY_SERVICE_NAME = "IDENTITY_SERVICE_NAME";
    public static final String IDENTITY_DIRECTORY_NAME = "IDENTITY_DIRECTORY_NAME";
    public static final String IDENTITY_FILE_PATH = "IDENTITY_FILE_PATH";

    public static Configs instance = null;
    public Properties prop;
    
    public Configs(){
        prop = new Properties();
    }

    public static synchronized Configs getInstance(){
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }

    public String getProp(String key) {
        String value = "";
        try{
            prop.load(new FileInputStream(CONFIG_FILE));
            value = prop.getProperty(key);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return value;
    }
}
