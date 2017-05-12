/**
 * 
 */
package it.manza.bbc.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.manza.bbc.challenge.beans.Poll;
import it.manza.bbc.challenge.beans.Vote;
import it.manza.bbc.challenge.beans.VoteResult;

/**
 * @author mario
 *
 */

@Path("poll-service")
public class PollService {

	//I use a static object to simulate persistence
	private static Poll poll;
	
	static {
		//initialize poll object with 4 candidates
		List<String> candidates = new ArrayList<>();
		candidates.add("A");
		candidates.add("B");
		candidates.add("C");
		candidates.add("D");
		
		poll = new Poll("poll1", "test", candidates);
	}
	
	/**
	 * Get Poll Object
	 * @return voteResult (total vote and map with candidate and relative number of votes)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoll() {
		
		//easyest return object with all information but without result of
//		return Response.status(200).entity(poll).build();
		
		int totalVotes = poll.getVotes().size();
		
		//transform the list of votes to a map where the key is the candidate and value are votes he took
		Map<String, Long> voteMap = poll.getVotes().stream().collect(
			Collectors.groupingBy(Vote::getCandidate, Collectors.counting()));
		
		VoteResult voteResult = new VoteResult(totalVotes, voteMap);
		
		return Response.status(200).entity(voteResult).build();
	}
	
	/**
	 * Add vote to poll
	 * @param pollName
	 * @param vote
	 * @return
	 */
	@POST
	@Path("/{pollName}")
	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
	public Response countMeUp(
		@PathParam("pollName") String pollName, Vote vote) {

		//I can check if the pollName is the same of current poll and if the vote is for a real candidate
		if(!poll.getName().equals(pollName)){
			String output = "poll not exist";
			return Response.status(404).entity(output).build();
		}
		
		if(!poll.getCandidates().contains(vote.getCandidate())){
			String output = "candidate not exist";
			return Response.status(404).entity(output).build();
		}
		
		List<Vote> votes = poll.getVotes();
		
		//get the number of votes of the voter
		long count = votes.stream().filter(
			v -> v.getVoter().equals(vote.getVoter())).count();
		
		//check that the voter has already made 3 votes
		if(count < 3){
			poll.getVotes().add(vote);			
			String output = "vote aquired";
			return Response.status(201).entity(output).build();
		} else {
			String output = "too many votes";
			return Response.status(403).entity(output).build();
		}
	}
}
