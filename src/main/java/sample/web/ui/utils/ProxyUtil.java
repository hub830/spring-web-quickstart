package sample.web.ui.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyUtil {
	private static final Logger log = LoggerFactory.getLogger(ProxyUtil.class);
	static String proxyUrl = "http://qsdrk.daili666api.com/ip/?tid=559494483853837&num=1&delay=1&foreign=none&filter=on";

	public static String getProxy() {
		log.info("获取代理");
		String proxy = HttpClientUtil.get(proxyUrl,null,null).getContent();
		log.info("取得的代理为:{}", proxy);
		return proxy;
	}

	public static void main(String[] args) {
		getProxy();
	}
}
