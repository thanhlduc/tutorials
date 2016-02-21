package com.docler.holdings.simplepingapp.reporting;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.docler.holdings.simplepingapp.helper.StreamReader;

/**
 * 
 * Creation and sending report
 *
 */
public enum ReportSender {
	INSTANCE;

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final Logger logger = Logger.getLogger(ReportSender.class);

	/**
	 * Constructor
	 */
	private ReportSender() {

	}

	/**
	 * HTTP POST request
	 * 
	 * @param urlAddress
	 * @param content
	 * @return HTTP response code or -1 if error
	 */
	public int post(String urlAddress, String content) {
		int httpResponseCode = -1;
		URL url = null;
		try {
			url = new URL(urlAddress);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			logger.info("Sending 'POST' request to URL : " + url.toString());

			// add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(content);
			wr.flush();
			wr.close();
			logger.info("Post content : " + content);

			httpResponseCode = parseResponse(content, url, con);

		} catch (IOException ex) {
			logger.error("HTTP Post request error : " + urlAddress + " with content: " + content);
		}

		return httpResponseCode;
	}

	/**
	 * Parse post response
	 * 
	 * @param content
	 * @param url
	 * @param con
	 * @return http response code
	 * @throws IOException
	 */
	private int parseResponse(String content, URL url, HttpURLConnection con) throws IOException {
		int responseCode = con.getResponseCode();
		logger.info("Response Code : " + responseCode);
		String responseContent = StreamReader.readStream(con.getInputStream());
		logger.info("Response content : " + responseContent);
		return responseCode;
	}

}
