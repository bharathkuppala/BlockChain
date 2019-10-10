package com.Abhilash.Blockchain_Project;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Block {
	public int index;
    public String hash;
    public String previousHash;
    private String data;
    private String timeStamp;
    public String sender;
    public String recipient;
	public int difficulty;
	private int nonce;

    // Block Constructor.
    public Block(int difficulty, String data, String sender, String recipient,String previousHash) {
    	String pattern = "yyyy-MM-dd";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	this.difficulty = difficulty;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = simpleDateFormat.format(new Date());
        this.hash = calculateHash();
        this.sender = sender;
        this.recipient = recipient;
        this.nonce = 0;
        this.index = 0;
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(previousHash + timeStamp + Integer.toString(nonce)+ data);
        return calculatedhash;
    }
    
    public void mineBlock(int difficulty) {
    	String target = new String(new char[difficulty]).replace('\0', '0');
    	while(!this.hash.substring(0,difficulty).equals(target)) {
    		this.nonce++;
    		this.hash = this.calculateHash();
    	}
    }
    
    public String testMineBlock(int difficulty) {
    	String target = new String(new char[difficulty]).replace('\0', '0');
    	while(!this.hash.substring(0,difficulty).equals(target)) {
    		this.nonce++;
    		this.hash = this.calculateHash();
    	}
    	System.out.println("Block Mined :"+  this.hash);
    	return this.hash;
    }
    
    public void reMineBlock(int difficulty) {
    	String target = new String(new char[difficulty]).replace('\0', '0');
    	while(!this.hash.substring(0,difficulty).equals(target)) {
    		this.nonce++;
    		this.hash = this.calculateHash();
    	}
    }
}
