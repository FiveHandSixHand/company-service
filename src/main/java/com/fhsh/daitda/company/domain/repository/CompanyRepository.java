package com.fhsh.daitda.company.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fhsh.daitda.company.domain.entity.Company;

public interface CompanyRepository {
	Company save(Company company);
	Page<Company> findAll(Pageable pageable);
	Optional<Company> findById(UUID id); //
}