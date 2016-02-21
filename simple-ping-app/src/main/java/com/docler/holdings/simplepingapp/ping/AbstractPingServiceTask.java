package com.docler.holdings.simplepingapp.ping;

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

	protected static final String PING_ERROR = "Unable to ping host: ";

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

	/**
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

}
