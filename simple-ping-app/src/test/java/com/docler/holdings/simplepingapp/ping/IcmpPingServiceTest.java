package com.docler.holdings.simplepingapp.ping;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * Test for {@link IcmpPingService}
 *
 */
public class IcmpPingServiceTest {

	private transient IPingService pingService;

	@Before
	public void setUp() {
		pingService = new IcmpPingService();
	}

	@Test
	public void ping_URL_jasminDotcom_ReturnPingResult() {
		String jasminURL = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST1);
		String pingResult = pingService.ping(jasminURL, 3, 3);
		assertThat(pingResult.contains(jasminURL), is(true));
	}

	@Test
	public void ping_InvalidURL_ReturnNull() {
		String pingResult = pingService.ping("unknown", 3, 3);
		assertThat(pingResult.contains("unknown"), is(true));
	}
}
