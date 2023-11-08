package ru.karpov.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestApplicationTests {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void start()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getMainPageTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(view().name("main"))
				.andExpect(status().isOk());
	}

	@Test
	public void calculateTest() throws Exception {
		final Map<String, Integer> expectedResult = new HashMap<>();
		expectedResult.put("b", 4);
		expectedResult.put("c", 3);
		expectedResult.put("a", 2);
		mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
				.param("str", "aabbbbccc"))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("map"))
				.andExpect(model().attribute("map", expectedResult))
				.andExpect(view().name("result"))
				.andExpect(status().isOk());
	}



}
