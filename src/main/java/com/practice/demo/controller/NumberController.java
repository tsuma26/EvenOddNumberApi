package com.practice.demo.controller;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.componnet.PrintNumbersComponentImpl;

@RestController
@RequestMapping("/numberPrinting")
public class NumberController {

	private PrintNumbersComponentImpl printNum;

	@Autowired
	public NumberController(PrintNumbersComponentImpl printNum) {
		this.printNum = printNum;
	}

	@GetMapping("/singleThread/{number}")
	public ResponseEntity<String> printSingleThread(@PathVariable int number) {
		Instant start = Instant.now();
		printNum.number = number;
		printNum.count = 1;
		printNum.printAllNumbers();
		long timeElapsed = Duration.between(start, Instant.now()).toMillis();
		return new ResponseEntity<String>("Time taken using single thread: " + timeElapsed, HttpStatus.OK);
	}

	@GetMapping("/twoThread/{number}")
	public ResponseEntity<String> printUsingTwoThread(@PathVariable int number) {
		Instant start = Instant.now();
		printNum.number = number;
		printNum.count = 1;
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					printNum.printOddNo();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					printNum.printEventNo();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		long timeElapsed = Duration.between(start, Instant.now()).toMillis();
		return new ResponseEntity<String>("Time taken using 2 thread: " + timeElapsed, HttpStatus.OK);
	}

}
