package com.docler.holdings.simplepingapp.ping;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.docler.holdings.simplepingapp.cache.PingResult;
import com.docler.holdings.simplepingapp.cache.PingResultCacheManager;
import com.docler.holdings.simplepingapp.reporting.Report;
import com.docler.holdings.simplepingapp.reporting.ReportFactory;
import com.docler.holdings.simplepingapp.reporting.ReportSender;

/**
 * 
 * Abstract ping service
 *
 */
public abstract class AbstractPingServiceTask extends TimerTask implements IPingService {

	private static final String PING_RESULT = " result: ";

	private static final String FOR_HOST = " for host: ";

	private static final String COMMAND = " command: ";

	private static final String PING_WITH = "Ping with ";

	protected static final String PING_ERROR = "Unable to ping with protocol: ";

	protected static final String SPACE = " ";
	protected static final String WINDOWS = "Windows";
	protected static final String OS_NAME = "os.name";

	protected final Logger logger = Logger.getLogger(getClass());

	protected transient String url;

	/**
	 * Default constructor
	 */
	public AbstractPingServiceTask() {
		super();
	}

	/**
	 * Constructor with params
	 */
	public AbstractPingServiceTask(String url) {
		super();
		this.url = url;
	}

	@Override
	public String ping(String url) {
		String result = "";
		try {
			String pingCmd = getPingCommand(url);
			logger.info(PING_WITH + getProtocol() + COMMAND + pingCmd);
			result = processPingCommand(pingCmd);
		} catch (Exception e) {
			logger.error(PING_ERROR + getProtocol() + FOR_HOST + url, e);
			// Publish report
			publishReport(url);
		}

		// Save report
		savePingResult(url, result);
		logger.info(PING_WITH + getProtocol() + PING_RESULT + result);
		return result;
	}

	/**
	 * Process ping command
	 * 
	 * @param pingCmd
	 * @return a String of ping result
	 * @throws InterruptedException
	 * @throws IOException
	 */
	protected abstract String processPingCommand(String pingCmd) throws IOException, InterruptedException;

	/**
	 * Get ping command for a protocol
	 * 
	 * @param url
	 * @return a String
	 */
	protected abstract String getPingCommand(String url);

	/**
	 * Publish the report to an url
	 * 
	 * @param url
	 */
	protected void publishReport(String url) {
		String icmpPing = "";
		PingResult icmpResult = PingResultCacheManager.INSTANCE.getFromIcmpCache(url);
		if (icmpResult != null) {
			icmpPing = icmpResult.getPingResult();
		}

		String tcpPing = "";
		PingResult tcpResult = PingResultCacheManager.INSTANCE.getFromTcpIpCache(url);
		if (tcpResult != null) {
			tcpPing = tcpResult.getPingResult();
		}

		String traceRoute = "";
		PingResult traceResult = PingResultCacheManager.INSTANCE.getFromTraceCache(url);
		if (tcpResult != null) {
			traceRoute = traceResult.getPingResult();
		}

		Report reportToSend = new Report(url, icmpPing, tcpPing, traceRoute);
		String jsonReport = ReportFactory.INSTANCE.toJsonFormat(reportToSend);

		ReportSender.INSTANCE.post(url, jsonReport);
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Save report to cache
	 * 
	 * @param url
	 * @param result
	 */
	protected abstract void savePingResult(String url, String result);

	/**
	 * Create an ping report
	 * 
	 * @param url
	 * @param result
	 * @return
	 */
	protected PingResult createPingResult(String url, String result) {
		PingResult pingResult = new PingResult();
		pingResult.setPingDate(new Date());
		pingResult.setPingResult(result);
		return pingResult;
	}

	/**
	 * Run thread
	 */
	@Override
	public void run() {
		ping(url);
	}

	/**
	 * Get the ping protocol
	 * 
	 * @return a String
	 */
	protected abstract String getProtocol();
}
