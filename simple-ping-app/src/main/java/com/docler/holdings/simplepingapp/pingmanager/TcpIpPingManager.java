package com.docler.holdings.simplepingapp.pingmanager;

import java.util.Set;
import java.util.Timer;

import org.apache.commons.collections.CollectionUtils;

import com.docler.holdings.simplepingapp.ping.TcpIpPingServiceTask;

/**
 * 
 * Tcp/Ip Protocol Ping Manager
 *
 */
public class TcpIpPingManager extends AbstractPingManager {

	private int timeout;
	private long maxResponseTime;

	/**
	 * Default constructor
	 */
	public TcpIpPingManager() {
		timeout = getTimeout();
		maxResponseTime = getMaxResponseTime();
	}

	@Override
	protected void startPing(Set<String> urls) {
		Timer timer = new Timer();
		if (CollectionUtils.isNotEmpty(urls)) {
			for (String url : urls) {
				TcpIpPingServiceTask pingService = new TcpIpPingServiceTask(url, timeout, maxResponseTime);
				timer.schedule(pingService, 0, getDelay());
			}
		}
	}
}
