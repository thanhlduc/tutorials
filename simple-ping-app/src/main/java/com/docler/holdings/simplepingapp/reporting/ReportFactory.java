package com.docler.holdings.simplepingapp.reporting;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

/**
 * 
 * Report creation
 *
 */
public enum ReportFactory implements IReportFactory {
	INSTANCE;

	private static final String HOST_NAME = "host";
	private static final String ICMP_PING = "icmp_ping";
	private static final String TCP_PING = "tcp_ping";
	private static final String TRACE_ROUTE = "trace";

	private ReportFactory() {

	}

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

	@SuppressWarnings("unchecked")
	@Override
	public String toJsonFormat(Map<String, String> valuesMap) {
		JSONObject json = new JSONObject();
		if (valuesMap != null) {
			Set<Entry<String, String>> entrySet = valuesMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				json.put(entry.getKey(), entry.getValue());
			}
		}
		return json.toString();
	}
}
