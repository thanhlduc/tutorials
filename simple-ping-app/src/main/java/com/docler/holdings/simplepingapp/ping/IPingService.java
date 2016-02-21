package com.docler.holdings.simplepingapp.ping;

/**
 * 
 * Ping service contract
 *
 */
public interface IPingService {

	/**
	 * Ping a host
	 * 
	 * @param url
	 * @return the last ping result
	 */
	String ping(String url);
}
