package com.docler.holdings.simplepingapp.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * Stream reader utilities
 *
 */
public final class StreamReader {

	/**
	 * Read from a stream
	 * 
	 * 
	 * @param input
	 * @return a string
	 * @throws IOException
	 */
	public static String readStream(InputStream input) throws IOException {
		StringBuffer result = new StringBuffer();
		if (input != null) {
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));
			String inputLine;
			while ((inputLine = buffReader.readLine()) != null) {
				result.append(inputLine);
			}
			buffReader.close();
		}
		return result.toString();
	}
}
