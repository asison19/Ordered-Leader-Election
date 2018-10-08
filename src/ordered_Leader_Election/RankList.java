package ordered_Leader_Election;

public class RankList {
	int[] ranks;
	String[] names;
	
	int index = 0;
	
	public RankList(int threadAmount) {
		ranks = new int[threadAmount];
		names = new String[threadAmount];
		
		
	}
	
	public String findHighestRankedThread() {
		int highestRankLocation = 0;
		for(int i = 0; i < names.length; i++) {
			if(i == 0)
				highestRankLocation = i;
			if(ranks[i] > highestRankLocation)
				highestRankLocation = i;
				
		}
		return names[highestRankLocation];
	}
	
	public synchronized void addOfficial(String name, int rank) {
		ranks[index] = rank;
		names[index] = name;
	}

}
