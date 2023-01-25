package com.practice.demo.componnet;

import org.springframework.stereotype.Component;

@Component
public class PrintNumbersComponentImpl {
	public int count;
	public int number;
	 
	 public void printEventNo() throws InterruptedException {
		 synchronized (this) {
			 while(count < number) {
				 if(count%2 != 0) {
					 wait();
				 }
				 System.out.format("Thread: %s is executing and Count is: %s \n", Thread.currentThread().getName(), count);
				 count++;
				 notify();
			 }
		}
		
	 }
	 
	 public void printOddNo() throws InterruptedException{
		synchronized (this) {
			 while(count < number) {
				 if(count%2 == 0) {
					 wait();
				 }
				 System.out.format("Thread: %s is executing and Count is: %s \n", Thread.currentThread().getName(), count);
				 count++;
				 notify();
			 }
		}
	 }
	 
	 public void printAllNumbers() {
		 while(count <= number) {
			 System.out.format("Thread: %s is executing and Count is: %s \n", Thread.currentThread().getName(), count);
			 count++;
		 }
	 }


}
