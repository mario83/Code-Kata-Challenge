/**
 * 
 */
package it.manza.bbc.challenge.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mario
 *
 */
@XmlRootElement
public class Vote {

	private String voter;
	private String candidate;
	
	public Vote() {
		super();
	}
	
	public Vote(String voter, String candidate) {
		super();
		this.voter = voter;
		this.candidate = candidate;
	}

	public String getVoter() {
	
		return voter;
	}
	
	public void setVoter(String voter) {
	
		this.voter = voter;
	}
	
	public String getCandidate() {
	
		return candidate;
	}
	
	public void setCandidate(String candidate) {
	
		this.candidate = candidate;
	}

	@Override
	public String toString() {

		return "Vote [voter=" + voter + ", candidate=" + candidate + "]";
	}
	
}
