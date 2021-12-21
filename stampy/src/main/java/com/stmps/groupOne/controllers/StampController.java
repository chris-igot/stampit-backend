package com.stmps.groupOne.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StampController {
	@PostMapping("/click")
	public ResponseEntity<Object> getClickyWithIt(@RequestParam("x") Integer x,@RequestParam("y") Integer y,
			@RequestParam("boxDimY") Integer boxDimY, @RequestParam("boxDimX") Integer boxDimX) {
		Object obj = new Object();
		
		System.out.println("Click!");
		System.out.println(x);
		System.out.println(y);
		System.out.println(boxDimX);
		System.out.println(boxDimY);
		
		return ResponseEntity.ok().body(obj);

	}
}
