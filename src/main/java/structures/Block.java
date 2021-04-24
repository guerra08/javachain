package structures;

import lombok.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Getter
@Setter
public class Block {

    private String hash;
    private String previousHash;
    private String blockData;
    private Long timestamp;
    private Integer nonce;

    public Block(String data, String prevHash){
        this.blockData = data;
        this.previousHash = prevHash;
        this.timestamp = Instant.now().toEpochMilli();
        this.nonce = 1;
        this.hash = calculateSHA256Hash();
    }

    public String calculateSHA256Hash(){
        String blockRepresentation = hash +
                previousHash +
                blockData +
                timestamp.toString() +
                nonce.toString();
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
        return hash.equals(this.hash);
    }

}
