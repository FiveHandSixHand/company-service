package com.fhsh.daitda.company.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

// 1. Spring Data JPA가 실제 DB 작업을 수행할 내부 인터페이스
@Repository
interface JpaCompanyRepository extends JpaRepository<Company, UUID> {
}

// 2. 도메인 계층의 Repository 인터페이스를 구현
@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

	private final JpaCompanyRepository jpaCompanyRepository;
	/**
	 * Persist the given company and return the saved entity.
	 *
	 * @param company the company to persist
	 * @return the persisted Company entity
	 */
	@Override
	public Company save(Company company) {
		return jpaCompanyRepository.save(company);
	}
	/**
	 * Retrieves a Company by its UUID.
	 *
	 * @param id the UUID of the company to retrieve
	 * @return an Optional containing the Company if found, or an empty Optional if not
	 */
	@Override
	public Optional<Company> findById(UUID id) {
		return jpaCompanyRepository.findById(id);
	}
	/**
	 * Retrieves a page of Company entities according to the provided pagination and sorting.
	 *
	 * @param pageable controls the page number, page size, and sort order
	 * @return a Page containing the Company entities for the requested page
	 */
	@Override
	public Page<Company> findAll(Pageable pageable) {
		return jpaCompanyRepository.findAll(pageable);
	}
	/**
	 * Retrieves Company entities matching any of the given IDs.
	 *
	 * @param ids list of UUIDs identifying the companies to retrieve
	 * @return a list of Company entities whose IDs are contained in {@code ids}; order is not guaranteed
	 */
	@Override
	public List<Company> findAllById(List<UUID> ids) {
		return jpaCompanyRepository.findAllById(ids);
	}

}