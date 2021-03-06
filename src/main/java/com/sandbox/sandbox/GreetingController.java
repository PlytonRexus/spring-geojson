package com.sandbox.sandbox;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s of age %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(
        @RequestParam(value = "name", defaultValue = "World") String name,
        @RequestParam(value = "age", defaultValue = "18") int age) {
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name, age));
	}
}