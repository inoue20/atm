package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

	@GetMapping("/atm-spa")
	public String get() {
//		model.addAttribute("acountController", acountController);
		return "atm";
	}
}
