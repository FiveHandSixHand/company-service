package com.fhsh.daitda.company.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.fhsh.daitda.company.domain.entity.Company;

public interface CompanyRepository {
	Company save(Company company);

	Optional<Company> findById(UUID id); //
}