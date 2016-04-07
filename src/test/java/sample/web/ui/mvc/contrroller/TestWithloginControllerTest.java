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

			
			
<<<<<<< HEAD


			ResultActions actions = this.mockMvc.perform( get("/test/noLogin/login"));
//			ResultActions actions = this.mockMvc.perform(post("/login").param("name", "test027").param("password", "111111"));

	        MvcResult result = actions.andReturn();
	        
	        mockSession = (MockHttpSession)result.getRequest().getSession();
			this.mockMvc.perform(
					post("/test/login/test1").param("a", "1")
					.param("b", "1")
					.param("c", "c").session(mockSession))
			.andExpect(status().isFound())
			.andDo(MockMvcResultHandlers.print());
=======
>>>>>>> 679be7159a36d02451e3fcf8a33bd1e9c9db29a8
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
