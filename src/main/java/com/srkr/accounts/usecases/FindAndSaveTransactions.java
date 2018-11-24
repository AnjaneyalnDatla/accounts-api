package com.srkr.accounts.usecases;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.HeadersMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.postgres.HeaderTypes;
import com.srkr.accounts.domain.model.postgres.Headers;
import com.srkr.accounts.domain.model.repositories.PostgresHeaderTypesRepository;
import com.srkr.accounts.domain.model.repositories.PostgresHeadersRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Service
public class FindAndSaveTransactions {
	
	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	
	@Autowired
	TransactionsMapper transactionsMapper;
	
	@Autowired
	HeadersMapper headersMapper;
	
	@Autowired
	PostgresHeadersRepository postgresHeadersRepository;
	
	@Autowired
	PostgresHeaderTypesRepository postgresHeaderTypesRepository;

	public List<Transactions> findTransactionsByUsername(String user_name) {
		
		return null;
	}
	
	public List<Transactions> findAllTransactions() {
		return transactionsMapper.toListOfDomainObjects(postgresTransactionsRepository.findAll());
	}
	
	public void saveTransactions(String Obj) {
		ObjectMapper objectMapper = new ObjectMapper();		
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(Obj);
			//jsonMap = objectMapper.readValue(Obj, new TypeReference<Map<String,Object>>(){});
			List<com.srkr.accounts.domain.model.postgres.Transactions> transactionList = transactionsMapper.toPostgresObjectListFromMap(jsonNode);
			
			//create a header record before saving a transaction and insert header_id for every transaction
			
			Headers header = new Headers();
			header.setHeaderdate(new Date());
			header.setHeadernumber(100); //need sequence for generating the header number (header number = invoice number)
			//header.setId(1l);
			header.setHeaderTypes(postgresHeaderTypesRepository.findById(1l));
			
			header = postgresHeadersRepository.save(header);
			
			 for (int i=0;i<transactionList.size();i++) 
		        {
				 com.srkr.accounts.domain.model.postgres.Transactions transaction = transactionList.get(i);
				 transaction.setHeaders(header);
				 postgresTransactionsRepository.save(transactionList.get(i));
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
