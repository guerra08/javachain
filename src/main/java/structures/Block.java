package structures;

import exceptions.InvalidResourcePropertyException;
import lombok.*;
import utils.ResourceLoader;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Block {

    private String hash;
    private String previousHash;
    private String blockData;
    private long timestamp;
    private int nonce;

    public Block(String data, String prevHash){
        this.blockData = data;
        this.previousHash = prevHash;
        this.timestamp = Instant.now().toEpochMilli();
        this.nonce = ThreadLocalRandom.current().nextInt(0, 99);
        this.hash = calculateSHA256Hash();
    }

    public String calculateSHA256Hash(){
        String blockRepresentation =
                previousHash +
                blockData +
                timestamp +
                nonce;
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] bytes = digest.digest(
                    blockRepresentation.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes){
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkHashAgainstBlockHash(String hash){
        if(this.hash == null) return false;
        return this.hash.equals(hash);
    }

    public void mineBlock(){
        try{
            int nonceStrength = ResourceLoader.getNonceStrength();
            char nonceChar = ResourceLoader.getNonceCharacter();
            String targetSubstr = new String(new char[nonceStrength]).replace('\0', nonceChar);
            while(!this.hash.substring(0, nonceStrength).equals(targetSubstr)){
                this.nonce++;
                this.hash = calculateSHA256Hash();
            }
        } catch (InvalidResourcePropertyException e) {
            e.printStackTrace();
        }
    }

    public boolean wasBlockMined(){
        try{
            int nonceStrength = ResourceLoader.getNonceStrength();
            char nonceChar = ResourceLoader.getNonceCharacter();
            String targetSubstr = new String(new char[nonceStrength]).replace('\0', nonceChar);
            return this.hash.substring(0, nonceStrength).equals(targetSubstr);
        } catch (InvalidResourcePropertyException e) {
            e.printStackTrace();
        }
        return false;
    }

}
