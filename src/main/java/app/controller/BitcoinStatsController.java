package app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinStatsController {

	@RequestMapping("/")
	public String test() {
		return "test me!";
	}
}
