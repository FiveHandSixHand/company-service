package com.fhsh.daitda.company.presentation.dto.response;

import java.util.UUID;

import com.fhsh.daitda.company.domain.entity.Company;
import com.fhsh.daitda.company.domain.enums.CompanyStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


	/**
	 * Create a GetCompanyResponse DTO populated from the given Company domain entity.
	 *
	 * Copies the company's identifiers, name, and type, and maps the company's address
	 * fields (city, district, street) into a nested AddressResponse.
	 *
	 * @param company the source Company entity
	 * @return a GetCompanyResponse populated with values from {@code company}
	 */
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

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class AddressResponse {
		private String city;
		private String district;
		private String street;
	}
}