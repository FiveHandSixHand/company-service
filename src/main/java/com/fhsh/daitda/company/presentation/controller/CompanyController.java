package com.fhsh.daitda.company.presentation.controller;

import com.fhsh.daitda.company.application.service.command.CompanyCommandService;
import com.fhsh.daitda.company.application.service.query.CompanyQueryService; // 1. Import 추가
import com.fhsh.daitda.company.presentation.dto.response.GetCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

	private final CompanyCommandService companyCommandService;

	private final CompanyQueryService companyQueryService;

	/**
	 * 업체 단건 상세 조회
	 */
	@GetMapping("/{companyId}")
	public ResponseEntity<GetCompanyResponse> getCompany(@PathVariable UUID companyId) {
		// 3. 이제 companyQueryService를 사용할 수 있습니다.
		GetCompanyResponse response = companyQueryService.getCompany(companyId);
		return ResponseEntity.ok(response);
	}
}