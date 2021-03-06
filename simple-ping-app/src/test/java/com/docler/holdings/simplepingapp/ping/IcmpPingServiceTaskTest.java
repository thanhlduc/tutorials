package com.docler.holdings.simplepingapp.ping;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * Test for {@link IcmpPingServiceTask}
 *
 */
public class IcmpPingServiceTaskTest {
	private static final String WINDOWS = "Windows";
	private static final String OS_NAME = "os.name";
	private transient IPingService pingService;

	@Before
	public void setUp() {
		pingService = new IcmpPingServiceTask();
	}

	@Test
	public void ping_URL_jasminDotcom_ReturnPingResult() {
		String jasminURL = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST1);
		String pingResult = pingService.ping(jasminURL);
		assertThat(pingResult.contains(jasminURL), is(true));
	}

	@Test
	public void ping_InvalidURL_ReturnNull() {
		String pingResult = pingService.ping("unknown");
		if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
			assertThat(pingResult.contains("unknown"), is(true));
		} else {
			assertThat(pingResult.isEmpty(), is(true));
		}
	}
}
