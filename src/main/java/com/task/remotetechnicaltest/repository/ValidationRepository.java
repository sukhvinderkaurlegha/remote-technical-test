package com.task.remotetechnicaltest.repository;

import com.task.remotetechnicaltest.entity.ValidationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<ValidationEntity, String> {
}
