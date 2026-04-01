package com.fhsh.daitda.company.infrastructure.persistence.repository;

import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// 1. Spring Data JPA가 실제 DB 작업을 수행할 내부 인터페이스
@Repository
interface JpaCompanyRepository extends JpaRepository<Company, UUID> {
}

// 2. 도메인 계층의 Repository 인터페이스를 구현
@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

	private final JpaCompanyRepository jpaCompanyRepository;

	@Override
	public Company save(Company company) {
		return jpaCompanyRepository.save(company);
	}

	@Override
	public Optional<Company> findById(UUID id) {
		return jpaCompanyRepository.findById(id);
	}
}