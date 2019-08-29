package com.example.ImporterService.resources;

import java.io.BufferedWriter;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ImporterService.models.Reservation;
import com.example.ImporterService.services.ReservationPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Service that imports reservations via GET and POST, 
 * checks if they are valid, stores them into a file (/Desktop/reservations.txt)
 * and publishes the reservation added to the RabbitMQ event queue.
 * The compactionService will be listening on this queue and react appropriately.
 * @author alessandra
 */
@RestController
public class ImportResource {
	

	@Autowired
	private ReservationPublisher publisher;
	
	@Autowired
	private ObjectMapper mapper;
	 
	/**
	 * Gets the reservations via POST request
	 * @param reservation
	 * @return
	 */
	@PostMapping("/reservations")
	public Boolean getReservations(@RequestBody Reservation reservation) {			      
		 return processReservation(reservation);	  
	}
	
	
	/**
	  * Gets the reservations via GET request
	  * (e.g. http://localhost:8081/reservations?reservationId=0001&guestName=John%20Smith&hotelName=Mondschein&city=Innsbruck)
	  * @param reservation
	  * @return
	  */
	 @RequestMapping("/reservations")
	 public Boolean getReservationsWithParams(Reservation reservation) {   
		 return processReservation(reservation);	  
	 }
	 

	
	 /**
	  * Processes a reservation in input, 
	  * Calling validate, store and then publishing the 
	  * current reservation to the RabbitMQ event queue
	  * @param reservation
	  * @return 
	  */
	 private Boolean processReservation(Reservation reservation) {
		if (validate(reservation) && store(reservation)) {
			String reservationJson;
			try {
				reservationJson = mapper.writeValueAsString(reservation);
				publisher.sendMessage(reservation);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return false;
			}

			return true;
		 }
		 return false;
	 }
	
	 
	/**
	 * Validates the data in input (in this case it just checks for the string not to be empty)
	 * @param data represents the strings in input
	 * @return true or false depending on the data validity
	 */
	private Boolean validate(Reservation reservation) {
		return ! (reservation.getCity() == null 
				|| reservation.getCity().isEmpty());		
	}
	
	
	/**
	 * Writes data into a text file 
	 * @param data represents strings in input
	 * @return true or false depending on the success of the operation
	 */
	private Boolean store(Reservation reservation) {
		try{
			writeToFile(reservation.toString());
		}
		catch(IOException ex) {
			System.err.println("Impossible to store the reservation: "+ex.getMessage());
			return false;
		}
		
		return true;
		
	}
	
	
	
	
	/**
	 * Writes data to a text file on the desktop 
	 * @param data
	 * @throws IOException
	 */
	public static void writeToFile(String data) throws IOException
	{	     
		//Gets the desktop path and appends the file name
		String desktopPath = System.getProperty ("user.home") + "/Desktop/";
		String path = desktopPath + "reservations.txt";
		File file = new File(path);

		//If the file does not exist, it creates it
		if (!file.exists()) {
		   file.createNewFile();
		}

		//Writes to the file appending a new line
	    BufferedWriter writer = new BufferedWriter(
	                                new FileWriter(file, true)); 
	    writer.write(data);
	    writer.newLine(); 
	    writer.close();
	}
	
	
}

