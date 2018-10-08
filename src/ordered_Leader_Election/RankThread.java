/*
 * @author Andrew Sison
 * 
 */

package ordered_Leader_Election;
import java.util.Scanner;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.ReentrantLock;

public class RankThread extends Thread{
	static int n;
	ReentrantLock lock = new ReentrantLock();
	Scanner scn = new Scanner(System.in);
	RankList rankList;
	OfficialThread[] officials;
	Phaser phaser = new Phaser();
	int phaseNumber = 0;
	
	public static void main(String[] args) {
		(new RankThread()).start();		
	}
	
	public synchronized void run() {

		System.out.print("Enter the number of elected official threads: ");
		n = scn.nextInt();
		
		electThread();
		
	}
	
	private void electThread() {
		rankList = new RankList(n);
		officials = new OfficialThread[n];
		
		//create and start official threads
		for(int i = 0; i < n; i++) {
			officials[i] = new OfficialThread(rankList, phaser, this, lock);
			officials[i].start();
		}
		
		phaser.awaitAdvance(phaseNumber);
		phaseNumber++;
		System.out.println("The highest ranked thread is: " + rankList.findHighestRankedThread());
		
	}
}

/* Example output
Enter the number of elected official threads: 12
Thread-1 of rank: -1741379350 thinks Thread-1 is the leader.
Thread-2 of rank: 2122000830 thinks Thread-2 is the leader.
Thread-11 of rank: -373602385 thinks Thread-11 is the leader.
Thread-9 of rank: 2005195477 thinks Thread-9 is the leader.
Thread-7 of rank: -46216753 thinks Thread-7 is the leader.
Thread-5 of rank: -1368289184 thinks Thread-5 is the leader.
Thread-4 of rank: -913965499 thinks Thread-4 is the leader.
Thread-6 of rank: -212254067 thinks Thread-6 is the leader.
Thread-8 of rank: 1296358853 thinks Thread-8 is the leader.
Thread-10 of rank: 1307326149 thinks Thread-10 is the leader.
Thread-12 of rank: -1784231353 thinks Thread-12 is the leader.
Thread-3 of rank: -1154546174 thinks Thread-3 is the leader.
The highest ranked thread is: Thread-4
*/
