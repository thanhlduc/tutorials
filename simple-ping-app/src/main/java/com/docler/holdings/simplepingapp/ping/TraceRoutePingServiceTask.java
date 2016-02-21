package com.docler.holdings.simplepingapp.ping;

import com.docler.holdings.simplepingapp.cache.PingResultCacheManager;
import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * 
 * Trace route ping service
 *
 */
public final class TraceRoutePingServiceTask extends AbstractCommandPingServiceTask {

	private static final String TRACE_ROUTE = "Trace Route";

	/**
	 * Default constructor
	 */
	public TraceRoutePingServiceTask() {
		super();
	}

	/**
	 * Constructor with params
	 */
	public TraceRoutePingServiceTask(String url) {
		super(url);
	}

	@Override
	protected void savePingResult(String url, String result) {
		PingResultCacheManager.INSTANCE.putToTraceRouteCache(url, createPingResult(url, result));
	}

	@Override
	protected String getPingCommand(String url) {
		StringBuilder cmd = new StringBuilder();
		if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
			// For Windows
			cmd.append(ConfigReader.INSTANCE.getProperty(ConfigReader.WINDOWS_TRACE_CMD));
			cmd.append(SPACE);
			cmd.append(url);
		} else {
			// For Linux and OSX
			cmd.append(ConfigReader.INSTANCE.getProperty(ConfigReader.LINUX_TRACE_CMD));
			cmd.append(SPACE);
			cmd.append(url);
		}
		String pingString = cmd.toString();
		return pingString;
	}

	@Override
	public void run() {
		ping(url);
	}

	@Override
	protected String getProtocol() {
		return TRACE_ROUTE;
	}

}
