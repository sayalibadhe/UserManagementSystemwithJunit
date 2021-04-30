package com.poc6;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc6.model.ResponseModel;
import com.poc6.model.User;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserMethodsTest {
	
private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper objm = new ObjectMapper();
	
	@BeforeAll
	public void setUp()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).dispatchOptions(true).build();
	}
	
	 @Test
	 public void addUserTest() throws Exception { 
		  User user=new User();
		  user.setFname("Pooja");
		  user.setLname("Sharma");
		  user.setContact("9930310500");
		  user.setCity("Kalva");
		  user.setCountry("India");
		  user.setPincode("421200");
		  user.setEmail("pooja@gmail.com");
		  user.setState("maharashtra");
		  String JsonRequest= objm.writeValueAsString(user);
		
		  MvcResult result = mockMvc .perform(
		  post("/users/add").content(JsonRequest).contentType(
		  MediaType.APPLICATION_JSON_VALUE)) .andExpect(status().isOk()).andReturn();
		 
		 
		 
		  String resultContext =result.getResponse().getContentAsString(); 
		  ResponseModel response=objm.readValue(resultContext, ResponseModel.class);
		  Assertions.assertTrue(response.isSuccess() == Boolean.TRUE);
		  Assertions.assertEquals("Success",response.getStatus()); 
		  }
	 
	 @Test
	 public void getAllUsersTest() throws Exception{
		 MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.get("/users/getAllUser")
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			ResponseModel response = objm.readValue(resultContent, ResponseModel.class);
			Assertions.assertTrue(response.isSuccess() == true);
	 }
	 
	 @Test
	 
	public void getUserByIdTest() throws Exception{
		 int id=2;
		 MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.get("/users/getUser/" + id)
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			ResponseModel response = objm.readValue(resultContent, ResponseModel.class);
			Assertions.assertTrue(response.isSuccess() == true);
	 }
	 @Test
	 
		public void getUserByfnameTest() throws Exception{
			 String name="sayali";
			 MvcResult result = mockMvc
						.perform(
								MockMvcRequestBuilders.get("/users/getUserByName/" + name)
										.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
				String resultContent = result.getResponse().getContentAsString();
				ResponseModel response = objm.readValue(resultContent, ResponseModel.class);
				Assertions.assertTrue(response.isSuccess() == true);
		 }
	 @Test
	 public void getUserBypincodeTest() throws Exception{
		 String pincode="421204";
		 MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.get("/users/getUserByPincode/" + pincode)
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			ResponseModel response = objm.readValue(resultContent, ResponseModel.class);
			Assertions.assertTrue(response.isSuccess() == true);
	 }
	 
	 @Test
	 
		public void updateUserTest() throws Exception{
		 
			User user=new User();
			int id=5;
			//user.setId(9);
			user.setLname("badhe");
			user.setFname("dhruv");
			user.setContact("9930370089");
			user.setCity("thane");
			user.setCountry("India");
			user.setPincode("421208");
			user.setState("Maharashtra");
			user.setEmail("dhruv@gmail.com");
		    String JsonRequest= objm.writeValueAsString(user);
			
		    MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.put("/users/updateUser/"+id).content(JsonRequest)
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContext = result.getResponse().getContentAsString();
			
			ResponseModel response = objm.readValue(resultContext, ResponseModel.class);
			Assertions.assertTrue(response.isSuccess() == true);
	 }
	 
	 @Test
	 
	public void deleteUserTest() throws Exception{
		 int id=9;
		 
		 MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.delete("/users/delete/"+id)
									.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			ResponseModel response = objm.readValue(resultContent, ResponseModel.class);
			Assertions.assertTrue(response.isSuccess() == true);
	 }
}
