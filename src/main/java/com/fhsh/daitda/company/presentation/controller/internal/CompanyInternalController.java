package com.fhsh.daitda.company.presentation.controller.internal;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty; // 리스트용
import jakarta.validation.constraints.NotNull; // 단일 객체용
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhsh.daitda.company.application.service.query.CompanyQueryService;
import com.fhsh.daitda.company.presentation.dto.response.GetCompanyResponse;
import com.fhsh.daitda.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("internal/v1/companies")
@RequiredArgsConstructor
@Validated
public class CompanyInternalController {

	private final CompanyQueryService companyQueryService;

	/**
	 * 단건 조회: 업체 ID 검증 포함
	 */
	@GetMapping("/{companyId}")
	public CommonResponse<GetCompanyResponse> getCompanyInternal(
		@PathVariable @NotNull(message = "업체 ID는 필수입니다.") UUID companyId) {

		GetCompanyResponse response = companyQueryService.getCompany(companyId);
		return CommonResponse.success(response);
	}

	/**
	 * 리스트 조회: ID 리스트가 비어있는지 검증
	 */
	@PostMapping("/names-by-ids")
	public CommonResponse<Map<UUID, String>> getCompanyNamesInternal(
		@RequestBody @NotEmpty(message = "조회할 ID 리스트가 비어있습니다.") List<UUID> companyIds) {

		Map<UUID, String> response = companyQueryService.getCompanyNamesMap(companyIds);
		return CommonResponse.success(response);
	}
}