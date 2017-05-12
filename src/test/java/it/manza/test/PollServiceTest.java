/**
 * 
 */
package it.manza.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import it.manza.bbc.challenge.PollService;
import it.manza.bbc.challenge.beans.Vote;

/**
 * @author mario
 *
 */
public class PollServiceTest extends JerseyTest{

	@Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(PollService.class);
    }
	
	@Test
    public void testGetPoll() {
        Response output = target("/poll-service").request().get();
        assertEquals("should return status 200", 200, output.getStatus());
    }
	
	@Test
    public void countMeUp1() {
		//Count Me Up accepts a vote
		Vote vote = new Vote("A", "A");
		Response output = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
        assertEquals("should return status 201", 201, output.getStatus());
    }
	
	@Test
    public void countMeUp2() {
		//Count Me Up only accepts 3 votes per user
		Vote vote = new Vote("B", "A");
		Response output = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
		Response output1 = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
		Response output2 = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
		Response output3 = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
        assertEquals("should return status 201", 201, output.getStatus());
        assertEquals("should return status 201", 201, output1.getStatus());
        assertEquals("should return status 201", 201, output2.getStatus());
        assertEquals("should return status 403", 403, output3.getStatus());
    }
	
	@Test
    public void countMeUp3() {
		//Count Me Up only accepts 3 votes per user regardless of candidate
		Vote vote = new Vote("C", "A");
		Response output = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
		Response output1 = target("/poll-service/poll1").request().post(
			Entity.entity(vote, MediaType.APPLICATION_JSON));
		
		Vote vote2 = new Vote("C", "D");
		Response output2 = target("/poll-service/poll1").request().post(
			Entity.entity(vote2, MediaType.APPLICATION_JSON));
		Response output3 = target("/poll-service/poll1").request().post(
			Entity.entity(vote2, MediaType.APPLICATION_JSON));
        assertEquals("should return status 201", 201, output.getStatus());
        assertEquals("should return status 201", 201, output1.getStatus());
        assertEquals("should return status 201", 201, output2.getStatus());
        assertEquals("should return status 403", 403, output3.getStatus());
    }
	
	@Test
    public void countMeUp4() {
		//Count Me Up returns the voting results
		long start = System.currentTimeMillis();
        
		Response output = target("/poll-service").request().get();
     
        long executionTime = System.currentTimeMillis() - start;
        
        assertEquals("should return status 200", 200, output.getStatus());
        assertNotNull("Should return list", output.getEntity());
        
        //test for execution time
        assertTrue(executionTime < 1000);
    }
}
