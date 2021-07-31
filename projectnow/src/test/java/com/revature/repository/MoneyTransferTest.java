package com.revature.repository;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MoneyTransferTest {
	@Test
	void send() {
		JdbcMoneyTransferRepository bcn= new JdbcMoneyTransferRepository();
		
		assertThrows(AmountRangeException.class, () -> bcn.send(-10) );
		
		
	}
}
