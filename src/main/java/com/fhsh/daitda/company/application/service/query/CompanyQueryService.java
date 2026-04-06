package com.fhsh.daitda.company.application.service.query;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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


	/**
	 * FeignClient용: 업체 ID 리스트로 업체 이름 맵 조회
	 */
	public Map<UUID, String> getCompanyNamesMap(List<UUID> companyIds) {
		// 1. 레포지토리에서 ID 리스트에 해당하는 업체들을 한꺼번에 가져옵니다.
		List<Company> companies = companyRepository.findAllById(companyIds);

		// 2. 가져온 리스트를 Map<ID, Name> 형태로 변환하여 반환합니다.
		return companies.stream()
			.collect(Collectors.toMap(
				Company::getCompanyId, // Key
				Company::getName       // Value
			));
	}

}