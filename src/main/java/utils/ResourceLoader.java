package utils;

import java.io.IOException;
import java.util.Properties;

public class ResourceLoader {

    public String loadBlockChainProperty(String name) {
        try{
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/blockchain.properties"));
            return prop.getProperty(name);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
