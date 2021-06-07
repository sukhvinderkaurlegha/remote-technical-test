package com.task.remotetechnicaltest.repository;

import com.task.remotetechnicaltest.entity.TestAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAccountsRepository extends JpaRepository<TestAccountsEntity, String> {

}
