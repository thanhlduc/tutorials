package com.docler.holdings.simplepingapp.ping;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * 
 * Test for {@link TcpIpPingServiceTask}
 *
 */
public class TcpIpPingServiceTaskTest {
	private TcpIpPingServiceTask pingService;

	@Before
	public void setUp() {
		pingService = new TcpIpPingServiceTask();
	}

	@Test
	public void ping_URL_jasminDotcom_ReturnPingResult() {
		String jasminURL = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST1);
		pingService.setTimeout(300);
		String pingResult = pingService.ping(jasminURL);
		assertThat(!pingResult.isEmpty(), is(true));
	}

	@Test
	public void ping_InvalidURL_ReturnEmptyString() {
		String pingResult = pingService.ping("unknown");
		pingService.setTimeout(300);
		assertThat(pingResult, is(""));
	}

}
