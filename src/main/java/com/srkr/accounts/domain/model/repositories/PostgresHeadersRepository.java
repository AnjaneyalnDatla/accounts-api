package com.srkr.accounts.domain.model.repositories;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.Headers;

public interface PostgresHeadersRepository extends Repository<Headers, Long>{
	
	Headers save(Headers header);

}

