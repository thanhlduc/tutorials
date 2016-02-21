package com.docler.holdings.simplepingapp.pingmanager;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * Abstract ping manager
 *
 */
public abstract class AbstractPingManager implements IPingManager {

	protected int delay;

	@Override
	public void pingHosts(Set<String> urls) {
		startPing(urls);
	}

	protected int getDelay() {
		return getIntValue(ConfigReader.DELAY);
	}

	protected int getTimeout() {
		return getIntValue(ConfigReader.TIMEOUT);
	}

	protected int getMaxResponseTime() {
		return getIntValue(ConfigReader.MAX_RESPONSE_TIME);
	}

	private int getIntValue(String key) {
		int value = -1;
		String intValueStr = ConfigReader.INSTANCE.getProperty(key);
		if (StringUtils.isNotEmpty(intValueStr)) {
			value = Integer.parseInt(intValueStr);
		}
		return value;
	}

	/**
	 * Start ping with the given hosts
	 * 
	 * @param urls
	 */
	protected abstract void startPing(Set<String> urls);
}
