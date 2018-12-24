package com.yash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiIndoreController {

	@GetMapping("/hi")
	public String getHiIndore() {
		return "Hi Indore!!!!!!";
	}
}
