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
	 * ✨  회사 생성 구현
	 */
	@Override
	public Company save(Company company) {
		return jpaCompanyRepository.save(company);
	}
	/**
	 * ✨ 단건  조회 구현
	 */
	@Override
	public Optional<Company> findById(UUID id) {
		return jpaCompanyRepository.findById(id);
	}
	/**
	 * ✨  전체 조회(페이징) 구현
	 */
	@Override
	public Page<Company> findAll(Pageable pageable) {
		return jpaCompanyRepository.findAll(pageable);
	}
	/**
	 * ✨  feign 리스트 구현 !
	 */
	@Override
	public List<Company> findAllById(List<UUID> ids) {
		return jpaCompanyRepository.findAllById(ids);
	}

}