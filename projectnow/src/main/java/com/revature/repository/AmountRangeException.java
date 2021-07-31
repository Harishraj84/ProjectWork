package com.revature.repository;

public class AmountRangeException extends RuntimeException {
	public AmountRangeException(int amount) {
		System.out.println(amount);
	}

}
