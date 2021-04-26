package utils;

import exceptions.InvalidResourcePropertyException;

import java.io.IOException;
import java.util.Properties;

public class ResourceLoader {

    private static Properties properties = null;

    public static int getNonceStrength() throws InvalidResourcePropertyException {
        loadBlockChainProperties();
        String strength = properties.getProperty("nonce.strength");
        if(strength == null) throw new InvalidResourcePropertyException();
        return Integer.parseInt(strength);
    }

    public static char getNonceCharacter() throws InvalidResourcePropertyException {
        loadBlockChainProperties();
        String targetChar = properties.getProperty("nonce.target.character");
        if(targetChar == null) throw new InvalidResourcePropertyException();
        return targetChar.charAt(0);
    }

    public static String getJGroupsClusterName() throws InvalidResourcePropertyException {
        loadBlockChainProperties();
        String clusterName = properties.getProperty("jgroups.cluster.name");
        if(clusterName == null) throw new InvalidResourcePropertyException();
        return clusterName;
    }

    public static String getProperty(String name){
        loadBlockChainProperties();
        return properties.getProperty(name);
    }

    private static void loadBlockChainProperties() {
        try{
            if(properties == null){
                properties = new Properties();
                properties.load(ResourceLoader.class.getResourceAsStream("/blockchain.properties"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
