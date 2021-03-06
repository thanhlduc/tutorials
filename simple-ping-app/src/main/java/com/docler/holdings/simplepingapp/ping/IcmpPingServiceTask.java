package com.docler.holdings.simplepingapp.ping;

import com.docler.holdings.simplepingapp.cache.PingResultCacheManager;
import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * 
 * ICMP protocol ping service
 *
 */
public final class IcmpPingServiceTask extends AbstractCommandPingServiceTask {

	/**
	 * Default constructor
	 */
	public IcmpPingServiceTask() {
		super();
	}

	/**
	 * Default constructor
	 */
	public IcmpPingServiceTask(String url) {
		super(url);
	}

	@Override
	protected void savePingResult(String url, String result) {
		PingResultCacheManager.INSTANCE.putToIcmpCache(url, createPingResult(url, result));
	}

	@Override
	protected String getPingCommand(String url) {
		StringBuilder cmd = new StringBuilder();
		if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
			// For Windows
			cmd.append(ConfigReader.INSTANCE.getProperty(ConfigReader.WINDOWS_ICMP_CMD));
			cmd.append(SPACE);
			cmd.append(url);
		} else {
			// For Linux and OSX
			cmd.append(ConfigReader.INSTANCE.getProperty(ConfigReader.LINUX_ICMP_CMD));
			cmd.append(SPACE);
			cmd.append(url);
		}
		String pingString = cmd.toString();
		return pingString;
	}

	@Override
	protected String getProtocol() {
		return "ICMP";
	}

}
