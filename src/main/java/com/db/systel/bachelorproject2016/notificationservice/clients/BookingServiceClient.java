package com.db.systel.bachelorproject2016.notificationservice.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerBooking;

public class BookingServiceClient {

	private static RestTemplate restTemplate;

	/*
	 * TODO: Service Discovery wenn im Container Service
	 * An dieser Stelle wird die Adresse des Dienstes festgelegt
	 */
	private static String hostAddress = "http://localhost";
	private static int port = 8080;

	/*
	 * Je nachdem, um was für einen Ausfall es sich handelt, müssen die Daten
	 * anders beim Buchungsservice angefragt werden --> zwei Funktionen, die die
	 * HTTPEntität und URL zusammen bauen
	 */
	public static List<CustomerBooking> getAffectedBookings(int trainConnectionID, String day) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		HttpEntity<List<Integer>> entity = new HttpEntity<List<Integer>>(headers);

		String URL = String.format("%s:%s/%s", hostAddress, port,
				"cancel-train-connection?trainConnectionID=" + trainConnectionID + "&day=" + day);

		return getAffectedBookings(URL, entity);
	}

	public static List<CustomerBooking> getAffectedBookings(List<Integer> seatIDs) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		HttpEntity<List<Integer>> entity = new HttpEntity<List<Integer>>(seatIDs, headers);

		String URL = String.format("%s:%s/%s", hostAddress, port, "disable-partial-bookings");

		return getAffectedBookings(URL, entity);
	}

	/*
	 * Anfrage an den Dienst
	 * 
	 * Ist erstmal nicht viel; aber wenn sich dort was ändern sollte, dann muss
	 * es nicht in beiden Funktionen geändert werden / wenn noch mehr
	 * Ausfalltypen dazu kommen kann man hier direkt drauf zugreifen
	 */
	public static List<CustomerBooking> getAffectedBookings(String URL, HttpEntity<?> entity) {

		restTemplate = new RestTemplate();

		try {

			ResponseEntity<CustomerBooking[]> response = restTemplate.exchange(URL, HttpMethod.POST, entity,
					CustomerBooking[].class);
			
			System.out.println("Successfully received affected bookings.");

			return Arrays.asList(response.getBody());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
