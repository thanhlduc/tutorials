package com.docler.holdings.simplepingapp.cache;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

/**
 * Test for {@link ReportCacheManager}
 *
 */
public class ReportCacheManagerTest {

	@Test
	public void putToIcmpCache_Key_URL1_PingResult_Return1ElementInCache() {
		PingResult pingResult = new PingResult();
		Date today = new Date();
		pingResult.setPingDate(today);
		pingResult.setPingResult("ping1");
		ReportCacheManager.INSTANCE.putToIcmpCache("URL1", pingResult);

		PingResult cachedResult = ReportCacheManager.INSTANCE.getFromIcmpCache("URL1");
		assertThat(cachedResult.getPingDate(), is(today));
		assertThat(cachedResult.getPingResult(), is("ping1"));
	}

	@Test
	public void putToTcpIpCache_Key_URL1_PingResult_Return1ElementInCache() {
		PingResult pingResult = new PingResult();
		Date today = new Date();
		pingResult.setPingDate(today);
		pingResult.setPingResult("ping1");
		ReportCacheManager.INSTANCE.putToTcpIpCache("URL1", pingResult);

		PingResult cachedResult = ReportCacheManager.INSTANCE.getFromTcpIpCache("URL1");
		assertThat(cachedResult.getPingDate(), is(today));
		assertThat(cachedResult.getPingResult(), is("ping1"));
	}
}
