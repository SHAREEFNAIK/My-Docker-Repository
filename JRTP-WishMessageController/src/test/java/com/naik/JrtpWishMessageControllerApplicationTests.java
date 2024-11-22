package com.naik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naik.controller.WishOperationController;
import com.naik.model.UserInfo;
import com.naik.service.WishMessageService;

//@SpringBootTest
@WebMvcTest(value=WishOperationController.class)
class JrtpWishMessageTestingApplicationTests {

	@MockBean
	private WishMessageService wishServive;
	@Autowired
	private MockMvc mockmvc;
	
	@Test
	public void testShowMessage() throws Exception {
		//create dummy mockobj and define its behavior
		Mockito.when(wishServive.showMessage()).thenReturn("Hello Naik");
		// create requestbuilder obj
		MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.get("/wishAPI/wish");
		// send request and receive result
		MvcResult result= mockmvc.perform(builder).andReturn();
		//collect responce from result
		MockHttpServletResponse response= result.getResponse();
		// collect body
		String message=response.getContentAsString();
		assertEquals("Hello Naik",message);
		System.out.println("test passed");
	}
	
	@Test
	public void test1RegisterUser() throws Exception{
		// create model class object
		UserInfo info =new UserInfo(1,"Naik","hyd") ;
		
		Mockito.when(wishServive.registerUser()).thenReturn("user registered");
		//convert object data into json data
		ObjectMapper mapper = new ObjectMapper();
		String jsonData= mapper.writeValueAsString(info);
		
		MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/wishAPI/save").contentType("application/json").content(jsonData);
		MvcResult result= mockmvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String msg= response.getContentAsString();
		
		//assertEquals(201, response.getStatus());
		assertEquals("user registered", msg);
		System.out.println(" second test case");
	}
	
	@Test
	public void test2RegisterUser() throws Exception{
		// create model class object
		UserInfo info =new UserInfo();
		
		Mockito.when(wishServive.registerUser()).thenReturn("not registered");
		//convert object data into json data
		ObjectMapper mapper = new ObjectMapper();
		String jsonData= mapper.writeValueAsString(info);
		
		MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/wishAPI/save").contentType("application/json").content(jsonData);
		MvcResult result= mockmvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String msg= response.getContentAsString();
		
		//assertEquals(201, response.getStatus());
		assertEquals("not registered", msg);
		System.out.println(" second test case");
	}
	
	
}
