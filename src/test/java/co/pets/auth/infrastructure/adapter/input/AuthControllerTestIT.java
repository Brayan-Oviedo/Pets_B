package co.pets.auth.infrastructure.adapter.input;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.pets.application.domain.exceptions.Messages;
import co.pets.application.domain.exceptions.ResException;
import co.pets.auth.application.domain.jwt.JwtDto;
import co.pets.auth.application.domain.user.BUser;
import co.pets.auth.application.domain.user.NewUser;
import co.pets.auth.application.service.jwt.JwtProvider;

@SpringBootTest
public class AuthControllerTestIT {
	
	@Autowired
    protected WebApplicationContext webApplicationContext;
	
	@Autowired
	private JwtProvider provider;
 
    private MockMvc mockMvc;
    private BUser u;
    private NewUser newU;
    private ObjectMapper objectMapper;
    private String token;
    
    @BeforeEach
    public void init() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	
    	objectMapper = new ObjectMapper();
    	u = new BUser("Fan", "123");
    	newU = new NewUser();
    	BeanUtils.copyProperties(u, newU);
    	newU.setName("");
    	token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJGYW50YXNtYSIsInJvbHMiOltdLCJpYXQiOjE2MjMxOTA0ODAsImV4cCI6MTYyMzE5MDUwMH0."
				+ "HPQImubptE_ariS2hK7xWM0VqmEQkJIdiQvsPDQ6hvlOtFJUTeNQYE2qY5-KNWgFdKalqLjG5bIc4y_yMNpEdg";
    }
    
    @Test
    public void testRegisterUserWithoutException() throws Exception {
    	String jsonRequest = objectMapper.writeValueAsString(newU);
    	
    	mockMvc.perform(
    			post("/auth/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isOk()).andReturn(); 
    }
    
    @Test
    public void testRegisterUserWithUserExisting() throws Exception { 
    	String jsonRequest = objectMapper.writeValueAsString(newU);
    	
    	mockMvc.perform(
    			post("/auth/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isBadRequest()).andReturn(); 
    }
    
    @Test
    public void testLoginUserWithoutException() throws Exception {
    	String jsonRequest = objectMapper.writeValueAsString(u);
    	
    	MvcResult response = mockMvc.perform(
    			post("/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isOk())
    			.andReturn(); 
    	
    	String resultContent = response.getResponse().getContentAsString();
    	JwtDto result = objectMapper.readValue(resultContent, JwtDto.class);
    	
    	assertTrue(provider.validateToken(result.getToken()));;
    }
    
    @Test
    public void testLoginUserNotExisting() throws Exception {
    	u.setUserName("");
    	
    	String jsonRequest = objectMapper.writeValueAsString(u);
    	
    	MvcResult response = mockMvc.perform(
    			post("/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isBadRequest())
    			.andReturn(); 
    	
    	String resultContent = response.getResponse().getContentAsString();
    	ResException result = objectMapper.readValue(resultContent, ResException.class);
    	
    	assertEquals(Messages.MESSAGE_USER_NO_EXISTING, result.getMssg());;
    }
    
    @Test
    public void testLoginPasswordWrong() throws Exception {
    	u.setPassword("");
    	
    	String jsonRequest = objectMapper.writeValueAsString(u);
    	
    	MvcResult response = mockMvc.perform(
    			post("/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isBadRequest())
    			.andReturn(); 
    	
    	String resultContent = response.getResponse().getContentAsString();
    	ResException result = objectMapper.readValue(resultContent, ResException.class);
    	
    	assertEquals(Messages.MESSAGE_PASSWORD_WRONG, result.getMssg());;
    }
    
    @Test
    public void testRefreshTokenWithoutException() throws Exception {
    	String jsonRequest = objectMapper.writeValueAsString(new JwtDto(token));
    	
    	MvcResult response = mockMvc.perform(
    			post("/auth/refreshtoken").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isOk())
    			.andReturn(); 
    	
    	String resultContent = response.getResponse().getContentAsString();
    	JwtDto result = objectMapper.readValue(resultContent, JwtDto.class);
    	
    	assertTrue(provider.validateToken(result.getToken()));
    }
    
    @Test
    public void testRefreshTokenWithBadToken() throws Exception {
    	String jsonRequest = objectMapper.writeValueAsString(new JwtDto(token + "...."));
    	
    	MvcResult response = mockMvc.perform(
    			post("/auth/refreshtoken").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    			.andExpect(status().isBadRequest())
    			.andReturn(); 
    	
    	String resultContent = response.getResponse().getContentAsString();
    	ResException result = objectMapper.readValue(resultContent, ResException.class);
    	
    	assertNotNull(result);
    }
    
}
