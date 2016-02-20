package com.docler.holdings.simplepingapp;

import org.apache.log4j.Logger;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;
import com.docler.holdings.simplepingapp.reporting.Report;
import com.docler.holdings.simplepingapp.reporting.ReportFactory;
import com.docler.holdings.simplepingapp.reporting.ReportSender;

/**
 * 
 * Main
 *
 */
public final class ApplicationStarter {
	private static transient Logger logger = Logger.getLogger(ApplicationStarter.class);

	public static void main(String[] args) throws Exception {
		ReportSender http = new ReportSender();

		logger.info("Testing: - Send Http POST request");
		Report report = new Report();
		report.setHostName("host");
		report.setIcmpPing("icmp");
		report.setTcpPing("tcp/ip");
		report.setTraceRoute("trace");
		String content = new ReportFactory().toJsonFormat(report);
		http.post(ConfigReader.INSTANCE.getProperty(ConfigReader.REPORT_HOST), content);

	}

}
