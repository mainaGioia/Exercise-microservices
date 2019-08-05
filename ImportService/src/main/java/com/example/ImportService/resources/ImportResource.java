package com.example.ImportService.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ImportService.models.Reservation;

@RestController
public class ImportResource {
	
	/**
	 * Gets the reservations via POST request
	 * @param reservation
	 * @return
	 */
	@PostMapping("/reservations")
	public String getRequestWithParams(@RequestBody Reservation reservation) {			      
		return (validate(reservation)) ? store(reservation) : "Invalid data!";
	}
	
	
	/**
	 * Validates the data in input (in this case it just checks for the string not to be empty)
	 * @param data represents the strings in input
	 * @return true or false depending on the data validity
	 */
	private Boolean validate(Reservation reservation) {
		if(reservation == null) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Writes data into a text file 
	 * @param data represents strings in input
	 * @return true or false depending on the success of the operation
	 */
	private String store(Reservation reservation) {
		try{
			writeToFile(reservation.toString());
		}
		catch(IOException ex) {
			return "Impossible to store the reservation: "+ex.getMessage();
		}
		
		return "Reservation "+reservation.getReservationId()+" correctly stored";
		
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
