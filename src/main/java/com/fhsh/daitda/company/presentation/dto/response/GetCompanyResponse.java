package com.fhsh.daitda.company.presentation.dto.response;

import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.enums.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCompanyResponse {
	private UUID companyId;
	private UUID hubId;
	private String name;
	private CompanyStatus type;
	private AddressResponse address;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class AddressResponse {
		private String city;
		private String district;
		private String street;
	}

	// 엔티티를 Response DTO로 변환하는 정적 팩토리 메서드
	public static GetCompanyResponse from(Company company) {
		return GetCompanyResponse.builder()
			.companyId(company.getCompanyId())
			.hubId(company.getHubId())
			.name(company.getName())
			.type(company.getType())
			.address(AddressResponse.builder()
				.city(company.getAddress().getCity())
				.district(company.getAddress().getDistrict())
				.street(company.getAddress().getStreet())
				.build())
			.build();
	}
}