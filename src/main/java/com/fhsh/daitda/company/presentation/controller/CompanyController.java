package com.fhsh.daitda.company.presentation.controller;

import com.fhsh.daitda.company.application.command.CompanyCreateCommand;
import com.fhsh.daitda.company.application.result.CompanyCreateResult;
import com.fhsh.daitda.company.application.service.command.CompanyCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies") // ✨ 규칙 적용: v1 + 복수형(companies)
@RequiredArgsConstructor
public class CompanyController {

	private final CompanyCommandService companyCommandService;

	@PostMapping
	public ResponseEntity<CompanyCreateResult> createCompany(@RequestBody CompanyCreateCommand command) {
		// 서비스 호출
		CompanyCreateResult result = companyCommandService.createCompany(command);
		return ResponseEntity.ok(result);
	}
}