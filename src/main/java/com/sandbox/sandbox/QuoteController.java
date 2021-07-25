package com.sandbox.sandbox;
import com.sandbox.sandbox.models.quote.Quote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {
	private static final String TEMPLATE_URL 
		= "https://gturnquist-quoters.cfapps.io/api/random";
	@GetMapping("/quote")
	public Quote quote(
		@RequestParam(name = "source", 
			defaultValue = "https://gturnquist-quoters.cfapps.io/api/random") 
			String source) {
		String url;
		if (source.equals("expensive") || source.equals("1")) {
			url = "https://quotes.rest/qod?language=en";
		} else {
			url = TEMPLATE_URL;
		}
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Quote.class);
	}
}
