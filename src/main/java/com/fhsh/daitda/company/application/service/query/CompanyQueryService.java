package com.fhsh.daitda.company.application.service.query;

import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.repository.CompanyRepository;
import com.fhsh.daitda.company.presentation.dto.response.GetCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
}