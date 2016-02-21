package com.docler.holdings.simplepingapp.ping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.docler.holdings.simplepingapp.cache.PingResultCacheManager;
import com.docler.holdings.simplepingapp.reporting.ReportFactory;

/**
 * 
 * TCP/IP protocol ping service
 *
 */
public final class TcpIpPingServiceTask extends AbstractPingServiceTask implements IPingService {

	private static final String HTTP_CODE = "http_code";
	private static final String RESPONSE_TIME = "response_time";
	private static final String HOST = "host";
	private static final String HEAD = "HEAD";
	private static final String HTTP = "http://";
	private static final String TCP_IP_PING_RESULT = "TCP/IP ping result : ";
	private static final String TCP_IP_PING = "TCP/IP ping url : ";
	private transient int timeout;
	private transient long responseTimeMax;

	/**
	 * Default constructor
	 */
	public TcpIpPingServiceTask() {
		super();
	}

	/**
	 * Constructor with params
	 * 
	 * @param url
	 * @param timeout
	 * @param responseTimeMax
	 */
	public TcpIpPingServiceTask(String url, int timeout, long responseTimeMax) {
		super(url);
		this.timeout = timeout;
		this.responseTimeMax = responseTimeMax;
	}

	@Override
	public String ping(String url) {
		String result = "";

		long timeToRespond = -1L;
		Date start, stop;

		logger.info(TCP_IP_PING + url);
		try {
			String httpURL = HTTP + url;
			start = new Date();
			HttpURLConnection con = (HttpURLConnection) new URL(httpURL).openConnection();
			con.setConnectTimeout(timeout);
			con.setReadTimeout(timeout);
			con.setRequestMethod(HEAD);
			con.connect();

			// Response time
			stop = new Date();
			timeToRespond = (stop.getTime() - start.getTime());
			if (timeToRespond > (long) responseTimeMax) {
				// Publish report
				publishReport(url);
			}

			result = computeResponse(url, timeToRespond, con);
		} catch (IOException e) {
			logger.error(PING_ERROR + url, e);
			// Publish report
			publishReport(url);
		}

		// Save report
		savePingResult(url, result);

		logger.info(TCP_IP_PING_RESULT + result);
		return result;
	}

	private String computeResponse(String url, long timeToRespond, HttpURLConnection con) throws IOException {
		String result;
		int responseCode = con.getResponseCode();
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put(HOST, url);
		responseMap.put(RESPONSE_TIME, String.valueOf(timeToRespond));
		responseMap.put(HTTP_CODE, String.valueOf(responseCode));
		result = ReportFactory.INSTANCE.toJsonFormat(responseMap);
		return result;
	}

	@Override
	public void run() {
		ping(url);
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the responseTimeMax
	 */
	public long getResponseTimeMax() {
		return responseTimeMax;
	}

	/**
	 * @param responseTimeMax
	 *            the responseTimeMax to set
	 */
	public void setResponseTimeMax(long responseTimeMax) {
		this.responseTimeMax = responseTimeMax;
	}

	@Override
	protected void savePingResult(String url, String result) {
		PingResultCacheManager.INSTANCE.putToTcpIpCache(url, createPingResult(url, result));
	}

}
