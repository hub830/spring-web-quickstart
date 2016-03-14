package sample.web.ui.service;

//import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.web.ui.SampleWebUiApplication;
import sample.web.ui.domain.Proxy;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleWebUiApplication.class)
public class ProxyServiceTest {

	@Autowired
	ProxyService service;
	
	Proxy proxy;
	
	@Before
	public void setUp() throws Exception {
		proxy = new Proxy("127.0.0.1",8080);
		boolean exists = service.exists(proxy.getIp());
		if(!exists) {
			proxy = service.save(proxy);
		}else {
			proxy = service.findByIP("127.0.0.1");
		}
	}


	@Test
	public void testSave() {
		Proxy proxy = new Proxy("127.0.0.1",8080);
		boolean exists = service.exists(proxy.getIp());
		if(!exists) {
			proxy = service.save(proxy);
			assertNotNull(proxy);
		}
	}

	@Test
	public void testUpdate() {
		proxy.setUpdatetime(new Date());
		proxy = service.save(proxy);
		
	}

	@Test
	public void testFindByIP() {
		proxy = service.findByIP("127.0.0.1");
		assertEquals("127.0.0.1", proxy.getIp());
	}
	

	@Test
	public void testProxys() {
		
	}
	
	

	public static boolean testProxy(Proxy proxy) {
		HttpHost proxyHttpHost = new HttpHost(proxy.getIp(), proxy.getPort());
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHttpHost);
		CloseableHttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		try {
			
			// 创建httpget.
			HttpGet httpget = new HttpGet("http://www.baidu.com/");
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println(response.getStatusLine());
				return true;
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
