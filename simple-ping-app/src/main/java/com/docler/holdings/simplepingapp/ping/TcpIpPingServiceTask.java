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
public final class TcpIpPingServiceTask extends AbstractPingServiceTask {

	private static final String TCP_IP = "TCP/IP";
	private static final String HTTP_CODE = "http_code";
	private static final String RESPONSE_TIME = "response_time";
	private static final String HOST = "host";
	private static final String HEAD = "HEAD";
	private static final String HTTP = "http://";
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

	@Override
	protected String getProtocol() {
		return TCP_IP;
	}

	@Override
	protected String processPingCommand(String httpURL) throws IOException, InterruptedException {
		Date start = new Date();
		HttpURLConnection con = (HttpURLConnection) new URL(httpURL).openConnection();
		con.setConnectTimeout(timeout);
		con.setReadTimeout(timeout);
		con.setRequestMethod(HEAD);
		con.connect();

		// Response time
		Date stop = new Date();
		long timeToRespond = (stop.getTime() - start.getTime());
		if (timeToRespond > (long) responseTimeMax) {
			// Publish report
			publishReport(url);
		}

		String result = computeResponse(url, timeToRespond, con);
		return result;
	}

	@Override
	protected String getPingCommand(String url) {
		return HTTP + url;
	}

}
