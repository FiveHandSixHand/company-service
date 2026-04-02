package com.fhsh.daitda.company.presentation.dto.request;

import com.fhsh.daitda.company.domain.enums.CompanyStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCompanyRequest {
	private String name;
	private CompanyStatus type;
	private AddressRequest address;

	@Getter
	@NoArgsConstructor
	public static class AddressRequest {
		private String city;
		private String district;
		private String street;
	}
}