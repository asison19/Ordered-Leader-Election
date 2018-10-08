package ordered_Leader_Election;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class OfficialThread extends Thread{
	int rank;
	String name;
	String opinion; //who this thread thinks is the leader
	RankList rankList;
	Phaser phaser;
	RankThread rankThread;
	ReentrantLock lock;
	
	public OfficialThread(RankList rankList, Phaser phaser, RankThread rankThread, ReentrantLock lock) {
		Random rand = new Random();
		rank = rand.nextInt();
		name = this.getName();
		opinion = this.getName();
		this.rankList = rankList;
		this.phaser = phaser;
		phaser.register();
		this.rankThread = rankThread;
		this.lock = lock;
	}
	
	public synchronized void run() {
		System.out.println(name + " of rank: " + this.rank + " thinks " + opinion + " is the leader.");
		lock.lock();
		rankList.addOfficial(name, rank);
		lock.unlock();
		phaser.arriveAndAwaitAdvance();
		
		rankThread.interrupt();
		
	}
	
}
