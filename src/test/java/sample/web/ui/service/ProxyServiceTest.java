package sample.web.ui.service;

//import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import java.util.Date;

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
		proxy = new Proxy("127.0.0.1","8080");
		boolean exists = service.exists(proxy.getIp());
		if(!exists) {
			proxy = service.save(proxy);
		}else {
			proxy = service.findByIP("127.0.0.1");
		}
	}


	@Test
	public void testSave() {
		Proxy proxy = new Proxy("127.0.0.1","8080");
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
}
