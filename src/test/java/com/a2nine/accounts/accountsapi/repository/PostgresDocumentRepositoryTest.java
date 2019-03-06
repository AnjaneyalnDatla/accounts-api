package com.a2nine.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.a2nine.accounts.domain.model.postgres.Document;
import com.a2nine.accounts.domain.model.repositories.PostgresDocumentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresDocumentRepositoryTest {

	@Autowired
	PostgresDocumentRepository documentRepository;
	
	private List<Document> documentsList;

	@Before
	public void setUp() {
		this.documentsList = new ArrayList<Document>();
		
		Document pgDocument = new Document();
		pgDocument.setDocumentLink("testing link");;
		pgDocument.setDocumentName("Billing");
		pgDocument.setDocumentReferencerNumber(new Long(143235));
		pgDocument.setDocumentUploadedDate(new Date());
		
		documentsList.add(pgDocument);
	}
	
	@Test
	public void findByDocumentReferencerNumber() {
		Set<Document> documents = documentRepository.findByDocumentReferencerNumber(1l);
		assertNotNull(documents);
	}

	@Test
	public void save() {
		documentsList.forEach((document) -> {
			this.documentRepository.save(document);
		});
		assertTrue(documentsList.size() > 0);
	}
}
