package com.docler.holdings.simplepingapp.ping;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;
import com.docler.holdings.simplepingapp.helper.StreamReader;

/**
 * 
 * ICMP protocol ping service
 *
 */
public final class IcmpPingService implements IPingService {

	private static final String SPACE = " ";
	private static final String WINDOWS = "Windows";
	private static final String OS_NAME = "os.name";

	private static final Logger logger = Logger.getLogger(IcmpPingService.class);

	@Override
	public String ping(String url, int delay, int timeout) {
		String result = pingUrl(url);
		return result;
	}

	private String pingUrl(String url) {
		String result = "";
		try {
			String pingCmd = getPingCommand(url);
			logger.info("Ping command: " + pingCmd);
			Process myProcess = Runtime.getRuntime().exec(pingCmd);
			myProcess.waitFor();

			if (myProcess.exitValue() == 0) {
				InputStream inputStream = myProcess.getInputStream();
				result = StreamReader.readStream(inputStream);
			} else {
				InputStream inputStream = myProcess.getInputStream();
				result = StreamReader.readStream(inputStream);
			}
		} catch (Exception e) {
			logger.error("Error for ping the url: " + url);
		}
		return result;
	}

	private String getPingCommand(String url) {
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
		String pingString = cmd.toString();
		return pingString;
	}

}
