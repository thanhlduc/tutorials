package com.docler.holdings.simplepingapp;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;
import com.docler.holdings.simplepingapp.pingmanager.IcmpPingManager;
import com.docler.holdings.simplepingapp.pingmanager.TcpIpPingManager;
import com.docler.holdings.simplepingapp.pingmanager.TraceRoutePingManager;

/**
 * 
 * Main
 *
 */
public final class ApplicationStarter {
	private static transient Logger logger = Logger.getLogger(ApplicationStarter.class);

	public static void main(String[] args) throws Exception {
		// Hosts
		Set<String> urls = new HashSet<>();
		String url1 = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST1);
		String url2 = ConfigReader.INSTANCE.getProperty(ConfigReader.SURVEY_HOST2);
		urls.add(url1);
		urls.add(url2);

		// ICMP Protocol ping
		logger.info("Start Icmp Ping");
		new IcmpPingManager().pingHosts(urls);

		// TCP/IP protocol ping
		logger.info("Start Tcp/ip Ping");
		new TcpIpPingManager().pingHosts(urls);

		// Trace route
		logger.info("Start Trace Route");
		new TraceRoutePingManager().pingHosts(urls);

		// Wait for ping
		Thread.sleep(100000);
	}

}
