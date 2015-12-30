package sample.web.ui.mvc.contrroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sample.web.ui.AbstractShiroTest;
import sample.web.ui.SampleWebUiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SampleWebUiApplication.class, loader = SpringApplicationContextLoader.class)
public class TestWithloginControllerTest  extends AbstractShiroTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

    private Subject testSubject;

    protected MockHttpSession mockSession;

	@Before
	public void setup()   {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();


	}

	@Test
	public void testTest1()  {


		
		try {

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
