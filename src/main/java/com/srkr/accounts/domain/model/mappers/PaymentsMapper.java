package com.srkr.accounts.domain.model.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.Payment;

@Component
public class PaymentsMapper {

	@Autowired
	AccountsMapper accountsMapper;

	public com.srkr.accounts.domain.model.postgres.Payment toPostgresObject(Payment aPayment) {
		if (aPayment.getIsNew())
			return new com.srkr.accounts.domain.model.postgres.Payment(aPayment.id(), aPayment.billNUmber(), null,
					aPayment.amount(), aPayment.paymentDate(), aPayment.getIsActive(), aPayment.dateUpdated(),
					this.accountsMapper.toPostgresObject(aPayment.accounts()));
		else
			return null;
	}

	public Payment toDomainObject(com.srkr.accounts.domain.model.postgres.Payment pgPayment) {
		return new Payment(pgPayment.getId(), pgPayment.getBillNumber(), pgPayment.getAmount(),
				pgPayment.getPaymentDate(), pgPayment.getIsActive(), pgPayment.getDateUpdated(),
				this.accountsMapper.toDomainObject(pgPayment.getAccounts()), false);

	}

	public Set<Payment> toListDomainObject(Set<com.srkr.accounts.domain.model.postgres.Payment> pgList) {
		if (pgList == null)
			return null;

		return pgList.stream().map((pgLt) -> toDomainObject(pgLt)).collect(Collectors.toSet());
	}

	public Set<com.srkr.accounts.domain.model.postgres.Payment> toListPostgresObject(Set<Payment> aList) {
		if (aList == null)
			return null;
		return aList.stream().map((lt) -> toPostgresObject(lt)).collect(Collectors.toSet());
	}

}
