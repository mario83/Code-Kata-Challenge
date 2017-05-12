/**
 * 
 */
package it.manza.bbc.challenge.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mario
 *
 */
@XmlRootElement
public class Poll {

	private String name;
	private String description;
	private List<String> candidates;
	private List<Vote> votes;
		
	public Poll() {
		super();
	}

	public Poll(String name, String description, List<String> candidates) {
		super();
		this.name = name;
		this.description = description;
		this.candidates = candidates;
		this.votes = new ArrayList<>();
	}

	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public String getDescription() {
	
		return description;
	}

	public void setDescription(String description) {
	
		this.description = description;
	}
	
	public List<String> getCandidates() {
	
		return candidates;
	}
	
	public void setCandidates(List<String> candidates) {
	
		this.candidates = candidates;
	}
	
	public List<Vote> getVotes() {

		return votes;
	}

	public void setVotes(List<Vote> votes) {

		this.votes = votes;
	}
	
	@Override
	public String toString() {

		return "Poll [name=" + name + ", description=" + description +
			", candidates=" + candidates + ", votes=" + votes + "]";
	}
}
