import exceptions.EmptyBlockChainException;
import exceptions.UnminedBlockException;
import structures.Block;
import structures.BlockChain;

public class Application {

    public static void main(String[] args) {

        try{
            BlockChain blockChain = new BlockChain();

            blockChain.addBlock("This is the genesis block.", null);
            blockChain.addBlock("This is the second block", blockChain.getLastBlock().getHash());

            Block anotherBlock = new Block("Lorem ipsum", blockChain.getLastBlock().getHash());
            anotherBlock.mineBlock();

            blockChain.addBlock(anotherBlock);

            System.out.println("Is BlockChain valid? " + blockChain.isBlockChainValid());
        }catch (EmptyBlockChainException | UnminedBlockException e){
            e.printStackTrace();
        }
    }

}
