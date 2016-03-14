package sample.web.ui;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert.*;

import io.undertow.util.FileUtils;

public class TestCHY {
	private static final Logger log = LoggerFactory.getLogger(TestCHY.class);

	// @Test
	public void testLogin() throws Exception {
		try {
			InputStream is = new FileInputStream("C:/Users/fbi/git/spring-web-quickstart/src/main/resources/public/templates/flat/test/1.html");
			String content = FileUtils.readFile(is);

			Parser parser = new Parser(content);

			// 这里是控制测试的部分，后面的例子修改的就是这个地方。
			NodeFilter filter = new TagNameFilter("OPTION");
			NodeList nodes = parser.extractAllNodesThatMatch(filter);

			if (nodes != null) {
				for (int i = 0; i < nodes.size(); i++) {
					Node textnode = (Node) nodes.elementAt(i);
					System.out.println(textnode.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testCleaner() throws Exception {
		HtmlCleaner cleaner = new HtmlCleaner();
		InputStream is = new FileInputStream("C:/Users/fbi/git/spring-web-quickstart/src/main/resources/public/templates/flat/test/1.html");
		String content = FileUtils.readFile(is);
		TagNode tn = cleaner.clean(content);
		String xpath = ".//*[@id='contable']/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/select/option[3]";
		Object[] objarr = null;
		objarr = tn.evaluateXPath(xpath);

		if (objarr != null && objarr.length > 0) {
			for (Object obj : objarr) {
				TagNode tntr = (TagNode) obj;
				String value = tntr.getAttributeByName("value");

				log.info(value);

			}

		}
	}
	

//	@Test
	public void testSign() throws Exception {
		HtmlCleaner cleaner = new HtmlCleaner();
		InputStream is = new FileInputStream("C:/Users/fbi/git/spring-web-quickstart/src/main/resources/public/templates/flat/test/3.html");
		String content = FileUtils.readFile(is);
		TagNode tn = cleaner.clean(content);
		String xpath = "//table[@id='contable']//img";
		Object[] objarr = null;
		objarr = tn.evaluateXPath(xpath);

		if (objarr != null && objarr.length > 0) {
			for (Object obj : objarr) {
				TagNode tntr = (TagNode) obj;
				String value = tntr.getAttributeByName("src");

				log.info(value);

			}

		}
	}
	@Test
	public void testMd5() {
		String string = md5("123456");

		assertEquals(string.toUpperCase(),"49BA59ABBE56E057");
		
	}

	private static String md5(String msg) {
		String md5 = DigestUtils.md5Hex(msg);
		return md5.substring(8, 24);
	}
}
