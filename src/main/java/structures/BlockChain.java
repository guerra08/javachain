package structures;

import exceptions.EmptyBlockChainException;
import exceptions.UnminedBlockException;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private final List<Block> blockChain;

    public BlockChain(){
        this.blockChain = new ArrayList<>();
    }

    public Block addBlock(String data, String prevHash) throws EmptyBlockChainException {
        if(this.blockChain.size() == 0 || this.getLastBlock().checkHashAgainstBlockHash(prevHash)){
            Block b = new Block(data, prevHash);
            b.mineBlock();
            this.blockChain.add(b);
            return b;
        }
        return null;
    }

    public Block addBlock(String data) throws EmptyBlockChainException {
        if(this.blockChain.size() == 0){
            Block b = new Block(data, getLastBlock().getHash());
            b.mineBlock();
            this.blockChain.add(b);
            return b;
        }
        return null;
    }

    public Block addBlock(Block b) throws UnminedBlockException, EmptyBlockChainException {
        if(!b.wasBlockMined()) throw new UnminedBlockException();
        if(this.blockChain.size() == 0 || this.getLastBlock().checkHashAgainstBlockHash(b.getPreviousHash())){
            blockChain.add(b);
            return b;
        }
        return null;
    }

    public Block getGenesisBlock() throws EmptyBlockChainException {
        if(blockChain.size() == 0) throw new EmptyBlockChainException();
        return this.blockChain.get(0);
    }

    public Block getLastBlock() throws EmptyBlockChainException {
        if(blockChain.size() == 0) throw new EmptyBlockChainException();
        return this.blockChain.get(this.blockChain.size() - 1);
    }
    
    public boolean isBlockChainValid(){
        if(blockChain.size() == 0) return true;
        for (int i = 1; i < blockChain.size(); i++) {
            Block currentBlock = blockChain.get(i);
            Block previousBlock = blockChain.get(i - 1);
             if(!currentBlock.checkHashAgainstBlockHash(currentBlock.calculateSHA256Hash()))
                return false;
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()))
                return false;
            if(currentBlock.getTimestamp() < previousBlock.getTimestamp())
                return false;
            if(!currentBlock.wasBlockMined())
                return false;
        }
        return true;
    }

    public Block findBlockByHash(String hash){
        return blockChain.stream().filter(block -> block.getHash().equals(hash))
                .findFirst().orElse(null);
    }

}
