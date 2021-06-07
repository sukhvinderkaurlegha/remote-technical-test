package com.task.remotetechnicaltest.controller;

import com.task.remotetechnicaltest.entity.TestAccountsEntity;
import com.task.remotetechnicaltest.entity.ValidationEntity;
import com.task.remotetechnicaltest.service.ValidateMeterReadingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/")
public class ValidateMeterReadingController {

	@Autowired
	private ValidateMeterReadingService validateMeterReadingService;

	@GetMapping("/getAllAccounts")
	private ResponseEntity<List<TestAccountsEntity>> getAllAccounts() {
		log.info("getting all test accounts");
		List<TestAccountsEntity> listOfAccounts = validateMeterReadingService.getAllTestAccounts();

		return ResponseEntity.ok().body(listOfAccounts);
	}

	@PostMapping("/meter-reading-uploads")
	private ResponseEntity<List<ValidationEntity>> updateValidAccountsTable() throws IOException, URISyntaxException {
		log.info("writing all valid accounts to valid_accounts table");
		validateMeterReadingService.validateMeterReading();

		log.info("VALID ACCOUNTS [{}]", validateMeterReadingService.getAllValidAccountsWithoutDuplicates());
		log.info("INVALID ACCOUNTS [{}]", validateMeterReadingService.getAllTestAccounts().stream().count() - validateMeterReadingService.getAllValidAccountsWithoutDuplicates());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/getAllAccountIds")
	private ResponseEntity<List<String>> getAllAccountIds() {
		log.info("getting all test account Ids");
		List<String> listOfAccountIds = validateMeterReadingService.getAllTestAccountsIdsFromDatabase();

		return ResponseEntity.ok().body(listOfAccountIds);
	}

	@GetMapping("/getValidAccountsCountWithoutDuplicates")
	private ResponseEntity<Integer> getAllValidAccountsWithoutDuplicates() {
		log.info("getting all valid accounts without duplicates");
		Integer validAccountsCount = validateMeterReadingService.getAllValidAccountsWithoutDuplicates();

		return ResponseEntity.ok().body(validAccountsCount);
	}
}
