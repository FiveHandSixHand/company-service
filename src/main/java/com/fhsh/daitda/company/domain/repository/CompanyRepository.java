package com.fhsh.daitda.company.domain.repository;

import com.fhsh.daitda.company.domain.entity.Company;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository {
	Company save(Company company);
	Optional<Company> findById(UUID id); //
}