package com.fhsh.daitda.company.application.service.command;

import com.fhsh.daitda.company.application.command.CompanyCreateCommand;
import com.fhsh.daitda.company.application.result.CompanyCreateResult;
import com.fhsh.daitda.company.domain.entity.Address;
import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyCommandService {

	private final CompanyRepository companyRepository;

	public CompanyCreateResult createCompany(CompanyCreateCommand command) {

		// 1. command.getAddress()로 Dto를 가져온 뒤 toEntity() 호출
		// (기존의 command.address() -> command.getAddress()로 수정)
		Address address = command.getAddress().toEntity();

		// 2. 도메인 엔티티 생성
		// (기존의 hubId(), type(), name() -> getHubId(), getType(), getName()으로 수정)
		Company company = Company.create(
			command.getHubId(),
			command.getType(),
			command.getName(),
			address
		);

		// 3. 저장
		Company savedCompany = companyRepository.save(company);

		// 4. 결과 반환 (엔티티의 필드명 companyId에 맞춰 호출)
		return CompanyCreateResult.from(
			savedCompany.getCompanyId(),
			savedCompany.getName()
		);
	}
}