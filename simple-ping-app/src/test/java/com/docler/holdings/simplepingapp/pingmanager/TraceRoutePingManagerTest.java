package com.docler.holdings.simplepingapp.pingmanager;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.docler.holdings.simplepingapp.cache.PingResult;
import com.docler.holdings.simplepingapp.cache.PingResultCacheManager;
import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * 
 * Test for {@link TraceRoutePingManager}
 *
 */
public class TraceRoutePingManagerTest {
	private IPingManager pingManager;

	@Before
	public void setUp() {
		pingManager = new TraceRoutePingManager();
	}

	@Test
	public void pingHosts_HostsOK_ReturnPingResultOK() throws InterruptedException {
		String url1 = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST1);
		String url2 = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST2);
		HashSet<String> urls = new HashSet<>();
		urls.add(url1);
		urls.add(url2);
		pingManager.pingHosts(urls);

		// Verify report in cache
		Thread.sleep(40000);
		PingResult jasminReport = PingResultCacheManager.INSTANCE.getFromTraceRouteCache(url1);
		PingResult oranumReport = PingResultCacheManager.INSTANCE.getFromTraceRouteCache(url2);
		assertThat(jasminReport != null, is(true));
		assertThat(oranumReport != null, is(true));
	}

}
