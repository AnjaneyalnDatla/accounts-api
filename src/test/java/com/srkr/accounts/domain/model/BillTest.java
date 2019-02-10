package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BillTest {

	@Test
	public void TestBill() {
		Set<Payment> payments = new HashSet<Payment>();
		Payment payment = createPaymentObj();
		payments.add(payment);
		payments.add(payment);
		Bill bill = new Bill(new Long(123456), 26, "A1234567", 5000.00, createDateObj("2019-02-01"), createDateObj("2019-02-09"), payments);
		assertNotNull(bill);
		assertNotNull(bill.payments());
		assertNotNull( bill.bill_issued_date());
		assertNotNull(bill.bill_payment_date());
		assertEquals(new Long(123456), bill.id());
		assertEquals(new Integer(26), bill.transaction_number());
		assertEquals("A1234567", bill.billNumber());
		assertEquals(new Double(5000.00), bill.amount());
		assertEquals(createDateObj("2019-02-01"), bill.bill_issued_date());
		assertEquals(createDateObj("2019-02-09"), bill.bill_payment_date());
		//SET TEST
		assertSame(payments, bill.payments());
		assertEquals(1,bill.payments().size());
		
	}

	private Payment createPaymentObj() {
		Accounts accounts = new Accounts("Accounts Name", "Accounts Description", 
				new AccountTypes("AccountTypesName", 
					new AccountCategory("AccountCategoryName", "AccountCategoryDescription"), 
						"AccountTypesDescription"));
		Payment payment = new Payment(new Long(122343), "A678N1", 2999.00, createDateObj("2019-02-03"), true, new Date(), accounts, true);
		return payment;
	}

	private Date createDateObj(String sDate) {
		try {
			return new SimpleDateFormat("yyyy-mm-dd").parse(sDate);
		} catch (ParseException e) {
			return null;
		}
	}
}
