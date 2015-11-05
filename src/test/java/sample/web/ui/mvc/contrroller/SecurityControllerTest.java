package sample.web.ui.mvc.contrroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sample.web.ui.SampleWebUiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SampleWebUiApplication.class, loader = SpringApplicationContextLoader.class)
public class SecurityControllerTest {


	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void test() throws Exception {
		testSignup();
		testLogin();
	}


//	@Test
	public void testSignup() throws Exception {
		this.mockMvc.perform(
				post("/signup").param("name", "test027")
				.param("email", "test@test.com")
				.param("password", "111111")
				.param("block", "false"))
		.andExpect(status().isFound())
		.andDo(MockMvcResultHandlers.print());
	}



//	@Test
	public void testLogin() throws Exception {
		this.mockMvc.perform(
				post("/login2").param("name", "test027")
				.param("password", "111111"))
		.andExpect(status().isFound())
		.andDo(MockMvcResultHandlers.print());
	}

}
