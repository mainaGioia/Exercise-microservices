package com.example.ImporterService.models;

/**
 * Represents a reservation (id, guestName, hotelName, city)
 * @author alessandra
 *
 */
public class Reservation implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String reservationId;
	private String guestName;
	private String hotelName;
	private String city;
	
	public Reservation() {
		
	}
	
	/**
	 * @param reservationId
	 * @param guestName
	 * @param hotelName
	 * @param city
	 */
	public Reservation(String reservationId, String guestName, String hotelName, String city) {
		this.reservationId = reservationId;
		this.guestName = guestName;
		this.hotelName = hotelName;
		this.city = city;
	}


	/**
	 * @return the reservationId
	 */
	public String getReservationId() {
		return reservationId;
	}


	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}


	/**
	 * @return the guestName
	 */
	public String getGuestName() {
		return guestName;
	}


	/**
	 * @param guestName the guestName to set
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}


	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}


	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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
		return "{reservationId: \""+reservationId+"\", "+
				"guestName: \""+ guestName + "\", "+
				"hotelName: \""+ hotelName + "\", "+
				"city: \"" + city + "\"}";
	}
	
	

}
