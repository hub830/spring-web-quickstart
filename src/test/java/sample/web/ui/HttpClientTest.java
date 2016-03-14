package sample.web.ui;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static void main(String[] args) throws Exception {
		HttpClientTest test = new HttpClientTest();
		test.get();
	}

	/**
	 * 发送 get请求
	 */
	public void get() {
		HttpHost proxy = new HttpHost("101.200.202.168", 80);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		CloseableHttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		try {
			
			// 创建httpget.
			HttpGet httpget = new HttpGet("http://www.baidu.com/");
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println(response.getStatusLine());
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
	}

}