import exceptions.EmptyBlockChainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.Block;
import structures.BlockChain;

import static org.junit.jupiter.api.Assertions.*;

public class BlockChainTests {

    private BlockChain blockChain;

    @BeforeEach
    void setUp(){
        this.blockChain = new BlockChain();
    }

    @Test
    void testSuccessfulCreationOfGenesisBlock(){
        assertDoesNotThrow(() -> blockChain.addBlock("Genesis", null));
    }

    @Test
    void testSuccessfulCreationOfSecondBlock() throws EmptyBlockChainException {
        Block gen = blockChain.addBlock("Genesis block", null);

        assertNotNull(blockChain.addBlock("Second block", gen.getHash()));
    }

    @Test
    void testUnsuccessfulCreationOfSecondBlock() throws EmptyBlockChainException {
        Block gen = blockChain.addBlock("Genesis block", null);

        assertNull(blockChain.addBlock("Second block", gen.getHash() + "abc"));
    }

    @Test
    void testGetGenesisBlockOfPopulatedChain() throws EmptyBlockChainException {
        blockChain.addBlock("Genesis block", null);

        assertDoesNotThrow(blockChain::getGenesisBlock);
    }

    @Test
    void testGetGenesisBlockOfEmptyChain(){
        assertThrows(EmptyBlockChainException.class, blockChain::getGenesisBlock);
    }

}
