package sample.web.ui.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Proxy {
	private String ip;
	private int port;
	
	
	public static Proxy valueOf(String proxy) {
		String[] p = proxy.split(":");
		return new Proxy(p[0],Integer.parseInt(p[1]));
	}
}
