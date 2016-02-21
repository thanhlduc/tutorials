package com.docler.holdings.simplepingapp.pingmanager;

import java.util.Set;
import java.util.Timer;

import org.apache.commons.collections.CollectionUtils;

import com.docler.holdings.simplepingapp.ping.TraceRoutePingServiceTask;

/**
 * 
 * Trace route Protocol Ping Manager
 *
 */
public class TraceRoutePingManager extends AbstractPingManager {

	@Override
	protected void startPing(Set<String> urls) {
		if (CollectionUtils.isNotEmpty(urls)) {
			for (String url : urls) {
				Timer timer = new Timer();
				TraceRoutePingServiceTask pingService = new TraceRoutePingServiceTask(url);
				timer.schedule(pingService, 0, getDelay());
			}
		}
	}
}
