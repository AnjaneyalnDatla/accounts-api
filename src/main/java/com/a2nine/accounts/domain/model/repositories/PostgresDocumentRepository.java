package com.a2nine.accounts.domain.model.repositories;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.Document;

public interface PostgresDocumentRepository extends Repository<Document, Long> {
	
	void save(Document document);

	Set<Document> findByDocumentReferencerNumber(Long documentReferenceNumber);

}
