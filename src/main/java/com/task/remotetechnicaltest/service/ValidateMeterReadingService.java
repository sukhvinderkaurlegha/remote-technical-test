package com.task.remotetechnicaltest.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.task.remotetechnicaltest.entity.TestAccountsEntity;
import com.task.remotetechnicaltest.entity.ValidationEntity;
import com.task.remotetechnicaltest.repository.TestAccountsRepository;
import com.task.remotetechnicaltest.repository.ValidationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ValidateMeterReadingService {

	private static String FILE_NAME = "validation/meter_reading.csv";

	private TestAccountsRepository testAccountsRepository;
	private ValidationRepository validationRepository;

	@Autowired
	public ValidateMeterReadingService(TestAccountsRepository testAccountsRepository, ValidationRepository validationRepository) {
		this.testAccountsRepository = testAccountsRepository;
		this.validationRepository = validationRepository;
	}

	public List<TestAccountsEntity> getAllTestAccounts() {
		List<TestAccountsEntity> testAccounts;
		testAccounts = testAccountsRepository.findAll();

		return testAccounts;
	}

	public void validateMeterReading() throws IOException, URISyntaxException {
		List<ValidationEntity> validationEntityList = new ArrayList<>();

		String[] line;
		ValidationEntity validationEntity = new ValidationEntity();
		log.info("validating meter reading against test_accounts table");
		try (CSVReader csvReader = openFile(FILE_NAME)) {
			while ((line = csvReader.readNext()) != null) {

				if (line[2].length()==0) {
					line[2] = "0";
				}
				if (line[1].isEmpty()) {
					line[1] = "no account id";
				}

					//only readings with length 4 (NNNN) should be processed
					int lengthOfReading = line[2].length();
					if (lengthOfReading==4) {
						String accountIdInMeterReadingCsv = line[1];
						if (getAllTestAccountsIdsFromDatabase().contains(accountIdInMeterReadingCsv)) {
							validationEntity.setAccount_id(line[1]);
							validationEntity.setMeter_reading(line[0]);
							validationEntity.setReading(line[2]);
							//account id is a unique column within the db so duplicates will not be saved to the db
							validationRepository.save(validationEntity);
							validationEntityList.add(validationEntity);
						}
					}
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}

		log.info("valid accounts including duplicates [{}]", validationEntityList.stream().count());
		log.info("all accounts in test_accounts table [{}]", getAllTestAccountsIdsFromDatabase().stream().count());

	}

	private CSVReader openFile(String fileName) throws URISyntaxException, IOException {
		Reader reader = Files
			.newBufferedReader(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));
		CSVReader csvReader = new CSVReader(reader);

		return csvReader;
	}

	public List<String> getAllTestAccountsIdsFromDatabase() {
		List<TestAccountsEntity> allAccounts = testAccountsRepository.findAll();

		List<String> ids = new ArrayList<>();
		for (int i = 0; i < allAccounts.size(); i++) {

			allAccounts.get(i).getAccount_id();
			String id = allAccounts.get(i).getAccount_id();
			ids.add(id);
		}
		return ids;
	}

	public Integer getAllValidAccountsWithoutDuplicates() {
		List<ValidationEntity> allAccounts = validationRepository.findAll();
		return allAccounts.size();
	}
}
