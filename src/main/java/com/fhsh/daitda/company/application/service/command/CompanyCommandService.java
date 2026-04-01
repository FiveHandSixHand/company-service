package com.fhsh.daitda.company.application.service.command;

import java.util.UUID;

import com.fhsh.daitda.company.application.command.CompanyCreateCommand;
import com.fhsh.daitda.company.application.result.CompanyCreateResult;
import com.fhsh.daitda.company.domain.entity.Address;
import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.repository.CompanyRepository;
import com.fhsh.daitda.company.presentation.dto.request.UpdateCompanyRequest;

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

	/**
	 * 업체 수정 로직
	 */
	public void modifyCompany(UUID companyId, UpdateCompanyRequest request) {
		// 1. 수정할 대상 조회
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new IllegalArgumentException("업체를 찾을 수 없습니다."));
		//수정 확인용
		System.out.println("수정 전 이름: " + company.getName());

		// 2. 요청 DTO의 데이터를 주소 엔티티로 변환
		Address address = Address.builder()
			.city(request.getAddress().getCity())
			.district(request.getAddress().getDistrict())
			.street(request.getAddress().getStreet())
			.build();

		// 3. 엔티티 내부의 update 메서드 호출 (상태 변경)
		company.update(request.getName(), request.getType(), address);

		// 2. 수정 후 로그 (확인용)
		System.out.println("수정 후 이름: " + company.getName());

		// Dirty Checking 덕분에 따로 repository.save()를 부르지 않아도 됩니다!
	}




}