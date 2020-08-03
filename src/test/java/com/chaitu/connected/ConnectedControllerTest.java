package com.chaitu.connected;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ConnectedControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void emptySourceAndDestination() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("No")));
	}
	
	@Test
	public void inValidSource() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Boston1").param("destination", "New York").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("No")));
	}
	
	@Test
	public void inValidDestination() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Boston").param("destination", "NewYork").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("No")));
	}
	
	@Test
	public void sameSourceAndDestination() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Boston").param("destination", "Boston").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("Yes")));
	}

	@Test
	public void connectedDirectly() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Boston").param("destination", "New York").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("Yes")));
	}
	
	@Test
	public void connectedViaAnotherCity() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Boston").param("destination", "Philadelphia").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("Yes")));
	}

	@Test
	public void notConnected() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Philadelphia").param("destination", "Albany").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.equalTo("No")));
	}
	
}