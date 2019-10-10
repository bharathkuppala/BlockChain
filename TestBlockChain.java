package com.Abhilash.Blockchain_Project;

import java.util.Scanner;

public class TestBlockChain {
	public static void main(String[] args) {
		BlockChain bc = new BlockChain();
		while(true) {
        	System.out.println("1.Add a Transaction to the block chain.");
        	System.out.println("2.Verify BlockChain");
        	System.out.println("3.View BlockChain");
        	System.out.println("4.Corrupt BlockChain");
        	System.out.println("5.Hide Corruption By Recomputing hashes.");
        	System.out.println("6.Exit");
        	System.out.println("Enter any one option");
        	
        	Scanner sc = new Scanner(System.in);
        	int opt = sc.nextInt();
        	switch(opt) {
        	case 1: 
        		Scanner sc1 = new Scanner(System.in);
        		System.out.println("Enter difficulty");
        		int difficulty = sc1.nextInt();
        		System.out.println("Enter transaction amount");
        		int amount = sc1.nextInt();
        		System.out.println("Enter sender information");
        		String sender = sc1.next();
        		System.out.println("Enter recipient information");
        		String recipient =  sc1.next();
        		//System.out.println(Integer.toString(difficulty)+" " + Integer.toString(amount) + " " + sender + " " + recipient);
        		//Block blk = BlockChain.blockchain.get(BlockChain.blockchain.size()-1);
        		//System.out.println(blk.hash);
        		//block.previousHash = blk.hash;
        		BlockChain.addTransactionToBlockChain(new Block(difficulty,Integer.toString(amount),sender,recipient,""));
        		break;
        	case 2:
        		System.out.println("Verifying...");
        		Boolean chainStatus = BlockChain.verifyBlockChain();
        		System.out.println("Chain verification:"+ Boolean.toString(chainStatus));
        		break;
        	case 3:
        		BlockChain.viewBlockChain();
        		break;
        	case 4:
        		System.out.println(BlockChain.blockchain.size());
        		Scanner sc2 = new Scanner(System.in);
        		System.out.println("Enter block to corrupt");
        		int blockNumber = sc2.nextInt();
        		if(BlockChain.blockchain.size() < blockNumber) {
        			System.out.println("Elements in array does'nt match with specified blockNumber:" + Integer.toString(blockNumber));
        			continue;
        		}
        		System.out.println("Enter new data for block :"+Integer.toString(blockNumber));
        		int amt = sc2.nextInt();
        		System.out.println("Block "+  Integer.toString(blockNumber) + " now contains :"+ " " + Integer.toString(amt));
        		BlockChain.corruptBlockChain(blockNumber,Integer.toString(amt));
        		System.out.println("Block" +Integer.toString(blockNumber) + "is corrupted!" );
        		break;
        	case 5:
        		BlockChain.hideCorruptionByRecompute();
        		break;
        	case 6:
        		System.out.println("Exited with status code 0.");
        		System.exit(0);
        		sc.close();
        		break;
        	default :
        		System.out.println("Invalid input!!");
        		break;
        	}
        }

    }
}
