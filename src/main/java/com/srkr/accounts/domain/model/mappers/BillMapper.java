package com.srkr.accounts.domain.model.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.Bill;
import com.srkr.accounts.domain.model.Organisation;

@Component
public class BillMapper {

	@Autowired
	PaymentsMapper paymentsMapper;

	public com.srkr.accounts.domain.model.postgres.Bill toPostgresObject(Bill aBill) {
		return new com.srkr.accounts.domain.model.postgres.Bill(aBill.id(), aBill.transaction_number(), null,
				aBill.billNumber(), aBill.amount(), aBill.bill_issued_date(), aBill.bill_payment_date(),
				this.paymentsMapper.toListPostgresObject(aBill.payments()), aBill.organisation().name(),
				aBill.organisation().code());

	}

	public Bill toDomainObject(com.srkr.accounts.domain.model.postgres.Bill pgBill) {
		return new Bill(pgBill.getId(), pgBill.getTransaction_number(), pgBill.getBillNumber(), pgBill.getAmount(),
				pgBill.getBill_issued_date(), pgBill.getBill_payment_date(),
				this.paymentsMapper.toListDomainObject(pgBill.getPayments()),
				new Organisation(pgBill.getOrgName(), pgBill.getOrgCode()));

	}

	public Set<Bill> toListDomainObject(Set<com.srkr.accounts.domain.model.postgres.Bill> pgBill) {
		if (pgBill == null)
			return null;

		return pgBill.stream().map((pgLt) -> toDomainObject(pgLt)).collect(Collectors.toSet());
	}

	public Set<com.srkr.accounts.domain.model.postgres.Bill> toListPostgresObject(Set<Bill> aList) {
		if (aList == null)
			return null;
		return aList.stream().map((lt) -> toPostgresObject(lt)).collect(Collectors.toSet());
	}

}
