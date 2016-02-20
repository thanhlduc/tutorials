package com.docler.holdings.simplepingapp.reporting;

import org.json.simple.JSONObject;

/**
 * 
 * Report creation
 *
 */
public final class ReportFactory implements IReportFactory {

	private static final String HOST_NAME = "host";
	private static final String ICMP_PING = "icmp_ping";
	private static final String TCP_PING = "tcp_ping";
	private static final String TRACE_ROUTE = "trace";

	/**
	 * Create an json format string
	 * 
	 * @param report
	 * @return a string
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String toJsonFormat(Report report) {
		JSONObject json = new JSONObject();
		if (report != null) {
			json.put(HOST_NAME, report.getHostName());
			json.put(ICMP_PING, report.getIcmpPing());
			json.put(TCP_PING, report.getTcpPing());
			json.put(TRACE_ROUTE, report.getTraceRoute());
		}
		return json.toString();
	}
}
