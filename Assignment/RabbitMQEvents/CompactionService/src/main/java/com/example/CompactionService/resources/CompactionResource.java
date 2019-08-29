package com.example.CompactionService.resources;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.CompactionService.models.Reservation;
import com.example.CompactionService.models.Summary;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service that builds the reservations'summaries by city, 
 * reading the reservations from a file.
 * @author alessandra
 *
 */
@Component
public class CompactionResource {

	/**
	 * Listens to events on the reservations queue and computes new summaries 
	 * when the events get triggered
	 * @param msg reservation received
	 * @return
	 */
	@RabbitListener(queues="${reservations.rabbitmq.queue}")
    public void receivedMessage(Message msg) {
		String reservation = new String(msg.getBody());
        System.out.println("Received Message: " + reservation);
        getSummaries();
    }
	
	
	/**
	 * Builds the reservations' summaries by city
	 * (e.g. 5 people booked in Budapest, 2 booked in Innsbruck...)
	 * @param city
	 * @return 
	 */
	public void getSummaries(){
		Map<String, Integer> summaries;
		List<Summary> result = new ArrayList<Summary>();
		int count = 0;

		try {
			summaries = readFileBuildingSummaries();
			// build the list of summaries from the map
			for (Map.Entry<String, Integer> entry : summaries.entrySet()) {
				result.add(new Summary(Integer.toString(count), // summary id
						entry.getValue().toString(), // number of reservations
						entry.getKey() // city
				));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Adding a bit of delay
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(result.toString());
	}
	
	
	/**
	 * Maps all the entries in the file into Reservations and then groups them by city
	 * @return
	 * @throws IOException
	 */
	private Map<String, Integer> readFileBuildingSummaries() throws IOException {
		Map<String, Integer> reservationsMap = new TreeMap<String, Integer>();

		//Build the path of the reservations.txt file on Desktop
		String desktopPath = System.getProperty ("user.home") + "/Desktop/";
		String path = desktopPath + "reservations.txt";	
    	ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		//read from file
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
		    String line;
		    while ((line = br.readLine()) != null) {
				// Needed to hande a json without quotes on field names
				//In case of incorrect double quotes, replace them
				String transformedText = line.replace('”' , '"');
				//Map the JSON string back to a reservation 
				Reservation reservation = mapper.readValue(transformedText, Reservation.class);
				//Add to the map the corresponding entry <city, count>
				int count = reservationsMap.get(reservation.getCity()) == null ? 
		         		0 : reservationsMap.get(reservation.getCity());
		        reservationsMap.put(reservation.getCity(), ++count);
		    }
		} finally {
		    br.close();
		}
				
		return reservationsMap;
	
	}


}