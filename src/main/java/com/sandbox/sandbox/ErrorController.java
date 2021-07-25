/** 
 * Configuration not yet complete
 */

package com.sandbox.sandbox;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	@GetMapping("/error")
	public String error() {
		return "Error: Oops! Something went wrong. Try again later?";
	}
}