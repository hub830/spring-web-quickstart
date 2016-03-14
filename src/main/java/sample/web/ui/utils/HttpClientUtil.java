package sample.web.ui.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private final static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

  /*
	*//**
	 * 发送 get请求
	 *//*
	public static String get(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			log.info("executing get request " + httpget.getURI());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				log.info("Response Status:{}",response.getStatusLine());
				if (entity != null) {
					log.info("Response content length:{}", entity.getContentLength());
					String content = EntityUtils.toString(entity);
					log.info("Response content:{}", content);
					return content;
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			log.error("",e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error("",e);
			}
		}
		return null;
	}


	*//**
	 * 发送 get请求
	 *//*
	public static String get(String url,String proxy) {
		Proxy p = Proxy.valueOf(proxy);
		HttpHost httpHostProxy = new HttpHost(p.getIp(),p.getPort());
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpHostProxy);
		CloseableHttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		
		try {
			HttpGet httpget = new HttpGet(url);
			log.info("executing get request " + httpget.getURI());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				log.info("Response Status:{}",response.getStatusLine());
				if (entity != null) {
					log.info("Response content length:{}", entity.getContentLength());
					String content = EntityUtils.toString(entity);
					log.info("Response content:{}", content);
					return content;
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			log.error("",e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error("",e);
			}
		}
		return null;
	}
	

    public static String post(String url,List<NameValuePair> params) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(params, "UTF-8");  
            httppost.setEntity(uefEntity);  
            log.info("executing post request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {
                	String content = EntityUtils.toString(entity, "UTF-8");
					log.info("Response content:{}", content);
                    return  content;  
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
			log.error("",e);
        }  finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
				log.error("",e); 
            }  
        }
        return null;
    }  

    public static String post(String url,List<NameValuePair> params,String proxy) {

		Proxy p = Proxy.valueOf(proxy);
		HttpHost httpHostProxy = new HttpHost(p.getIp(),p.getPort());
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpHostProxy);
		CloseableHttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		
        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(params, "UTF-8");  
            httppost.setEntity(uefEntity);  
            log.info("executing post request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {
                	String content = EntityUtils.toString(entity, "UTF-8");
					log.info("Response content:{}", content);
                    return  content;  
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
			log.error("",e);
        }  finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
				log.error("",e); 
            }  
        }
        return null;
    }

    public static String post(String url,List<NameValuePair> params,String proxy,String cookie) {

		Proxy p = Proxy.valueOf(proxy);
		HttpHost httpHostProxy = new HttpHost(p.getIp(),p.getPort());
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpHostProxy);
		CloseableHttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();

        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(params, "UTF-8");  
            httppost.setEntity(uefEntity);  
            log.info("executing post request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {
                	String content = EntityUtils.toString(entity, "UTF-8");
					log.info("Response content:{}", content);
                    return  content;  
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
			log.error("",e);
        }  finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
				log.error("",e); 
            }  
        }
        return null;
    }*/
    
    private static CookieStore getCookieStore(String cookie) {
    	CookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie basicClientCookie = new BasicClientCookie("JSESSIONID", cookie);
		basicClientCookie.setVersion(0);
		basicClientCookie.setDomain(".fwjy1.bjchy.gov.cn");
		basicClientCookie.setPath("/");
		cookieStore.addCookie(basicClientCookie);
		return cookieStore;
    }
    
    private static String getCookie(HttpResponse httpResponse) {
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
		return JSESSIONID;
    }
    
    public static Response post(String url,String proxy,String cookie,List<NameValuePair> params) {
		HttpClientBuilder builder = HttpClients.custom();
		if(proxy!=null) {
			Proxy p = Proxy.valueOf(proxy);
			HttpHost httpHostProxy = new HttpHost(p.getIp(),p.getPort());
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpHostProxy);
			builder.setRoutePlanner(routePlanner);
		}
		if(cookie!=null) {
			CookieStore cookieStore = getCookieStore(cookie);
			builder.setDefaultCookieStore(cookieStore);
		}
		CloseableHttpClient httpclient = builder.build();
		

        HttpPost httppost = new HttpPost(url);
        try { 
        	if(params!=null) {
            	UrlEncodedFormEntity uefEntity;
                uefEntity = new UrlEncodedFormEntity(params, "UTF-8");  
                httppost.setEntity(uefEntity);  
        	}
            log.info("executing post request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);
            String JSESSIONID = getCookie(response);
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {
                	String content = EntityUtils.toString(entity, "UTF-8");
					log.info("Response content:{}", content);
                    return  new Response(JSESSIONID,content);  
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
			log.error("",e);
        }  finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
				log.error("",e); 
            }  
        }
        return null;
    }

    public static Response get(String url,String proxy,String cookie) {
		HttpClientBuilder builder = HttpClients.custom();
		if(proxy!=null) {
			Proxy p = Proxy.valueOf(proxy);
			HttpHost httpHostProxy = new HttpHost(p.getIp(),p.getPort());
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpHostProxy);
			builder.setRoutePlanner(routePlanner);
		}
		if(cookie!=null) {
			CookieStore cookieStore = getCookieStore(cookie);
			builder.setDefaultCookieStore(cookieStore);
		}
		CloseableHttpClient httpclient = builder.build();
		

		HttpGet httpget = new HttpGet(url);
        try { 
            log.info("executing post request " + httpget.getURI());  
            CloseableHttpResponse response = httpclient.execute(httpget);
            String JSESSIONID = getCookie(response);
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {
                	String content = EntityUtils.toString(entity, "UTF-8");
					log.info("Response content:{}", content);
                    return  new Response(JSESSIONID,content);  
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
			log.error("",e);
        }  finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
				log.error("",e); 
            }  
        }
        return null;
    }
	public static void main(String[] args) {
//		String string = get("http://www.baidu.com","101.200.202.168:80");
//		String string = post("http://www.baidu.com",new ArrayList<NameValuePair>(),"101.200.202.168:80");
		get("http://www.baidu.com","101.200.202.168:80",null);
	}
}
