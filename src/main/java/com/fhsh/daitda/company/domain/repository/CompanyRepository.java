package com.fhsh.daitda.company.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fhsh.daitda.company.domain.entity.Company;

public interface CompanyRepository {
	/**
 * Persists the given Company and returns the stored entity.
 *
 * @param company the Company to persist
 * @return the persisted Company, potentially updated with generated identifiers or persisted state
 */
Company save(Company company);
	/**
 * Retrieves a page of Company records according to the provided pagination and sorting.
 *
 * @param pageable pagination and sorting information for the query
 * @return a Page containing the matching Company entities for the requested page
 */
Page<Company> findAll(Pageable pageable);
	/**
 * Finds a company by its UUID.
 *
 * @param id the UUID of the company to retrieve
 * @return an Optional containing the matching Company if found, otherwise an empty Optional
 */
Optional<Company> findById(UUID id); /**
 * Retrieves all Company entities matching the provided list of UUIDs.
 *
 * @param ids the UUIDs of the companies to retrieve
 * @return a list of Company objects found for the given IDs; companies not found are omitted
 */
	List<Company> findAllById(List<UUID> ids);
}