import java.util.ArrayList;
import java.util.List;
import com.google.gson.GsonBuilder;

public class BlockChain {
		public static List<Block> chain;
		public static int difficulty = 2;
		public static ArrayList<Block> blockchain = new ArrayList<Block>();
		public BlockChain() {
			blockchain.add(genesisBlock());
			blockchain.get(0).mineBlock(difficulty);
		}
		
		private Block genesisBlock() {
			//System.out.println("called!");
			Block genesis = new Block(2,"Genesis!","","","null");
			return genesis;
		}
    	
	
    
	    public static void addTransactionToBlockChain(Block block) {
	    	System.out.println("Add a Transaction to the block chain.");
	    	Block newBlock = block;
	    	newBlock.mineBlock(block.difficulty);
	    	newBlock.previousHash = blockchain.get(blockchain.size()-1).hash;
	    	newBlock.index = blockchain.get(blockchain.size()-1).index + 1;
	    	BlockChain.blockchain.add(newBlock);
	    }
	    
	    public static boolean verifyBlockChain() {
	    	System.out.println("Verify BlockChain");
	    	for(int i=1;i<blockchain.size();i++) {
	    		Block currentBlock = blockchain.get(i);
	    		Block previousBlock = blockchain.get(i-1);
	    		String testHash = currentBlock.testMineBlock(currentBlock.difficulty);
	    		if(!currentBlock.hash.equals(testHash)) {
	    			return false;
	    		}
	    		
	    		if(!currentBlock.previousHash.equals(previousBlock.hash)) {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
    
	    public static void viewBlockChain() {
	    	System.out.println("View BlockChain");
	 		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
	 		System.out.println(blockchainJson);
	    }
    
	    public static void corruptBlockChain(int blockNumber, String amt) {
	    	System.out.println("Corrupt BlockChain");
	    	System.out.println(blockchain.size());
	    	if(blockchain.size() > 0) {
		    		Block blockToBeCorrupted = blockchain.get(blockNumber - 1);
				blockToBeCorrupted.data = amt;
		    		blockToBeCorrupted.mineBlock(blockToBeCorrupted.difficulty);
		    		blockchain.set(blockNumber - 1, blockToBeCorrupted);
	    	} else {
	    		System.out.println("Array does'nt contain enough elements to perform this operation.");
	    	}
	    	
	    }
	    
	    public static void hideCorruptionByRecompute() {
	    	System.out.println("Hide Corruption By Recomputing hashes.");
	    	for(int i=0;i<blockchain.size()-1;i++) {
	    		Block currentBlock = blockchain.get(i);
	    		if(currentBlock.previousHash == null) {
	    			continue;
	    		}
	    		Block nextBlock = blockchain.get(i+1);
	    		if(!currentBlock.hash.equals(nextBlock.previousHash)) {
	    			//currentBlock.reMineBlock(currentBlock.difficulty);
	    			//nextBlock.previousHash = currentBlock.hash;
	    			blockchain.get(i).reMineBlock(blockchain.get(i).difficulty);
	    			blockchain.get(i+1).previousHash = blockchain.get(i).hash;
	    			System.out.println(currentBlock.hash);
	    			System.out.println(nextBlock.previousHash);
	    		}
	    		System.out.println("Repaired blockchain.");
	    	}
	    }

}
