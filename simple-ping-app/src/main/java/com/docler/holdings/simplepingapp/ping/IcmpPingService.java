package com.docler.holdings.simplepingapp.ping;

import java.io.InputStream;
import java.util.Date;

import com.docler.holdings.simplepingapp.cache.PingResult;
import com.docler.holdings.simplepingapp.cache.ReportCacheManager;
import com.docler.holdings.simplepingapp.configuration.ConfigReader;
import com.docler.holdings.simplepingapp.helper.StreamReader;

/**
 * 
 * ICMP protocol ping service
 *
 */
public final class IcmpPingService extends AbstractPingService implements IPingService {

	private static final String PING_ICMP_RESULT = "Ping icmp result: ";
	private static final String PING_ICMP_COMMAND = "Ping icmp command: ";
	private static final String SPACE = " ";
	private static final String WINDOWS = "Windows";
	private static final String OS_NAME = "os.name";

	/**
	 * Default constructor
	 */
	public IcmpPingService() {
		super();
	}

	/**
	 * Default constructor
	 */
	public IcmpPingService(String url) {
		super(url);
	}

	@Override
	public String ping(String url) {
		String result = "";
		try {
			String pingCmd = getPingCommand(url);
			logger.info(PING_ICMP_COMMAND + pingCmd);
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
			logger.error(PING_ERROR + url);
			// Publish report
			publishReport(url);
		}

		// Save report
		saveReport(url, result);

		logger.info(PING_ICMP_RESULT + result);
		return result;
	}

	@Override
	protected void saveReport(String url, String result) {
		PingResult pingResult = new PingResult();
		pingResult.setPingDate(new Date());
		pingResult.setPingResult(result);
		ReportCacheManager.INSTANCE.putToIcmpCache(url, pingResult);
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

	@Override
	public void run() {
		ping(url);
	}

}
