package com.fhsh.daitda.company.presentation.controller.internal;

import com.fhsh.daitda.company.application.service.query.CompanyQueryService;
import com.fhsh.daitda.company.presentation.dto.response.GetCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyInternalService {

	private final CompanyQueryService companyQueryService;

	/**
	 * 외부(Product-Service 등) 호출용 단건 조회 로직
	 */
	public GetCompanyResponse getCompanyById(UUID companyId) {
		return companyQueryService.getCompany(companyId);
	}

	/**
	 * 외부 호출용 대량(Bulk) ID-이름 매핑 조회 로직
	 */
	public Map<UUID, String> getCompanyNamesMap(List<UUID> companyIds) {
		return companyQueryService.getCompanyNamesMap(companyIds);
	}
}