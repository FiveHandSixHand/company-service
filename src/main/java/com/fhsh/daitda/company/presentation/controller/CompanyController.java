package com.fhsh.daitda.company.presentation.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhsh.daitda.company.application.command.CompanyCreateCommand;
import com.fhsh.daitda.company.application.result.CompanyCreateResult;
import com.fhsh.daitda.company.application.service.command.CompanyCommandService;
import com.fhsh.daitda.company.application.service.query.CompanyQueryService;
import com.fhsh.daitda.company.presentation.dto.request.CompanyCreateRequest;
import com.fhsh.daitda.company.presentation.dto.request.UpdateCompanyRequest;
import com.fhsh.daitda.company.presentation.dto.response.GetCompanyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

	private final CompanyCommandService companyCommandService;

	private final CompanyQueryService companyQueryService;

	/**
	 * 업체 등록 API (추가됨!)
	 */
	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody CompanyCreateRequest request) {

		// 1. Command 생성 (직접 new 키워드 사용)
		CompanyCreateCommand command = new CompanyCreateCommand(
			request.getHubId(),
			request.getType(),
			request.getName(),
			request.getAddress() // request에서도 AddressDto를 사용해야 함
		);

		// 2. 서비스 호출
		CompanyCreateResult result = companyCommandService.createCompany(command);

		// 3. 결과 반환
		return ResponseEntity.ok("업체 등록 성공! 생성된 ID: " + result.companyId());
	}

	/**
	 * 업체 단건 상세 조회
	 */
	@GetMapping("/{companyId}")
	public ResponseEntity<GetCompanyResponse> getCompany(@PathVariable UUID companyId) {
		// 3. 이제 companyQueryService를 사용할 수 있습니다.
		GetCompanyResponse response = companyQueryService.getCompany(companyId);
		return ResponseEntity.ok(response);
	}

	/**
	 * 업체 수정
	 */

	@PutMapping("/{companyId}")
	public ResponseEntity<Void> updateCompany(
		@PathVariable UUID companyId,
		@RequestBody UpdateCompanyRequest request) {
		// 서비스 호출
		companyCommandService.modifyCompany(companyId, request);
		// 수정 성공 시 보통 200 OK 또는 204 No Content를 반환합니다.
		return ResponseEntity.ok().build();
	}

	/**
	 * 업체 삭제 API (Soft Delete)
	 */
	@DeleteMapping("/{companyId}")
	public ResponseEntity<String> deleteCompany( // 👈 여기가 <Void>로 되어있을 거예요. <String>으로 수정!
		@PathVariable UUID companyId,
		@RequestHeader(value = "X-User-Id") UUID userId) {

		companyCommandService.deleteCompany(companyId, userId);

		// 이제 "성공!" 이라는 문자열(String)을 담아서 보낼 수 있습니다.
		return ResponseEntity.ok("업체(ID: " + companyId + ")가 성공적으로 삭제되었습니다.");
	}

}