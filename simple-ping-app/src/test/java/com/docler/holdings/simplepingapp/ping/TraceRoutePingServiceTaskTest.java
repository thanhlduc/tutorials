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
	private static final String WINDOWS = "Windows";
	private static final String OS_NAME = "os.name";
	private transient IPingService pingService;

	@Before
	public void setUp() {
		pingService = new TraceRoutePingServiceTask();
	}

	@Test
	public void ping_URL_jasminDotcom_ReturnPingResult()
			throws InterruptedException {
		String jasminURL = ConfigReader.INSTANCE
				.getProperty(ConfigReader.SURVEY_HOST1);
		String pingResult = pingService.ping(jasminURL);

		Thread.sleep(1000);
		assertThat(pingResult.contains(jasminURL), is(true));
	}

	@Test
	public void ping_InvalidURL_ReturnError() throws InterruptedException {
		String pingResult = pingService.ping("unknown");

		Thread.sleep(1000);
		if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
			assertThat(pingResult.contains("unknown"), is(true));
		} else {
			assertThat(pingResult.isEmpty(), is(true));
		}
	}
}
