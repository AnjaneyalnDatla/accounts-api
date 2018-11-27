package com.srkr.accounts.domain.model.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.HeaderTypes;
import com.srkr.accounts.domain.model.postgres.Headers;

@Component
public class HeadersMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	public Headers toPostgresObject(com.srkr.accounts.domain.model.Headers headers) {

		Headers pgHeaders = new Headers();
		pgHeaders.setId(headers.id());
		pgHeaders.setHeaderdate(headers.headerdate());
		pgHeaders.setHeadernumber(headers.headernumber());
		HeaderTypes pgHeaderTypes = new HeaderTypes();
		pgHeaderTypes.setId(headers.headerTypes().id());
		pgHeaderTypes.setName(headers.headerTypes().name());
		pgHeaderTypes.setDescription(headers.headerTypes().description());
		pgHeaders.setHeaderTypes(pgHeaderTypes);
		pgHeaders.setAccounts(accountsMapper.toPostgresObject(headers.accounts()));
		return pgHeaders;

	}

	public com.srkr.accounts.domain.model.Headers toDomainObject(Headers pgHeaders) {
		com.srkr.accounts.domain.model.Headers headers = new com.srkr.accounts.domain.model.Headers(pgHeaders.getId(),
				pgHeaders.getHeadernumber(), pgHeaders.getHeaderdate(),
				new com.srkr.accounts.domain.model.HeaderTypes(pgHeaders.getHeaderTypes().getId(),
						pgHeaders.getHeaderTypes().getName(), pgHeaders.getHeaderTypes().getDescription()),
				accountsMapper.toDomainObject(pgHeaders.getAccounts()),null);

		return headers;
	}

}
