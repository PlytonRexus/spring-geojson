package com.sandbox.sandbox;

import com.sandbox.sandbox.models.quote.Quote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

	@GetMapping("/") @PostMapping("/") @PutMapping("/")
	public Quote home() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate
			.getForObject("https://gturnquist-quoters.cfapps.io/api/random", 
				Quote.class);
	}
}