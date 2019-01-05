package com.srkr.accounts.domain.model.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.Document;

@Component
public class DocumentMapper {

	public Document toPostgresObject(com.srkr.accounts.domain.model.Document aDocument) {

		return new Document(aDocument.id(), aDocument.documentReferencerNumber(), aDocument.documentName(),
				aDocument.documentLink(), aDocument.documentUploadedDate());
	}

	public com.srkr.accounts.domain.model.Document toDomainObject(Document pgDocument) {
		return new com.srkr.accounts.domain.model.Document(pgDocument.getId(), pgDocument.getDocumentReferencerNumber(),
				pgDocument.getDocumentName(), pgDocument.getDocumentLink(), pgDocument.getDocumentUploadedDate());
	}

	public Set<com.srkr.accounts.domain.model.Document> toListDomainObject(Set<Document> pgList) {
		if (pgList == null)
			return null;

		return pgList.stream().map((pgLt) -> toDomainObject(pgLt)).collect(Collectors.toSet());
	}

	public Set<Document> toListPostgresObject(Set<com.srkr.accounts.domain.model.Document> aList) {
		return aList.stream().map((lt) -> toPostgresObject(lt)).collect(Collectors.toSet());
	}
}
