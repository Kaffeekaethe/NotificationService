package com.db.systel.bachelorproject2016.notificationservice.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerContact;

public class CustomerManagementServiceClient {

	private static RestTemplate restTemplate;

	// TODO: Service Discovery f�r BuchungsDienste --> URL und Port
	
	/*
	 * Für Kommentare den BookingServiceClient anschauen; ist relativ identisch
	 */
	private static String hostAddress = "http://127.0.0.1";
	private static int port = 8081;

	public static List<CustomerContact> getContactInformation(List<Integer> customerIDs) {
		restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		HttpEntity<?> entity = new HttpEntity<>(customerIDs, headers);

		String URL = String.format("%s:%s/%s", hostAddress, port, "get-contact-information?customerIDs=");
		for(int i = 0; i < customerIDs.size(); i++){
			URL += customerIDs.get(i);
			if(i < customerIDs.size()-1){
				URL += ",";
			}
		}
		try {
			System.out.println("Sending to " + URL);
			ResponseEntity<CustomerContact[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, CustomerContact[].class);			
			System.out.println("Successfully received contact information");
			return Arrays.asList(response.getBody());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
