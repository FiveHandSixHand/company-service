package com.fhsh.daitda.company.application.service.query;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.repository.CompanyRepository;
import com.fhsh.daitda.company.presentation.dto.response.GetCompanyResponse;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 최적화를 위해 readOnly 설정
public class CompanyQueryService {

	private final CompanyRepository companyRepository;

	/**
	 * 업체 단건 상세 조회
	 */
	public GetCompanyResponse getCompany(UUID companyId) {
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new IllegalArgumentException("해당 업체를 찾을 수 없습니다. ID: " + companyId));

		return GetCompanyResponse.from(company);
	}
	/**
	 * 업체 전체 조회
	 */

	@Transactional(readOnly = true)
	public Page<GetCompanyResponse> getCompanies(Pageable pageable) {
		return companyRepository.findAll(pageable)
			.map(GetCompanyResponse::from); // 엔티티를 DTO로 변환하는 static 메서드 가정
	}

}