package com.docler.holdings.simplepingapp.pingmanager;

import java.util.Set;
import java.util.Timer;

import org.apache.commons.collections.CollectionUtils;

import com.docler.holdings.simplepingapp.ping.IcmpPingServiceTask;

/**
 * 
 * Icmp Protocol Ping Manager
 *
 */
public class IcmpPingManager extends AbstractPingManager {

	@Override
	protected void startPing(Set<String> urls) {
		if (CollectionUtils.isNotEmpty(urls)) {
			for (String url : urls) {
				Timer timer = new Timer();
				IcmpPingServiceTask pingService = new IcmpPingServiceTask(url);
				timer.schedule(pingService, 0, getDelay());
			}
		}
	}
}
