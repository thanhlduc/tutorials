package com.docler.holdings.simplepingapp.cache;

import java.io.InputStream;

import org.apache.log4j.Logger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Cache for ping report
 * 
 */
public enum PingResultCacheManager {
	INSTANCE;

	public static final String ICMP_CACHE_NAME = "icmpCache";
	public static final String TCPIP_CACHE_NAME = "tcpipCache";
	public static final String TRACE_ROUTE_CACHE_NAME = "traceRouteCache";

	private CacheManager cacheManager;

	private Ehcache icmpCache;

	private Ehcache tcpipCache;

	private Ehcache traceRouteCache;

	private transient final Logger logger = Logger.getLogger(PingResultCacheManager.class);

	/**
	 * Default constructor
	 * 
	 */
	private PingResultCacheManager() {
		try {
			logger.info("Create new instance of ReportCacheManager");
			cacheManager = createCacheManager();
			icmpCache = createCache(ICMP_CACHE_NAME);
			tcpipCache = createCache(TCPIP_CACHE_NAME);
			traceRouteCache = createCache(TRACE_ROUTE_CACHE_NAME);
		} catch (Exception e) {
			logger.error("Cannot initiate an instance of ReportCacheManager", e);
		}
	}

	/**
	 * Loading ehcache config file and creating CacheManager
	 * 
	 * @return
	 */
	private CacheManager createCacheManager() {
		logger.info("Create CacheManager");
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		InputStream resourceAsStream = contextClassLoader.getResourceAsStream("ehcache.xml");
		return CacheManager.create(resourceAsStream);
	}

	/**
	 * Create cache with given name
	 */
	private Ehcache createCache(String cacheName) {
		logger.info("Create Ehcache with name: " + cacheName);
		Ehcache cache = cacheManager.getEhcache(cacheName);
		return cache;
	}

	/**
	 * Add new trace route result to Trace Route Cache
	 * 
	 * @param key
	 * @param pingResult
	 */
	public void putToTraceRouteCache(String key, PingResult pingResult) {
		Element element = new Element(key, pingResult);
		traceRouteCache.put(element);
	}

	/**
	 * Add new ping result to Icmp Cache
	 * 
	 * @param key
	 * @param pingResult
	 */
	public void putToIcmpCache(String key, PingResult pingResult) {
		Element element = new Element(key, pingResult);
		icmpCache.put(element);
	}

	/**
	 * Add new ping result to Tcp Cache
	 * 
	 * @param key
	 * @param pingResult
	 */
	public void putToTcpIpCache(String key, PingResult pingResult) {
		Element element = new Element(key, pingResult);
		tcpipCache.put(element);
	}

	/**
	 * Get a ping result from Icmp cache
	 * 
	 * @param key
	 * @return
	 */
	public PingResult getFromIcmpCache(String key) {
		return getFromCache(icmpCache, key);
	}

	/**
	 * Get a ping result from Trace route cache
	 * 
	 * @param key
	 * @return
	 */
	public PingResult getFromTraceRouteCache(String key) {
		return getFromCache(traceRouteCache, key);
	}

	private PingResult getFromCache(Ehcache cache, String key) {
		PingResult result = null;
		Element pingResult = cache.get(key);
		if (pingResult != null) {
			result = (PingResult) pingResult.getObjectValue();
		}
		return result;
	}

	/**
	 * Get a ping result from Icmp cache
	 * 
	 * @param key
	 * @return
	 */
	public PingResult getFromTcpIpCache(String key) {
		return getFromCache(tcpipCache, key);
	}

	public PingResult getFromTraceCache(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
