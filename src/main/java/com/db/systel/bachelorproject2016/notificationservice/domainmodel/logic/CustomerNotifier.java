package com.db.systel.bachelorproject2016.notificationservice.domainmodel.logic;

import java.util.ArrayList;
import java.util.List;

import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerBooking;
import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerContact;

public class CustomerNotifier {

	/*
	 * Benachrichtigt den Kunden. Du könntest an dieser Stelle z.B. auch die
	 * Teilbuchungen verwenden um in der Mail ein bisschen was genaueres zu
	 * schreiben
	 */
	public static void notifyCustomer(List<String> contactData) {

		// TODO: E-Mails via SMTP-Server verschicken
		for (String data : contactData) {
			System.out.println("Sending E-Mail to " + data);
		}

	}

	/*
	 * Auslesen der wichtigen Schlüssel
	 */
	public static List<Integer> extractCustomerIDs(List<CustomerBooking> customerBookings) {
		List<Integer> customerIDs = new ArrayList<Integer>();
		for (CustomerBooking obj : customerBookings) {
			customerIDs.add(obj.getCustomerID());
		}
		return customerIDs;
	}

	public static List<String> extractContactData(List<CustomerContact> customerContact) {
		List<String> customerMailAddresses = new ArrayList<String>();
		for (CustomerContact cc : customerContact) {
			customerMailAddresses.add(cc.getEMailAddress());
		}
		return customerMailAddresses;
	}

}
