package sample.web.ui.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
	private String cookie;
	private String content;
	/**
	 * @param cookie
	 * @param content
	 */
	public Response(String cookie, String content) {
		super();
		this.cookie = cookie;
		this.content = content;
	}
	
}
