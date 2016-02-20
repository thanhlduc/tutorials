package com.docler.holdings.simplepingapp.ping;

import java.io.InputStream;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;
import com.docler.holdings.simplepingapp.helper.StreamReader;

/**
 * 
 * ICMP protocol ping service
 *
 */
public final class ICMPPingService implements IPingService {

	private static final String SPACE = " ";
	private static final String WINDOWS = "Windows";
	private static final String OS_NAME = "os.name";

	@Override
	public String ping(String url, int delay, int timeout) {
		String result = "";
		try {
			StringBuilder cmd = new StringBuilder();
			if (System.getProperty(OS_NAME).startsWith(WINDOWS)) {
				// For Windows
				cmd.append(ConfigReader.INSTANCE.getProperty(ConfigReader.WINDOWS_ICMP_CMD));
				cmd.append(SPACE);
				cmd.append(url);
			} else {
				// For Linux and OSX
				cmd.append(ConfigReader.INSTANCE.getProperty(ConfigReader.LINUX_ICMP_CMD));
				cmd.append(SPACE);
				cmd.append(url);
			}

			Process myProcess = Runtime.getRuntime().exec(cmd.toString());
			myProcess.waitFor();

			if (myProcess.exitValue() == 0) {
				InputStream inputStream = myProcess.getInputStream();
				result = StreamReader.readStream(inputStream);
			} else {
				InputStream inputStream = myProcess.getInputStream();
				result = StreamReader.readStream(inputStream);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
