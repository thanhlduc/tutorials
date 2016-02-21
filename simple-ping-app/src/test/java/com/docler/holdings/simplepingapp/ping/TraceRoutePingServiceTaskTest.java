package com.docler.holdings.simplepingapp.ping;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * Test for {@link TraceRoutePingServiceTask}
 *
 */
public class TraceRoutePingServiceTaskTest {

	private transient IPingService pingService;

	@Before
	public void setUp() {
		pingService = new TraceRoutePingServiceTask();
	}

	@Test
	public void ping_URL_jasminDotcom_ReturnPingResult() {
		String jasminURL = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST1);
		String pingResult = pingService.ping(jasminURL);
		assertThat(pingResult.contains(jasminURL), is(true));
	}

	@Test
	public void ping_InvalidURL_ReturnError() {
		String pingResult = pingService.ping("unknown");
		assertThat(pingResult.contains("unknown"), is(true));
	}
}
