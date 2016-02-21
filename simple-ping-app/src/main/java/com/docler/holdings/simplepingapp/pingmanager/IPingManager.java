package com.docler.holdings.simplepingapp.pingmanager;

import java.util.Set;

/**
 * 
 * Ping manager contract
 *
 */
public interface IPingManager {

	/**
	 * Ping hosts from given urls
	 * 
	 * @param urls
	 */
	public void pingHosts(Set<String> urls);
}
