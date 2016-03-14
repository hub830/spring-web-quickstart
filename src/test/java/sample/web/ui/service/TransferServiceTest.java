package sample.web.ui.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.web.ui.SampleWebUiApplication;
import sample.web.ui.domain.Transfer;
import sample.web.utils.RandomNumberUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleWebUiApplication.class)
public class TransferServiceTest {
	Logger log = LoggerFactory.getLogger(TransferServiceTest.class);

	@Autowired
	TransferService service;
	
	Transfer transfer;
	
	@Before
	public void setUp() throws Exception {
		transfer = new Transfer("X京房权证朝字第574403号","C","1146822","00086139","齐春莲","110228197901232621","宋佳","130683198612297626","18300280857");
		transfer.setP_Appointment_testmsg(RandomNumberUtil.randomStringLong(5));
		service.save(transfer);
	}

	@Test
	public void testStep1() {
		transfer = service.step1(transfer.getId());
		log.info(transfer.getJ_captcha_response());
	}

//	@Test
	public void testStep2() {
		service.step2(transfer.getId(),"");
	}
	
//	@Test
	public void testFindOne() {
		transfer = service.findOne(1l);
		assertNotNull(transfer);
	}

//	@Test
	public void testFindUnprocessed() {
		List<Transfer> list = service.findUnprocessed();
		assertTrue(list.size()>0);
	}

}
