import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import com.google.common.collect.Lists;
import com.twitter.clientlib.api.TwitterApi;
import java.util.HashSet;
import java.util.Set;
import com.twitter.clientlib.TwitterCredentials;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentials;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.*;
public class TwitterProducer {
	
	public TwitterProducer() {
		
	}
	public static void main(String[] args) {
		/**
	     * Set the credentials for the required APIs.
	     * The Java SDK supports TwitterCredentialsOAuth2 & TwitterCredentialsBearer.
	     * Check the 'security' tag of the required APIs in https://api.twitter.com/2/openapi.json in order
	     * to use the right credential object.
	     */
		TwitterCredentials t = new TwitterCredentials();
		t.setTwitterConsumerKey("DH79Aec0v7sRSOxLgDU9aFK8F");
		t.setTwitterConsumerSecret("gnxnJ7T6SFtgTcjKSVHto1y1NbupgUmOQPTruTdj4E8MBkL80k");
		t.setTwitterToken("53960370-OVY3W6IEFIQM18XpK7UqPGc76cupiXSadF8JKMjQy");
		t.setTwitterTokenSecret("53960370-OVY3W6IEFIQM18XpK7UqPGc76cupiXSadF8JKMjQy");
		t.setBearerToken("AAAAAAAAAAAAAAAAAAAAACjmhQEAAAAAX%2Ba2qgEAalzV6JdOlAVm4hXYkVU%3DHPuA0c2M7Y9iPwnloyzwlUWMyUudwRCeQRrpPhQv7blMSrBSSz");
	    TwitterApi apiInstance = new TwitterApi(t);
	    Set<String> tweetFields = new HashSet<>();
	    tweetFields.add("author_id");
	    tweetFields.add("id");
	    tweetFields.add("created_at");

	    
	     // findTweetById
	    TweetSearchResponse result = null;
		try {
			OffsetDateTime date;
			Set<String> fields = new HashSet<>(Arrays.asList());
			//fields.add("username");
			
			result = apiInstance.tweetsRecentSearch("carlsen",null,null,null, null, 100, null, null, null, null, null, fields, null);
			List data = result.getData();
		     for (int i = 0; i< data.size();i++ ) {
		    	 Tweet t1 = (Tweet) data.get(i);
		    	 
		    	 System.out.println(t1);
		     }
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCode()+ e.getMessage());
		}
	     
//	     .tweetFields(tweetFields)
//	       .execute();
//	     if(result.getErrors() != null && result.getErrors().size() > 0) {
//	       System.out.println("Error:");
//
//	       result.getErrors().forEach(e -> {
//	         System.out.println(e.toString());
//	         if (e instanceof ResourceUnauthorizedProblem) {
//	           System.out.println(((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
//	         }
//	       });
//	     } else {
//	       System.out.println("findTweetById - Tweet Text: " + result.toString());
//	     }
//	    } catch (ApiException e) {
//	      System.err.println("Status code: " + e.getCode());
//	      System.err.println("Reason: " + e.getResponseBody());
//	      System.err.println("Response headers: " + e.getResponseHeaders());
//	      e.printStackTrace();
//	    }
	  }
	}
	
	
	
	
	
	
	