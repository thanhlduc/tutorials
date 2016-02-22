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

	private static final String RESPONSE_CONTENT = "Response content : ";
	private static final String RESPONSE_CODE = "Response Code : ";
	private static final String SEND_POST_REQUEST_TO_URL = "Sending 'POST' request to URL : ";
	private static final String POST_CONTENT = "Post report : ";
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
			logger.info(SEND_POST_REQUEST_TO_URL + url.toString());

			// add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			// Send post request
			logger.warn(POST_CONTENT + content);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(content);
			wr.flush();
			wr.close();

			httpResponseCode = parseResponse(content, url, con);

		} catch (IOException ex) {
			logger.warn("HTTP Post request error : " + urlAddress + " with content: " + content);
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
		logger.info(RESPONSE_CODE + responseCode);
		String responseContent = StreamReader.readStream(con.getInputStream());
		logger.info(RESPONSE_CONTENT + responseContent);
		return responseCode;
	}

}
