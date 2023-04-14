package com.manning.sbip.ch07;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.service.CourseService;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CourseTrackerApiApplicationTests {

	@Autowired
	private CourseService courseService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testPostCourse() throws Exception {
		Course course = Course.builder()
				.name("Rapid Spring Boot Application Development")
				.category("Spring")
				.rating(5)
				.description("Rapid Spring Boot Application Development").build();
		ObjectMapper objectMapper = new ObjectMapper();

		MockHttpServletResponse response = mockMvc.perform(post("/courses/")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(course)))
				.andDo(print())
				.andExpect(jsonPath("$.*", hasSize(5)))
				.andExpect(jsonPath("$.id", greaterThan(0)))
				.andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
				.andExpect(jsonPath("$.category").value("Spring"))
				.andExpect(jsonPath("$.rating").value(5))
				.andExpect(status().isCreated()).andReturn().getResponse();

		Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
		assertNotNull(courseService.getCourseById(id));


	}
	@Test
	void testInvalidCourseId() throws Exception {
		mockMvc.perform(get("/courses/{id}", 120))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

}
