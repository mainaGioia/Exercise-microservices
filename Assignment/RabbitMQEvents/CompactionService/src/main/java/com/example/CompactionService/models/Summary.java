package com.example.CompactionService.models;


/**
 * Represents the Summary of the reservations in a city's hotels (8 reservations in Prague)
 * @author alessandra
 *
 */
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

