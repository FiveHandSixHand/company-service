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

		// 1. 주소 정보를 엔티티로 변환 (Command 내부에 만든 toEntity 사용)
		Address address = command.getAddress().toEntity();

		// 2. 도메인 엔티티 생성
		Company company = Company.create(
			command.getHubId(),
			command.getType(),
			command.getName(),
			address
		);

		// 3. 저장
		Company savedCompany = companyRepository.save(company);

		// 4. 결과 반환
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

	/**
	 * 업체 삭제  로직
	 */

	@Transactional
	public void deleteCompany(UUID companyId, UUID userId) {
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new IllegalArgumentException("삭제할 업체를 찾을 수 없습니다."));

		// Soft Delete 실행
		company.delete(userId);

		// 역시 변경 감지(Dirty Checking)로 인해 자동으로 update 쿼리가 날아갑니다.
	}



}