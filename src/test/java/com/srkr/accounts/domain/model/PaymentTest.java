package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class PaymentTest {

	@Test
	public void TestPayment() {

		Accounts accounts = new Accounts("Accounts Name", "Accounts Description", new AccountTypes("AccountTypesName",
				new AccountCategory("AccountCategoryName", "AccountCategoryDescription"), "AccountTypesDescription"));
		Payment payment = new Payment(new Long(328912), "A-092678", 5809.00,
				new Date((new GregorianCalendar(2019, 2, 1)).getTimeInMillis()), false,
				new Date((new GregorianCalendar(2019, 2, 8)).getTimeInMillis()), accounts, true,
				new Organisation("DEFAULT", "DEFAULT"));
		assertNotNull(payment);
		assertNotNull(payment.accounts());
		assertEquals(accounts, payment.accounts());
		assertEquals(new Long(328912), payment.id());
		assertEquals("A-092678", payment.billNUmber());
		assertEquals(new Double(5809.00), payment.amount());
		assertEquals(new Date((new GregorianCalendar(2019, 2, 1)).getTimeInMillis()), payment.paymentDate());
		assertEquals(false, payment.getIsActive());
		assertEquals(new Date((new GregorianCalendar(2019, 2, 8)).getTimeInMillis()), payment.dateUpdated());
		assertEquals(true, payment.getIsNew());

	}
}
