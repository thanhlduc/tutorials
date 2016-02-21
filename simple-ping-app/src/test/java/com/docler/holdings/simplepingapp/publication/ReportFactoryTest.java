package com.docler.holdings.simplepingapp.publication;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import com.docler.holdings.simplepingapp.reporting.IReportFactory;
import com.docler.holdings.simplepingapp.reporting.Report;
import com.docler.holdings.simplepingapp.reporting.ReportFactory;

/**
 * 
 * Test for ReportFactory
 *
 */
public class ReportFactoryTest {
	private transient IReportFactory factory;

	@Before
	public void setUp() {
		factory = ReportFactory.INSTANCE;
	}

	@Test
	public void toJson_NullReport_ReturnEmptyJson() {
		Report report = null;
		assertThat(factory.toJsonFormat(report), is("{}"));
	}

	@Test
	public void toJson_CompleteReport_ReturnCompleteJson() {
		Report report = new Report();
		report.setHostName("host");
		report.setIcmpPing("icmp");
		report.setTcpPing("tcpip");
		report.setTraceRoute("trace");
		assertThat(factory.toJsonFormat(report),
				is("{\"trace\":\"trace\",\"host\":\"host\",\"icmp_ping\":\"icmp\",\"tcp_ping\":\"tcpip\"}"));
	}
}
