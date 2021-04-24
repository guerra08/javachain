import structures.Block;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Block> blockChain = new ArrayList<>();

        Block genesis = new Block("This is the first", null);
        blockChain.add(genesis);

        Block second = new Block("The second is here", blockChain.get(blockChain.size() - 1).getHash());
        blockChain.add(second);

        System.out.println(blockChain);
    }

}
