package com.CSC340.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;




	@RestController
	public class RestApiController {


		@GetMapping("/summary")


		public Object getSummary(@RequestParam(value = "title", defaultValue = "Percy Jackson") String title) {
			try {

				String url = "https://en.wikipedia.org/api/rest_v1/page/summary/" + title;
                RestTemplate restTemplate = new RestTemplate();
				ObjectMapper mapper = new ObjectMapper();
				String jsonListResponse = restTemplate.getForObject(url, String.class);
				JsonNode root = mapper.readTree(jsonListResponse);


				Summary sum = new Summary(root.get("title").asText());
				return sum;
			} catch (JsonProcessingException ex) {
				Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE,
						null, ex);
				return "error in /summary";
			}

		}
	}



