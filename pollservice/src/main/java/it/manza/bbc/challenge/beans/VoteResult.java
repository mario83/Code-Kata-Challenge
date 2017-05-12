/**
 * 
 */
package it.manza.bbc.challenge.beans;

import java.util.Map;

/**
 * @author mario
 *
 */
public class VoteResult {

	private int totalVotes;
	private Map<String, Long> voteMap;
	
	public VoteResult(int totalVotes, Map<String, Long> voteMap) {
		super();
		this.totalVotes = totalVotes;
		this.voteMap = voteMap;
	}
	
	public int getTotalVotes() {
	
		return totalVotes;
	}
	
	public void setTotalVotes(int totalVotes) {
	
		this.totalVotes = totalVotes;
	}
	
	public Map<String, Long> getVoteMap() {
	
		return voteMap;
	}
	
	public void setVoteMap(Map<String, Long> voteMap) {
	
		this.voteMap = voteMap;
	}
}
