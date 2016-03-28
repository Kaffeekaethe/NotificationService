package notificationservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerBooking;
import com.db.systel.bachelorproject2016.notificationservice.domainmodel.data.CustomerContact;
import com.db.systel.bachelorproject2016.notificationservice.domainmodel.logic.CustomerNotifier;

public class NotificationServiceTest {

	@Test
	public void customerIDsExtraction() {

		CustomerBooking[] customerBookings = new CustomerBooking[] { new CustomerBooking(1, null),
				new CustomerBooking(2, null), new CustomerBooking(5, null) };

		List<Integer> expect = new ArrayList<Integer>();
		expect.add(1);
		expect.add(2);
		expect.add(5);

		assertEquals(CustomerNotifier.extractCustomerIDs(Arrays.asList(customerBookings)), expect);
	}

	@Test
	public void customerContactExtraction() {

		CustomerContact[] customerContacts = new CustomerContact[] {

				new CustomerContact(1, "test1@gmx.de"), new CustomerContact(2, "test2@gmx.de"), };

		List<String> expect = new ArrayList<String>();
		expect.add("test1@gmx.de");
		expect.add("test2@gmx.de");

		assertEquals(CustomerNotifier.extractContactData(Arrays.asList(customerContacts)), expect);
	}
}
