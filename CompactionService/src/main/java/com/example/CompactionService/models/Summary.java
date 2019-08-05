package com.example.CompactionService.models;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class Summary {
	
	private String summaryId;
	private String numberOfReservations;
	private String city;
	
	
	/**
	 * @param summaryId
	 * @param numberOfReservations
	 * @param city
	 */
	public Summary(String summaryId, String numberOfReservations, String city) {
		this.summaryId = summaryId;
		this.numberOfReservations = numberOfReservations;
		this.city = city;
	}
	
	/**
	 * @return the summaryId
	 */
	public String getSummaryId() {
		return summaryId;
	}
	
	/**
	 * @param summaryId the summaryId to set
	 */
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	

	/**
	 * @return the numberOfReservations
	 */
	public String getNumberOfReservations() {
		return numberOfReservations;
	}
	
	/**
	 * @param numberOfReservations the numberOfReservations to set
	 */
	public void setNumberOfReservations(String numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return numberOfReservations +" people booked in " + city;
	}
	
}




/**

@RequestMapping("/{city}")
public List<Summary> getSummary(@PathVariable("city") String city) throws IOException, InterruptedException {
	//get all reservations which match the param city
	Map<String, Integer> summaries = readFileBuildingSummaries();
	//build the list to serialize
	int count = 0;
	List<Summary> result = new ArrayList<Summary>();
	for (Map.Entry<String, Integer> entry : summaries.entrySet()) {
	    result.add(new Summary(
	    		Integer.toString(count), 
	    		entry.getValue().toString(), 
	    		entry.getKey()
	    		));
	}		
	
	//Adding a bit of delay
	Thread.sleep(3000);
	
	return result;
}



private Map<String, Integer> readFileBuildingSummaries() throws IOException {
	Map<String, Integer> reservationsMap = new HashMap<String, Integer>();

	//Build the path of the reservations.txt file on Desktop
	String desktopPath = System.getProperty ("user.home") + "/Desktop/";
	String path = desktopPath + "reservations.txt";	
	
	ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(path)));

    while (true) {
        try {
            // Read the next object from the stream. If there is none, the
            // EOFException will be thrown.
            // This call might also throw a ClassNotFoundException, which can be passed
            // up or handled here.
            Reservation reservation = (Reservation) in.readObject();
            int count = reservationsMap.get(reservation.getCity()) == null ? 
            		0 : reservationsMap.get(reservation.getCity());
            reservationsMap.put(reservation.getCity(), ++count);

            
        } catch (EOFException e) {
            //there are no more objects to read, return.
            return reservationsMap;
        } catch (ClassNotFoundException e) {
        	//No Reservation class found
			e.printStackTrace();
		} finally {
			//close the stream
            in.close();
        }
    }
}


}
**/
