import org.junit.jupiter.api.Test;
import structures.Block;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTests {

    @Test
    public void testFirstBlockCreation(){
        assertDoesNotThrow(() -> (new Block("Sample data", null))).mineBlock();
    }

    @Test
    void testValidBlockHashCheck(){
        Block block = new Block("This is just a drill", null);
        block.mineBlock();

        String blockHash = block.getHash();

        assertTrue(block.checkHashAgainstBlockHash(blockHash));
    }

    @Test
    void testInvalidBlockHashCheck(){
        Block block = new Block("Sneaky peaky", null);
        block.mineBlock();

        String wrongHash = block.getHash() + "123abc43124";

        assertFalse(block.checkHashAgainstBlockHash(wrongHash));
    }

    @Test
    void testMinedBlock(){
        Block block = new Block("Sneaky peaky", null);
        block.mineBlock();

        assertTrue(block.wasBlockMined());
    }

    @Test
    void testUnminedBlock(){
        Block block = new Block("Sneaky peaky", null);

        assertFalse(block.wasBlockMined());
    }

}
