package com.docler.holdings.simplepingapp;

import com.docler.holdings.simplepingapp.reporting.ReportSender;

/**
 * 
 * Main
 *
 */
public class ApplicationStarter {
	public static void main(String[] args) throws Exception {

		ReportSender http = new ReportSender();

		System.out.println("\nTesting Appache: - Send Http POST request");
		http.sendHttpPost();

		System.out.println("\nTesting: - Send Http POST request");
		http.sendHttpPost();

	}

}
