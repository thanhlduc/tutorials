package com.docler.holdings.simplepingapp.reporting;

/**
 * 
 * Ping report
 *
 */
public class Report {

	private transient String hostName;
	private transient String icmpPing;
	private transient String tcpPing;
	private transient String traceRoute;

	/**
	 * Default constructor
	 */
	public Report() {

	}

	/**
	 * Constructor with params
	 */
	public Report(String hostName, String icmpPing, String tcpPing, String traceRoute) {
		this.hostName = hostName;
		this.icmpPing = icmpPing;
		this.tcpPing = tcpPing;
		this.traceRoute = traceRoute;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the icmpPing
	 */
	public String getIcmpPing() {
		return icmpPing;
	}

	/**
	 * @param icmpPing
	 *            the icmpPing to set
	 */
	public void setIcmpPing(String icmpPing) {
		this.icmpPing = icmpPing;
	}

	/**
	 * @return the tcpPing
	 */
	public String getTcpPing() {
		return tcpPing;
	}

	/**
	 * @param tcpPing
	 *            the tcpPing to set
	 */
	public void setTcpPing(String tcpPing) {
		this.tcpPing = tcpPing;
	}

	/**
	 * @return the traceRoute
	 */
	public String getTraceRoute() {
		return traceRoute;
	}

	/**
	 * @param traceRoute
	 *            the traceRoute to set
	 */
	public void setTraceRoute(String traceRoute) {
		this.traceRoute = traceRoute;
	}

}
