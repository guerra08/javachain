import exceptions.EmptyBlockChainException;
import structures.BlockChain;

public class Application {

    public static void main(String[] args) {

        try{
            BlockChain blockChain = new BlockChain();

            blockChain.addBlock("This is the genesis block.", null);
            blockChain.addBlock("This is the second block", blockChain.getLastBlock().getHash());

            System.out.println(blockChain.isBlockChainValid());
        }catch (EmptyBlockChainException e){
            System.err.println("BlockChain cannot be empty");
        }
    }

}
