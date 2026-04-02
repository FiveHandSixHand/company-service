package com.fhsh.daitda.company.application.command;

import java.util.UUID;

import com.fhsh.daitda.company.domain.entity.Address;
import com.fhsh.daitda.company.domain.enums.CompanyStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateCommand {
	private UUID hubId;
	private CompanyStatus type;
	private String name;
	private AddressDto address; // ✨ 중첩 DTO 사용

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AddressDto {
		private String city;
		private String district;
		private String street;

		// 엔티티 변환용 편의 메서드
		public Address toEntity() {
			return Address.builder()
				.city(city)
				.district(district)
				.street(street)
				.build();
		}
	}
}