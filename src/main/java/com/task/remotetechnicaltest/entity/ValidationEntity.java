package com.task.remotetechnicaltest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "valid_accounts", schema = "remote_technical_test")
public class ValidationEntity {

	@Id
	@Column(unique = true, name = "account_id", nullable = false)
	private String account_id;

	@Column(name = "meter_reading", nullable = false)
	private String meter_reading;

	@Column(name = "reading", nullable = false)
	private String reading;

}
