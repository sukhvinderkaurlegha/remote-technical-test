package com.task.remotetechnicaltest.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "test_accounts", schema = "remote_technical_test")
public class TestAccountsEntity {

	@Id
	@Column(unique = true, name = "account_id", nullable = false)
	private String account_id;

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "email", nullable = false)
	private String email;

}
