package com.db.systel.bachelorproject2016.notificationservice.api;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.db.systel.bachelorproject2016.notificationservice.clients.BookingServiceClient;
import com.db.systel.bachelorproject2016.notificationservice.clients.CustomerManagementServiceClient;
import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerBooking;
import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerContact;
import com.db.systel.bachelorproject2016.notificationservice.domainmodel.logic.CustomerNotifier;

@Configuration
@EnableAutoConfiguration
@Controller
public class NotificationController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> home() {
		return new ResponseEntity<String>("I am a NotificationService.", HttpStatus.OK);
	}

	/*
	 * Technische Ausfälle --> Ein Wagen oder Platz kann nicht verwendet werden
	 * 
	 * Die beiden Methoden stellen zuerst je nachdem, um was für einen Ausfall
	 * es sich handelt, die betroffenen Buchungen fest
	 * 
	 * Der Ablauf danach ist gleich --> wird in this.workflow implementiert
	 */
	@RequestMapping(value = "/notify-seats-disabled", method = RequestMethod.POST)
	public ResponseEntity<List<Integer>> notificationSeat(@RequestParam List<Integer> seatIDs) {
		System.out.println("Received information that number of seats is unusable: " + seatIDs);
		try {
			System.out.println("Trying to get affected bookings");
			List<CustomerBooking> customerBookings = BookingServiceClient.getAffectedBookings(seatIDs);
			List<Integer> customerIDs = this.workflow(customerBookings);
			return new ResponseEntity<List<Integer>>(customerIDs, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<List<Integer>>(HttpStatus.NOT_FOUND);

		}

	}

	/*
	 * Ausfälle einer speziellen Zugfahrt
	 */
	@RequestMapping(value = "/notify-train-cancelled", method = RequestMethod.POST)
	public ResponseEntity<List<Integer>> notificationTrain(@RequestParam int trainConnectionID,
			@RequestParam String day) {
		System.out.println("Received cancellation of train connection " + trainConnectionID + " on day " + day);
		try {
			System.out.println("Trying to get affected bookings...");
			List<CustomerBooking> customerBookings = BookingServiceClient.getAffectedBookings(trainConnectionID, day);
			List<Integer> customerIDs = this.workflow(customerBookings);
			return new ResponseEntity<List<Integer>>(customerIDs, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<List<Integer>>(HttpStatus.NOT_FOUND);

		}

	}

	/*
	 * Der Ablauf nachdem die Buchungen, die betroffen sind, bekannt sind:
	 */
	public List<Integer> workflow(List<CustomerBooking> customerBookings) {
		List<Integer> customerIDs = null;

		// Hole die KundenIDs aus der Datenstruktur
		customerIDs = CustomerNotifier.extractCustomerIDs(customerBookings);

		System.out.println("Customer IDs " + customerIDs + ". Now requesting contact information...");
		// Hole bei der Kundenverwaltung die Kontaktdaten
		List<CustomerContact> customerContacts = CustomerManagementServiceClient.getContactInformation(customerIDs);

		System.out.println("Now parsing...");

		// Hole die Kontaktdaten aus der Datenstruktur
		List<String> contactData = CustomerNotifier.extractContactData(customerContacts);

		System.out.println("Parsed:");

		for (String s : contactData) {
			System.out.println(s);
		}

		// Benachrichtige die Kunden
		CustomerNotifier.notifyCustomer(contactData);

		return customerIDs;
	}
}
